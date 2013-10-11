PRINC = "3"

do_compile_append_tegra3() {
    #trdx: make available the feeds of a compatible arm architecture
    rm  ${S}/${sysconfdir}/opkg/*t30-feed.conf
    echo "src/gz base ${ANGSTROM_URI}/feeds/v2013.06/ipk/eglibc/cortexa8hf-vfp-neon/base" > ${S}/${sysconfdir}/opkg/base-feed.conf
    echo "src/gz debug ${ANGSTROM_URI}/feeds/v2013.06/ipk/eglibc/cortexa8hf-vfp-neon/debug" > ${S}/${sysconfdir}/opkg/debug-feed.conf
    echo "src/gz gstreamer ${ANGSTROM_URI}/feeds/v2013.06/ipk/eglibc/cortexa8hf-vfp-neon/gstreamer" > ${S}/${sysconfdir}/opkg/gstreamer-feed.conf
    echo "src/gz perl ${ANGSTROM_URI}/feeds/v2013.06/ipk/eglibc/cortexa8hf-vfp-neon/perl" > ${S}/${sysconfdir}/opkg/perl-feed.conf
    echo "src/gz python ${ANGSTROM_URI}/feeds/v2013.06/ipk/eglibc/cortexa8hf-vfp-neon/python" > ${S}/${sysconfdir}/opkg/python-feed.conf
}

pkg_postinst_${PN}_tegra3 () {
#!/bin/sh
if [ "x$D" != "x" ]; then
	exit 1
fi
	echo "arch cortexa8hf-vfp-neon 18" >> ${sysconfdir}/opkg/arch.conf
}

do_compile_append_tegra2() {
    #trdx: no feed available for a compatible arm architecture (arm, hard float, no neon) so empty the feed configs
    echo "" > ${S}/${sysconfdir}/opkg/base-feed.conf
    echo "" > ${S}/${sysconfdir}/opkg/debug-feed.conf
    echo "" > ${S}/${sysconfdir}/opkg/gstreamer-feed.conf
    echo "" > ${S}/${sysconfdir}/opkg/perl-feed.conf
    echo "" > ${S}/${sysconfdir}/opkg/python-feed.conf
}

do_compile_append_colibri-vf50() {
    #trdx: make available the feeds of a compatible arm architecture
    rm  ${S}/${sysconfdir}/opkg/*vf*-feed.conf
    echo "src/gz base ${ANGSTROM_URI}/feeds/v2013.06/ipk/eglibc/armv6-vfp/base" > ${S}/${sysconfdir}/opkg/base-feed.conf
    echo "src/gz debug ${ANGSTROM_URI}/feeds/v2013.06/ipk/eglibc/armv6-vfp/debug" > ${S}/${sysconfdir}/opkg/debug-feed.conf
    echo "src/gz gstreamer ${ANGSTROM_URI}/feeds/v2013.06/ipk/eglibc/armv6-vfp/gstreamer" > ${S}/${sysconfdir}/opkg/gstreamer-feed.conf
    echo "src/gz perl ${ANGSTROM_URI}/feeds/v2013.06/ipk/eglibc/armv6-vfp/perl" > ${S}/${sysconfdir}/opkg/perl-feed.conf
    echo "src/gz python ${ANGSTROM_URI}/feeds/v2013.06/ipk/eglibc/armv6-vfp/python" > ${S}/${sysconfdir}/opkg/python-feed.conf
}
