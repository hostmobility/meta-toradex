#create the deployment directory-tree

BURNFLASH := "${THISDIR}/files/${MACHINE}/burnflash.tar.bz2"
S = "${WORKDIR}/burnflash"
IMAGE_NAME_colibri-t20 = "T20_LinuxImage"
IMAGE_NAME_colibri-t30 = "T30_LinuxImage"
IMAGE_NAME_colibri-pxa = "PXA_LinuxImage"

IMAGE_ROOTFS = "${WORKDIR}/${IMAGE_NAME}${PV}/rootfs"

do_rootfs_prepend() {
    tar -C ${WORKDIR} -xf ${BURNFLASH} 
    mkdir -p ${IMAGE_ROOTFS}
    cp -pPr ${S}/* ${IMAGE_ROOTFS}/../ 
}

do_rootfs_append() {
    # put u-boot, kernel into the bin directories, remove the kernel from the rootfs/boot
    rm ${IMAGE_ROOTFS}/boot/uImage*
    cp -pP ${DEPLOY_DIR_IMAGE}/uImage* ${IMAGE_ROOTFS}/../bin/
    mv ${IMAGE_ROOTFS}/../bin/uImage-${MACHINE}.bin ${IMAGE_ROOTFS}/../bin/uImage

    cp -pP ${DEPLOY_DIR_IMAGE}/u-boot* ${IMAGE_ROOTFS}/../bin/
    rm -f ${IMAGE_ROOTFS}/../bin/u-boot-hsmmc-${MACHINE}.bin
    mv ${IMAGE_ROOTFS}/../bin/u-boot-${MACHINE}.bin ${IMAGE_ROOTFS}/../bin/u-boot.bin

    # add the rootfs version to the welcome banner
    echo "${IMAGE_NAME}${PV}_${DATE}" >> ${IMAGE_ROOTFS}/etc/issue
    echo "" >> ${IMAGE_ROOTFS}/etc/issue

    #create tarball
    DATE=`date +%Y%m%d`
    cd ${WORKDIR}; tar -cjvf ${DEPLOY_DIR_IMAGE}/${IMAGE_NAME}${PV}_${DATE}.tar.bz2 ${IMAGE_NAME}${PV}
}
