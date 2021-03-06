DESCRIPTION = "NVIDIAS tegrastats in a commandline version and one with output to a gtk title bar, gives information about cpu use"
LICENSE = "CLOSED"
PR = "r3"

SRC_URI =  "file://tegrastats \
            file://tegrastats-gtk \
	    file://Tegrastats-gtk.desktop "

S = "${WORKDIR}"

PACKAGES = "${PN}"

# Inhibit warnings about files being stripped.
INHIBIT_PACKAGE_DEBUG_SPLIT = "1"

do_install() {
    install -d ${D}${bindir}/ ${D}/home/root/Desktop/
    install -m 0755 ${S}/tegrastats* ${D}${bindir}/
    install -m 0755 ${S}/Tegrastats-gtk.desktop ${D}/home/root/Desktop/
}

FILES_${PN} = " \
	${bindir}/tegrastats* \
	/home/root/Desktop/Tegra* "
