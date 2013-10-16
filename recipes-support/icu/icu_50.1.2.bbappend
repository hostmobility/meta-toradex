# use a customized icu data library created from
# http://apps.icu-project.org/datacustom/ICUData50.html
PRINC_colibri-vf50 = "1"

FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

SRC_URI_colibri-vf50 = "${BASE_SRC_URI} file://icudt50l.zip "

do_configure_append_colibri-vf50 () {
    rm  ${S}/data/in/icudt50l.dat
    cp ${WORKDIR}/icudt50l.dat ${S}/data/in/
}
