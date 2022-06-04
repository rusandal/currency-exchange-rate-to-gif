FROM adoptopenjdk:11-jre-hotspot
RUN mkdir /opt/app
COPY build/libs/value-to-gif-0.0.1-SNAPSHOT-plain.jar /opt/app/exchange_to_gif.jar
CMD ["java", "-jar", "/opt/app/exchange_to_gif.jar"]