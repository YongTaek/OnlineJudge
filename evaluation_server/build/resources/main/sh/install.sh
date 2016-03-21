#!/usr/bin/env bash

sudo apt-get update
sudo apt-get install oracle-java8-installer docker-engine mysql-server
sudo groupadd docker
sudo gpasswd -a ${USER} docker
sudo service restart docker

sudo docker build -t container ../dockerfile/