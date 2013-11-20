DESCRIPTION = "Init script for qtdemo"
LICENSE = "MIT"
SRC_URI = " \
    file://qtdemo-init \
    file://mouse.rules \
    file://keyboard.rules \
"
PR = "r4"

LIC_FILES_CHKSUM = "file://${COREBASE}/LICENSE;md5=3f40d7994397109285ec7b81fdeb3b58 \
                    file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"

do_install() {
    install -d ${D}${sysconfdir}/init.d/  ${D}${sysconfdir}/udev/rules.d/
    install -m 0755 ${WORKDIR}/qtdemo-init ${D}${sysconfdir}/init.d/qtdemo
    install -m 0644 ${WORKDIR}/mouse.rules ${D}${sysconfdir}/udev/rules.d/
    install -m 0644 ${WORKDIR}/keyboard.rules ${D}${sysconfdir}/udev/rules.d/
}

inherit update-rc.d allarch

INITSCRIPT_NAME = "qtdemo"
INITSCRIPT_PARAMS = "start 99 5 2 . stop 19 0 1 6 ."
