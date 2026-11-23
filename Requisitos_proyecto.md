# Proyecto intermodular · 2º DAM

**IES Juan Bosco · Curso 2026-2027**

---

## 1. Descripción general

El proyecto intermodular busca evaluar los conocimientos adquiridos a lo largo del ciclo de Desarrollo de Aplicaciones Multiplataforma. Por ello, sus requisitos mínimos integran contenidos de los distintos módulos del ciclo.

Cada alumno o alumna desarrollará **de forma individual** el proyecto original y completo de una aplicación funcional que incluya, como mínimo, una base de datos relacional, una API REST que dé acceso a esos datos y al menos una aplicación cliente (móvil o de escritorio) que, de manera clara y amigable, acceda a esa información y la represente.

Al ser individual, este proyecto está pensado también como **portfolio profesional**: una muestra pública de lo que sabes hacer, que podrás enseñar en procesos de selección una vez evaluado.

La temática es libre, pero debe contar con el **visto bueno de la profesora** antes de empezar el desarrollo (ver Hito 1).

## 2. Calificación

La nota final del proyecto se compone de tres partes:

| Parte | Peso |
| --- | --- |
| Aplicación | 50 % |
| Documentación (memoria) | 30 % |
| Presentación oral y defensa | 20 % |

Para superar el proyecto es necesario obtener una calificación mínima de **5 puntos**.

## 3. Integración de módulos

El proyecto es transversal. La siguiente tabla recoge qué debe aportar cada módulo y dónde se evidencia:

| Módulo | Requisito mínimo | Evidencia |
|---|---|---|
| Acceso a Datos | Base de datos relacional con los mínimos del apartado 4.1, acceso mediante JDBC u ORM | Script SQL, código de la capa de persistencia |
| Programación de Servicios y Procesos | API REST propia que exponga los datos de la aplicación | Carpeta `api/` del repositorio, documentación de los endpoints |
| Diseño de Interfaces | Interfaz usable y accesible, prototipo previo, guía de estilo coherente | Prototipo y guía de estilo en la memoria, aplicación cliente |
| Programación Multimedia y Dispositivos Móviles | Al menos un cliente móvil (Android) que consuma la API | Carpeta `android/` del repositorio |
| Sistemas de Gestión Empresarial | Módulo propio de Odoo integrado con el proyecto | Carpeta `odoo/` del repositorio |

## 4. Aplicación

### 4.1. Base de datos

Requisitos mínimos:

- Base de datos relacional con **al menos 4 tablas**.
- Todas las tablas deben tener un **identificador único como clave primaria**.
- Al menos una columna de alguna tabla debe almacenar **fechas** (fecha de creación, de nacimiento, de lectura…).
- Relaciones entre tablas mediante claves foráneas.
- Script SQL que genere la estructura completa de la base de datos y cargue datos de prueba.

Sistemas gestores admitidos: MySQL/MariaDB, PostgreSQL, Oracle, SQLite. Cualquier otro requiere visto bueno previo.

### 4.2. API REST

- API propia que dé acceso a los datos de la aplicación mediante peticiones HTTP y respuestas JSON.
- Tecnologías recomendadas: Java con Spring Boot. Otras opciones requieren visto bueno previo.
- Documentación de los endpoints (por ejemplo con Swagger/OpenAPI o una tabla en la memoria).

### 4.3. Aplicación cliente

- Al menos una aplicación cliente que consuma la API: Android (Java o Kotlin). Se puede añadir un cliente de escritorio o web adicional.
- La aplicación debe cumplir criterios de **usabilidad y accesibilidad**, independientemente de los conocimientos del usuario final: textos legibles, contraste suficiente, mensajes de error comprensibles, navegación coherente.
- Todos los formularios, vistas e informes deben estar **validados y probados**.

### 4.4. Funcionalidades obligatorias

Además del contenido propio de cada proyecto, la aplicación debe incluir:

- **Registro de usuarios** con CRUD completo (alta, consulta, modificación y baja).
- **Al menos un usuario con rol de administrador**, con permisos diferenciados respecto al resto de usuarios.
- **Login** de usuarios.
- **Logotipo original** de la aplicación. Puede generarse con IA, indicándolo en la declaración de uso de IA de la memoria.
- **Sección «Sobre mí»** con un enlace al CV o perfil profesional del autor (LinkedIn, web personal, perfil de GitHub…).

### 4.5. Seguridad

