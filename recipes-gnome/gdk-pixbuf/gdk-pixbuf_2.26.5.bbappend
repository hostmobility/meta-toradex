PRINC = "1"
# build for x11, at least lxpanel needs this
DEPENDS += "virtual/libx11"

EXTRA_OECONF += "--with-x11"
