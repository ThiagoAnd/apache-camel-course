package br.com.thiago.camelmicroservicea.routes.b;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

/**
 * Esse router vai pegar todos os arquivos que vc colocar no path do input e mandar para o output, ele vai agir como um listener
 * Então sempre q for adicionado um arquivo ali, ele vai jogar para a outra pasta enquanto o programa estiver rodando
 */
//@Component //Comentando o cmponent para testar o router do activemq
public class MyFileRouter extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("file:camel-microservice-a/files/input")
                .log("${body}")
                .to("file:camel-microservice-a/files/output");
    }
}