- Las contraseñas se almacenarán **cifradas mediante una función hash**, nunca en texto plano.
- **No se subirán credenciales al repositorio** (contraseñas de la base de datos, claves de API, tokens). Se usarán ficheros de configuración externos (`.env`, `application.properties` local…) incluidos en `.gitignore`, y se subirá en su lugar un fichero de ejemplo (`.env.example`) sin valores reales.
- Control de acceso según el rol del usuario: las operaciones de administración solo deben estar disponibles para administradores.

### 4.6. Protección de datos

- Si la aplicación almacena datos personales, la memoria debe incluir una breve reflexión sobre qué datos se recogen, para qué y cómo se protegen, con referencia al RGPD.
- Los datos de prueba deben ser **ficticios**. No se usarán datos reales de personas en ningún caso.

### 4.7. Pruebas

- Pruebas unitarias de, al menos, la lógica principal de la API.
- Plan de pruebas documentado en la memoria: qué se ha probado, cómo y con qué resultado (incluidas las pruebas de los formularios y vistas).

### 4.8. Dependencias externas

Cualquier módulo, librería o servicio adicional (librerías Java o Android, servicios externos…) debe indicarse en la memoria, junto con su configuración.

## 5. Repositorio en GitHub

### 5.1. Condiciones

- El repositorio se creará en la **cuenta personal de GitHub** del alumno.
- El nombre del repositorio será **el nombre de la aplicación**.
- El repositorio será **privado** hasta la presentación y calificación del proyecto, y se compartirá con la profesora añadiéndola como colaboradora.
- Tras la publicación de las notas, se recomienda hacerlo **público** para usarlo como portfolio. Antes de hacerlo, revisa que no queden credenciales ni datos personales en el historial y añade una licencia.
- El `README.md` de la raíz debe describir brevemente la aplicación, explicar cómo ponerla en marcha y **enlazar a la memoria** en la carpeta `docs/`.

### 5.2. Estructura

El repositorio debe separar la documentación de las distintas aplicaciones que forman el proyecto:

```
NombreDeLaApp/
├── README.md               ← descripción, puesta en marcha y enlace a la memoria
├── .gitignore
├── docs/
│   ├── memoria.md          ← memoria del proyecto (a partir de la plantilla)
│   ├── img/                ← capturas, diagramas exportados, logotipo
│   └── documentacionNombreDeLaApp.pdf
├── database/
│   └── scriptNombreDeLaApp.sql
├── api/                    ← código de la API REST
├── android/                ← aplicación móvil 
├── odoo/                   ← módulo de Odoo 
└── presentacion/
    └── presentacionNombreDeLaApp.pdf
```

Si el proyecto incluye otros clientes (escritorio, web…), cada uno tendrá su propia carpeta en la raíz.

### 5.3. Forma de trabajo

Se valorará el uso de las buenas prácticas trabajadas en clase: commits frecuentes con mensajes descriptivos, ramas por funcionalidad, issues para organizar las tareas y un tablero en GitHub Projects para la planificación.

## 6. Documentación (memoria)

- La memoria se redactará en **Markdown**, partiendo de la plantilla facilitada, y estará en la carpeta `docs/` del repositorio.
- Los diagramas (entidad-relación, arquitectura, casos de uso, planificación) se pueden hacer con **Mermaid** directamente en el Markdown, o como imágenes en `docs/img/`.
- Para la entrega se generará además un **PDF** a partir del Markdown (por ejemplo con una extensión de VS Code o con Pandoc), comprobando que los diagramas y las imágenes se ven correctamente.
- Se debe cuidar la ortografía, la redacción y la presentación.
- La memoria debe cubrir, al menos, los apartados de la plantilla. Pueden reordenarse o renombrarse si el proyecto lo requiere, siempre que el contenido esté presente.

### 6.1. Uso de inteligencia artificial generativa

El uso de herramientas de IA generativa **está permitido**, pero debe declararse en el apartado correspondiente de la memoria, indicando qué herramienta se ha usado, para qué, en qué parte del proyecto y cómo se ha revisado o adaptado el resultado.

En la defensa, el alumno debe ser capaz de **explicar cualquier parte del código y de la documentación entregados**, independientemente de cómo se hayan generado.

## 7. Presentación oral y defensa

- Exposición de **entre 10 y 15 minutos** ante el equipo docente, con el apoyo multimedia que se considere oportuno (presentación, imágenes, vídeo…), incluyendo una **demostración** de la aplicación.
- A continuación se abrirá un turno de preguntas que el alumno deberá responder.
- Es **obligatorio participar** planteando preguntas a los compañeros que presentan.

### 7.1. Rúbrica de la exposición oral y defensa

