PRINC = "1"

# workaround, meta-openembedded tries to install an unneeded but unavailable .service file
# so provide one during install and later on delete it again
do_install_prepend () {
    install -d ${S}/systemd/
    ln -s /dev/null ${S}/systemd/dummy.service
}

do_install_append () {
    rm -f ${D}${systemd_unitdir}/system/dummy.service
}
