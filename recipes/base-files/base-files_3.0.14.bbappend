PRINC = "6"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

do_install_append () {
#trdx specific
        echo "search colibri.net"  > ${D}${sysconfdir}/resolv.conf
        echo "nameserver 8.8.8.8" >> ${D}${sysconfdir}/resolv.conf
}
