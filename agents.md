# Agentes en la API de Gestor de Reservas de Pádel

Esta documentación describe los agentes principales que componen la API de gestión de reservas de pistas de pádel. Cada agente está representado por un controlador REST que maneja operaciones específicas del sistema.

## Usuario Agent
- **Controlador**: `UsuarioController`
- **Responsabilidades**: Gestiona las cuentas de usuario, incluyendo creación, consulta, actualización y eliminación de usuarios.
- **Endpoints**: `/api/usuarios`
- **Operaciones**:
  - Crear usuario (POST)
  - Obtener todos los usuarios (GET)
  - Obtener usuario por ID (GET)
  - Actualizar usuario (PUT)
  - Eliminar usuario (DELETE)

## Pista Agent
- **Controlador**: `PistaController`
- **Responsabilidades**: Gestiona las pistas de pádel, incluyendo creación, consulta, actualización y eliminación de pistas.
- **Endpoints**: `/api/pistas`
- **Operaciones**:
  - Crear pista (POST)
  - Obtener todas las pistas (GET)
  - Obtener pista por ID (GET)
  - Actualizar pista (PUT)
  - Eliminar pista (DELETE)

## Reserva Agent
- **Controlador**: `ReservaController`
- **Responsabilidades**: Gestiona las reservas de pistas, incluyendo creación, consulta y eliminación de reservas.
- **Endpoints**: `/api/reservas`
- **Operaciones**:
  - Crear reserva (POST)
  - Obtener todas las reservas (GET)
  - Eliminar reserva (DELETE)

## Notas
- Cada agente utiliza servicios y repositorios correspondientes para interactuar con la base de datos.
- La API incluye validaciones de negocio, como horarios permitidos para reservas y prevención de duplicados.
- Los agentes están implementados usando Spring Boot con JPA/Hibernate.</content>
<parameter name="filePath">C:\Users\Mamen\Desktop\DAM\Programación de Aplicaciones Utilizando Framework\UT 6.- Framework de diseño\TAREA 06\QUINTO INTENTO\gestor-reservas-padel-master\agents.md

## Personalizado
