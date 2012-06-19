#additional packages to include in our Toradex images

#the following builds cross gdb and gdbserver
DEPENDS += "gdb-cross"

IMAGE_INSTALL_MACHINE_colibri-t20 = "trdx-odm"

IMAGE_INSTALL_MACHINE = ""

IMAGE_INSTALL += " \
	alsa-utils \
	alsa-utils-aplay \
	alsa-utils-amixer \
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
	gpioconfig \
	tegrastats \
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
#	openssl-conf 
#	ntpclient 
