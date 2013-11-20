#Angstrom image
DESCRIPTION = "Image based on the LXDE desktop"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/LICENSE;md5=3f40d7994397109285ec7b81fdeb3b58"

PV = "V2.1"
PR = "r5"

#start of the resulting deployable tarball name
IMAGE_NAME_colibri-t20 = "Colibri_T20_LinuxImage"
IMAGE_NAME_colibri-t30 = "Colibri_T30_LinuxImage"
IMAGE_NAME_apalis-t30 = "Apalis_T30_LinuxImage"
IMAGE_NAME_colibri-pxa = "Colibri_PXA_LinuxImage"
IMAGE_NAME_colibri-vf = "Colibri_VF_LinuxImage"
IMAGE_NAME = "${MACHINE}_LinuxImage"

#create the deployment directory-tree
require trdx-image-fstype.inc

#remove interfering sysv scripts, connman systemd service
ROOTFS_POSTINSTALL_COMMAND = "for i in ${IMAGE_ROOTFS}/etc/rc0.d ${IMAGE_ROOTFS}/etc/rc1.d ${IMAGE_ROOTFS}/etc/rc2.d ${IMAGE_ROOTFS}/etc/rc3.d ${IMAGE_ROOTFS}/etc/rc4.d ${IMAGE_ROOTFS}/etc/rc5.d ${IMAGE_ROOTFS}/etc/rc6.d ${IMAGE_ROOTFS}/etc/rcS.d ; do rm -f $i/*dropbear $i/*avahi-daemon $i/*dbus-1 $i/*lxdm $i/*ntpd $i/*syslog $i/*ofono $i/*alsa-state $i/*networking $i/*udev-late-mount $i/*sendsigs $i/*save-rtc.sh $i/*umountnfs.sh $i/*portmap $i/*umountfs $i/*halt $i/*rmnologin.sh $i/*reboot; rm -f $i/*banner.sh $i/*sysfs.sh $i/*checkroot.sh $i/*alignment.sh $i/*mountall.sh $i/*populate-volatile.sh  $i/*devpts.sh  $i/*hostname.sh $i/*portmap  $i/*mountnfs.sh  $i/*bootmisc.sh ; done"

IMAGE_LINGUAS = "en-us"
#IMAGE_LINGUAS = "de-de fr-fr en-gb en-us pt-br es-es kn-in ml-in ta-in"
#ROOTFS_POSTPROCESS_COMMAND += 'install_linguas; '

DISTRO_UPDATE_ALTERNATIVES ??= ""
ROOTFS_PKGMANAGE_PKGS ?= '${@base_conditional("ONLINE_PACKAGE_MANAGEMENT", "none", "", "${ROOTFS_PKGMANAGE} ${DISTRO_UPDATE_ALTERNATIVES} update-alternatives-cworth", d)}'

CONMANPKGS ?= "connman connman-systemd connman-plugin-loopback connman-plugin-ethernet connman-plugin-wifi connman-client connman-gnome"
CONMANPKGS_libc-uclibc = ""

DEPENDS += "gst-plugins-good gst-plugins-bad gst-plugins-ugly"

#deploy the OpenGL ES headers to the sysroot
DEPENDS_tegra += "nvsamples"

IMAGE_BROWSER = "firefox"
#keep the rootfs size small
IMAGE_BROWSER_colibri-vf = "midori"

# this recommends from midori pulls in 8Meg of icons
BAD_RECOMMENDATIONS += " gnome-icon-theme "
#don't install some id databases
BAD_RECOMMENDATIONS_VF_colibri_vf += " udev-hwdb "
BAD_RECOMMENDATIONS += " ${BAD_RECOMMENDATIONS_VF} "

# this would pull in a large amount of gst-plugins, we only add a selected few
#    gst-plugins-base-meta
#    gst-plugins-good-meta
#    gst-plugins-bad-meta
#    gst-ffmpeg
GSTREAMER = " \
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
    gst-plugins-good-autodetect \
    gst-plugins-good-avi \
    gst-plugins-good-deinterlace \
    gst-plugins-good-id3demux \
    gst-plugins-good-isomp4 \
    gst-plugins-good-matroska \
    gst-plugins-good-rtp \
    gst-plugins-good-udp \
    gst-plugins-good-video4linux2 \
    gst-plugins-good-wavenc \
    gst-plugins-good-wavparse \
    gst-plugins-ugly-asf \
"
GSTREAMER_colibri-vf = ""

IMAGE_INSTALL += " \
    gconf \
    gnome-vfs \
    gnome-vfs-plugin-file \
    gvfs \
    gvfsd-trash \
    xdg-utils \
    xvinfo \
    \
    initscripts \
    libgsf \
    polkit-gnome \
    libwnck \
    libxres \
    makedevs \
    mime-support \
    xcursor-transparent-theme \
    zeroconf \
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
    xauth \
    xhost \
    xset \
    setxkbmap \
    \
    xrdb \
    xorg-minimal-fonts xserver-xorg-multimedia-modules xserver-xorg-utils \
    scrot \
    \
    libxdamage libxvmc libxinerama \
    libxcursor \
    \
    florence \
    bash \
    tinylogin \
    \
    ${GSTREAMER} \
    v4l-utils \
    libpcre \
    libpcreposix \
    libxcomposite \
    alsa-states \
    ${IMAGE_BROWSER} \
"

require lx.inc
require trdx-extra.inc

IMAGE_DEV_MANAGER   = "udev"
IMAGE_INIT_MANAGER  = "systemd"
IMAGE_INITSCRIPTS   = " "
IMAGE_LOGIN_MANAGER = "tinylogin shadow"

export IMAGE_BASENAME = "LXDE-image"

inherit core-image
