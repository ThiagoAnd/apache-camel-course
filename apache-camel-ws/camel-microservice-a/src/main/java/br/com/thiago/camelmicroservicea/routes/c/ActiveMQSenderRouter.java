package br.com.thiago.camelmicroservicea.routes.c;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

/**
 *Esse .to criou uma fila la no activemq, eu não precisei criar, só startei pelo docker e adicionei um starter do activemq para o camel
 */
@Component
public class ActiveMQSenderRouter extends RouteBuilder {
    @Override
    public void configure() throws Exception {

        from("timer:active-mq-timer?period=10000")
                .transform().constant("Minha mensagem no activemq")
                .to("activemq:my-active-mq-queue");

    }
}
