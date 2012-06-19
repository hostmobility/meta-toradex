DESCRIPTION = "binary netscape plugins for flash support"
LICENSE = "CLOSED"
#licensing required"

PR = "r1"

SRC_URI =  "file://bin-files.tar.bz2 \
            "
FILES_${PN} += " \
	/usr/lib/mozilla/plugins/*.so \
	"

PACKAGE_ARCH = "${MACHINE_ARCH}"

#no gnu_hash in NVIDIA binaries, skip QA dev-so for this package
#we have symlinks ending in .so, skip QA ldflags for this package
INSANE_SKIP_${PN} = "dev-so ldflags"

do_install () {
	install -d ${D}/usr/lib/mozilla/plugins
	install -m 0644 ${WORKDIR}/*.so ${D}/usr/lib/mozilla/plugins/
}
