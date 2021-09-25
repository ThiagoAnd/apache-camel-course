package br.com.thiago.camelmicroservicea.routes.a;


import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class MyFirstTimerRouter extends RouteBuilder {
    /**
     * Camel é um framework de integração.<br>
     * Aqui vamos definir uma sequencia de steps, que seria uma rota no Camel
     * <ul>
     *     <li>Escutar uma queue - mas vamos utilizar na verdade um timer, que é uma palavra reservada no camel(primeiro end point)</li>
     *     <li>Transformar os dados que vieram</li>
     *     <li>Salvar em um banco - mas vamos utilizar na verdade um log que é palavra reservada no camel(segundo end point)</li>
     * </ul>
     * @throws Exception ainda para descrever
     */
    @Override
    public void configure() throws Exception {

        from("timer:primeiro-timer")
                .transform().constant("Minha constante agora esta no body")
                .to("log:primeiro-timer");


    }
}
