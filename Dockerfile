FROM openjdk:11-jre-slim
LABEL authors="mahdifersi"

COPY target/5SE3-G2-khaddem.jar .
EXPOSE 8089
ENTRYPOINT ["java", "-jar", "5SE3-G2-khaddem.jar"]
