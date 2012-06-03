DESCRIPTION = "binary files"
LICENSE = "CLOSED"
PR = "r1"

PACKAGE_ARCH = "${MACHINE_ARCH}"

SRC_URI =  " \
	file://libnvodm_disp.so.vgaonly \
	file://libnvodm_disp.so.allmodes \
	file://libnvodm_dtvtuner.so \
	file://libnvodm_hdmi.so \
	file://libnvodm_imager.so \
	file://libnvodm_misc.so \
	file://libnvodm_query.so \
            "

PACKAGES = "${PN}"

FILES_${PN} += "/usr/lib/* "

#no gnu_hash in NVIDIA binaries, skip QA for this package
INSANE_SKIP_${PN} = "True"
#we have symlinks ending in .so , remove "dev-so", refere to ./openembedded-core/meta/classes/insane.bbclass
ERROR_QA = "debug-deps dev-deps debug-files arch la2 pkgconfig la perms"

PACKAGE_ARCH = "${MACHINE_ARCH}"

do_install () {
	install -d ${D}/usr/lib
	install -m 0755 ${WORKDIR}/lib* ${D}/usr/lib/
	cd  ${D}/usr/lib/ ; ln -s libnvodm_disp.so.vgaonly libnvodm_disp.so
}
