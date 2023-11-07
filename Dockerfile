FROM openjdk:11-jre-slim

COPY target/5SE3-G2-khaddem.jar .
EXPOSE 8089
ENV IMAGE_NAME="dhiahamrouni_5se3-g2-khaddem"
ENTRYPOINT ["java", "-jar", "5SE3-G2-khaddem.jar"]
