#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <arpa/inet.h>
#include <sys/socket.h>

int setup_socket(int *sockfd)
{
	int ret;
	int broadcastEnable=1;

	if ((*sockfd = socket(AF_INET, SOCK_DGRAM, IPPROTO_UDP)) == -1) {

		return (-1);
	}

	if(ret=setsockopt(*sockfd, SOL_SOCKET, SO_BROADCAST, &broadcastEnable, sizeof(broadcastEnable)))
	{
		return -1;

	}

	return *sockfd;
}

struct sockaddr_in *setup_sockaddr_in(struct sockaddr_in *addr, uint16_t port, char *addr_string)
{

	memset((char *)addr, 0, sizeof(struct sockaddr_in));
	
	addr->sin_family = AF_INET;

	if (port != NULL) {

		addr->sin_port = htons(port);
	}
	
	if (addr_string == NULL) {
		addr->sin_addr.s_addr = htonl(INADDR_ANY);
		//addr->sin_addr.s_addr = htonl(INADDR_BROADCAST);
	} else {
		if (inet_aton(addr_string, &addr->sin_addr) == 0) {
			fprintf(stderr, "inet_aton() failed\n");
			return (NULL);
		}
	}

	return (addr);
}

int bind_socket(int sockfd, struct sockaddr_in *addr)
{

	if (bind(sockfd, (struct sockaddr *)addr, sizeof(struct sockaddr)) == -1) {
		fprintf(stderr, "bind() failed\n");
		return (-1);
	}
	return (0);
}

int send_data_raw(int sockfd, char buffer[], unsigned int buffer_length, struct sockaddr_in *addr,
		const socklen_t slen)
{
	if (sendto(sockfd, buffer, buffer_length, 0, (struct sockaddr *)addr, slen) < 0) {
		fprintf(stderr, "sendto() failed\n");
		return (-1);
	}
	return (0);
}

int recv_data_raw(int sockfd, char buffer[], int *recv_len, unsigned int buffer_length,
		struct sockaddr_in *addr, socklen_t slen)
{
	memset(buffer, 0, buffer_length);

	if ((*recv_len = recvfrom
				(sockfd, buffer, buffer_length, MSG_DONTWAIT, (struct sockaddr *)addr, &slen)) < 0) {
		//#fprintf(stderr, "recvfrom() failed\n");
		return (-1);
	}
	return *recv_len;
}
