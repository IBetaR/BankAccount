# Cuenta Bancaria - Corporación Cápsula Challenge
** * 
Proyecto - desafío para gestionar una cuenta bancaria. Chequeo de saldo, acreditación y débito de saldos.
** * 
## Getting Started

Para correr las funcionalidades requeridas, la app cuenta con tres end points.
Usando el número de cuenta como atributo de búsqueda, es posible:
Ver saldo disponible, acreditar y debitar un monto determinado. 

Usando base de datos relacionales MySQL, la aplicación esta configurada para iniciar con una cuenta usando el CommandLineRunner
creando una cuenta con los siguientes atributos:

* [Bank Username]() : ibr
* [Number account]() : C1
* [Balance]() : $ 0.00 (por defecto)
* [Type]() : Cuenta corriente

** * 
## Chequeo de saldo en cuenta

Usando el número de cuenta como atributo de búsqueda: C1

** * 
### Ver saldo disponible

Por defecto inicia en 0.00 una vez creada la cuenta.

```
http://localhost:8080/api/v1/accounts/account/balance/C1
```
** * 
### Acretitar un monto

La app esta configurada para aceptar cualquier valor mayor positivo mayor que cero

```
http://localhost:8080/api/v1/accounts/account/balance/credit/C1/1000
```
** * 
### Debitar un monto

La app esta configurada para aceptar cualquier valor mayor positivo mayor que cero.
Si la cantidad es mayor al saldo disponible, arroja una exception de "Saldo insuficiente"

```
http://localhost:8080/api/v1/accounts/account/balance/debit/C1/500
```
** * 
## Built With

* [Spring Boot](https://spring.io/projects/spring-boot) - The web framework used
* [Maven](https://maven.apache.org/) - Dependency Management
** * 
## Authors

* **Ilich Betancourt Rangel** - *Developer Jr.* - [Ibetar](https://github.com/IBetaR)

## Pruebas
La App cuenta con pruebas unitarias y de integración. 

En la raiz del proyecto se encuentra a archivo con colección de postman
** *

## Documentación

* Vía Swagger con el link: 
```
http://localhost:8080/swagger-ui.html#/
```
** * 
