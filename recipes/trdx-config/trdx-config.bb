DESCRIPTION = "setup files"
LICENSE = "Public Domain"
PR = "r2"

inherit update-rc.d

PACKAGE_ARCH = "${MACHINE_ARCH}"

SRC_URI =  "	file://inittab \
		file://nvrm_daemon \
		file://COPYING \
            	"
LIC_FILES_CHKSUM = "file://${WORKDIR}/COPYING;md5=1c3a7fb45253c11c74434676d84fe7dd"

PACKAGES = "${PN}"

FILES_${PN} = "/etc/* ${sysconfdir}"
PACKAGE_ARCH = "${MACHINE_ARCH}"

DEPENDS_${PN} += "update-rc.d"
RDEPENDS_${PN} += "update-rc.d"

do_install () {
	install -d ${D}/etc/init.d
	install -m 0644 ${WORKDIR}/inittab ${D}/etc/
	install -m 0755 ${WORKDIR}/nvrm_daemon ${D}/etc/init.d
}

INITSCRIPT_NAME = "nvrm_daemon"
INITSCRIPT_PARAMS = "defaults 91"