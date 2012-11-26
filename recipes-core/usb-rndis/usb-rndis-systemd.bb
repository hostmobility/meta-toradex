SECTION = "network"
DESCRIPTION = "RNDIS usb client configuration and startup"
RDEPENDS_${PN} = ""
# The license is meant for this recipe and the files it installes. 
# RNDIS is part of the kernel, udhcpd is part of busybox
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/LICENSE;md5=3f40d7994397109285ec7b81fdeb3b58"

# The kernel provides with CONFIG_USB_G_ANDROID a composite gadget driver among other with RNDIS functionality.
# This package contains systemd files to configure RNDIS at startup, configures a fix IP localy and provides a dhcp server on the new interface.
# Local IP is 192.168.11.2, remote IP is 192.168.11.1

inherit allarch systemd

SRC_URI = " \
    file://start-rndis.sh \
    file://usb-rndis.service \
    file://busybox-dhcpd.service \
    file://udhcpd.conf "

do_install() {
    install -d ${D}/${sysconfdir} ${D}/${bindir}
    install -m 0755 ${WORKDIR}/start-rndis.sh ${D}/${bindir}
    install -m 0644 ${WORKDIR}/udhcpd.conf ${D}/${sysconfdir}/
}

NATIVE_SYSTEMD_SUPPORT = "1"
SYSTEMD_PACKAGES = "${PN}"
SYSTEMD_SERVICE_${PN} = "usb-rndis.service busybox-dhcpd.service "
