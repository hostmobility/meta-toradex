PRINC = "4"

do_compile_append_tegra2() {
    #trdx: no feed available for a compatible arm architecture (arm, hard float, no neon) so empty the feed configs
    echo "" > ${S}/${sysconfdir}/opkg/base-feed.conf
    echo "" > ${S}/${sysconfdir}/opkg/debug-feed.conf
    echo "" > ${S}/${sysconfdir}/opkg/gstreamer-feed.conf
    echo "" > ${S}/${sysconfdir}/opkg/perl-feed.conf
    echo "" > ${S}/${sysconfdir}/opkg/python-feed.conf
}
