FROM ubuntu:14.04
MAINTAINER JinYong <jinyong@jadekim.kr>

RUN apt-get update
RUN apt-get install -y software-properties-common
RUN add-apt-repository -y ppa:openjdk-r/ppa
RUN apt-get update
RUN apt-get install -y gcc-4.8 g++-4.8 openjdk-8-jdk

WORKDIR /root/oj

ENV PATH $PATH:/root/oj
