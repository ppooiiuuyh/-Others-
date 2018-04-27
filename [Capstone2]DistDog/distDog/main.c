#include "distDog.c"

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

	time_t timer = time(NULL);
	int timeOut = 3, flag = 0;
	int state_num = 0;
	remove("state.txt");
	while(1){
		if(getWorkState() == 1) {// when this system is running as main
			
			//adaptation
			if(flag == 0) {
				char stateData[BUFFER_LEN]= {0,};
				loadStateFile(stateData);
				char state_num_str[5];
				strncpy(state_num_str, stateData + 17, 4);
				state_num_str[4] = NULL;
				state_num = atoi(state_num_str);
				
				flag = 1;
			}

			if((time(NULL) - timer)>timeOut){
				timer = time(NULL);
				char stateData[BUFFER_LEN]= {0,};
				
				char state_num_str[5];
				sprintf(state_num_str, "%d", state_num);
				state_num_str[4] = NULL;
				
				makeStateFile(stateData,mymac,state_num_str);
				saveStateFile(stateData); //responsibility of saving is on the distDog caller.
			}
			
			printf("[Now Running]state num : %d\n", state_num);
			state_num++;
			sleep(1);
	
		}
		else if(getWorkState() == 0){// when this system isn't running as main
			if(flag == 1) {
				flag = 0;
			}
			sleep(3);
			char stateData[BUFFER_LEN]= {0,};
			loadStateFile(stateData);

			char state_num_str[5];
			strncpy(state_num_str, stateData + 17, 4);
			state_num_str[4] = NULL;
			printf("\n[user using process] now running as sub .  loaded state : %s\n",state_num_str);	
		}
	}



	//******************************user programming area end

	//thread_exit ( <--call before program exit)
	pthread_join(distDog_p_thread,NULL);

	
        return 0;
}


