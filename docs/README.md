## 01. ¿Qué ventaja ofrece el polimorfismo en el diseño de clases frente al uso de múltiples condicionales para determinar el comportamiento de un objeto?

El polimorfismo permite que diferentes objetos respondan de manera distinta al mismo método. Esto evita tener muchos `if` o `switch` para determinar el comportamiento y hace que el código sea más flexible, limpio y fácil de mantener.

## 02. ¿Por qué una clase inmutable puede mejorar la seguridad en un sistema?

Una clase inmutable no permite modificar su estado después de crear el objeto. Esto mejora la seguridad porque evita cambios inesperados en los datos y hace que los objetos sean más predecibles y seguros de compartir.

## 03. ¿Qué problema podría aparecer en un sistema si los atributos de las clases se mantienen públicos en lugar de privados con getters y setters controlados?

Si los atributos son públicos, cualquier parte del programa puede modificarlos directamente, lo que puede generar datos inválidos o comportamientos inesperados. Usar atributos privados con getters y setters permite controlar cómo se accede y modifica la información.

## 04. Según el principio Abierto/Cerrado, ¿cómo deberíamos modificar el sistema si queremos añadir una nueva funcionalidad sin alterar el código existente?

Según el Principio Abierto/Cerrado, el sistema debe estar abierto para extensión, pero cerrado para modificación. Para agregar una funcionalidad, debemos crear nuevas clases o implementar nuevas interfaces sin modificar el código existente.

## 05. ¿Por qué es importante que una clase cumpla con el Principio de Única Responsabilidad? Da un ejemplo donde se vulnere.

Es importante porque permite que cada clase tenga una responsabilidad clara, haciendo que el código sea más fácil de entender, mantener y modificar. Por ejemplo, se viola este principio cuando una clase calcula el salario de un empleado y además se encarga de generar reportes, ya que tiene dos responsabilidades diferentes.

## 06. ¿Qué es y para qué usamos el pom.xml?

El archivo pom.xml es el archivo de configuración principal de un proyecto Maven. Allí se definen las dependencias, información del proyecto, plugins y otras configuraciones necesarias para construir y administrar el proyecto.

## 07. ¿Qué diferencia hay entre mvn compile, mvn package y mvn install?

* mvn compile: compila el código fuente del proyecto.
* mvn package: compila el proyecto y genera el paquete, por ejemplo un archivo .jar.
* mvn install: realiza el proceso de package y además instala el paquete en el repositorio local de Maven para que pueda ser utilizado por otros proyectos.

## 08. ¿Qué diferencia existe entre una interfaz y una clase abstracta?

Una interfaz define principalmente un contrato que las clases deben cumplir, mientras que una clase abstracta puede definir tanto métodos abstractos como métodos con implementación. Una clase puede implementar varias interfaces, pero solo puede heredar de una clase, sea abstracta o no.


## Reto 1 — La Boletería del Cine Astor

Sistema de boletería que permite armar una orden con boletas y confitería, aplicar el descuento según el tipo de espectador y generar la factura final.

Entrada esperada por línea de la orden: codigo cantidad (ej. boleta3d 2).
Códigos disponibles: `boleta2d`, `boleta3d`, `crispetas`, `gaseosa`. Se termina
escribiendo fin.

## Patrón de diseño utilizado

- **Categoría:** Comportamiento
- **Patrón:** **Strategy**
- **Justificación:** el cálculo del descuento depende del tipo de espectador
  (General 0%, Estudiante 15%, Tercera edad 25%) y ese algoritmo debe poder
  crecer (nuevos tipos de espectador) sin modificar la clase que arma la
  factura. Strategy encapsula cada algoritmo de descuento en su propia clase
  y permite intercambiarlos en tiempo de ejecución.
- **Cómo se aplicó:**
    - TipoEspectador (interfaz): declara el contrato calcularDescuento(subtotal).
    - EspectadorGeneral, Estudiante, TerceraEdad: estrategias concretas,
      cada una con su propio porcentaje.
    - Orden: el "contexto": recibe una `TipoEspectador` por constructor y le
      delega el cálculo, sin conocer los detalles de cada tipo.

Complementariamente se usó una jerarquía por herencia (Producto → Boleta,
ArticuloConfiteria) para representar los ítems del catálogo de forma
polimórfica y extensible.

