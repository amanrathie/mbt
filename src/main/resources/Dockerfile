FROM alpine:3.5
ENV JAVA_HOME /usr/lib/jvm/java-1.8-openjdk/jre
ENV PATH $PATH:/usr/lib/jvm/java-1.8-openjdk/jre/bin:/usr/lib/jvm/java-1.8-openjdk/bin
RUN apk update && apk upgrade
RUN apk add --no-cache tzdata
RUN apk add --no-cache openjdk8-jre
RUN cp /usr/share/zoneinfo/Brazil/East /etc/localtime
run apk del tzdata
ADD @project.build.finalName@.jar /opt/

CMD java -jar -Duser.timezone=America/Sao_Paulo -Dspring.profiles.active=@profiles.spring@ /opt/@project.build.finalName@.jar
