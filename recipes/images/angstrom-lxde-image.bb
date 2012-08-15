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
CONMANPKGS ?= "connman connman-plugin-loopback connman-plugin-ethernet connman-plugin-wifi connman-systemd connman-gnome"
CONMANPKGS_libc-uclibc = ""

DEPENDS += "gst-plugins-good gst-plugins-bad gst-plugins-ugly"

#deploy the OpenGL ES headers to the sysroot
DEPENDS += "nvsamples"


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
sysvinit \
xcursor-transparent-theme \
zeroconf \
"

IMAGE_INSTALL += " \
	${IMAGE_INSTALL_CLASSIC} \
	virtual-psplash \
	angstrom-task-boot \
	task-basic \
	udev-extra-rules \
	${CONMANPKGS} \
	${ROOTFS_PKGMANAGE_PKGS} \
	timestamp-service \
	task-base-extended \
	${XSERVER}\
	xserver-common \
	xserver-xorg-extension-dbe \
	xserver-xorg-extension-extmod \
	xserver-xorg-extension-dri \
	xserver-xorg-extension-dri2 \
	xserver-xorg-extension-extmod \
	xserver-xorg-extension-glx \
	xauth \
	xhost \
	xset \
	\
	${XSERVER} \
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
	gst-plugins-good-isomp4 \
	gst-plugins-good-matroska \
	gst-plugins-good-rtp \
	gst-plugins-good-udp \
	gst-plugins-good-avi \
	gst-plugins-good-wavenc \
	gst-plugins-good-wavparse \
	gst-plugins-ugly-asf \
	libpcre \
	libpcreposix \
	libxcomposite \
	alsa-states \
"

# firefox at the moment segfaults
#	firefox \
#	flash-plugins \

#	gst-plugin-mpegdemux 

require lx.inc
require trdx-extra.inc

IMAGE_DEV_MANAGER   = "udev"
IMAGE_INIT_MANAGER  = "systemd"
IMAGE_INITSCRIPTS   = " "
IMAGE_LOGIN_MANAGER = "tinylogin shadow"

export IMAGE_BASENAME = "LXDE-image"

inherit core-image
