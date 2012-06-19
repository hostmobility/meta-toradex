DESCRIPTION = "setup files"
LICENSE = "Public Domain"
PR = "r4"

inherit update-rc.d

PACKAGE_ARCH = "${MACHINE_ARCH}"

SRC_URI =  "	file://inittab \
		file://nvrm_daemon \
		file://udev-late-mount \
		file://COPYING \
            	"
LIC_FILES_CHKSUM = "file://${WORKDIR}/COPYING;md5=1c3a7fb45253c11c74434676d84fe7dd"

PACKAGES = "${PN} ${PN}-udev-late-mount"

FILES_${PN}-udev-late-mount = "${sysconfdir}/init.d/udev-late-mount"
FILES_${PN}                 = "${sysconfdir}/inittab ${sysconfdir}/init.d/nvrm_daemon"

DEPENDS_${PN} += "update-rc.d"
RDEPENDS_${PN} += "update-rc.d ${PN}-udev-late-mount"

do_install () {
	install -d ${D}${sysconfdir}/init.d
	install -m 0644 ${WORKDIR}/inittab ${D}${sysconfdir}
	install -m 0755 ${WORKDIR}/nvrm_daemon ${D}${sysconfdir}init.d
	install -m 0755 ${WORKDIR}/udev-late-mount ${D}${sysconfdir}/init.d
}

INITSCRIPT_PACKAGES = "${PN} ${PN}-udev-late-mount"
INITSCRIPT_NAME_${PN} = "nvrm_daemon"
INITSCRIPT_PARAMS_${PN} = "defaults 91"
INITSCRIPT_NAME_${PN}-udev-late-mount = "udev-late-mount"
INITSCRIPT_PARAMS_${PN}-udev-late-mount = "defaults 60"
