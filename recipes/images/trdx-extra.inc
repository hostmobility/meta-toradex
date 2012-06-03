#additional packages to include in our Toradex images

#the following builds cross gdb
DEPENDS += "gdb-cross"

#the gdb recipe provides gdbserver
DEPENDS += "gdb"

IMAGE_INSTALL_MACHINE_colibri-t20 += " \
	trdx-odm \
"
IMAGE_INSTALL_MACHINE += " \
"

IMAGE-INSTALL += " \
	alsa-utils \
	alsa-utils-aplay \
	alsa-utils-amixer \
	\
	\
	aspell \
	consolekit \
	sqlite3 \
	\
	curl \
	ntp \
	nfs-utils-client \
	openssh-scp \
	openssh-sftp \
	pam-plugin-ck-connector \
	\
	bzip2 \
	gdbserver \
	grep \
	joe \
	ldd \
	mtd-utils \
	update-modules \
	\
	hdparm \
	iperf \
	memtester \
	stress \
	lmbench \
	nbench-byte \
	\
	trdx-nv-binaries \
	${IMAGE_INSTALL_MACHINE} \
	trdx-config \
	\
	trdx-oak-sensors \
"
#	tegrastats \
#	gpioconfig \
#	openssl-conf \
#	ntpclient \
