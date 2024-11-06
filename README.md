# Parser-ISO8583
Este projeto tem o objetivo de facilitar o uso da ISO8583 através da realização correta do parser para um formato de json que possa ser lido e entendido de maneira mais fácil.

## Dependências
- Docker

# Como executar
```
$ cd Parser-ISO8583
$ docker build -t parser-iso8583-api .
$ docker run -p 8080:8080 parser-iso8583-api
```