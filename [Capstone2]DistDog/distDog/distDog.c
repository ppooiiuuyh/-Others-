//#pragma once
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <arpa/inet.h>
#include <sys/socket.h>
#include <pthread.h>
#include <unistd.h>
#include <time.h>
#include <sys/ioctl.h>
#include <sys/types.h>
#include <netinet/ether.h>
#include <netinet/in.h>
#include <net/if.h>
#include <netdb.h>
#include <fcntl.h>
#include <sys/stat.h>



#define SERVER_PORT 1234
#define CLIENT_PORT 1234
#define SERVER_IP "182.168.0.1"
#define BUFFER_LEN 512
#define BUFFER_LEN_PING 18

/* to do
makeSendBuffers
recv no wait
initMac
hadoop performance test
processRecvData
saveStateData
*/

/*
 * gcc -I. -o distDog distDog.c socket.c -pthread
 * ./distDog 1 222.222.0.255 
 * ./distDog 0 222.222.0.255 
 * ./distDog 1 182.168.0.255 
 * ./distDog 0 182.168.0.255 
 * scp -r distDog pi@222.222.0.1:/home/pi/Desktop  //passwd raspberry
 * ssh 222.222.0.1



arg[0] : program name,  
arg[1] : starting mode , 
arg[2] : network broadcast IP



 * sudo iptables -A INPUT -m pkttype --pkt-type broadcast -j ACCEPT
 */


int sockfd = 0;
struct sockaddr_in client_addr;
struct sockaddr_in dst_addr;
struct sockaddr_in server_addr;
const socklen_t slen = sizeof(struct sockaddr_in);

int workFlag = 0; //0 for sub, 1 for main
int distDog_thr_id; 
pthread_t distDog_p_thread;
char mymac[20] = "IT:IS:JU:ST:DU:MMY";
char lastRecvedMac[20] = "IT:IS:JU:ST:DU:MMY";

void initSocket(int argc, char **argv);
void initDistDog(int argc, char **argv);
void initMac(char mac[]);

int distDog_startThread(void* data);
void *distDog_t_function(void* data);

void makeSendBuffer_state(char str[]);
void makeSendBuffer_ping(char str[]);
void processRecieveDatas(char str[], int type);
void loadStateFile(char str[]);
void saveStateFile(char str[]);
void makeStateFile(char stateData[], char mac[], char content[]);
int getWorkState();


void initDistDog(int argc, char **argv){
	
	if(argc<3){ 
		printf("no arg error\n");
		return -1;
	}
	else{
		workFlag = atoi(argv[1]);
		printf("main flag is %d\n",workFlag);
		initMac(mymac);
		
		/*
		if(argc >=6){
			strcpy(mymac,argv[5]);
		}
		*/
	}

}

void initMac(char mac[]){
	int sockfd, cnt, req_cnt= 20;
	char mac_adr[128] = {0X00,};
	struct sockaddr_in *sock;
	struct ifconf ifcnf_s;
	struct ifreq *ifr_s;
	sockfd = socket(PF_INET, SOCK_DGRAM, 0);
	
	if(sockfd <0){
		printf("sockfd < 0");
	}

	memset((void *)&ifcnf_s, 0x0, sizeof(ifcnf_s) );
	ifcnf_s.ifc_len = sizeof(struct ifreq) * req_cnt;
	ifcnf_s.ifc_buf = malloc(ifcnf_s.ifc_len);
	
	if(ioctl(sockfd, SIOCGIFCONF, (char *)&ifcnf_s)<0){
		printf("SIOCGIFCONF 115");

	}

	if(ifcnf_s.ifc_len > (sizeof(struct ifreq) * req_cnt)){

		req_cnt = ifcnf_s.ifc_len;
		ifcnf_s.ifc_buf = realloc(ifcnf_s.ifc_buf, req_cnt);
	}

	ifr_s = ifcnf_s.ifc_req;
	
	for(cnt = 0; cnt<ifcnf_s.ifc_len ; cnt += sizeof(struct ifreq), ifr_s++){

		if(ioctl(sockfd, SIOCGIFFLAGS, ifr_s) <0){
			printf("130");
		}	
		if(ifr_s->ifr_flags & IFF_LOOPBACK)
			continue;
		sock = (struct sockaddr_in *)&ifr_s->ifr_addr;
		printf("\n <IP address >  - %s\n", inet_ntoa(sock->sin_addr));
		
        if(ioctl(sockfd, SIOCGIFHWADDR, ifr_s) <0){
                printf("138"); 
        }

	convrt_mac(ether_ntoa((struct ether_addr *)(ifr_s->ifr_hwaddr.sa_data)), mac_adr, sizeof(mac_adr) -1);
	printf("<MAC addree> - %s\n", mac_adr);
	}
	strcpy(mac, mac_adr);
}

