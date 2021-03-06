DESCRIPTION = "binary files from Nvidia along with there configuration"
LICENSE = "CLOSED SGI Khronos"
PR = "r17"

PACKAGE_ARCH = "${MACHINE_ARCH}"
COMPATIBLE_MACHINE = "(tegra)"

PROVIDES += "virtual/egl virtual/libgles1 virtual/libgles2"
DEPENDS = "virtual/xserver"

LIC_DIR = "${datadir}/common-licenses"
#gstnvvidconf.tar.bz2 will hopefully be integrated into the public L4T packages

# the khronos headers are taken from here: https://www.khronos.org/registry/khronos_headers.tgz
# this tarball changes from time to time breaking the receipe, thus it is provided with the recipe
SRC_COMMON =  " \
    file://nvgstplayer.desktop \
    file://aplay.desktop \
    file://mimeapps.list \
    file://khronos_headers.tgz \
    https://www.khronos.org/registry/omxil/api/1.1.2/OpenMAX_IL_1_1_2_Header.zip;name=openmax-h;unpack=no \
    file://egl.pc \
    file://gles.pc \
    file://glesv2.pc \
"
SRC_URI_tegra2 =  " \
    file://ventana_Tegra-Linux-codecs-R16.4.0_armhf.tbz2 \
    file://ventana_Tegra-Linux-R16.4.0_armhf.tbz2 \
    ${SRC_COMMON} \
"

SRC_URI_tegra3 =  " \
    file://cardhu_Tegra-Linux-codecs-R16.4.0_armhf.tbz2 \
    file://cardhu_Tegra-Linux-R16.4.0_armhf.tbz2 \
    ${SRC_COMMON} \
"

SRC_URI[openmax-h.md5sum] = "402a7600397c341895cfecbbe1f4e028"
SRC_URI[openmax-h.sha256sum] = "e7159c88185df60a0dbb28583815067c31285a1f1d4652f6752a7329ccb534d1"
#    https://www.khronos.org/registry/omxil/api/1.2.0/OpenMAX_IL_1_2.0_Header.zip;name=openmax-h;unpack=no \
#SRC_URI[openmax-h.md5sum] = "a328b82e29d1e2abc1f20f070b9041a9"
#SRC_URI[openmax-h.sha256sum] = "9a121921450497e5373abcda000daf52af2ee31097d59c0d299a522b66936fa7"

# xserver-xorg driver ABI version to be used by the symlink, must match the required ABI version from the used xserver
XSERVER_DRIVER_ABI_REQUIRED = "14"

LIC_FILES_CHKSUM = "file://../khronos_headers/GLES2/gl2.h;beginline=8;endline=29;md5=c0e8cc16602f8077310fb00bbf128ef6"

PACKAGES = "${PN}-dbg ${PN}-restricted-codecs ${PN}-nv-gstapps ${PN} ${PN}-dev"

# Inhibit warnings about files being stripped.
INHIBIT_PACKAGE_DEBUG_SPLIT = "1"

