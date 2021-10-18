package br.com.thiago.camelmicroserviceb.controller;

import br.com.thiago.camelmicroserviceb.model.CurrencyExchange;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.math.BigDecimal;

/**
 * Classe utilizada para criar exemplo de api rest. Os outros exemplos qu estão em routes, podem ser utilizados sem esta classe
 */
@RestController
public class CurrencyExchangeController {

    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    public CurrencyExchange findConversionValue(@PathVariable String from, @PathVariable String to){

        return new CurrencyExchange(1001L,from,to, BigDecimal.TEN);
    }

}
