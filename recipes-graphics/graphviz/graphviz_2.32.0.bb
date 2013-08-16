SECTION = "graphical"
DESCRIPTION = "Graphviz - Graph Visualization Software"
HOMEPAGE = "http://www.graphviz.org"
LICENSE = "EPL-1.0"
DEPENDS = "cairo pango expat"
RDEPENDS_${PN} = "cairo pango expat"

SRC_URI = "http://www.graphviz.org/pub/graphviz/stable/SOURCES/graphviz-${PV}.tar.gz \
    file://cross_compile.patch"
LIC_FILES_CHKSUM = "file://COPYING;md5=9109f5fc16cf963fb3cdd32781b3ce04"
SRC_URI[md5sum] = "deda3933da701e2cc9ad968249a0b096"
SRC_URI[sha256sum] = "d853b2313e08a1dd0cce20d9ff8051acfa9ec418b5ba2dc65c8f4ddb07a7a77a"

PR = "r1"

FILES_${PN}-dev += " \
    /usr/lib/graphviz/*.so \
"

inherit autotools
