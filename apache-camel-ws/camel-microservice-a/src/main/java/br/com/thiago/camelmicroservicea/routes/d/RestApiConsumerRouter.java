package br.com.thiago.camelmicroservicea.routes.d;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

/**
 * No curso não apareceu como faço para entrar na area dos topicos do Kafka, não sei se o docker compose up veio com essa configuração
 * Em um outro momento, criar uma imagem do Kafka e tentar melhor
 */
//@Component
public class RestApiConsumerRouter extends RouteBuilder {
    @Override
    public void configure() throws Exception {

        restConfiguration().host("localhost").port(8000);//Aqui setamos o host e a porta, como estamos rodando local, é o localhost e a porta foi automatico para 8000

        from("timer:rest-api-consumer?period=10000") //A cada 10 segundos eu vou nessa rota
                .setHeader("fromParametro", () -> "BRLLLLL") //Seto o parametro com um valor
                .setHeader("toParametro", () -> "USDDDDD") //Mesma coisa
                .log("${body}") //Log
                .to("rest:get:/currency-exchange/from/{fromParametro}/to/{toParametro}") //Faço uma chamada rest para um get e vou na minha url que esta la no microserviço B, setando ja o FROM e TO
                .log("${body}"); //Log depois
    }
}
