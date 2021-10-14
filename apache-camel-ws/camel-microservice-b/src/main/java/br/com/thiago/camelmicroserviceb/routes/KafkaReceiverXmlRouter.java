package br.com.thiago.camelmicroserviceb.routes;

import br.com.thiago.camelmicroserviceb.model.CurrencyExchange;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;


@Component
public class KafkaReceiverXmlRouter extends RouteBuilder {

    @Override
    public void configure() {
        from("kafka:myKafkaTopic")
                .to("log:MICROSERVIÇO B - received-message-from-kafka");
    }
}

