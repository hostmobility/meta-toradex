DESCRIPTION = "GPIOConfig tool for Colibri T20"
SECTION = "base"
LICENSE = "propriatry"
PR = "r3"

PACKAGE_ARCH = "${MACHINE_ARCH}"

DEPENDS = "gtk+"
RDEPENDS = "gtk+"

S = "${WORKDIR}/target-utils/GPIOConfig"

SVN_REV = "190"
SRC_URI = "svn://tegradev:tegra123!@mammut.toradex.int:8090/colibri_tegra_linux/trunk;module=target-utils/GPIOConfig;rev=${SVN_REV};proto=http"
#SRC_URI += "file://Makefile"
SRC_URI += "file://GPIOConfig.desktop"
SRC_URI += "file://GPIOConfig.png"

PACKAGES = "${PN}"

# just don't do any configuring
do_configure() {
}

do_install() {
        install -d ${D}/${bindir}
	install -d ${D}/${datadir}/applications
	install -d ${D}/${datadir}/pixmaps
        install ${S}/GPIOConfig ${D}/${bindir}
	install ${WORKDIR}/GPIOConfig.desktop ${D}/${datadir}/applications
        install ${WORKDIR}/GPIOConfig.png ${D}/${datadir}/pixmaps/GPIOConfig.png
}

pkg_postinst_${PN}() {
	mkdir -p ${base_prefix}/home/root/Desktop
	cp ${datadir}/applications/GPIOConfig.desktop ${base_prefix}/home/root/Desktop/
}

pkg_postremove_${PN}() {
        rm -f ${base_prefix}/home/Desktop/GPIOConfig.desktop
}



