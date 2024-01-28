FROM ubuntu:latest
LABEL authors="hamed"

ENTRYPOINT ["top", "-b"]