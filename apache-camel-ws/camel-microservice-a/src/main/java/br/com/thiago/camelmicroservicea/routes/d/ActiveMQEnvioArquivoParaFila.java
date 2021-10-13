package br.com.thiago.camelmicroservicea.routes.d;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

/**
 * Essa rota vai agir como um listener, quando eu colocar um .txt por exemplo no diretorio /json,
 * eu vou jogar um log com o conteudo do txt e mandar para a fila my-active-mq-queue, e se o microserviço B estiver executando
 * eu vou conseguir executar uma rota que esta la e ela esta esperando entrar alguma coisa na fila que eu coloquei acima para
 * jogar um log.
 */
//@Component
public class ActiveMQEnvioArquivoParaFila extends RouteBuilder {
    @Override
    public void configure() throws Exception {

        from("file:camel-microservice-a/files/json")
                .log("${body}") // mostrei o body da mensagem
                .to("activemq:my-active-mq-queue"); //enviei para essa fila
    }
}
