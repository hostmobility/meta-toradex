PRINC = "1"
WALLPAPER-MACHINE = "toradex-neutral.png"
WALLPAPER-MACHINE_colibri-t20 = "toradex-t20.png"
WALLPAPER-MACHINE_colibri-t30 = "toradex-t30.png"

THISDIR := "${@os.path.dirname(bb.data.getVar('FILE', d, True))}"
FILESPATH =. "${@base_set_filespath(["${THISDIR}/${PN}-${PV}"], d)}:"

SRC_URI += " \
	file://autostart.patch \
	file://consistent_defconfig_dirs.patch \
	file://toradex-neutral.png \
	file://${WALLPAPER-MACHINE} \
	file://wallpaper.patch \
	file://desktop.conf \
	file://defaults.list \
	file://hdmiaudio.sh \
"

do_install_append () {
    install -m 0755 -d ${D}/${datadir}/lxde/wallpapers
    install -m 0644 ${WORKDIR}/toradex-*.png  ${D}/${datadir}/lxde/wallpapers/
    ln -sf ${WALLPAPER-MACHINE} ${D}/${datadir}/lxde/wallpapers/toradex.png
    rm  ${D}/etc/xdg/lxsession/LXDE/desktop.conf
    install -m 0644 ${WORKDIR}/desktop.conf ${D}/etc/xdg/lxsession/LXDE/
    install -m 0755 -d ${D}/${datadir}/applications/
    install -m 0644 ${WORKDIR}/defaults.list  ${D}/${datadir}/applications/
    install -m 0755 -d ${D}/${bindir}
    install -m 0755 ${WORKDIR}/hdmiaudio.sh  ${D}/${bindir}/
}
