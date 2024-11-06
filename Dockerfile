FROM registry.access.redhat.com/ubi9/openjdk-21:1.20-2 AS build
USER root

ENV BUILD_DIR=/build
RUN mkdir -p $BUILD_DIR
WORKDIR $BUILD_DIR
ADD . $BUILD_DIR
RUN mvn clean package -DskipTests

WORKDIR /app
FROM registry.access.redhat.com/ubi9/openjdk-21-runtime:1.20-2
USER root
RUN useradd -ms /bin/bash appuser

RUN mkdir /tap static
COPY --from=build /build/target/*.jar /app/run.jar

RUN chown -R appuser:appuser /app
RUN chown -R appuser:appuser /tap
RUN chmod -R 777 /tap

# Expõe a porta 8080
EXPOSE 8080

USER appuser

ENTRYPOINT ["java", "-Djava.io.tmpdir=/tap", "-XX:-UsePerfData", "-jar", "/app/run.jar"]
