SECTION = "x11/libs"
DESCRIPTION = "qtdemo autostart"
RDEPENDS_${PN} = ""
# The license is meant for this recipe and the files it installs.
# RNDIS is part of the kernel, udhcpd is part of busybox
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/LICENSE;md5=3f40d7994397109285ec7b81fdeb3b58"

PR = "r1"

inherit allarch systemd

SRC_URI = "file://qtdemo.service \
           file://qtdemo-init "

FILES_${PN} += " ${sysconfdir}/init.d "
do_install () {
    install -d ${D}/${sysconfdir}/init.d
    install -m 0755 ${WORKDIR}/qtdemo-init ${D}/${sysconfdir}/init.d/

    install -d ${D}${systemd_unitdir}/system/
    install -m 0644 ${WORKDIR}/qtdemo.service ${D}${systemd_unitdir}/system
}

NATIVE_SYSTEMD_SUPPORT = "1"
SYSTEMD_PACKAGES = "${PN}"
SYSTEMD_SERVICE_${PN} = "qtdemo.service"