FILES_${PN}-dbg += " \
    /usr/lib/gstreamer-0.10/.debug \
"
FILES_${PN} += " \
    ${sysconfdir}/X11/def* \
    ${sysconfdir}/X11/xorg.conf.* \
    ${sysconfdir}/init/* \
    ${sysconfdir}/udev/rules.d/* \
    ${sysconfdir}/init/nv* \
    ${sysconfdir}/init/wpa* \	
    /lib/firmware/* \
    ${LIC_DIR}/${PN}/* \
    /usr/lib/lib* \
    /usr/lib/xorg/* \
    /usr/lib/gstreamer*/* \
    /home/root/.local/share/applications/* \
"
FILES_${PN}-restricted-codecs += " \
    /lib/firmware/*.axf \
    ${LIC_DIR}/${PN}-restricted-codecs/* \
"
FILES_${PN}-nv-gstapps += " \
    /usr/bin/* \
    /usr/share/doc/nv_gstapps/* \
    ${LIC_DIR}/${PN}-nv-gstapps/* \
"

#no gnu_hash in NVIDIA binaries, skip QA dev-so for this package
#we have symlinks ending in .so, skip QA ldflags for this package
INSANE_SKIP_${PN} = "dev-so ldflags"
INSANE_SKIP_${PN}-nv-gstapps = "dev-so ldflags"

do_patch () {
    mkdir -p OpenMAX/il
    unzip -d OpenMAX/il OpenMAX_IL_1_1_2_Header.zip
}

do_compile () {
    #unpack the different packages
    #nvidia drivers
    mkdir -p nvidia_drivers${LIC_DIR}/${PN}/nvidia_drivers
    tar -C nvidia_drivers -xjf ${WORKDIR}/Linux_for_Tegra/nv_tegra/nvidia_drivers.tbz2
    tar -C nvidia_drivers -xjf ${WORKDIR}/Linux_for_Tegra/nv_tegra/config.tbz2
    cp ${WORKDIR}/Linux_for_Tegra/nv_tegra/LICENSE nvidia_drivers${LIC_DIR}/${PN}/nvidia_drivers/

    #nvidia sample gstreamer apps
    mkdir -p nvgstapps${LIC_DIR}/${PN}-nv-gstapps
    mkdir -p nvgstapps/usr/share/doc/nv_gstapps
    tar -C nvgstapps -xjf ${WORKDIR}/Linux_for_Tegra/nv_tegra/nv_sample_apps/nvgstapps.tbz2
    cp ${WORKDIR}/Linux_for_Tegra/nv_tegra/nv_sample_apps/LICENSE* nvgstapps${LIC_DIR}/${PN}-nv-gstapps/
    cp ${WORKDIR}/Linux_for_Tegra/nv_tegra/nv_sample_apps/nv*.txt nvgstapps/usr/share/doc/nv_gstapps/

    #restricted codecs
    mkdir -p restricted_codecs${LIC_DIR}/${PN}-restricted-codecs/
    tar -C restricted_codecs -xjf ${WORKDIR}/restricted_codecs.tbz2
    cp ${WORKDIR}/*.txt restricted_codecs${LIC_DIR}/${PN}-restricted-codecs/
}

do_install () {
    #nvidia_driver
    install -d ${D}/usr/lib/xorg/modules/drivers ${D}/home/root/.local/share/applications/
    install -d ${D}${LIC_DIR}/${PN}/nvidia_drivers ${D}/lib/firmware/
    install -d ${D}/${sysconfdir}/X11 ${D}/${sysconfdir}/init ${D}/${sysconfdir}/udev/rules.d
    install -m 0644 nvidia_drivers/${sysconfdir}/X11/xorg.conf ${D}/${sysconfdir}/X11/xorg.conf.nvidia
    install -m 0755 nvidia_drivers/${sysconfdir}/init/* ${D}/${sysconfdir}/init/
    install -m 0644 nvidia_drivers/${sysconfdir}/udev/rules.d/* ${D}/${sysconfdir}/udev/rules.d/
    install -m 0644 nvidia_drivers/${sysconfdir}/nv* ${D}/${sysconfdir}/
    install -m 0644 nvidia_drivers/${sysconfdir}/wpa_supplicant.conf ${D}/${sysconfdir}/wpa_supplicant.conf.nvidia
    install -m 0644 nvidia_drivers/lib/firmware/* ${D}/lib/firmware/
    install -m 0644 nvidia_drivers${LIC_DIR}/${PN}/nvidia_drivers/* ${D}${LIC_DIR}/${PN}/nvidia_drivers/
    install -m 0644 nvidia_drivers/usr/lib/*.so ${D}/usr/lib/
    install -m 0644 nvidia_drivers/usr/lib/*.so.? ${D}/usr/lib/
    rm ${D}/usr/lib/libjpeg.so
    install -m 0644 nvidia_drivers/usr/lib/xorg/modules/drivers/* ${D}/usr/lib/xorg/modules/drivers/
    ln -s tegra_drv.abi${XSERVER_DRIVER_ABI_REQUIRED}.so ${D}/usr/lib/xorg/modules/drivers/tegra_drv.so
    # create symlink to the shared libs for development, *.so -> *.so.x
    export LIBNAME=`ls ${D}/usr/lib/libGLESv2.so.?`
    export LIBNAME=`basename $LIBNAME`
    ln -s $LIBNAME ${D}/usr/lib/libGLESv2.so
    export LIBNAME=`ls ${D}/usr/lib/libEGL.so.?`
    export LIBNAME=`basename $LIBNAME`
    ln -s $LIBNAME ${D}/usr/lib/libEGL.so
    export LIBNAME=`ls ${D}/usr/lib/libGLESv1_CM.so.?`
    export LIBNAME=`basename $LIBNAME`
    ln -s $LIBNAME ${D}/usr/lib/libGLESv1_CM.so

    #nvidia sample gstreamer apps
    install -d ${D}/usr/bin ${D}/usr/lib/gstreamer-0.10 ${D}/usr/share/doc/nv_gstapps
    install -d ${D}/usr/lib/xorg/modules/drivers ${D}/home/root/.local/share/applications/
    install -d ${D}${LIC_DIR}/${PN}-nv-gstapps/
    install -m 0755 nvgstapps/usr/bin/* ${D}/usr/bin/
    install -m 0644 nvgstapps/usr/lib/gstreamer-0.10/*.so ${D}/usr/lib/gstreamer-0.10/
    install -m 0644 nvgstapps/usr/share/doc/nv_gstapps/* ${D}/usr/share/doc/nv_gstapps/
    install -m 0644 nvgstapps${LIC_DIR}/${PN}-nv-gstapps/* ${D}${LIC_DIR}/${PN}-nv-gstapps/
    install -m 0644 ${WORKDIR}/*.desktop ${D}/home/root/.local/share/applications/
    install -m 0644 ${WORKDIR}/mimeapps.list ${D}//home/root/.local/share/applications/
    ln -s libpcre.so.1 ${D}/usr/lib/libpcre.so.3
    ln -s libpcreposix.so.0 ${D}/usr/lib/libpcreposix.so.3

    #nvidia restricted codecs
    install -d ${D}${LIC_DIR}/${PN}-restricted-codecs ${D}/lib/firmware/
    install -m 0644 restricted_codecs/lib/firmware/* ${D}/lib/firmware/
    install -m 0644 restricted_codecs${LIC_DIR}/${PN}-restricted-codecs/* ${D}${LIC_DIR}/${PN}-restricted-codecs/

    #khronos headers for EGL/GLES/GLES2/OpenMax
    for dir in EGL GLES GLES2 KD KHR
    do
        install -d ${D}${includedir}/$dir 
        install -m 0644 ${WORKDIR}/khronos_headers/$dir/* ${D}${includedir}/$dir
    done
    dir="OpenMAX/il"
    install -d ${D}${includedir}/$dir
    install -m 0644 ${WORKDIR}/$dir/* ${D}${includedir}/$dir

    install -d  ${D}/usr/lib/pkgconfig
    install -m 0644 ${WORKDIR}/*.pc ${D}/usr/lib/pkgconfig/
}

# Add the ABI dependency at package generation time, as otherwise bitbake will
# attempt to find a provider for it (and fail) when it does the parse.
python populate_packages_prepend() {
    pn = d.getVar("PN", True)
    d.appendVar("RDEPENDS_" + pn, " xorg-abi-video-${XSERVER_DRIVER_ABI_REQUIRED}")
}
