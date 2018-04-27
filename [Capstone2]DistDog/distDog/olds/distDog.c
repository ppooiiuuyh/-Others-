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

#define SERVER_PORT 1234
#define CLIENT_PORT 1234
#define SERVER_IP "182.168.0.1"
#define BUFFER_LEN 512
#define BUFFER_LEN_PING 50

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
 * ./distDog 1 182.168.0.255 182.168.0.255 1234
 * ./distDog 0 182.168.0.255 182.168.0.255 1234
 * ./distDog 1 222.222.0.255 222.222.0.255 1234
 * ./distDog 0 222.222.0.255 222.222.0.255 1234 AB:CD:EF:GH
 * scp -r distDog 222.222.0.1:/home/pi/Desktop
 * ssh 222.222.0.1

arg[0] : program name,  
arg[1] : starting mode , 
arg[2] : my IP , 
arg[3] : dist IP(broadcast ip) , 
arg[4] : my recieve port  

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

void initDistDog(int argc, char **argv){
	
	if(argc<3){ 
		printf("no arg error\n");
		return -1;
	}
	else{
		workFlag = atoi(argv[1]);
		printf("main flag is %d\n",workFlag);
		if(argc >=6){
			strcpy(mymac,argv[5]);
		}		

		initMac(mymac);
		
		
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
        if (setup_sockaddr_in(&client_addr, atoi(argv[4]), argv[2]) == NULL) {
                return (-1);
        }
        /// Setup server (destination) addr struct
	printf("dst ip is %s\n",argv[3]);
        if (setup_sockaddr_in(&server_addr, SERVER_PORT, argv[3]) == NULL) {
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
				if(pingCount % 15 == 0){  // send State
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
				
				}
				pingCount ++;
				timeTemp = time(NULL);



				/*
				******recv part
				*/
				char recv_buff[BUFFER_LEN] = { 0 };
				strcpy(recv_buff,mymac);
				int recv_len = 0;	

			
				//**********************************	
				recv_data_raw(sockfd, &recv_buff, &recv_len, BUFFER_LEN, &client_addr, slen);

				//**********************************need to flush after recv


				//compare mac
				//if same with recv_mac, ignore this
				if(strcmp(recv_buff,mymac) == 0){
				}
				else if(strcmp(recv_buff,mymac) >0 ){ //if higher than me
					printf("rcv mac : %s\n" ,recv_buff);
					printf("my mac : %s\n" ,mymac);
					printf("recv mac is higher than me. change to submode \n");
					
					
					//processRecieveDatas(recv_buff ,0);//update statefile via recived data. It may not be necessary


					workFlag = 10;	
				}
				else{//if lower than me. ignore that
					printf("recv mac is lower than me. ignore this \n");
				}
			
			}
		}
		//0 for sub (ready state 10)
		else if(workFlag == 0){
			
			
			printf("recv message: ");
			char recv_buff[BUFFER_LEN] = { 0 };
			int recv_len = 0;		

			/**********************************
			//recv_data_raw(sockfd, &recv_buff, &recv_len, BUFFER_LEN, &client_addr, slen); 
			**********************************/

			if(recv_len>0) sub_timer =time(NULL);


			/**********************************
			if( recvdate is ping) processRecieveDatas(recv_buff ,1);//update statefile via recived data
			else if (recvdata is statefile) processRecieveDatas(recv_buff ,0);//update statefile via recived data
			**********************************/
			processRecieveDatas(recv_buff ,0);//update statefile via recived data //dummy



			printf(" %s\n", recv_buff);


			sleep(1);
			printf("no recv data. changing to main mode after %dsec\n",5- (time(NULL) - sub_timer)); 
			

			if(time(NULL) - sub_timer >= 5){
				workFlag = 11;
				continue;
			}
		
		}


		else{ //preparing state before go to next state
			if(workFlag ==11){
				pingCount =0;
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
	strcpy(sendBuffer, mymac); //temp
	//loadStateFile(sendBuffer);
	
}


void makeSendBuffer_ping(char sendBuffer[]){	
	strcpy(sendBuffer, mymac);//temp 
}

void loadStateFile(char str[]){
	char loadedFileTemp[100]; //LEN is dummy
	//load file

	strcpy(str,loadedFileTemp);
}
void saveStateFile(char str[]){
}

void processRecieveDatas(char recv_buff[], int type){
	// 0 for ping, 1 for state	
	if(type == 0){
		 
	}
	else if (type ==1){
		//processing data
		//...
		
		saveStateFile(recv_buff);
	}
}

//******************************************************************





//#include <distDog.c>
//it is dummy main. exampling of usage.
int main(int argc, char **argv)
{
	//*****************************distDog init start

	/*
	*********flag init
	*/
	initDistDog(argc, argv); //<-- step1


	
	/*
	*********socket initialize********
	*/	
	initSocket(argc, argv); //<-- step2
        printf("complete socket init\n");


	/*
	*********make thread********
	*/
	distDog_thr_id = distDog_startThread(NULL); //<-- step3
	printf("complete thread start!. thread id : %d\n", distDog_thr_id);


	//*****************************distDog init complete







	//******************************user programming area start 

	char dummyState[100]= "dflkjaw efjawejoaweijfowjlawe";  //<- user defining state file as char array
	while(1){
		sleep(5);
		//saveStateFile(dummyState); //responsibility of saving is on the distDog caller.
		//printf(	"stateFile : %s\n",loadedState);	
	}



	//******************************user programming area end

	//thread_exit ( <--call before program exit)
	pthread_join(distDog_p_thread,NULL);

	
        return 0;
}

