# Documentación de API - Gestor de Reservas de Pádel

## Descripción General

Esta API REST proporciona endpoints para gestionar un sistema completo de reservas de pistas de pádel. Incluye gestión de usuarios, pistas, sus correspondientes reservas y autenticación mediante JWT.

## URL Base

```
http://localhost:8080
```

---

## 📋 Tabla de Contenidos

1. [Autenticación](#autenticación)
2. [Usuarios](#usuarios)
3. [Pistas](#pistas)
4. [Reservas](#reservas)
5. [Respuestas de Error](#respuestas-de-error)
6. [Información de Seguridad](#información-de-seguridad)

---

## Autenticación

### 1. Registrar Usuario
**POST** `/api/auth/register`

Crea un nuevo usuario y genera un token JWT para acceder a la API.

**Request Body:**
```json
{
  "nombre": "Juan García",
  "email": "juan@example.com",
  "password": "securePassword123"
}
```

**Response (201 Created):**
```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
  "email": "juan@example.com",
  "nombre": "Juan García",
  "rol": "USER"
}
```

**Response (400 Bad Request):**
```json
{
  "error": "Email ya registrado"
}
```

---

### 2. Login
**POST** `/api/auth/login`

Autentica un usuario existente y genera un token JWT.

**Request Body:**
```json
{
  "email": "juan@example.com",
  "password": "securePassword123"
}
```

**Response (200 OK):**
```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
  "email": "juan@example.com",
  "nombre": "Juan García",
  "rol": "USER"
}
```

**Response (401 Unauthorized):**
```json
{
  "error": "Credenciales inválidas"
}
```

## Usuarios

> ⚠️ **Requiere autenticación**: Todos los endpoints de usuarios requieren un token JWT válido en el header `Authorization: Bearer {token}`

### 1. Crear Usuario
**POST** `/api/usuarios`

Crea un nuevo usuario en el sistema.

**Headers:**
```
Authorization: Bearer {token}
Content-Type: application/json
```

**Request Body:**
```json
{
  "nombre": "Juan García",
  "email": "juan@example.com",
  "password": "securePassword123"
}
```

**Response (201 Created):**
```json
{
  "id": 1,
  "nombre": "Juan García",
  "email": "juan@example.com",
  "password": "securePassword123"
}
```

---

### 2. Obtener Todos los Usuarios
**GET** `/api/usuarios`

Obtiene la lista completa de todos los usuarios registrados.

**Headers:**
```
Authorization: Bearer {token}
```

**Response (200 OK):**
```json
[
  {
    "id": 1,
    "nombre": "Juan García",
    "email": "juan@example.com",
    "password": "securePassword123"
  },
  {
    "id": 2,
    "nombre": "María López",
    "email": "maria@example.com",
    "password": "anotherPassword456"
  }
]
```

---

### 3. Obtener Usuario por ID
**GET** `/api/usuarios/{id}`

Obtiene los detalles de un usuario específico por su identificador.

**Headers:**
```
Authorization: Bearer {token}
```

**Parámetros:**
- `id` (path parameter, required): Identificador único del usuario

**Response (200 OK):**
```json
{
  "id": 1,
  "nombre": "Juan García",
  "email": "juan@example.com",
  "password": "securePassword123"
}
```

**Response (404 Not Found):**
```json
{
  "error": "Usuario no encontrado"
}
```

---

### 4. Actualizar Usuario
**PUT** `/api/usuarios/{id}`

Actualiza los datos de un usuario existente.

**Headers:**
```
Authorization: Bearer {token}
Content-Type: application/json
```

**Parámetros:**
- `id` (path parameter, required): Identificador único del usuario

**Request Body:**
```json
{
  "nombre": "Juan García Actualizado",
  "email": "juanactualizado@example.com",
  "password": "newSecurePassword789"
}
```

**Response (200 OK):**
```json
{
  "id": 1,
  "nombre": "Juan García Actualizado",
  "email": "juanactualizado@example.com",
  "password": "newSecurePassword789"
}
```

---

### 5. Eliminar Usuario
**DELETE** `/api/usuarios/{id}`

Elimina un usuario del sistema. **Nota:** Se eliminarán todas sus reservas asociadas.

**Headers:**
```
Authorization: Bearer {token}
```

**Parámetros:**
- `id` (path parameter, required): Identificador único del usuario

**Response (204 No Content):**
```
(Sin contenido)
```

**Response (404 Not Found):**
```json
{
  "error": "Usuario no encontrado"
}
```

---

## Pistas

> ⚠️ **Requiere autenticación**: Todos los endpoints de pistas requieren un token JWT válido en el header `Authorization: Bearer {token}`

### 1. Crear Pista
**POST** `/api/pistas`

Crea una nueva pista en el sistema.

**Headers:**
```
Authorization: Bearer {token}
Content-Type: application/json
```

**Request Body:**
```json
{
  "nombre": "Pista Norte",
  "ubicacion": "Primera planta, sector A",
  "disponible": true
}
```

**Response (201 Created):**
```json
{
  "id": 1,
  "nombre": "Pista Norte",
  "ubicacion": "Primera planta, sector A",
  "disponible": true
}
```

---

### 2. Obtener Todas las Pistas
**GET** `/api/pistas`

Obtiene la lista completa de todas las pistas disponibles.

**Headers:**
```
Authorization: Bearer {token}
```

**Response (200 OK):**
```json
[
  {
    "id": 1,
    "nombre": "Pista Norte",
    "ubicacion": "Primera planta, sector A",
    "disponible": true
  },
  {
    "id": 2,
    "nombre": "Pista Sur",
    "ubicacion": "Primera planta, sector B",
    "disponible": true
  },
  {
    "id": 3,
    "nombre": "Pista Este",
    "ubicacion": "Segunda planta, sector C",
    "disponible": false
  }
]
```

---

### 3. Obtener Pista por ID
**GET** `/api/pistas/{id}`

Obtiene los detalles de una pista específica.

**Headers:**
```
Authorization: Bearer {token}
```

**Parámetros:**
- `id` (path parameter, required): Identificador único de la pista

**Response (200 OK):**
```json
{
  "id": 1,
  "nombre": "Pista Norte",
  "ubicacion": "Primera planta, sector A",
  "disponible": true
}
```

**Response (404 Not Found):**
```json
{
  "error": "Pista no encontrada"
}
```

---

### 4. Actualizar Pista
**PUT** `/api/pistas/{id}`

Actualiza los datos de una pista existente.

**Headers:**
```
Authorization: Bearer {token}
Content-Type: application/json
```

**Parámetros:**
- `id` (path parameter, required): Identificador único de la pista

**Request Body:**
```json
{
  "nombre": "Pista Norte Premium",
  "ubicacion": "Primera planta, sector A",
  "disponible": false
}
```

**Response (200 OK):**
```json
{
  "id": 1,
  "nombre": "Pista Norte Premium",
  "ubicacion": "Primera planta, sector A",
  "disponible": false
}
```

---

### 5. Eliminar Pista
**DELETE** `/api/pistas/{id}`

Elimina una pista del sistema.

**Parámetros:**
- `id` (path parameter, required): Identificador único de la pista

**Response (204 No Content):**
```
(Sin contenido)
```

**Response (404 Not Found):**
```json
{
  "error": "Pista no encontrada"
}
```

---

## Reservas

### 1. Crear Reserva
**POST** `/api/reservas`

Crea una nueva reserva asociando un usuario con una pista en una fecha y horario específicos.

**Horarios válidos disponibles:**
- 17:30 - 19:00
- 19:00 - 20:30
- 20:30 - 22:00
- 22:00 - 23:30

**Request Body:**
```json
{
  "fecha": "2026-05-15",
  "horaInicio": "17:30",
  "horaFin": "19:00",
  "usuarioId": 1,
  "pistaId": 1
}
```

**Response (201 Created):**
```json
{
  "id": 1,
  "fecha": "2026-05-15",
  "horaInicio": "17:30",
  "horaFin": "19:00",
  "usuarioId": 1,
  "pistaId": 1
}
```

**Response (400 Bad Request):**
Posibles errores:
- Horario no permitido
- Usuario no encontrado
- Pista no encontrada
- Reserva duplicada (misma pista, fecha y horario)

```json
{
  "error": "Ya existe una reserva para esta pista en la fecha y horario especificados"
}
```

---

### 2. Obtener Todas las Reservas
**GET** `/api/reservas`

Obtiene el listado completo de todas las reservas del sistema.

**Response (200 OK):**
```json
[
  {
    "id": 1,
    "fecha": "2026-05-15",
    "horaInicio": "17:30",
    "horaFin": "19:00",
    "usuarioId": 1,
    "pistaId": 1
  },
  {
    "id": 2,
    "fecha": "2026-05-16",
    "horaInicio": "19:00",
    "horaFin": "20:30",
    "usuarioId": 2,
    "pistaId": 2
  }
]
```

---

### 3. Eliminar Reserva
**DELETE** `/api/reservas/{id}`

Cancela una reserva existente.

**Parámetros:**
- `id` (path parameter, required): Identificador único de la reserva

**Response (204 No Content):**
```
(Sin contenido)
```

**Response (404 Not Found):**
```json
{
  "error": "Reserva no encontrada"
}
```

---

## Respuestas de Error

### 400 - Bad Request
Se retorna cuando los datos proporcionados son inválidos o incompletos.

```json
{
  "error": "Descripción del error específico"
}
```

### 404 - Not Found
Se retorna cuando el recurso solicitado no existe.

```json
{
  "error": "Recurso no encontrado"
}
```

### 500 - Internal Server Error
Se retorna cuando ocurre un error en el servidor.

```json
{
  "error": "Error interno del servidor"
}
```

---

## Ejemplos de Uso

### Ejemplo 1: Crear un usuario y hacer una reserva

1. **Crear usuario:**
```bash
curl -X POST http://localhost:8080/api/usuarios \
  -H "Content-Type: application/json" \
  -d '{
    "nombre": "Carlos Ruiz",
    "email": "carlos@example.com",
    "password": "password123"
  }'
```

2. **Crear pista:**
```bash
curl -X POST http://localhost:8080/api/pistas \
  -H "Content-Type: application/json" \
  -d '{
    "nombre": "Pista Oeste",
    "ubicacion": "Segunda planta, sector D",
    "disponible": true
  }'
```

3. **Crear reserva:**
```bash
curl -X POST http://localhost:8080/api/reservas \
  -H "Content-Type: application/json" \
  -d '{
    "fecha": "2026-06-01",
    "horaInicio": "20:30",
    "horaFin": "22:00",
    "usuarioId": 1,
    "pistaId": 1
  }'
```

### Ejemplo 2: Obtener todas las reservas

```bash
curl -X GET http://localhost:8080/api/reservas \
  -H "Content-Type: application/json"
```

### Ejemplo 3: Actualizar usuario

```bash
curl -X PUT http://localhost:8080/api/usuarios/1 \
  -H "Content-Type: application/json" \
  -d '{
    "nombre": "Carlos Ruiz García",
    "email": "carlos.ruiz@example.com",
    "password": "newPassword456"
  }'
```

---

## Notas Importantes

1. **Validación de Horarios**: Las reservas solo pueden crearse en los horarios permitidos (17:30-19:00, 19:00-20:30, 20:30-22:00, 22:00-23:30).

2. **Reservas Duplicadas**: No se permite crear dos reservas para la misma pista en la misma fecha y horario.

3. **Eliminación en Cascada**: Al eliminar un usuario, se eliminarán todas sus reservas asociadas.

4. **Fecha**: Se debe proporcionar en formato ISO 8601 (YYYY-MM-DD).

5. **Hora**: Se debe proporcionar en formato HH:mm (24 horas).

---

## Información de Versión

- **Versión API**: 1.0
- **Última actualización**: 2026-04-28
- **Framework**: Spring Boot 3.x
- **Base de Datos**: JPA/Hibernate


