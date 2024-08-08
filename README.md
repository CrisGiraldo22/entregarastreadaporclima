# Entrega Rastreada por Clima

Este es un proyecto de demostración para una aplicación Spring Boot que rastrea entregas basado en información meteorológica. Utiliza Spring Boot, JPA, y una base de datos en memoria H2 para su funcionamiento.

## Descripción

La aplicación permite gestionar entregas y proporciona información basada en el clima utilizando una API de clima. Incluye una configuración básica para el correo electrónico y una consola web para la base de datos H2.

## Instalación

Sigue estos pasos para configurar y ejecutar el proyecto:

1. *Clona el repositorio:*
   ```bash
   git clone https://github.com/CrisGiraldo22/entregarastreadaporclima.git

Navega al directorio del proyecto:
cd/entregarastreadaporclima

Construye el proyecto con Maven: mvn clean install

Ejecuta la aplicación: mvn spring-boot:run

Configuración

Configuración del Puerto del Servidor
El servidor se ejecuta en el puerto 8080 por defecto.

Configuración de la Base de Datos
Tipo: H2 en memoria
URL: jdbc:h2:mem:deliverydb;DB_CLOSE_DELAY=-1
Usuario: sa
Contraseña: (vacío)

Configuración de JPA
DDL Auto: update
Mostrar SQL: true
Formato SQL: true

Configuración de Correo Electrónico
Host: localhost
Puerto: 1025
Autenticación: false
STARTTLS: false
API Key para WeatherAPI
API Key: Reemplaza tu_api_key con tu API key real en el archivo application.properties.


Configuración de Logging
Nivel de logging root: INFO
Nivel de logging para Spring Web: DEBUG
Consola Web de H2
Habilitada: Sí
Ruta: /h2-console

Uso
Accede a la aplicación: Abre tu navegador y visita http://localhost:8080.
Accede a la consola web de H2: Visita http://localhost:8080/h2-console para ver y administrar la base de datos H2.

