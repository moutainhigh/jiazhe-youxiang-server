#!/bin/bash

APP_DIR=/opt/jiazhe/webserver
APP_NAME=youxiang-server
JAR_FILE=$APP_NAME.jar
pid=0

start(){
  checkpid
  if [ ! -n "$pid" ]; then
    echo "$APP_NAME start..."
    JAVA_CMD="nohup java -jar $APP_DIR/$APP_NAME.jar > /dev/null 2>&1 &"
    echo $JAVA_CMD
    su - root -c "$JAVA_CMD"
    echo Start Success!
  else
      echo "$APP_NAME is runing PID: $pid"
  fi

}


status(){
   checkpid
   if [ ! -n "$pid" ]; then
     echo "$APP_NAME not runing"
   else
     echo "$APP_NAME runing PID: $pid"
   fi
}

checkpid(){
    echo "$APP_NAME checkpid..."
    pid=`ps -ef |grep $JAR_FILE |grep -v grep |awk '{print $2}'`
    echo "$APP_NAME pid=$pid"
}

stop(){
    checkpid
    if [ ! -n "$pid" ]; then
     echo "$APP_NAME not runing"
    else
      echo "$APP_NAME stop..."
      kill -9 $pid
    fi
}

restart(){
    stop
    sleep 1s
    start
}

case $1 in
          start) start;;
          stop)  stop;;
          restart)  restart;;
          status)  status;;
              *)  echo "require start|stop|restart|status"  ;;
esac