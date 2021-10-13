package br.com.thiago.camelmicroserviceb.routes;

import br.com.thiago.camelmicroserviceb.model.CurrencyExchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;


@Component
public class ActiveMqReceiverRouter extends RouteBuilder {


    @Autowired
    private MyCurrencyExchangeProcessor myCurrencyExchangeProcessor;

    @Autowired
    private MyCurrencyExchangeProcessorTransformer myCurrencyExchangeProcessorTransformer;

    @Override
    public void configure() throws Exception {
        from("activemq:my-active-mq-queue") //Aqui começamos a escutar a fila my-active-mq-queue e quando cair alguma mensagem nos prosseguimos a rota
                .unmarshal().json(JsonLibrary.Jackson, CurrencyExchange.class)// Aqui eu pego o body e faço um mapper para um objeto currencyExchange. e quando eu faço o log, ele manda o TOSTRING desse objeto que eu criei
                .bean(myCurrencyExchangeProcessor)// Usando um bean só para fazer um log
                .bean(myCurrencyExchangeProcessorTransformer) // Usando esse outro bean para multiplicar por 10x e mostrar no log
                .to("log:MICROSERVIÇO B - received-message-from-active-mq"); // a rota vem para essa linha, que diz que mostrará um log com a string received-message-from-active-mq
    }
}

@Component
class MyCurrencyExchangeProcessor {

    Logger logger = LoggerFactory.getLogger(MyCurrencyExchangeProcessor.class);

    public void processMessage(CurrencyExchange currencyExchange) {
        logger.info("Testando logger com o getConversionMultiple : {}", currencyExchange.getConversionMultiple());
    }
}

@Component
class MyCurrencyExchangeProcessorTransformer {

    Logger logger = LoggerFactory.getLogger(MyCurrencyExchangeProcessorTransformer.class);

    public void processMessage(CurrencyExchange currencyExchange) {

        currencyExchange.setConversionMultiple(currencyExchange.getConversionMultiple().multiply(BigDecimal.TEN));
        logger.info("Multiplicando o conversionMultiple por 10x : {}", currencyExchange.getConversionMultiple());
    }
}
