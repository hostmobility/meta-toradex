DESCRIPTION = "binary netscape plugins for flash support"
LICENSE = "CLOSED"
#licensing required"

PR = "r1"

SRC_URI =  "file://bin-files.tar.bz2 \
            "

PACKAGES = "${PN}"

FILES_${PN} += " \
	/usr/lib/* \
	"

PACKAGE_ARCH = "${MACHINE_ARCH}"

#no gnu_hash in NVIDIA binaries, skip QA for this package
INSANE_SKIP_${PN} = "True"
#we have symlinks ending in .so , remove "dev-so", refere to ./openembedded-core/meta/classes/insane.bbclass
ERROR_QA = "arch la2 pkgconfig la perms"

do_install () {
	install -d ${D} ${D}/usr/lib/firefox-3.6.8/plugins
	install -m 0755 ${WORKDIR}/*.so ${D}/usr/lib/firefox-3.6.8/plugins/
}
