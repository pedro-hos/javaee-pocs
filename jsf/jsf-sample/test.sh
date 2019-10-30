#!/bin/bash

### seq 5 | parallel ./test.sh 5

TMPDIR=/tmp/
PREFIX=$(cat /dev/urandom | tr -dc 'a-zA-Z0-9' | fold -w 32 | head -n 1)
LOG=$TMPDIR/test-$PREFIX.log

i=0

while : ;do
a=$(curl --cookie-jar $TMPDIR/$PREFIX.$i localhost:8080/jsf-sample/home.xhtml 2>/dev/null)
name1=$(echo $a | sed 's/.*body>\([^\"]*\)<\/body.*/\1/')
a=$(curl --cookie $TMPDIR/$PREFIX.$i localhost:8080/jsf-sample/bean.xhtml 2>/dev/null)
name2=$(echo $a | sed 's/.*body>\([^\"]*\)<\/body.*/\1/')
echo "$name1 : $name2" >> $LOG
[[ $name1 != $name2 ]] && echo -e "race $name1 : $name2\n" >> $LOG
(( i++ ))
[[ $i -eq $1 ]] && break

done

rm $TMPDIR/$PREFIX.*





