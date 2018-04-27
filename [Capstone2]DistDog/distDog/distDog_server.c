#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <arpa/inet.h>
#include <sys/socket.h>
//#include "shared.h"

/*
 * gcc -I. -o server server.c socket.c
 */
#define SERVER_PORT 1234
#define CLIENT_PORT 5678
#define SERVER_IP "182.168.0.10"

#define BUFFER_LEN 512

int recv_data_layer(char *data, uint16_t data_len);
int send_data_layer(char *data, uint16_t data_len);

int main(int argc, char **argv)
{
        int sockfd = 0;
        struct sockaddr_in server_addr;
        struct sockaddr_in client_addr;
        const socklen_t slen = sizeof(struct sockaddr_in);

        char recv_buff[BUFFER_LEN] = { 0 };
        int recv_len = 0;
        char send_buff[BUFFER_LEN] = { 0 };

        printf("SERVER\n");

        /// Setup socket
        if (setup_socket(&sockfd) < 0) {
                return (-1);
        }

	printf("1\n");
        /// Setup server addr struct
	if (setup_sockaddr_in(&server_addr, SERVER_PORT , SERVER_IP) == NULL) {
		printf("1_1\n");
		return (0);
        }
	printf("2\n");
        /// Bind SERVER_PORT to address structure on sockfd
        if (bind_socket(sockfd, &server_addr)) {
                return (-1);
        }
	printf("3\n");
        for (;;) {

                recv_data_raw(sockfd, &recv_buff, &recv_len, BUFFER_LEN, &client_addr, slen);

                printf("Received packet from %s:%d\n", inet_ntoa(client_addr.sin_addr),
                       ntohs(client_addr.sin_port));
                printf("Data: %s\n", recv_buff);

                send_data_raw(sockfd, &recv_buff, recv_len, &client_addr, slen);

        }

        return 0;
}
