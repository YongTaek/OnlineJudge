#!/usr/bin/env bash

if $1 || $2
then
    if $1 = "c"
    then
        gcc-4.8 $2 -O2 -Wall -lm --static -std=c99 -DONLINE_JUDGE
        ./a.out
    elif $1 = "c++"
    then
        g++-4.8 $2 -O2 -Wall -lm --static -DONLINE_JUDGE
        ./a.out
    elif $1 = "java"
    then
        javac $2
        java $2 #TODO: output name set
    elif $1 = "python2"
    then
        python2 $2
    elif $1 = "python3"
    then
        python3 $2
    else
        echo "not support language"
    fi
else
    echo "not correct input"
fi