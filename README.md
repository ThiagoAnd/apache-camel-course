<div align="center">
   <img src="https://miro.medium.com/max/1200/1*rhBhSUbBuS_H3qmX0ZQnVw.jpeg" width="500" />
</div>
<br>

# Overview

<p>Apache Camel é um framework de integração, visando facilitar a comunicação entre sistemas de uma ponta a outra. Utiliza um DSL (Domain Specific Language), que é linguagem de alto nivel, especifica para configurar essa integração. O projeto possui varios comentarios, pois foram usados como lembretes ja que foi utilizado varios processos envolvendo serviços de mensageria como Kafka, ActiveMq,RabbitMq, alem do Docker.<br>Para uma informação um pouco mais detalhada sobre o Camel:<a href="https://www.alura.com.br/conteudo/camel">&nbsphttps://www.alura.com.br/conteudo/camel</a>  </p>
<br>
<p><strong>By </strong>Thiago de Andrade</p>
<br>

# Deploy

<p>O fluxo atual esta setado para utilizar o RabbitMq, para utilizar o Kafka ou o ActiveMQ, deve descomentar somente as rotas do serviço que se quer utilizar e subir uma imagem do serviço escolhido</p>
<p>Para utilizar o rabbitmq, você pode utlizar localmente a aplicação e somente subir a imagem do rabbitmq ou instalar junto com o Erlang no seu computador. Ou pode executar o docker-compose para uma visualização basica de uma das rotas utilizadas para o Rabbitmq com o microserviço A.</p>

# Settings

* Docker-compose: https://github.com/ThiagoAnd/apache-camel-course/blob/main/apache-camel-ws/docker-compose.yaml
<pre>docker-compose up</pre>
* Rabbitmq management (usr:guest,pwd:guest)
<pre>http://localhost:15672</pre>
* Activemq management (usr:admin,pwd:admin)
<pre>http://localhost:8161/</pre>

