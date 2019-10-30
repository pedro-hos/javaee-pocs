#!/bin/bash
# seq 5 | parallel ./test2.sh 100 15m

TMPDIR=/tmp/
PREFIX=$(cat /dev/urandom | tr -dc 'a-zA-Z0-9' | fold -w 32 | head -n 1)

LOG=$TMPDIR/test2-$PREFIX.log

i=0

while : ;do
a=$(curl --cookie-jar $TMPDIR/$PREFIX.$i localhost:8080/jsf-sample/home.xhtml 2>/dev/null)
name[$i]=$(echo $a | sed 's/.*body>\([^\"]*\)<\/body.*/\1/')
echo "$i (1): ${name[$i]}" >> $LOG
(( i++ ))
[[ $i -eq $1 ]] && break
done

j=$i

echo "waiting.............."
sleep $2

i=0

while : ; do
a=$(curl --cookie $TMPDIR/$PREFIX.$i localhost:8080/jsf-sample/bean.xhtml 2>/dev/null)
name2=$(echo $a | sed 's/.*body>\([^\"]*\)<\/body.*/\1/')
echo "$i (2) : $name2" >> $LOG
[[ ${name[$i]} != $name2 ]] && echo -e "race ${name[$i]} : $name2\n" >> $LOG
(( i++ ))
[[ $i -eq j ]] && break
done

rm $TMPDIR/$PREFIX.*
