PRINC = "1"
# build for x11, at least lxpanel needs this
DEPENDS += "virtual/libx11"

EXTRA_OECONF += "--with-x11"

# do the timeconsuming gdk-pixbuf-query-loaders on the build machine
# backport from http://cgit.openembedded.org/openembedded-core/commit/meta/recipes-gnome/gdk-pixbuf?id=5ba420bc0e282a1cbf000ce32034ad7ab15c01d5
postinst_pixbufloader () {
if [ "x$D" != "x" ]; then
# Update the target's pixbuf loader's cache. Since the native binary will
# throw an error if the shared objects do not belong to the same ELF class,
# we trick the gdk-pixbuf-query-loaders into scanning the native shared
# objects and then we remove the NATIVE_ROOT prefix from the paths in
# loaders.cache.
gdk-pixbuf-query-loaders $(find $D/${libdir}/gdk-pixbuf-2.0/${LIBV}/loaders \
        -name *.so | sed -e "s:$D:$NATIVE_ROOT:g") > \
        $D/${libdir}/gdk-pixbuf-2.0/${LIBV}/loaders.cache || exit 1

sed -i -e "s:$NATIVE_ROOT:/:g" $D/${libdir}/gdk-pixbuf-2.0/${LIBV}/loaders.cache
rm -f $D/${libdir}/gdk-pixbuf-2.0/${LIBV}/sed*
exit 0
fi

# Update the pixbuf loaders in case they haven't been registered yet
GDK_PIXBUF_MODULEDIR=${libdir}/gdk-pixbuf-2.0/${LIBV}/loaders gdk-pixbuf-query-loaders --update-cache

if [ -x ${bindir}/gtk-update-icon-cache ] && [ -d ${datadir}/icons ]; then
    for icondir in /usr/share/icons/*; do
        if [ -d ${icondir} ]; then
            gtk-update-icon-cache -t -q ${icondir}
        fi
    done
fi
}
