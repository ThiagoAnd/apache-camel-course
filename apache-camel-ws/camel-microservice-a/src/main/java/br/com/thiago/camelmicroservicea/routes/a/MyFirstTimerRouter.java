package br.com.thiago.camelmicroservicea.routes.a;


import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class MyFirstTimerRouter extends RouteBuilder {

    @Autowired
    private GetCurrentTimeBean getCurrentTimeBean;

    @Autowired
    private SimpleLoggingProcessorComponent loggingComponent;

    /**
     * Camel é um framework de integração.<br>
     * Aqui vamos definir uma sequencia de steps, que seria uma rota no Camel<br><br>
     * .bean = é usado para buscar um bean criado no projeto<br>
     * .transformation = é usado para transformar o Body da mensagem da rota<br>
     * .bean2 = é usado tambem em operações que podem utilizar o body da mensagem mas que não se preocupam em muda-lo (apesar que no BV esta sendo feito por ele). Ver depois o .process como funciona<br>
     * .log = é usado para mostrar o log de alguma coisa<br>
     * <ul>
     *     <li>Escutar uma queue - mas vamos utilizar na verdade um timer, que é uma palavra reservada no camel(primeiro end point)</li>
     *     <li>Transformar os dados que vieram</li>
     *     <li>Salvar em um banco - mas vamos utilizar na verdade um log que é palavra reservada no camel(segundo end point)</li>
     * </ul>
     *
     * @throws Exception ainda para descrever
     */
    @Override
    public void configure() throws Exception {


        from("timer:primeiro-timer")
                 .transform().constant("Minha constante agora esta no body")
                //.transform().constant("A hora agora é "+ LocalDateTime.now())
                // .bean("getCurrentTimeBean") Aqui é uma forma de fazer, ele pega direto o nome do bean em letra minuscula sem autowired. Mas caso o bean mudar de nome e esquecer de mudar aqui pode ocorrer falhas
                //.bean(getCurrentTimeBean) //Assim desse modo não corre o risco de dar erro caso o bean mudar de nome
                .log("${body}")
                .process(new SimpleLoggingProcessor())
                //.bean(loggingComponent)
                .to("log:primeiro-timer");


    }
}

@Component
class GetCurrentTimeBean {
    public String getCurrentTime() {
        return "A hora agora é " + LocalDateTime.now();
    }
}

@Component
class SimpleLoggingProcessorComponent {
    private Logger logger = LoggerFactory.getLogger(SimpleLoggingProcessorComponent.class);

    public void process(String message) {
        logger.info("Mensagem recuperada do processor: {}",message);
    }
}

class SimpleLoggingProcessor implements Processor{
    private Logger logger = LoggerFactory.getLogger(SimpleLoggingProcessorComponent.class);

    @Override
    public void process(Exchange exchange) throws Exception {
        logger.info("Exchange recuperada pelo processor: {}",exchange.getMessage());

    }
}
