PRINC = "2"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI += " \
	file://missingAM_GLIB_GNU_GETTEXT.patch \
	file://configfiles.patch \
	file://fix_event_check_bug_caused_cpu_100.patch \
"

#issues during V2.0alpha development, might be removed later
INITSCRIPT_PARAMS_colibri-t20  = "start 98 5 2 . stop 20 0 1 6 ."


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
