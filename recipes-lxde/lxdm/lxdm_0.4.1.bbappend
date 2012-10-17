PRINC = "4"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI += " \
    file://configfiles.patch \
    file://lxdm.service \
"

pkg_postinst_${PN}() {
  # Register up as default dm
  mkdir -p ${sysconfdir}/X11/
  echo "${sbindir}/lxdm" > ${sysconfdir}/X11/default-display-manager
  # the lxdm script in init.d includes the script functions, make an empty one if this does not yet exist
  touch /etc/init.d/functions
}
 
pkg_postrm_${PN} () {
  sed -i /lxdm/d ${sysconfdir}/X11/default-display-manager || true
}

do_install_append () {
    install -d ${D}/${sysconfdir}/systemd/system
    install -m 0644 ${WORKDIR}/lxdm.service ${D}/${sysconfdir}/systemd/system
    ln -s lxdm.service ${D}/${sysconfdir}/systemd/system/display-manager.service
}
