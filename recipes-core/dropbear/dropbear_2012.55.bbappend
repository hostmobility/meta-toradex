PRINC := "${@int(PRINC) + 1}"

# look for files in the layer first
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

#don't do reverse DNS lookups, this leads to long delays as our 
#embedded targets  arn't usually in an environment where there 
#is a reachable DNS server
SRC_URI += "file://remove-reverse-dns.patch"
