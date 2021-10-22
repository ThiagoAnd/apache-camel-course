package br.com.thiago.camelmicroservicea.routes.rabbitmq;

import com.rabbitmq.client.AMQP;
import org.apache.camel.Exchange;
import org.apache.camel.ExchangeProperties;
import org.apache.camel.ExchangeProperty;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class RabbitMqRouter extends RouteBuilder {

    private static int counter = 1;
    @Override
    public void configure() throws Exception {
        from("timer:RabbitMq-timer?period=5000")
                .routeId("Rota inicial")
                .setBody().constant("Body da mensagem da rota com timer")
                //.log(exchangeProperty(Exchange.TIMER_COUNTER).toString())//Quando o timer é utilizado, é criado uma propriedade TIMER_COUNTER na exchange, aqui eu peguei o nome dela, e logo abaixo com o ${exchangeProperty.CamelTimerCounter} eu utilizei para loggar
                .log("Enviado para a fila no rabbitmq: ${exchangeProperty.CamelTimerCounter} X")
                .to("rabbitmq:exchange-timer?queue=fila-timer");//componente:nomeexchange?fila=nomefila
    }
}



