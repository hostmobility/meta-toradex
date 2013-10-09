PRINC = "1"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

#don't install the perl scripts (and thus don't RDEPEND on perl)

RDEPENDS_${PN} = ""

do_compile_prepend() {
    sed -i 's/eeprom stub//' Makefile
}