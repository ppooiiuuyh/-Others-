#include <linux/fs.h> /* open( ), read( ), write( ), close( ) 커널 함수 #*/
#include <linux/cdev.h> /* 문자 디바이스 */ 
#include <linux/module.h> 
#include <linux/io.h> /* ioremap( ), iounmap( ) 커널 함수 */ 
#include <linux/gpio.h> 
#include <asm/uaccess.h> /* copy_to_user( ), #copy_from_user( ) 커널 함수 */ 
#include <linux/delay.h>
#include <linux/timer.h>
#include <linux/delay.h>
#include <linux/sched.h>
#include <linux/interrupt.h>

MODULE_LICENSE("GPL");
MODULE_AUTHOR("JIYEON KIM");
MODULE_DESCRIPTION("Raspberry Pi External Watchdog");

#define GPIO_MAJOR 		200
#define GPIO_MINOR 		0
#define GPIO_DEVICE             "WATCH_DOG"              /* 디바이스 장치 파일의 이름 */
#define GPIO_HWREBOOT           18                         /* LED 사용을 위한 GPIO의 번호 */
#define GPIO_SIGNAL_IN		23
#define GPIO_SIGNAL_OUT		24
#define HWREBOOT_TIMEOUT_PERIOD	7
#define SIGOUT_TIMEOUT_PERIOD		3

static char msg[BLOCK_SIZE] = {0};                      /* write( ) 함수에서 읽은 데이터 저장 */
static struct timer_list hwReboot_timer;
static struct timer_list sigOut_timer;
static int timerExtender_irq;

void cleanupModule(void);
void sec_delay(int sec);
void hwReboot(void);
static void hwReboot_timer_func(unsigned long data);
static void sigOut_timer_func(unsigned long data);

/*********************************************************
 *  functions for external watchdog (hardware reboot)
 *********************************************************/
//delay function for kernel timer
void sec_delay(int sec){
	unsigned long delay = jiffies + sec*HZ;
	while(time_before ( jiffies,delay)){
		schedule();
	}
}


//function for hardware reboot
void hwReboot(void){

	gpio_set_value(GPIO_HWREBOOT, 1);
	sec_delay(1);

	printk("jiffies : %d",jiffies);


	gpio_set_value(GPIO_HWREBOOT, 0);
}


//function for hardware reboot timer
static void hwReboot_timer_func(unsigned long data){
	hwReboot();
	hwReboot_timer.expires = jiffies + (HWREBOOT_TIMEOUT_PERIOD*HZ);
	del_timer(&hwReboot_timer);
	add_timer(&hwReboot_timer);
}

//function for signal out timer
static void sigOut_timer_func(unsigned long data){
	gpio_set_value(GPIO_SIGNAL_OUT,1);
	sec_delay(1);
	gpio_set_value(GPIO_SIGNAL_OUT,0);
	del_timer(&sigOut_timer);
	sigOut_timer.expires = jiffies +(SIGOUT_TIMEOUT_PERIOD*HZ);
	add_timer(&sigOut_timer);
}


//function to be added at interrupt table.
static irqreturn_t isr_func(int irq,void *data){   
	if(irq==timerExtender_irq){    
		printk("1111interrupt!");
		del_timer(&hwReboot_timer);
		hwReboot_timer.expires = jiffies + (HWREBOOT_TIMEOUT_PERIOD*HZ);
		add_timer(&hwReboot_timer);
		printk("interrupt!");
	}
	printk("2222interrupt!");

	return IRQ_HANDLED; 
}
//////////////////////////////////////////////////////////







int initModule(void)
{
	dev_t devno;
	unsigned int count;
	int err;
	/*********************************************
	 * init Module
	 *********************************************/
	{
		printk(KERN_INFO "Hello module!\n");
		try_module_get(THIS_MODULE);
		devno = MKDEV(GPIO_MAJOR, GPIO_MINOR);
		register_chrdev_region(devno, 1, GPIO_DEVICE);
		printk("init Module finished");
	}


	/*********************************************
	 * init GPIO
	 *********************************************/
	{
		gpio_request(GPIO_HWREBOOT, "HWREBOOT");  
		gpio_direction_output(GPIO_HWREBOOT, 0);

		gpio_request(GPIO_SIGNAL_IN, "SIGNAL_IN");
		gpio_direction_input(GPIO_SIGNAL_IN);

		gpio_request(GPIO_SIGNAL_OUT, "SIGNAL_OUT");
		gpio_direction_output(GPIO_SIGNAL_OUT, 0);

		printk("init GPIO Finished");    
	}


	/*********************************************
	 * init & add Timer (h/w reboot timer , signal out timer)
	 *********************************************/
	{
		del_timer_sync(&hwReboot_timer);
		init_timer(&hwReboot_timer);
		hwReboot_timer.function = hwReboot_timer_func;
		hwReboot_timer.expires = jiffies + (HWREBOOT_TIMEOUT_PERIOD*HZ);
		add_timer(&hwReboot_timer);



		del_timer_sync(&sigOut_timer);
		init_timer(&sigOut_timer);
		sigOut_timer.function = sigOut_timer_func;
		sigOut_timer.expires = jiffies + (SIGOUT_TIMEOUT_PERIOD*HZ);
		add_timer(&sigOut_timer);	

		printk("init Timer finished");   
	}


	/*********************************************
	 * init & add Interrupt to irq (h/w reboot timer extender)
	 *********************************************/
	{
		timerExtender_irq =  gpio_to_irq(GPIO_SIGNAL_IN);
		request_irq(timerExtender_irq,isr_func, IRQF_TRIGGER_RISING , "timeout",NULL);

		printk("init interrupte finished");
	}


//	cleanupModule();
	return 0;
}

void cleanupModule(void)
{
	/***************************************************
	 * cleanup module
	 ****************************************************/
	{
		dev_t devno = MKDEV(GPIO_MAJOR, GPIO_MINOR);
		unregister_chrdev_region(devno, 1);
	}

	/***************************************************
	 * cleanup GPIO
	 ****************************************************/
	{
		gpio_free(GPIO_HWREBOOT);
		gpio_free(GPIO_SIGNAL_IN);
		gpio_free(GPIO_SIGNAL_OUT);
		free_irq(timerExtender_irq,NULL);
	}

	module_put(THIS_MODULE);
	printk(KERN_INFO "Good-bye module!\n");
}

module_init(initModule);
module_exit(cleanupModule);

