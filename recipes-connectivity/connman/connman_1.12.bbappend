PRINC = "1"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI += " \
    file://dont_start_connman_on_nfsboot.patch \
"

SYSTEMD_PACKAGES = "${PN}-systemd"
