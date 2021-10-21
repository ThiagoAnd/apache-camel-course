package br.com.thiago.camelmicroservicea.routes.pattern;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class EipPatternsRouter extends RouteBuilder {
    @Override
    public void configure() throws Exception {


        //O tracing TRUE é outra forma de mostrar mais informações que ajudam no processo de verificar algum erro , junto com o log
       // getContext().setTracing(true);

        //Para criar uma dead letter queue, faça como na linha abaixo
        //errorHandler(deadLetterChannel("activemq:dead-letter-queue"));

        //Esse abaixo era pra ser um exemplo de pattern multicast, mas como eu disse ali em baixo eu ainda não vi a necessidade de usar .multicast pq sem ele faz a mesma coisa, então preciso verificar
//        from("timer:multicast?period=10000")
//               // .multicast() //Funciona a mesma coisa com ou sem. Eu posso mandar para multiplos endpoins sem o .multicast, ver o pq
//                .to("log:algumacoisa111","log:algumacoisa2");

        //Esse debaixo é um metodo para fazer o split de um arquivo CSV
//        from("file:camel-microservice-a/files/csv")
//                .unmarshal().csv()//Aqui pede para tratar o corpo como um arquivo CSV pois se não tiver isso e der split, vai tratar como um unico arquivo
//                .split(body())//Aqui vai fazer o split de linha por linha e mostrar o log das linhas no console
//                //.split(body(),",") Aqui é outro modo de usar o split, ele divide a cada ",". Ou seja, a cada virgula é um novo Body
//                .log("${body}")
//                .to("activemq:split-csv-queue");//Aqui se não tiver criado ele cria a fila split-csv-queue

        //Routing slip - Um modo para direcionar para filas especificas:

        String routingSlip = "direct:endpoint1,direct:endpoint3";

        from("timer:routingslip?period=10000")
                .transform().constant("Meu body é uma mensagem")
                .routingSlip(simple(routingSlip));

        from("direct:endpoint1")
               // .to("{{endpoint-vindo-do-properties}}");//As 2 chaves servem para dizer que essa é uma variavel vindo do application.properties. Verificar o funcionamento
                       .to("log:foi para endpoint1");
        from("direct:endpoint2")
                .to("log:foi para endpoint2");
        from("direct:endpoint3")
                .to("log:foi para endpoint3");





    }
}
