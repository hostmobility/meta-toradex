#! /bin/sh
### BEGIN INIT INFO
# Provides:          lxdm
# Default-Start:     2 3 4 5
# Default-Stop:      0 1 6
# Short-Description: LXDM Display Manager (and wrapper script)
# Description:       init script for the LXDM Display Manager
# Copied from SLIM openembedded recipe
### END INIT INFO

case $1 in
	start)
        	/usr/sbin/lxdm -d &> /dev/null
                ;;
	stop)
        	killall /usr/sbin/lxdm
                ;;
        restart)
	        $0 stop
                sleep 2
                $0 start
                ;;
        *)
        	echo "usage: $0 [start|stop|restart]"
                ;;
esac

# End of file

