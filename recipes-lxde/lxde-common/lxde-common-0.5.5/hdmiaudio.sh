#! /bin/sh
#LXDM starts X in such a way that hdmi audio is not functional. Changing the Virtual Console corrects this. Is called by lxsessions autostart
chvt 1 2>&1 > /dev/null
chvt 7 2>&1 > /dev/null
