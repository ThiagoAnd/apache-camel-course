package br.com.thiago.camelmicroservicea.routes.d;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class ActiveMQEnvioXmlParaFila extends RouteBuilder {
    @Override
    public void configure() throws Exception {

        from("file:camel-microservice-a/files/xml")
                .log("${body}") // mostrei o body da mensagem
                .to("activemq:my-active-mq-xml-queue"); //enviei para essa fila
    }
}
