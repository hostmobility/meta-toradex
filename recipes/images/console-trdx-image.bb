#Angstrom image
DESCRIPTION = "Image booting to a console"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/LICENSE;md5=3f40d7994397109285ec7b81fdeb3b58"

PV = "V2.1"
PR = "r4"

#start of the resulting deployable tarball name
IMAGE_NAME_colibri-t20 = "Colibri_T20_LinuxConsoleImage"
IMAGE_NAME_colibri-t30 = "Colibri_T30_LinuxConsoleImage"
IMAGE_NAME_apalis-t30 = "Apalis_T30_LinuxConsoleImage"
IMAGE_NAME_colibri-pxa = "Colibri_PXA_LinuxConsoleImage"
IMAGE_NAME_colibri-vf = "Colibri_VF_LinuxConsoleImage"
IMAGE_NAME = "${MACHINE}_LinuxConsoleImage"

#create the deployment directory-tree
require trdx-image-fstype.inc

#remove interfering sysv scripts, connman systemd service
ROOTFS_POSTINSTALL_COMMAND = "for i in ${IMAGE_ROOTFS}/etc/rc0.d ${IMAGE_ROOTFS}/etc/rc1.d ${IMAGE_ROOTFS}/etc/rc2.d ${IMAGE_ROOTFS}/etc/rc3.d ${IMAGE_ROOTFS}/etc/rc4.d ${IMAGE_ROOTFS}/etc/rc5.d ${IMAGE_ROOTFS}/etc/rc6.d ${IMAGE_ROOTFS}/etc/rcS.d ; do rm -f $i/*dropbear $i/*avahi-daemon $i/*dbus-1 $i/*lxdm $i/*ntpd $i/*syslog $i/*ofono $i/*alsa-state $i/*networking $i/*udev-late-mount $i/*sendsigs $i/*save-rtc.sh $i/*umountnfs.sh $i/*portmap $i/*umountfs $i/*halt $i/*rmnologin.sh $i/*reboot; rm -f $i/*banner.sh $i/*sysfs.sh $i/*checkroot.sh $i/*alignment.sh $i/*mountall.sh $i/*populate-volatile.sh  $i/*devpts.sh  $i/*hostname.sh $i/*portmap  $i/*mountnfs.sh  $i/*bootmisc.sh ; done"

IMAGE_LINGUAS = "en-us"
#IMAGE_LINGUAS = "de-de fr-fr en-gb en-us pt-br es-es kn-in ml-in ta-in"
#ROOTFS_POSTPROCESS_COMMAND += 'install_linguas; '

DISTRO_UPDATE_ALTERNATIVES ??= ""
ROOTFS_PKGMANAGE_PKGS ?= '${@base_conditional("ONLINE_PACKAGE_MANAGEMENT", "none", "", "${ROOTFS_PKGMANAGE} ${DISTRO_UPDATE_ALTERNATIVES} update-alternatives-cworth", d)}'

CONMANPKGS = ""
#CONMANPKGS ?= "connman connman-systemd connman-plugin-loopback connman-plugin-ethernet connman-plugin-wifi connman-systemd connman-client"
CONMANPKGS_libc-uclibc = ""

#don't install some id databases
BAD_RECOMMENDATIONS += " udev-hwdb "

#this adds a few MB to the image
IMAGE_INSTALL_X11_tegra = "${XSERVER} xterm xclock"
IMAGE_INSTALL_X11 = ""

IMAGE_INSTALL += " \
    angstrom-packagegroup-boot \
    task-basic \
    udev-extra-rules \
    ${CONMANPKGS} \
    ${ROOTFS_PKGMANAGE_PKGS} \
    timestamp-service \
    task-base-extended \
    ${IMAGE_INSTALL_X11} \
"


require trdx-extra.inc

IMAGE_DEV_MANAGER   = "udev"
IMAGE_INIT_MANAGER  = "systemd"
IMAGE_INITSCRIPTS   = " "
IMAGE_LOGIN_MANAGER = "tinylogin shadow"

export IMAGE_BASENAME = "console-trdx-image"

inherit image
