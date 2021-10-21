package br.com.thiago.camelmicroserviceb.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.security.*;
import java.security.cert.CertificateException;
import org.apache.camel.converter.crypto.CryptoDataFormat;





//@Component
public class ActiveMqCryptoReceiverXmlRouter extends RouteBuilder {

    @Override
    public void configure() throws Exception{
        from("activemq:split-csv-queue")
                .unmarshal(createEncryptor())//Era para decriptar mas ainda esta vindo com caracteres especiais
                .to("log:MICROSERVIÇO B - received-message-from-active-mq");
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

