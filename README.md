# MeetUpSpot API

Esta API RESTful, desenvolvida em Java com Spring Boot, tem como objetivo encontrar o melhor ponto de encontro para um grupo de pessoas, considerando a menor distância e tempo de viagem entre os locais de origem de cada indivíduo.

## Como Calcula o Melhor Ponto de Encontro

A API calcula a melhor cidade definindo o **centro geodésico** entre as cidades fornecidas. O centro geodésico é o ponto médio que minimiza a soma das distâncias entre todas as cidades. Este cálculo é realizado antes mesmo de fazer requisições para a API de routing, tornando o processo mais otimizado.

### Passos do Cálculo

1. **Obtenção das Coordenadas**: A API usa a OpenWeatherMap para obter as coordenadas geográficas das cidades fornecidas.
2. **Cálculo do Centro Geodésico**: Calcula o ponto médio das coordenadas (centro geodésico) para determinar o melhor ponto de encontro.
3. **Distâncias e Tempos**: Utiliza a Distance Matrix API para calcular as distâncias e tempos de viagem entre o centro geodésico e cada cidade.
4. **Determinação do Melhor Ponto de Encontro**: A API avalia qual cidade está mais próxima ao centro geodésico e que minimiza a soma das distâncias e tempos de viagem para todas as cidades.

## Funcionalidades

- **Recebe uma lista de cidades**: A API aceita uma lista de cidades como parâmetro de entrada, onde cada cidade deve ser informada no formato `Nome da Cidade, UF`.
- **Calcula distâncias e tempos de viagem**: Utiliza a Distance Matrix API para calcular a distância e o tempo de viagem entre cada cidade e um ponto de encontro potencial.
- **Determina o melhor ponto de encontro**: A API identifica o ponto de encontro que minimiza a soma das distâncias e dos tempos de viagem entre todas as cidades.
- **Retorna um JSON**: A resposta da API é um objeto JSON contendo informações sobre as distâncias, tempos de viagem e o ponto de encontro final.

## Endpoints

### GET /coordinates/meetup-spot

**Parâmetros:**

- `request`: Uma string contendo a lista de cidades separadas por `&`.

**Retorno:**

- Um objeto JSON com as informações detalhadas sobre as viagens e o ponto de encontro final.

**Exemplo de Requisição:**

http://localhost:8080/coordinates/meetup-spot?request=Blumenau,SC&request=São%20Paulo,SP&request=Lapa,PR&request=Porto%20Alegre,RS

**Exemplo de Resposta:**

json
{"travelDTOList":[{"origin":{"name":"Lapa,PR","latitude":-25.76709,"longitude":-49.716777},"destination":{"name":"Blumenau,SC","latitude":-26.9195567,"longitude":-49.0658025},"distance":229298,"duration":12558},{"origin":{"name":"São Paulo,SP","latitude":-23.5506507,"longitude":-46.6333824},"destination":{"name":"Blumenau,SC","latitude":-26.9195567,"longitude":-49.0658025},"distance":633202,"duration":30458},{"origin":{"name":"Porto Alegre,RS","latitude":-30.0324999,"longitude":-51.2303767},"destination":{"name":"Blumenau,SC","latitude":-26.9195567,"longitude":-49.0658025},"distance":582412,"duration":25335}],"totalDistance":1444912,"totalDuration":68351}


## Tecnologias Utilizadas

- **Java**
- **Spring Boot**
- **OpenWeatherMap API**: Para obter coordenadas geográficas.
- **Distance Matrix API**: Para calcular distâncias e tempos de viagem.

## Pré-requisitos

- **Java Development Kit (JDK)**: Instalar a versão compatível com o projeto.
- **Ferramenta de build**: Maven ou Gradle.
- **IDE**: Eclipse, IntelliJ IDEA ou outra de sua preferência.
- **Chaves de API**: Obter chaves de API para OpenWeatherMap e Distance Matrix API.

## Como Executar

1. Clonar o repositório.
2. Construir o projeto usando Maven ou Gradle.
3. Executar a aplicação Spring Boot.
4. Acessar o endpoint da API usando um cliente HTTP como Postman ou curl.

## Informações de Contato

- **Email**: fernandozimmermannmaciel@gmail.com
- **LinkedIn**: Fernando Zimmermann Maciel



=======
# meet-up-spot-backend
An apllication that calcules the best spot for you and your friends to meetup
>>>>>>> main
