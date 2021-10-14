package br.com.thiago.camelmicroservicea.routes.d;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

/**
 * No curso não apareceu como faço para entrar na area dos topicos do Kafka, não sei se o docker compose up veio com essa configuração
 * Em um outro momento, criar uma imagem do Kafka e tentar melhor
 */
@Component
public class KafkaEnvioArquivoParaFila extends RouteBuilder {
    @Override
    public void configure() throws Exception {

        from("file:camel-microservice-a/files/json")
                .log("${body}")
                .to("kafka:myKafkaTopic");
    }
}
