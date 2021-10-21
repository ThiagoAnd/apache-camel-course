package br.com.thiago.camelmicroservicea.routes.c;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.converter.crypto.CryptoDataFormat;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.security.*;
import java.security.cert.CertificateException;

/**
 * Esse .to criou uma fila la no activemq, eu não precisei criar, só startei pelo docker e adicionei um starter do activemq para o camel
 */
//@Component
public class ActiveMQCryptoSenderRouter extends RouteBuilder {
    @Override
    public void configure() throws Exception {

        from("timer:active-mq-timer?period=10000") //aguardei um timer de 10 segundos, o timer é uma palavra reservada, agora o active-mq-timer acredito que é apenas o nome de uma variavel, pois alterei e não mudou o comportamento
                .transform().constant("Minha mensagem no activemq")//Setei o body da mensagem
                .log("${body}") // mostrei o body da mensagem
                .marshal(createEncryptor()) //Aqui encripta a mensagem que foi criada conforme o que esta no arquivo que foi criado segundo o notes.md
                .to("activemq:my-active-mq-queue"); //enviei para essa fila

    }

    private CryptoDataFormat createEncryptor() throws KeyStoreException, IOException, NoSuchAlgorithmException,
            CertificateException, UnrecoverableKeyException {
        KeyStore keyStore = KeyStore.getInstance("JCEKS");
        ClassLoader classLoader = getClass().getClassLoader();
        keyStore.load(classLoader.getResourceAsStream("myDesKey.jceks"), "someKeystorePassword".toCharArray());
        Key sharedKey = keyStore.getKey("myDesKey", "someKeyPassword".toCharArray());

        CryptoDataFormat sharedKeyCrypto = new CryptoDataFormat("DES", sharedKey);
        return sharedKeyCrypto;
    }
}
