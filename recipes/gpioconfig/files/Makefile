CROSS_COMPILE ?= /vibrante/toolchains/tegra2-4.4.1-nv/bin/arm-none-linux-gnueabi-
CC ?= $(CROSS_COMPILE)gcc
STRIP ?= $(CROSS_COMPILE)strip

SYSROOT_DIR=/srv/nfs/rootfs-include-lib-gtk
prefix = ""
bindir = $(prefix)/usr/bin

CFLAGS ?= -Wall -g -O2 `pkg-config --cflags gtk+-2.0`

#DRV = gpio_usr_drv

PROG = GPIOConfig

OBJS = gpio_gtk.o custom-list.o gpio_usr_drv.o

all: $(OBJS)
#	$(CC) -o $(DRV) $(DRV).c
	$(CC) -o $(PROG) $(OBJS) $(LDFLAGS) `pkg-config --cflags gtk+-2.0` `pkg-config --libs gtk+-2.0` -L $(SYSROOT_DIR)/usr/lib -Wl,--allow-shlib-undefined
	$(STRIP) $(PROG)

%o: %c 
	$(CC) $(CFLAGS) $(CPPFLAGS) `pkg-config --cflags gtk+-2.0` -c $<
	
clean:
	rm -f $(DRV) $(PROG) $(OBJS) *.o~ *.h~ 2>/dev/null

install:
	install -d $(bindir)
	install $(PROG) $(bindir)

