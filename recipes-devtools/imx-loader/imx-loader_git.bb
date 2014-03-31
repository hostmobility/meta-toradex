DESCRIPTION = "USB/UART loader for i.MX51/53/6x and Vybrid"
SECTION = "base"
HOMEPAGE = "https://github.com/boundarydevices/imx_usb_loader"
LICENSE = "LGPLv2+"
LIC_FILES_CHKSUM = "file://COPYING;md5=d32239bcb673463ab874e80d47fae504"

DEPENDS = "libusb1-native"

SRCREV = "fd7be57412dfa175646888ea681a2625003f03a6"
SRC_URI = "git://github.com/toradex/imx_loader.git;protocol=git;branch=uart_loader"

S = "${WORKDIR}/git/"

PR = "r1"

#we want mkfs.ubifs binary to run on a 32 bit architecture, on x86-64 this requires the 32 bit compatibility libs
#EXTRA_OEMAKE_class-native = "'CC=${CC}' 'RANLIB=${RANLIB}' 'AR=${AR}' 'CFLAGS=${CFLAGS} -I${S}/include -DWITHOUT_XATTR -m32' 'BUILDDIR=${S}'"

BBCLASSEXTEND = "native"

do_install () {
    oe_runmake DESTDIR=${D} install
}