void convrt_mac(const char *data, char *cvrt_str, int sz){

	char buf[128] = {0x00,};
	char t_buf[8];
	char *stp = strtok((char *)data, ":");
	int temp = 0;
	do{
		memset(t_buf, 0x0, sizeof(t_buf));
		sscanf(stp,"%x", &temp);
		snprintf(t_buf, sizeof(t_buf)-1, "%02X", temp);
		strncat(buf, t_buf, sizeof(buf)-1);
		strncat(buf,":", sizeof(buf)-1);
		
	}while((stp = strtok(NULL,":")) != NULL);
	
	buf[strlen(buf)-1] = '\0';
	strncpy(cvrt_str, buf, sz);

}



void initSocket(int argc, char **argv){


        /// Setup socket
        if (setup_socket(&sockfd) < 0) {
                return (-1);
        }


        /// Setup client (my ip) addr struct
	printf("my ip is %s\n",argv[2]);
        if (setup_sockaddr_in(&client_addr, SERVER_PORT, argv[2]) == NULL) {
                return (-1);
        }
        /// Setup server (destination) addr struct
	printf("dst ip is %s\n",argv[2]);
        if (setup_sockaddr_in(&server_addr, SERVER_PORT, argv[2]) == NULL) {
                return (-1);
        }


	printf("start bind\n");
	// Bind CLIENT_PORT to address structure on sockfd
        printf("client port is %d\n",client_addr.sin_port);
	if (bind_socket(sockfd, &client_addr) < 0) {
                return (-1);
        }

}



int distDog_startThread(void *data){
	return pthread_create(&distDog_p_thread, NULL, distDog_t_function,NULL);
}





