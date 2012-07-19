DESCRIPTION = "setup files"
LICENSE = "Public Domain"
PR = "r5"

inherit update-rc.d

PACKAGE_ARCH = "${MACHINE_ARCH}"

SRC_URI =  "	file://inittab \
		file://udev-late-mount \
		file://COPYING \
	   "
LIC_FILES_CHKSUM = "file://${WORKDIR}/COPYING;md5=1c3a7fb45253c11c74434676d84fe7dd"

PACKAGES = "${PN}"

FILES_${PN} += "${sysconfdir}/init.d/udev-late-mount"

DEPENDS_${PN} += "update-rc.d"
RDEPENDS_${PN} += "update-rc.d"

do_install () {
	install -d ${D}${sysconfdir}/init.d
	install -m 0644 ${WORKDIR}/inittab ${D}${sysconfdir}
	install -m 0755 ${WORKDIR}/udev-late-mount ${D}${sysconfdir}/init.d
}

INITSCRIPT_NAME = "udev-late-mount"
INITSCRIPT_PARAMS = "defaults 60"
