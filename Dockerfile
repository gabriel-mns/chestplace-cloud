# Usar a imagem base do Java 23
FROM openjdk:23-jdk-slim

# Define o diretório de trabalho no container
WORKDIR /app

# Copy the JAR file into the container
COPY target/chestplace-0.0.1-SNAPSHOT.jar /app/app.jar

# # Database connection
# ENV DB_URL=${DB_URL}
# ENV DB_USERNAME=${DB_USERNAME}
# ENV DB_PASSWORD=${DB_PASSWORD}
# ENV DRIVER_CLASS=${DRIVER_CLASS}

# # Service Bus connection
# ENV SERVICEBUS_CONNECTION_STRING=${SERVICEBUS_CONNECTION_STRING}
# ENV SERVICEBUS_NAMESPACE=${SERVICEBUS_NAMESPACE}
# ENV SERVICEBUS_QUEUE_NAME=${SERVICEBUS_QUEUE_NAME}

# Expor a porta de debug
EXPOSE 8080 5005

# Define the startup command
ENTRYPOINT ["java", "-jar", "/app/app.jar"]