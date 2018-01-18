#!/usr/bin/env bash
echo "--------StrateManager is started------->>"
export JAVA_HOME=/home/hadoop/jdk1.8.0_152
export JRE_HOME=$JAVA_HOME/jre
export CLASSPATH=.:$JAVA_HOME/lib:$JRE_HOME/lib
export PATH=$PATH:$JAVA_HOME/bin:$JRE_HOME/bin

echo "start"

basepath=$(cd `dirname $0`; pwd)
cd $basepath
cd ../

PWD=`pwd`
for i in lib/*;
    do CLASSPATH=$CLASSPATH:$PWD/$i
done

export CLASSPATH=$CLASSPATH:$PWD/StrateManager.jar

className='com.bjjh.MessMan.main.main'
parameter=("/home/hadoop/strateManager/log/" "/home/hadoop/strateManager/log/")


echo ${parameter[*]}

java -Dfile.encoding=UTF-8 -Xmx1024M $className ${parameter[*]} > $PWD/Log/smlog.log 2>&1