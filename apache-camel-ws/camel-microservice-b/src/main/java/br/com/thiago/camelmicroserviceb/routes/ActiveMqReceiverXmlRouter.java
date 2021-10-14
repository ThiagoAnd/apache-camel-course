package br.com.thiago.camelmicroserviceb.routes;

import br.com.thiago.camelmicroserviceb.model.CurrencyExchange;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;


//@Component
public class ActiveMqReceiverXmlRouter extends RouteBuilder {

    @Override
    public void configure() {
        from("activemq:my-active-mq-xml-queue")
                .unmarshal().jacksonxml(CurrencyExchange.class)
                .to("log:MICROSERVIÇO B - received-message-from-active-mq"); // a rota vem para essa linha, que diz que mostrará um log com a string received-message-from-active-mq
    }
}

