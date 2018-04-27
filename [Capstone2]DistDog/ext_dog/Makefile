KDIR = /lib/modules/4.9.46-v7/build

obj-m := external_watchdog.o
#obj-m := external_watchdog.o

default:
	$(MAKE) -C $(KDIR) M=$$PWD modules

clean:
	$(MAKE) -C $(KDIR) M=$$PWD clean
