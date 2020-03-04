FROM openjdk:8-jdk-alpine

RUN apk update && apk add wget && rm -rf /var/cache/apk/*
VOLUME ["/app"]
COPY build/libs/nasa-image-1.0-SNAPSHOT.war /app/

EXPOSE 9090

COPY dates.txt .

ADD https://raw.githubusercontent.com/SignalMedia/signal-secret-service/master/init.sh / 
RUN chmod +x /init.sh && /init.sh


ENTRYPOINT ["/init.sh","/usr/bin/java", "-jar","/app/nasa-image-1.0-SNAPSHOT.war"]