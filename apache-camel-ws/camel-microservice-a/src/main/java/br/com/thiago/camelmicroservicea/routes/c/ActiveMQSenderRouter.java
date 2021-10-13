package br.com.thiago.camelmicroservicea.routes.c;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

/**
 *Esse .to criou uma fila la no activemq, eu não precisei criar, só startei pelo docker e adicionei um starter do activemq para o camel
 */
//@Component
public class ActiveMQSenderRouter extends RouteBuilder {
    @Override
    public void configure() throws Exception {

        from("timer:active-mq-timer?period=10000") //aguardei um timer de 10 segundos, o timer é uma palavra reservada, agora o active-mq-timer acredito que é apenas o nome de uma variavel, pois alterei e não mudou o comportamento
                .transform().constant("Minha mensagem no activemq")//Setei o body da mensagem
                .log("${body}") // mostrei o body da mensagem
                .to("activemq:my-active-mq-queue"); //enviei para essa fila

    }
}
