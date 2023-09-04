# Tech challenge Entrega da aplicação e dos arquivos de configuração do kubernates

## Daniel Aleixo Correa
| Descrição | URL |
| ------ | ------ |
| Aplicação | https://github.com/danielcorreaa/tech-challenge-v2 |
| Arquivos Kubernates | https://github.com/danielcorreaa/tech-challenge-kubernates |
| Imagem da aplicação | https://hub.docker.com/layers/daniel36/techchallenge/1.4/images/sha256-a235a85f634f082d64b18feeec4d7d43596def219dec3a8826d55c3908d06121?context=repo |

git clone https://github.com/danielcorreaa/tech-challenge-v2.git
git clone https://github.com/danielcorreaa/tech-challenge-kubernates.git

## Integração mercado pago (Segue evidências)
Para integração com mercado pago local:
 O endpoint abaixo
  ```sh
 http://localhost:8080/api/v1/payment/pay 
 Ex:
    {
      "orderId": 2
    }
 ```
 Esse endpoint envia uma solicitação para o mercado livre endpoint
 ```sh
 https://api.mercadopago.com/instore/orders/qr/seller/collectors/1423020936/pos/DANICAIXA01/qrs
 ```
 e retorna um QR Code,
o Pagamento pode ser feito acessando o aplicativo do mercado livre com o 
```sh
Login: TESTUSER2011766782
Senha: BX5kk2cuTS
```
Então é apenas ler o QR code e realizar o pagamento

No desenvolmento local ultizo um webhook do site pipedream
```sh
https://pipedream.com/@danielcor/requestbin-p_3nCKxL7/inspect/2UwzUopHXaxToFFdSJcCEq0p4Ct
```
O mercado livre envia a notificação para o endereço acima e é possível encontrar um endpoint na no body do response
```sh
body
{2}
resource: https://api.mercadolibre.com/merchant_orders/11552575593
topic: merchant_order
...
```

com esse endpoint é possível enviar uma solicitação para o endpoint
com o campo order_status, consigo validar se o pagamento foi aprovado ou não é com o external_reference que tem o Id do meu pedido, condigo atualizar os dados de pagamento para um pedido específico 