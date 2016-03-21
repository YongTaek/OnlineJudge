#!/usr/bin/env bash


sudo apt-get update
sudo apt-get install -y software-properties-common
sudo add-apt-repository -y ppa:openjdk-r/ppa
sudo apt-get update
sudo apt-get install openjdk-8-jdk docker-engine mysql-server
sudo groupadd docker
sudo gpasswd -a ${USER} docker
sudo service restart docker

sudo docker build -t container ../dockerfile/