void *distDog_t_function(void *data){

	int pingCount = 0;
	time_t sub_timer = time(NULL);
	time_t timeTemp = time(NULL);

        while(workFlag != -1 ){
		//1 for main (ready state 11)
		if(workFlag == 1){
	        	
			if(time(NULL) - timeTemp >= 2){
				/*
				******send part
				*/
				if(pingCount % 7 == 6){  // send State
					pingCount = 0;
		
					char sendBuffer[BUFFER_LEN];
					makeSendBuffer_state(sendBuffer);
					printf("send state message: %s \n", sendBuffer);
        	        		send_data_raw(sockfd, &sendBuffer, BUFFER_LEN, &server_addr, slen);
					
				}

				else{ //send ping	
					char sendBuffer[BUFFER_LEN_PING];
					makeSendBuffer_ping(sendBuffer);
					printf("send ping message: %s \n", sendBuffer);
        	        		send_data_raw(sockfd, &sendBuffer, BUFFER_LEN_PING, &server_addr, slen);

					//saveStateFile(sendBuffer);
				
				}
				pingCount ++;



				/*
				******recv part
				*/
				char recv_buff[BUFFER_LEN] = { 0 };
				strcpy(recv_buff,mymac);
				int recv_len = 0;	

			
				//****recv data	
				recv_data_raw(sockfd, &recv_buff, &recv_len, BUFFER_LEN, &client_addr, slen);
				if(recv_len >= 0 ) {
						
					//****flush udp queues after recv
					char flush_recv_buff[BUFFER_LEN] = { 0 };
					int flush_recv_len = 0;					
					do{
						recv_data_raw(sockfd, &flush_recv_buff, &flush_recv_len, BUFFER_LEN, &client_addr, slen);
					}while(flush_recv_len > -1); 
	
	
					//compare mac
					//if same with recv_mac, ignore this
					if(strncmp(recv_buff,mymac,17) == 0){
					}
					else if(strncmp(recv_buff,mymac,17) >0 ){ //if higher than me
						printf("rcv mac : %s\n" ,recv_buff);
						printf("my mac : %s\n" ,mymac);
						printf("recv mac is higher than me. change to submode \n");
						
						
						processRecieveDatas(recv_buff ,0);//update statefile via recived data. It may not be necessary
	
	
						workFlag = 10;	
					}
					else{//if lower than me. ignore that
						printf("recv mac is lower than me. ignore this \n");
				}

				}

				
			

				//ready for nextStep
				timeTemp = time(NULL);
			}
		}
		//0 for sub (ready state 10)
		else if(workFlag == 0){
	
			
			char recv_buff[BUFFER_LEN] = { 0 };
			int recv_len = 0;		

			recv_data_raw(sockfd, &recv_buff, &recv_len, BUFFER_LEN, &client_addr, slen); 
		

			if(recv_len>0){ //if recved something
				sub_timer =time(NULL);
				printf("recv message: %s, timer reset\n",recv_buff);

				if( recv_len>BUFFER_LEN_PING){//if recv_data is statefile
					processRecieveDatas(recv_buff ,1);//1 for state
				
				}
				else {//if recv_data is ping
					processRecieveDatas(recv_buff ,0);//do nothing //0 for ping
				}
				
			}
			
			else{ //if recved nothing
				printf("no recv data. changing to main mode after %dsec\n",3 - (time(NULL) - sub_timer)); 
			
			}

			if(time(NULL) - sub_timer >= 3){
				workFlag = 11;
				continue;
			}
		
			sleep(1);
		}


		else{ //preparing state before go to next state
			if(workFlag ==11){
				pingCount =0;
				char fileTemp[BUFFER_LEN] = {0,};
				loadStateFile(fileTemp);
				int i= 0;
				for(i=0 ; i<17; i++){
					fileTemp[i] = mymac[i];
				}	
				saveStateFile(fileTemp);

				workFlag = 1;
			}
			else if(workFlag == 10){
				sub_timer = time(NULL);
				workFlag = 0;
			}
			else if(workFlag == -11){
				workFlag = -1; // for end
			}
		}
        }

}





//******************************************************************
void makeSendBuffer_state(char sendBuffer[]){
	loadStateFile(sendBuffer);
}


void makeSendBuffer_ping(char sendBuffer[]){	
	strcpy(sendBuffer, mymac);//temp 
}


void makeStateFile(char stateData[], char mac[], char content[]){
	strcpy(stateData,mac);
	strcat(stateData,content);
}

void loadStateFile(char str[]){
	char loadedFileTemp[BUFFER_LEN]; //LEN is dummy
	char fileName[10] = "state.txt";
	int fd;
	fd = open(fileName,O_RDONLY);
	if(fd == -1){ //make file
	}
	//load file
	//Read the value from the "state.txt" file in the programe execution path
	memset(loadedFileTemp, 0x00, BUFFER_LEN);
	read(fd, loadedFileTemp, BUFFER_LEN -1);
	close(fd);


	strcpy(str,loadedFileTemp);
}
void saveStateFile(char str[]){
	int fd;
	char fileName[10]="state.txt";
	fd = open(fileName,O_RDWR|O_CREAT|0);
	if(fd==-1){
	//file open fail
	}
	write(fd,str,strlen(str));
	close(fd);
}

void processRecieveDatas(char recv_buff[], int type){
	// 0 for ping, 1 for state	
	if(type == 0){
		 //do nothing
	}
	else if (type ==1){
		//processing data
		//...
		int i =0;
		for(i=0 ; i<17; i++){
			recv_buff[i] = mymac[i];
		}
		saveStateFile(recv_buff);
	}
}


int getWorkState(){
	if(workFlag == 1)
		return 1;
	else
		return 0;
}

//******************************************************************


