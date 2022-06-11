# HelpDesk
Exemplo de solução de HelpDesk usando JavaEE com Glassfish

## Como fazer?

### Build do projeto
```bash
mvn clean package
```

### Deploy em servidor Glassfish (5.0 - JavaEE 8) local
```bash
mvn glassfish:deploy
```

### Deploy em servidor Heroku remoto
```bash
mvn heroku:deploy-war
```

Obs.: O deploy remoto só ocorre quando você tem o Heroku CLI instalado localmente e devidamente logado via `heroku login`. É possível trocar o nome da aplicação para deploy dentro de `pom.xml > build > plugins > plugin > heroku-maven-plugin > configuration > appName`, este nome é o prefixo disponível para o Heroku publico, exemplo: se appName for `teste-web-app`, o nome de dominio da aplicação será `teste-web-app.heroku.app`