PRINC := "${@int(PRINC) + 2}"

do_install_append () {
    # make an empty file, so that ${PN} packages has not size 0 and does get created
    mkdir -p ${D}${datadir}/gstreamer-${LIBV}
    touch ${D}${datadir}/gstreamer-${LIBV}/${PN}
}

