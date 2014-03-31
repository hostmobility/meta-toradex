#create the deployment directory-tree

BURNFLASH := "${THISDIR}/files/${MACHINE}/burnflash.tar.bz2"

S = "${WORKDIR}/burnflash"

IMAGE_ROOTFS = "${WORKDIR}/${IMAGE_NAME}${PV}/rootfs"
U_BOOT_EXT = "bin"
U_BOOT_EXT_colibri-vf = "imx"

do_rootfs_prepend() {
    tar -C ${WORKDIR} -xf ${BURNFLASH} 
    mkdir -p ${IMAGE_ROOTFS}
    cp -pPr ${S}/* ${IMAGE_ROOTFS}/../ 
}

#we need some native tools for deployment
DEPENDS_colibri-vf = "mtd-utils-native imx-loader-native"

do_rootfs_append_colibri-vf() {
    IMAGE_ROOT="${IMAGE_ROOTFS}/../vf_flash/"
    rm -f ${IMAGE_ROOT}/mkfs.ubifs  ${IMAGE_ROOT}/imx_uart
    mkdir -p ${IMAGE_ROOT}

    # add the mkfs.ubifs binary
    cp ${STAGING_DIR_NATIVE}/usr/sbin/mkfs.ubifs ${IMAGE_ROOT}/
    ${BUILD_STRIP} ${IMAGE_ROOT}/mkfs.ubifs

    # add imx-loader and its configuration files
    cp ${STAGING_BINDIR_NATIVE}/imx_uart ${IMAGE_ROOT}/
    ${BUILD_STRIP} ${IMAGE_ROOT}/imx_uart*
    cp ${STAGING_ETCDIR_NATIVE}/imx-loader.d/* ${IMAGE_ROOT}/
}

do_rootfs_append() {
    # put u-boot, kernel into the bin directories, remove the kernel from the rootfs/boot
    rm ${IMAGE_ROOTFS}/boot/uImage*
    cp -pP ${DEPLOY_DIR_IMAGE}/uImage* ${IMAGE_ROOTFS}/../${MACHINE}_bin/
    mv ${IMAGE_ROOTFS}/../${MACHINE}_bin/uImage-${MACHINE}.bin ${IMAGE_ROOTFS}/../${MACHINE}_bin/uImage

    cp -pP ${DEPLOY_DIR_IMAGE}/u-boot* ${IMAGE_ROOTFS}/../${MACHINE}_bin/
    rm -f ${IMAGE_ROOTFS}/../${MACHINE}_bin/u-boot-hsmmc-${MACHINE}.${U_BOOT_EXT}
    mv ${IMAGE_ROOTFS}/../${MACHINE}_bin/u-boot-${MACHINE}.${U_BOOT_EXT} ${IMAGE_ROOTFS}/../${MACHINE}_bin/u-boot.${U_BOOT_EXT}

    # add the rootfs version to the welcome banner
    echo "${IMAGE_NAME}${PV}_${DATE}" >> ${IMAGE_ROOTFS}/etc/issue
    echo "" >> ${IMAGE_ROOTFS}/etc/issue

    # remove intermediate file rootfs.lock
    rm ${WORKDIR}/${IMAGE_NAME}${PV}/rootfs.lock

    #create tarball
    DATE=`date +%Y%m%d`
    cd ${WORKDIR}; tar -cjvf ${DEPLOY_DIR_IMAGE}/${IMAGE_NAME}${PV}_${DATE}.tar.bz2 ${IMAGE_NAME}${PV}
}
