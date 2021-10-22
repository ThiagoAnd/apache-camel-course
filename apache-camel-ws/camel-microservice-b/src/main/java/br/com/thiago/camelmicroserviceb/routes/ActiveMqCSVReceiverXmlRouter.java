package br.com.thiago.camelmicroserviceb.routes;

import br.com.thiago.camelmicroserviceb.model.CurrencyExchange;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;


//@Component
public class  ActiveMqCSVReceiverXmlRouter extends RouteBuilder {

    @Override
    public void configure() {
        //Verificar depois, no curso não mostrou o console, mas aqui da um erro de serializar a mensagem do tipo arraylist
        from("activemq:split-csv-queue")
                .to("log:MICROSERVIÇO B - received-message-from-active-mq");
    }
}

