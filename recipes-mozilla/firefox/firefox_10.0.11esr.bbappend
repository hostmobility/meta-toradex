PRINC = "4"

FILESEXTRAPATHS_prepend := "${THISDIR}/files:"
SRC_URI += "file://alignment.patch \
            file://distribution.ini \
"

do_install_append() {
    install -d ${D}${libdir}/${PN}/distribution
    install -m 0644 ${WORKDIR}/distribution.ini ${D}${libdir}/${PN}/distribution/    
}

