PRINC = "1"

FILESEXTRAPATHS_prepend := "${THISDIR}/files"

RDEPENDS_${PN} += "librsvg-gtk"
SRC_URI += " \
    file://fix_garbled_titlebar.patch \
"
