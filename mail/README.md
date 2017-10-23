Como executar:
===

1. Fazer Download do (fakeSMTP)[http://nilhcem.com/FakeSMTP/index.html]
2. `JBOSS_HOME/bin/jboss-cli.sh --connect --file=config-mail.cli`
3. `mvn clean install` na aplicação
4. Deploy do **mail.war**
5. Executar fakeSMTP `java -jar fakeSMTP.jar`
6. Acessar: http://localhost:8080/mail/ e enviar um email
7. Checkar no fakeSMTP
