//package br.com.thiago.camelmicroservicea.config;
//
//import com.rabbitmq.client.ConnectionFactory;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
////Classe de configuração que tinha sido utilizada para conectar no rabbitmq. Não é mais necessaria pois não ocorreu mais o erro de que não conseguiu se conectar
////Mas caso ocorrer novamente, acionar essa classe, e na rota do camel colocar:                 //.to("rabbitmq:nomedarota?connectionFactory=#rabbitConnectionFactory");
//@Configuration
//public class ProjectConfiguration {
//
//    @Bean
//    public ConnectionFactory rabbitConnectionFactory() {
//        ConnectionFactory factory = new ConnectionFactory();
//        factory.setHost("rabbitmq");
//        factory.setPort(5672);
//        factory.setUsername("guest");
//        factory.setPassword("guest");
//        return factory;
//    }
//}
