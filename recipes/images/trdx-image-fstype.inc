#create the deployment directory-tree

BURNFLASH := "${THISDIR}/files/burnflash.tar.bz2"
S = "${WORKDIR}/burnflash"
IMAGE_ROOTFS = "${WORKDIR}/T20_LinuxImage${PV}/rootfs"

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
    rm ${IMAGE_ROOTFS}/../bin/u-boot-hsmmc-${MACHINE}.bin
    mv ${IMAGE_ROOTFS}/../bin/u-boot-${MACHINE}.bin ${IMAGE_ROOTFS}/../bin/u-boot.bin

    #create tarball
    DATE=`date +%Y%m%d`
    cd ${WORKDIR}; tar -cjvf ${DEPLOY_DIR_IMAGE}/T20_LinuxImage${PV}_${DATE}.tar.bz2 T20_LinuxImage${PV}
}
