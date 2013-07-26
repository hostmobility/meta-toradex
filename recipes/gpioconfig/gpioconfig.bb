DESCRIPTION = "GPIOConfig tool for Colibri T20"
SECTION = "base"
LICENSE = "CLOSED"
PR = "r3"

PACKAGE_ARCH = "${MACHINE_ARCH}"

DEPENDS = "gtk+"
RDEPENDS_{PN} = "gtk+"

SRC_URI =  "file://GPIOConfig"
SRC_URI += "file://GPIOConfig.desktop"
SRC_URI += "file://GPIOConfig.png"

PACKAGES = "${PN}"

# Inhibit warnings about files being stripped.
INHIBIT_PACKAGE_DEBUG_SPLIT = "1"

# just don't do any configuring
do_configure() {
}

do_install() {
        install -d ${D}/${bindir}
	install -d ${D}/${datadir}/applications
	install -d ${D}/${datadir}/pixmaps
        install -m 755 ${WORKDIR}/GPIOConfig ${D}/${bindir}
	install -m 644 ${WORKDIR}/GPIOConfig.desktop ${D}/${datadir}/applications
        install -m 644 ${WORKDIR}/GPIOConfig.png ${D}/${datadir}/pixmaps/GPIOConfig.png
}

pkg_postinst_${PN}() {
	mkdir -p ${base_prefix}/home/root/Desktop
	cp ${datadir}/applications/GPIOConfig.desktop ${base_prefix}/home/root/Desktop/
}

pkg_postremove_${PN}() {
        rm -f ${base_prefix}/home/Desktop/GPIOConfig.desktop
}



