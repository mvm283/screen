FROM openjdk:12-alpine


COPY target/screenshot.jar /screenshot.jar
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /screenshot.jar screenshotservice c" ]
