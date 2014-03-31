#additional packages to include in our Toradex images

# Copy Licenses to image /usr/share/common-licenses, on tegra with individual licenses
COPY_LIC_MANIFEST = "1"
COPY_LIC_DIRS_tegra = "1"

#build some ipk which are needed together with CAN, but do not yet install them
DEPENDS += "canutils libsocketcan iproute2"

#the following builds cross gdb and gdbserver
DEPENDS += "gdb-cross"

# angstrom and oe-core disagree on the format
rootfs_update_timestamp_classic () {
	date -u +%2m%2d%2H%2M%4Y >${IMAGE_ROOTFS}/etc/timestamp
}
#create the file /etc/timestamp
IMAGE_PREPROCESS_COMMAND = "rootfs_update_timestamp_classic"

IMAGE_INSTALL_T20_T30 = " \
    gpioconfig \
    tegrastats \
    trdx-nv-binaries \
    trdx-nv-binaries-nv-gstapps \
    trdx-nv-binaries-restricted-codecs \
    usb-rndis-systemd \
    hdmi-hotplug \
    \
    curl \
    file \
    aspell \
"

IMAGE_INSTALL_MACHINE_colibri-t20 = " \
    ${IMAGE_INSTALL_T20_T30} \
    u-boot-fw-utils \
    kernel-module-fusion-f0710a \
"

IMAGE_INSTALL_MACHINE_colibri-t30 = " \
    ${IMAGE_INSTALL_T20_T30} \
    fs-init \
    util-linux-fstrim \
    kernel-module-fusion-f0710a \
"

IMAGE_INSTALL_MACHINE_apalis-t30 = " \
    ${IMAGE_INSTALL_T20_T30} \
    pciutils \
    ptpd \
    iproute2 \
    canutils \
    lvds-ctrl \
    fs-init \
    util-linux-fstrim \
    kernel-module-adv7180 \
    kernel-module-max9526 \
    kernel-module-ov7670soc \
    kernel-module-tvp5150soc \
    kernel-module-as0260soc \
    kernel-module-tegra-v4l2-camera \
    kernel-module-videobuf2-dma-nvmap \
    kernel-module-videobuf2-memops \
    kernel-module-fusion-f0710a \
"

IMAGE_INSTALL_MACHINE_colibri-vf = " \
    gpioconfig \
    ptpd \
    iproute2 \
    canutils \
    mtd-utils-ubifs \
    mqxboot \
    libmcc \
    kernel-module-fusion-f0710a \
    kernel-module-mcc \
    kernel-module-snd-ac97-codec \
    kernel-module-snd-soc-mvf \
    kernel-module-mvf-sai-ac97 \
    kernel-module-snd-soc-wm9712 \
"

IMAGE_INSTALL_MACHINE = ""

IMAGE_INSTALL += " \
    max9526-i2c \
    linux-firmware-ralink \
    \
    alsa-utils \
    alsa-utils-aplay \
    alsa-utils-amixer \
    \
    consolekit \
    sqlite3 \
    \
    curl \
    ntp \
    nfs-utils-client \
    openssh-scp \
    openssh-sftp \
    \
    bzip2 \
    gdbserver \
    grep \
    joe \
    minicom \
    ldd \
    lsof \
    mtd-utils \
    udisks \
    dosfstools \
    \
    devmem2 \
    evtest \
    hdparm \
    iperf \
    i2c-tools \
    memtester \
    stress \
    lmbench \
    nbench-byte \
    ethtool \
    \
    ${IMAGE_INSTALL_MACHINE} \
    \
    trdx-oak-sensors \
"
#    openssl-conf 
#    ntpclient 

do_rootfs_prepend () {
    export NATIVE_ROOT=${STAGING_DIR_NATIVE}
}