## Principios SOLID aplicados

| Principio | Dónde | Cómo |
|-------|---|---|
| **SRP** | Orden, TipoEspectador, ItemOrden | Cada clase tiene una única responsabilidad: Orden coordina la orden, TipoEspectador calcula el descuento, ItemOrden calcula el subtotal de su línea. |
| **OCP** | TipoEspectador, Producto | Se pueden agregar nuevos tipos de espectador o nuevos productos creando nuevas subclases, sin modificar Orden. |
| **LSP** | Boleta/ArticuloConfiteria sobre Producto; cualquier TipoEspectador sobre la interfaz | Cualquier subtipo puede usarse donde se espera el tipo base sin romper el comportamiento de Orden. |
| **ISP** | TipoEspectador | Interfaz mínima con un solo método relevante (calcularDescuento), ningún cliente depende de métodos que no usa. |
| **DIP** | Orden | Depende de la abstracción TipoEspectador, no de una implementación concreta; la estrategia se inyecta por constructor. |

---

<!-- Espacio reservado para documentación de Reto 2, Reto 3, Reto 4 y Reto 5 -->


---

## Reto 6 — Sala de Urgencias

- **Patrón de Diseño:** Comportamiento
- **Patrón Utilizado:** Chain of Responsibility
- **Justificación:** el flujo de atención médica requiere que una solicitud (el paciente con síntoma, gravedad y prioridad) sea evaluada secuencialmente por una serie de profesionales con distintas competencias. Cada profesional analiza si puede resolver el caso; si no está capacitado, delega la responsabilidad al siguiente eslabón de la cadena de forma desacoplada, hasta llegar al final donde se marca como remitido a otra institución si ningún profesional pudo atenderlo.
- **Cómo lo aplicamos:**
    - ProfesionalSalud: clase abstracta base (Handler) que define el contrato procesar(paciente), el enlace al siguiente profesional (siguiente) y la lógica para delegar al siguiente en la cadena.
    - Enfermero: manejador concreto que atiende pacientes de nivel Leve y prioridad máxima Baja (1). Si no puede atenderlo, lo pasa al siguiente.
    - MedicoGeneral: manejador concreto que atiende pacientes de nivel Moderado y prioridad máxima Media (2). Si no puede atenderlo, lo pasa al siguiente.
    - Especialista: manejador concreto que atiende pacientes de nivel Grave y prioridad máxima Alta (3). Si no puede atenderlo, lo pasa al siguiente.
    - CadenaAtencion: configura y ensambla la secuencia de atención (Enfermero -> MedicoGeneral -> Especialista) y despacha a los pacientes.
    - Paciente: clase que representa la solicitud y almacena la información clínica (síntoma, nivel, prioridad) y el estado final de atención.
    - SalaUrgencias: clase principal que gestiona la interacción con el usuario, coordina la atención y calcula las estadísticas finales usando Streams.

---

## Reto 7 — El Rover Explorador de Marte

- **Patrón de Diseño:** Comportamiento
- **Patrón Utilizado:** Command
- **Justificación:** cada acción enviada al rover por los distintos operadores debe ser tratada como un objeto independiente parametrizable, con la capacidad de ser ejecutada, registrada en un historial cronológico para auditoría y deshecha (undo) de manera individual sin acoplar el controlador de misión a los subsistemas del rover.
- **Cómo lo aplicamos:**
    - Comando: interfaz base que declara el contrato para ejecutar(), deshacer(), consultar operador, módulo, descripción con parámetros y estado de reversión.
    - ComandoMotor, ComandoBrazo, ComandoCamara, ComandoTaladro: comandos concretos que encapsulan los parámetros (metros, segundos, profundidad, acción) y ejecutan o revierten la operación sobre su receptor correspondiente.
    - Motor, Brazo, Camara, Taladro: clases receptoras (Receivers) que implementan las acciones físicas de cada módulo del rover.
    - ControlRover: invocador (Invoker) que mantiene el historial de comandos ejecutados y gestiona la reversión de acciones individuales por su número de registro.
    - RoverExploradorMarte: clase principal que coordina la entrada interactiva de acciones y operadores, gestiona la opción de deshacer y muestra el historial final.




