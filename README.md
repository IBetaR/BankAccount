# Cuenta Bancaria - Corporación Cápsula Challenge
** * 
Proyecto - desafío para gestionar una cuenta bancaria. Chequeo de saldo, acreditación y débito de saldos.
** * 
## Getting Started

Para correr las funcionalidades requeridas, la app cuenta con tres (3) end points.
Usando el número de cuenta como atributo de búsqueda, es posible ver saldo disponible, acreditar y debitar un monto determinado. 


Usando base de datos relacionales h2/MySQL, la aplicación esta configurada para iniciar con una cuenta usando el CommandLineRunner
creando una cuenta con los siguientes atributos:

* [Bank Username]() : ibr
* [Number account]() : C1
* [Balance]() : $ 0.00 (por defecto)
* [Type]() : Cuenta corriente

La raíz del proyecto cuenta con archivo JSON de colección de postman con los end points
básicos para las pruebas fundamentales de:

1. Chequeo de balance
2. Acreditar una cantidad al balance
3. Debitar una cantidad del balance

Adicionalmente, está disponible el link de acceso para probar la
aplicación mediante el Swagger/UI
** * 
## Preconditions

La App esta construida usando el framework Spring Boot y el manejo de dependencias con Maven.

Para correr la aplicación se debe verificar y comprobar que están instalados: 

* [Versión Java SE - JDK](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/module-summary.html) - The Java Platform, Standard Edition (Java SE) / The Java Development Kit (JDK) APIs
```
java --version
```
* [Versión Maven](https://maven.apache.org/) - Dependency Management
```
mvn -version
```
** * 
## Ejecutando aplicación

Ubicando el ejecutable .JAR correr por consola el comando:

```
java -jar .\capsulachallenge-1.0.jar
```

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

La app esta configurada para aceptar cualquier valor numérico mayor que cero.

```
http://localhost:8080/api/v1/accounts/account/balance/credit/C1/1000
```
** * 
### Debitar un monto

Acepta cualquier valor numérico mayor que cero.
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
La App cuenta con pruebas unitarias y de integración utilizando Junit5. 

Opciones de prueba mediante la SwaggerIU:

```
http://localhost:8080/swagger-ui.html#/
```

En la raiz del proyecto se encuentra un archivo con colección de postman
```
BankAccount - Cápsula Challenge Entregable.postman_collection.json
```
** *

## Documentación

* Vía Swagger con el link: 
```
http://localhost:8080/swagger-ui.html#/
```
** * 
