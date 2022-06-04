FROM adoptopenjdk:11-jre-hotspot
RUN mkdir /opt/app
COPY app.jar /opt/app/exchange_to_gif.jar
CMD ["java", "-jar", "/opt/app/exchange_to_gif.jar"]