package br.com.thiago.camelmicroservicea.routes.b;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;


//@Component
public class MyFileRouterWithChoice extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("file:camel-microservice-a/files/input")
                .routeId("Id da rota que aparece no debug do console")
                .transform().body(String.class) // Transforma o conteudo dos arquivos em string para poder validar no WHEN BODY....
                .choice()
                    .when(simple("${file:ext} ends with 'xml'")) //Verifica se a extensão é xml. Pesquisar por simple language Camel
                        .log("O arquivo é XML")
                    .when(simple("${body} contains 'USD'")) // Verifica o conteudo contem uma string
                        .log("O arquivo contem a string USD")
                    .otherwise()
                        .log("O arquivo não é XML nem JSON")
                .end()
                .log("${messageHistory}")//Mostra muito mais informações do que somente u m ${body} como headers, id da rota,etc
                .log("${file:size} ${file:path}")//Mais tipos de logs que pode ser visto com o simple language camel
                .to("file:camel-microservice-a/files/output");
    }
}
