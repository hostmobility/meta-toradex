PRINC := "${@int(PRINC) + 1}"

# look for files in the layer first
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

#don't do reverse DNS lookups, this leads to long delays as our 
#embedded targets  arn't usually in an environment where there 
#is a reachable DNS server
SRC_URI += "file://remove-reverse-dns.patch"

# Packport from http://patches.openembedded.org/patch/48957
# workaround different libexecdir definition in oe; it does not fix
# the multilib issues but mades sftp work again when dropbear and
# openssh-sftp-server are for the same architecture.
_sftp_server_path = "${@\
    ['${libexecdir}/sftp-server','${libdir}/openssh/sftp-server']\
    [d.getVar('libexecdir', True).endswith(d.expand('/${BPN}'))]}"
CFLAGS += "-DSFTPSERVER_PATH=\\"${_sftp_server_path}\\""