| Categoría | 5 · Sobresaliente | 3 · Notable | 1 · Aprobado | 0 · Insuficiente |
|---|---|---|---|---|
| **Contenido** | Se nota un buen dominio del tema, no comete errores, no duda | Demuestra un buen entendimiento de partes del tema. Exposición fluida, comete pocos errores | Tiene que hacer algunas rectificaciones y en ocasiones duda | Rectifica continuamente. El contenido es mínimo, no muestra conocimiento del tema |
| **Organización de la información** | La información está bien organizada, de forma clara y lógica | La mayor parte de la información se organiza de forma clara y lógica, aunque alguna diapositiva está fuera de lugar | No existe un plan claro para organizar la información, cierta dispersión | La información aparece dispersa y poco organizada |
| **Exposición** | Atrae la atención del público y mantiene el interés durante toda la exposición | Interesa bastante al principio, pero se hace un poco monótono | Le cuesta conseguir o mantener el interés del público | Apenas usa recursos para mantener la atención del público |
| **Expresión oral** | Habla con claridad durante toda la presentación. Pronunciación correcta y tono de voz adecuado | Habla con claridad la mayor parte del tiempo. Pronunciación aceptable, con alguna pausa innecesaria. Tono adecuado | Algunas veces habla con claridad. Recurre con frecuencia a pausas innecesarias. Tono de voz poco adecuado | La mayor parte del tiempo no habla con claridad. Muchas pausas y muletillas. Tono inadecuado para mantener el interés |
| **Lenguaje no verbal** | Buena postura y seguridad durante toda la presentación. Contacto visual con todos los presentes | Buena postura la mayor parte del tiempo y contacto visual con los presentes. En ocasiones se muestra inseguro | Algunas veces tiene buena postura y en ocasiones establece contacto visual. Muestra inseguridad | Mala postura, sin contacto visual con los presentes. Gran inseguridad |
| **Tiempo** | Tiempo ajustado al previsto, con un final que retoma las ideas principales | Tiempo ajustado, pero con un final precipitado o alargado por falta de control | Tiempo no ajustado. Exposición excesivamente corta | Excesivamente larga o insuficiente para desarrollar el tema |
| **Soporte** | Soportes visuales especialmente atractivos y de mucha calidad | Soportes visuales adecuados e interesantes | Soportes visuales adecuados pero poco interesantes | Soportes visuales inadecuados |
| **Demostración** | Demuestra la utilidad y el funcionamiento de la aplicación con claridad y sin fallos | Demuestra la utilidad y el funcionamiento, pero sin suficiente claridad o con algún fallo menor | Demuestra solo parcialmente la utilidad o el funcionamiento de la aplicación | No demuestra ni la utilidad ni el funcionamiento |
| **Comprensión y argumentación** | Contesta con precisión todas las preguntas planteadas | Contesta con precisión la mayoría de las preguntas | Contesta con precisión solo algunas preguntas | No puede contestar las preguntas planteadas |

## 8. Calendario y entregas

### 8.1. Entregas

Todas las entregas se realizarán, sin excepción, a través del **Aula Virtual** del proyecto, mediante tareas con fecha límite.

En cada entrega:

1. Crea en GitHub un **tag o release** con el nombre del hito (`hito1`, `hito2`…).
2. Sube al Aula Virtual el **enlace a esa release** y el **ZIP** descargado de ella.

### 8.2. Hito 1 · Anteproyecto

**Fecha límite: 16 de noviembre de 2026**

Contenido:

- Repositorio creado en tu cuenta personal con el nombre de la aplicación, privado y compartido con la profesora.
- `README.md` inicial con el nombre y una descripción breve de la aplicación.
- `docs/memoria.md` creado a partir de la plantilla, con el apartado de **Introducción** completado: presentación, objetivos y justificación del proyecto.
- Tecnologías previstas para cada parte (base de datos, API, cliente) y módulo de Odoo.
- Primer boceto de las tablas de la base de datos (puede ser una lista o un diagrama sencillo).

La profesora dará el visto bueno o propondrá cambios antes de continuar.

### 8.3. Siguientes hitos

Las fechas y el contenido de los siguientes hitos se publicarán en el Aula Virtual a lo largo del curso, teniendo en cuenta el periodo de formación en empresa.

### 8.4. Entrega final

La entrega final incluirá:

- Código fuente de todas las aplicaciones del proyecto.
- Memoria en Markdown (`docs/memoria.md`) y en PDF (`documentacionNombreDeLaApp.pdf`).
- Script de la base de datos (`scriptNombreDeLaApp.sql`), que genere una base de datos llamada `nombreApp`.
- Presentación utilizada en la defensa (`presentacionNombreDeLaApp`).
- `README.md` con los pasos necesarios para poner en marcha la aplicación.
