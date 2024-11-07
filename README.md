# Parser-ISO8583
Este projeto tem o objetivo de facilitar o uso da ISO8583 através da realização correta do parser para um formato de json que possa ser lido e entendido de maneira mais fácil.

# Ambiente
- Ubuntu 20.04
- Docker 24.0.7
- VSCode
- Git


# Dependências
- Docker

# Como executar
```
$ cd Parser-ISO8583
$ docker build -t api .
$ docker run -p 8080:8080 api
```

# Observações

O parser vai funcionar de acordo com o aquivo isopackager.xml que se encontra em "resources/packager/isopackager.xml"

Antes de validar o parser é necessário que a ISO informada esteja atendendo os padrões descritos no arquivo isopackager.xml

# Realizar uma chamada de parser usando a ferramenta curl

```
$ curl -X POST http://localhost:8080/api/parser?type=TYPE_PARSE_REQUEST -H "Content-Type: application/json" -d '{"iso":"00501430703400000AC0010016000000000000000022000000000000055597637424100314183900003633392020202020202020200000594330303130303030303030303030303030303030303306976373"}'
```

# Resultado da chamada utilizando a ferramenta curl

```
{
    "2":"0000000000000000",
    "3":"220000",
    "4":"000000000555",
    "37":"639         ",
    "39":"000",
    "56":"976373",
    "41":"YC001000",
    "42":"000000000000003",
    "11":"976374",
    "12":"241003141839",
    "14":"0000"
}

```