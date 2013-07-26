FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

PRINC := "${@int(PRINC) + 2}"

inherit systemd

SRC_URI += "file://xinput-calibrator.service"

SYSTEMD_SERVICE = "${PN}.service"

# menu entry should make the calibration permanent instead of printing cal data into a terminal
do_install_append() {
    install -m 0755 ${D}/usr/share/applications/xinput_calibrator.desktop ${D}/usr/share/applications/xinput_calibrator.desktop.old
    sed -i -e 's/xinput_calibrator; cat/rm -f \/etc\/pointercal.xinput; xinput_calibrator_once.sh/' ${D}/usr/share/applications/xinput_calibrator.desktop

    install -d ${D}${systemd_unitdir}/system/
    install -m 0644 ${WORKDIR}/${PN}.service ${D}${systemd_unitdir}/system
}
