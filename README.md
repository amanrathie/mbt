# Projeto Exemplo Spring Boot

## Configuração do Ambiente

- Instalar JDK 8 -> http://www.oracle.com/technetwork/pt/java/javase/downloads/jdk8-downloads-2133151.html
- Baixar o Maven -> https://maven.apache.org/download.cgi (apache-maven-3.3.3-src.zip)
  - Extrair em alguma pasta de sua preferência
  - Adicionar o caminho da pasta extraída no path do Windows ```Pressione Windows+Pause -> Configurações avançadas do sistema -> Variaveis de Ambiente -> Adicione o caminho na variavel PATH```

- Instalar GIT -> https://git-scm.com/downloads
    - Após a instalação, abra um terminal e execute: ```git config --global http.sslVerify false``` . Esse comando é para desabilitar o bloqueio a certificados auto assinados que o GIT tem.

- Instalar o IntelliJ (ou Eclipse se preferir, esse tutorial vai usar o IntelliJ). São duas opções:
    - Versão Community (ela não vai dar ajuda nos códigos Javascript e nas views do freemarker)  https://www.jetbrains.com/idea/download/
    - Versão EAP (é um preview da próxima versão a ser lançada e é gratuita. O inconveniente é que ela tem validade, aí vc precisa reinstalar mensalmente)  https://confluence.jetbrains.com/display/IDEADEV/IDEA+15+EAP
    - Após escolher e instalar a sua versão, configure:
        - Em Settings -> Maven -> Coloque a pasta do seu maven
        - Em Settings -> git -> Coloque a pasta do seu git
    - Após a configuração faça um ```Checkout from version control```. 
    - Normalmente não precisa mudar nada, é só escolher maven nessa primeira tela e depois next next next.
    
- Rode um ```clean compile test``` para compilar e rodar os testes do projeto. Se tudo estiver configurado corretamente esse comando vai ser executado com sucesso.

- Rodar a classe Application usando VMOptions: ```-Dspring.profiles.active=default,h2``` para subir a aplicação com o banco de dados local na url ```https://localhost:8443/@app.context@/```

##ADFS
Em caso de erro ```SAMLException: Response doesn't have any valid assertion which would pass subject validation```
limpe o cache do navegador ou abra uma janela anonima.

##Terminologias
- Classes de serviços são nomeadas com *Adjetivos Substantivos*: Buscador, Gerenciador, Instalador, etc.
- *Form: são DTO's que representam formulários de tela ou entradas de API REST
- *DTO: são classes para transferencia de dados entre camadas. Usamos DTO's para saídas da aplicação.
- *Filtro: objetos que determinam o resultado de uma consulta.
- *QueryBuilder: classe que faz consultas complexas com paginação, ordenação, filtragem e agrupamentos. Usamos *JPASQLQuery* do querydsl nessas classes.
- *Repository: classes de acesso ao banco de dados de uma entidade específica. Usamos preferencialmente *JPAQuery* do querydsl nessas classes.


##Camadas
- Web: Camada do Spring MVC com as telas e serviços REST
- Aplicação: Camada que representa os casos de uso do sistema
- Negócio: Camada que representa o domínio do sistema.

##Configurações
- Substituir parâmetros de usuário e senha, procurando por "TODO-configurar-*".
- O nome genérico utilizado para o sistema de exemplo foi "@app.context@". Substituir pelo nome correto da aplicação. Este nome esta parametrizado no pom.xml (app.context)
- Ajustar nome e artifactId no pom.xml
- Arquivos JKS de homologação e produção são apenas exemplificativos e devem ser substituídos. Nos arquivos devem ser gerados para cada sistema. Vide Wiki.
- Arquivos SQL do Flyway são apenas exemplificativos e devem ser substituídos.