#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <arpa/inet.h>
#include <sys/socket.h>


/*
 * gcc -I. -o client client.c socket.c
 */

#define SERVER_PORT 1234
#define CLIENT_PORT 5678
#define SERVER_IP "192.168.0.1"
#define BUFFER_LEN 512

int main(int argc, char **argv)
{
        int sockfd = 0;
        struct sockaddr_in client_addr;
        struct sockaddr_in server_addr;
        const socklen_t slen = sizeof(struct sockaddr_in);

        char recv_buff[BUFFER_LEN] = { 0 };
        int recv_len = 0;
        char send_buff[BUFFER_LEN] = { 0 };

        printf("CLIENT\n");

        /// Setup socket
        if (setup_socket(&sockfd) < 0) {
                return (-1);
        }
        /// Setup server addr struct
        if (setup_sockaddr_in(&server_addr, SERVER_PORT, "182.168.0.255") == NULL) {
                return (-1);
        }
        /// Setup client addr struct
        if (setup_sockaddr_in(&client_addr, CLIENT_PORT, NULL) == NULL) {
                return (-1);
        }
        /// Bind CLIENT_PORT to address structure on sockfd
        printf("%d",client_addr.sin_port);
	if (bind_socket(sockfd, &client_addr) < 0) {
                return (-1);
        }

        for (;;) {

                printf("Enter message: ");
                gets(send_buff);
                send_data_raw(sockfd, &send_buff, BUFFER_LEN, &server_addr, slen);
                //recv_data_raw(sockfd, &recv_buff, &recv_len, BUFFER_LEN, &server_addr, slen);

                printf("Reply:\n %s\n", recv_buff);

        }

        return 0;
}
