PRINC = "8"
WALLPAPER-MACHINE = "Wallpaper_Toradex.png"
WALLPAPER-MACHINE_colibri-t20 = "Wallpaper_ColibriT20.png"
WALLPAPER-MACHINE_colibri-t30 = "Wallpaper_ColibriT30.png"
WALLPAPER-MACHINE_apalis-t30 = "Wallpaper_ApalisT30.png"
WALLPAPER-MACHINE_colibri-vf50 = "Wallpaper_ColibriVF50.png"
WALLPAPER-MACHINE_apalis-vf61 = "Wallpaper_ColibriVF61.png"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-${PV}:"

PACKAGE_ARCH = "${MACHINE_ARCH}"

SRC_URI += " \
	file://autostart.patch \
	file://consistent_defconfig_dirs.patch \
	file://Wallpaper_Toradex.png \
	file://${WALLPAPER-MACHINE} \
	file://wallpaper.patch \
	file://desktop.conf \
	file://defaults.list \
	file://hdmiaudio.sh \
	file://panel-buttons.patch \
"

do_install_append () {
    install -m 0755 -d ${D}/${datadir}/lxde/wallpapers
    install -m 0644 ${WORKDIR}/Wallpaper*.png  ${D}/${datadir}/lxde/wallpapers/
    ln -sf ${WALLPAPER-MACHINE} ${D}/${datadir}/lxde/wallpapers/toradex.png
    rm  ${D}/etc/xdg/lxsession/LXDE/desktop.conf
    install -m 0644 ${WORKDIR}/desktop.conf ${D}/etc/xdg/lxsession/LXDE/
    install -m 0755 -d ${D}/${datadir}/applications/
    install -m 0644 ${WORKDIR}/defaults.list  ${D}/${datadir}/applications/
    install -m 0755 -d ${D}/${bindir}
    install -m 0755 ${WORKDIR}/hdmiaudio.sh  ${D}/${bindir}/
}
