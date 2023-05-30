# Sky Aviator Airlines

## - API Demo:

(Add Swagger Documentation After)

    • MS Contas (Gerenciamento do sistema)

| HTTP METHOD | PATCH                     | DESCRIPTION |
| ----------- | ------------------------- | ----------- |
| POST        | /api/v1/accounts/register |             |
| PUT         | /api/v1/accounts/register |             |
| PAT         | /api/v1/accounts/register |             |
| GET         | /api/v1/accounts          |             |
| GET         | /api/v1/accounts/login    |             |
| GET         | /api/v1/accounts/{Id}     |             |
| DEL         | /api/v1/accounts/{Id}     |             |

    • MS Usuários (Clientes)

| HTTP METHOD | PATCH                  | DESCRIPTION                      |
| ----------- | ---------------------- | -------------------------------- |
| POST        | /api/v1/users/register | (Realizar registro do cliente)   |
| PUT         | /api/v1/users/register |                                  |
| PAT         | /api/v1/users/register |                                  |
| GET         | /api/v1/users          | (Obter todos os clientes)        |
| GET         | /api/v1/users/login    | (Obter token de auth do cliente) |
| GET         | /api/v1/users/{Id}     | (Obter cliente por id)           |
| DEL         | /api/v1/users/{Id}     | (Deletar cliente)                |

    • MS Reservas

| HTTP METHOD | PATCH                                                   | DESCRIPTION                                             |
| ----------- | ------------------------------------------------------- | ------------------------------------------------------- |
| POST        | /api/v1/booking                                         | (Realizar reserva)                                      |
| PUT         | /api/v1/booking                                         |                                                         |
| PAT         | /api/v1/booking                                         |                                                         |
| GET         | /api/v1/booking                                         | (Obter todos as reservas, disponíveis ou indisponíveis) |
| GET         | /api/v1/booking/available                               | (Obter todas as reservas disponíveis)                   |
| GET         | /api/v1/booking/available/{Id}                          | (Obter todas as reservas disponíveis por Id)            |
| GET         | /api/v1/booking/reserved                                | (Obter todas as reservas indisponíveis)                 |
| GET         | /api/v1/booking/reserved/{Id}                           | (Obter todas as reservas indisponíveis por Id)          |
| GET         | /api/v1/booking/{city}                                  | (Obter Reserva com parâmetros)                          |
| GET         | /api/v1/booking/available/{city}?{lowerDate=2023-06-27} | (Obter Reserva por menor data - Obrigatório)            |
| GET         | /api/v1/booking/available/{city}?{upperDate=2023-09-30} | (Obter Reserva por maior data - Opcional)               |
| GET         | /api/v1/booking/available/{city}?{lowerHour=HH-MM-ss}   | (Obter Reserva por menor hora - Opcional)               |
| GET         | /api/v1/booking/available/{city}?{upperHour=HH-MM-ss}   | (Obter Reserva por maior hora - Opcional)               |
| DEL         | /api/v1/booking/{Id}                                    | (Deletar Reserva)                                       |
 
    • MS Aeroporto (Acesso restrito - Gerenciamento do aeroporto)

| HTTP METHOD | PATCH                                | DESCRIPTION |
| ----------- | ------------------------------------ | ----------- |
| POST        | /api/v1/airports                     |             |
| PUT         | /api/v1/airports                     |             |
| PAT         | /api/v1/airports                     |             |
| GET         | /api/v1/airports                     |             |
| GET         | /api/v1/airports/{Id}                |             |
| GET         | /api/v1/airports/{countries}         |             |
| GET         | /api/v1/airports/{city}              |             |
| GET         | /api/v1/airports/{city}?{lag}?{long} |             |
| GET         | /api/v1/airports/{lag}/{long}        |             |
| DEL         | /api/v1/airports/{Id}                |             |


• MS Cotação (Cotação do dólar para converter o preço atual do BD para dólar))

| HTTP METHOD | PATCH | DESCRIPTION |
| ----------- | ----- | ----------- |
| TODO        | TODO  | TODO        |