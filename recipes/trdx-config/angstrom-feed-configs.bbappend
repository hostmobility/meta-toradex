#make available the feeds of other, but compatible arm architectures
PRINC = "2"

do_compile_append() {
        echo "src/gz base2 ${ANGSTROM_URI}/feeds/core/ipk/eglibc/armv6-novfp/base" >> ${S}/${sysconfdir}/opkg/base-feed.conf
        echo "src/gz base2 ${ANGSTROM_URI}/feeds/core/ipk/eglibc/armv6/base" >> ${S}/${sysconfdir}/opkg/base-feed.conf
        echo "src/gz base3 ${ANGSTROM_URI}/feeds/core/ipk/eglibc/armv5te/base" >> ${S}/${sysconfdir}/opkg/base-feed.conf
}
