Hola no me ha dado tiempo a completar los docs de postman pero fucniona y se desplego en railway 





Puedes utilizar cualquier editor de código como IntelliJ IDEA o herramienta que desees para completar el
examen, incluyendo conexión a internet. No se permite compartir código, el examen es personal e
intransferible y los datos que introduzcáis en el desarrollo deberán ser únicos, tanto en caracteres, nombres,
funciones, etc...
El examen se compone de 3 fases que debes leer antes de empezar. Lee atentamente todo, incluyendo la
rúbrica del examen.
Objetivo:
Implementar una API RESTful para gestionar un sistema de alquiler de coches con una relación básica entre
usuarios y alquileres. La API debe cumplir con los siguientes requisitos



1. Entidades principales
Usuario (Un usuario puede tener varios alquileres)
• id (Long, autogenerado)
• username (String, único, no nulo)
• email (String, único, no nulo)
• licenseNumber (String, único, no nulo)
• Añade 2 campos más en esta entidad y que empiece con tus iniciales acmm_*.

Coche
• id (Long, autogenerado)
• plateNumber (String, único, no nulo)
• brand (String, no nulo)
• model (String, no nulo)
• year (Integer, no nulo)
• available (Boolean, por defecto true)
• Añade 1 campo más en esta entidad y que empiece con tu apellido_*: marin_*.

Alquiler (Un usuario puede tener varios alquileres, pero un alquiler pertenece a un solo usuario y un solo
coche)
• id (Long, autogenerado)
• userId (Long, referencia a Usuario)
• carId (Long, referencia a Coche)
• rentalDate (LocalDateTime)
• returnDate (LocalDateTime, puede ser nulo si no ha sido devuelto)
• status (Enum: ACTIVE, COMPLETED)
• En esta entidad 2 campos tienen que empezar con tu nombre_*: andrey_*.

2. Endpoints/Servicios que hay que desarrollar con los patrones de arquitectura correctos (Model,
Repository, Service, Controller):
a) CRUD de Usuario y Coche
Crear, leer, actualizar y eliminar usuarios
Crear, leer, actualizar y eliminar coches

b) Sistema de alquileres
POST /rentals → Un usuario puede alquilar un coche (Verificar disponibilidad).
PUT /rentals/{id}/return → Un usuario puede devolver un coche (Actualizar disponibilidad).
GET /users/{id}/rentals → Obtener el historial de alquileres de un usuario

c) Búsqueda de coches
GET /cars?brand={brand}&model={model} → Buscar coches por marca o modelo.
GET /cars/available → Obtener coches disponibles para alquilar

3. Postman y publicación en Railway
Tienes que desarrollar la documentación en Postman (POST, PUT, DELETE, GET…) de manera correcta en cada
endpoint/servicio. Documenta cada campo y pon ejemplos, de uso; ¡¡recuerda guardar y publicar!!. Es
importante entregar la collection v2.1 o bien la dirección de donde está publicada la documentación.
Sube el proyecto a Railway y github (invítame al repo PRIVADO andreycattalin). Recuerda apuntar la
documentación de Postman a la URL del servidor de Railway.
Entregables:
- Las urls que envíes de railway y postman deben ser accesibles públicamente.
- Github (varios commits) y ZIP (no rar) del proyecto
#   e x a m e n - A D A T - M a t e o - C o v a c h o - B e r r o c a l 
 
 