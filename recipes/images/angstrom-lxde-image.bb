#Angstrom image
DESCRIPTION = "Image based on the LXDE desktop"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/LICENSE;md5=3f40d7994397109285ec7b81fdeb3b58"

PV = "V2.0"
PR = "r3"

#create the deployment directory-tree
require trdx-image-fstype.inc

#create the file /etc/timestamp
IMAGE_PREPROCESS_COMMAND = "rootfs_update_timestamp"

#remove interfering sysv scripts, connman systemd service
ROOTFS_POSTINSTALL_COMMAND = "for i in ${IMAGE_ROOTFS}/etc/rc0.d ${IMAGE_ROOTFS}/etc/rc1.d ${IMAGE_ROOTFS}/etc/rc2.d ${IMAGE_ROOTFS}/etc/rc3.d ${IMAGE_ROOTFS}/etc/rc4.d ${IMAGE_ROOTFS}/etc/rc5.d ${IMAGE_ROOTFS}/etc/rc6.d ${IMAGE_ROOTFS}/etc/rcS.d ; do rm -f $i/*dropbear $i/*avahi-daemon $i/*dbus-1 $i/*lxdm $i/*ntpd $i/*syslog $i/*ofono $i/*alsa-state $i/*networking $i/*udev-late-mount $i/*sendsigs $i/*save-rtc.sh $i/*umountnfs.sh $i/*portmap $i/*umountfs $i/*halt $i/*rmnologin.sh $i/*reboot; rm -f $i/*banner.sh $i/*sysfs.sh $i/*checkroot.sh $i/*alignment.sh $i/*mountall.sh $i/*populate-volatile.sh  $i/*devpts.sh  $i/*hostname.sh $i/*portmap  $i/*mountnfs.sh  $i/*bootmisc.sh ; done"

#IMAGE_LINGUAS = ""
IMAGE_LINGUAS = "en-us"
#IMAGE_LINGUAS = "de-de fr-fr en-gb en-us pt-br es-es kn-in ml-in ta-in"
#ROOTFS_POSTPROCESS_COMMAND += 'install_linguas; '
#IMAGE_FEATURES += "package-management ssh-server-dropbear"

#MAYBE WE WILL NEED THESE ALSO:
# xorg-minimal-fonts xserver-xorg-multimedia-modules xserver-xorg-utils

DISTRO_UPDATE_ALTERNATIVES ??= ""
ROOTFS_PKGMANAGE_PKGS ?= '${@base_conditional("ONLINE_PACKAGE_MANAGEMENT", "none", "", "${ROOTFS_PKGMANAGE} ${DISTRO_UPDATE_ALTERNATIVES}", d)}'

#CONMANPKGS = ""
CONMANPKGS ?= "connman connman-plugin-loopback connman-plugin-ethernet connman-plugin-wifi connman-gnome"
CONMANPKGS_libc-uclibc = ""

DEPENDS += "gst-plugins-good gst-plugins-bad gst-plugins-ugly"

#deploy the OpenGL ES headers to the sysroot
DEPENDS += "nvsamples"

#build some ipk which are needed together with CAN, but do not yet install them
DEPENDS += "canutils libsocketcan iproute2"

# Additional X libs not pulled in by any package \
#  xtrans libxevie \

# this would pull in a large amount of gst-plugins, we only add a selected few
#  gst-plugins-base-meta \
#  gst-plugins-good-meta \
#  gst-plugins-bad-meta \
#  gst-ffmpeg \

# needed by nvidia commandline player \
#  libpcre \

# these were in the oe classic image
IMAGE_INSTALL_CLASSIC = " \
gconf \
gnome-vfs \
gnome-vfs-plugin-file \
gvfs \
gvfsd-trash \
xdg-utils \
xvinfo \
\
file \
initscripts \
libgsf \
polkit-gnome \
libwnck \
libxres \
makedevs \
mime-support \
xcursor-transparent-theme \
zeroconf \
"

IMAGE_INSTALL += " \
	${IMAGE_INSTALL_CLASSIC} \
	angstrom-packagegroup-boot \
	task-basic \
	udev-extra-rules \
	${CONMANPKGS} \
	${ROOTFS_PKGMANAGE_PKGS} \
	timestamp-service \
	task-base-extended \
	${XSERVER} \
	xserver-common \
	xserver-xorg-extension-dbe \
	xserver-xorg-extension-extmod \
	xserver-xorg-extension-extmod \
	xauth \
	xhost \
	xset \
	\
	xrandr \
	xrdb \
	xorg-minimal-fonts xserver-xorg-multimedia-modules xserver-xorg-utils \
	\
	libxdamage libxvmc libxinerama \
	libxcursor \
	\
	bash \
	tinylogin \
	\
	gstreamer \
	gst-plugins-base \
	gst-plugins-base-alsa \
	gst-plugins-base-audioconvert \
	gst-plugins-base-audioresample \
	gst-plugins-base-audiotestsrc \
	gst-plugins-base-decodebin \
	gst-plugins-base-decodebin2 \
	gst-plugins-base-playbin \
	gst-plugins-base-typefindfunctions \
	gst-plugins-base-ivorbisdec \
	gst-plugins-base-ogg \
	gst-plugins-base-theora \
	gst-plugins-base-videotestsrc \
	gst-plugins-base-vorbis \
	gst-plugins-good-audioparsers \
	gst-plugins-good-avi \
	gst-plugins-good-id3demux \
	gst-plugins-good-isomp4 \
	gst-plugins-good-matroska \
	gst-plugins-good-rtp \
	gst-plugins-good-udp \
	gst-plugins-good-video4linux2 \
	gst-plugins-good-wavenc \
	gst-plugins-good-wavparse \
	gst-plugins-ugly-asf \
	libpcre \
	libpcreposix \
	libxcomposite \
	alsa-states \
	firefox \
"

#	gst-plugin-mpegdemux 

require lx.inc
require trdx-extra.inc

IMAGE_DEV_MANAGER   = "udev"
IMAGE_INIT_MANAGER  = "systemd"
IMAGE_INITSCRIPTS   = " "
IMAGE_LOGIN_MANAGER = "tinylogin shadow"

export IMAGE_BASENAME = "LXDE-image"

inherit core-image
