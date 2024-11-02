package com.pucpr.chestplace.features.user.services;

import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.azure.messaging.servicebus.ServiceBusClientBuilder;
import com.azure.messaging.servicebus.ServiceBusSenderClient;

@Service
public class ServiceBusSenderService {
    
    private final ServiceBusSenderClient senderClient;

    public ServiceBusSenderService(Environment env) {

        String connectionString = env.getProperty("spring.cloud.azure.servicebus.connection-string");
        String queueName = env.getProperty("spring.cloud.azure.servicebus.queue-name");

        // Inicializa o client para o Service Bus com a connection string
        this.senderClient = new ServiceBusClientBuilder()
                .connectionString(connectionString)
                .sender()
                .queueName(queueName) // Nome da fila ou tópico
                .buildClient();
    }

    public void sendMessage(String messageContent) {
        // Envia a mensagem
        senderClient.sendMessage(new com.azure.messaging.servicebus.ServiceBusMessage(messageContent));
        System.out.println("Mensagem enviada para o Service Bus: " + messageContent);
    }
    
}
