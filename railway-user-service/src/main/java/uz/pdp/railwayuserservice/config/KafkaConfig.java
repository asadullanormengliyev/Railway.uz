package uz.pdp.railwayuserservice.config;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import uz.pdp.railwayuserservice.service.event.TicketCreateEvent;


import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConfig {

    @Value("${spring.kafka.consumer.group-id}")
    private String groupId;

    private Map<String, Object> consumerConfigs() {
        final Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "http://kafka:9092");
        props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        return props;
    }

    @Bean
    public ConsumerFactory<String, TicketCreateEvent> kafkaListenerConsumerFactory() {
        final ErrorHandlingDeserializer<TicketCreateEvent> errorHandlingDeserializer
                = new ErrorHandlingDeserializer<>(
                new JsonDeserializer<>(TicketCreateEvent.class, false)
        );

        return new DefaultKafkaConsumerFactory<>(this.consumerConfigs(), new StringDeserializer(), errorHandlingDeserializer);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, TicketCreateEvent> kafkaListenerContainerFactory() {
        final ConcurrentKafkaListenerContainerFactory<String, TicketCreateEvent> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(this.kafkaListenerConsumerFactory());
        return factory;
    }
}
