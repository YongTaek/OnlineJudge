#!/usr/bin/env bash

if $1 = "timeout"
then
    docker run tester -v /tmp/oj:/root/oj