# POO_FinalProject_Sanabria_Taborda_
## Sistema de Administración de Hotel
### Integrantes
Juan Felipe Sanabria Posada (Código SIA: 64903) y Jerónimo Taborda (Código SIA: 63057)

### Descripción general del sistema
Se desarrolla un programa capaz de administrar de manera completa y estructurada la información relacionada con la operación de un hotel. El sistema permite gestionar huéspedes, habitaciones, reservas y personal. Todo está construido bajo el paradigma de la Programación Orientada a Objetos, lo que significa que cada entidad importante del sistema (como Hotel, Habitación, Reserva, Huésped y Empleado) se modela como una clase con atributos y métodos propios.

Gracias a esta organización (realizada a través de un diagrama de clases UML) , el programa mantiene un alto nivel de claridad, extensibilidad y mantenimiento. Las clases se comunican entre sí mediante instancias y relaciones bien definidas, permitiendo que el flujo de datos sea ordenado y que la lógica esté distribuida de forma natural. Esto hace posible incorporar nuevas funcionalidades sin desordenar el sistema, como agregar más tipos de habitación, implementar tarifas dinámicas, integrar nuevos métodos de búsqueda o expandir los reportes del hotel.

Además, la aplicación implementa el almacenamiento de datos en archivos externos.  El uso de estructuras de datos adecuadas y la serialización correcta garantizan un manejo confiable de la información.

### Instrucciones para ejecutar el programa
El programa es ejecutable a través de la consola virtual. Cuando se inicia el programa, se presenta a través de esta un menú con 3 opciones principales: 
#### 1. Información del Hotel.
Esta sección del programa permite al operador que lo use configuar el hotel: Añadir habitaciones al hotel, Configurar hotel o cambiar información. 
En configurar hotel, se le asigna un nombre, correo, número telefónico y dirección al hotel (Equivalente a los datos reales del establecimiento)
#### 2. Menú de administrador.
Esta sección del sistema es la más importante. Abre otro menú, en donde se presentan opciones que permiten ir al manejo interno de 3 aspectos: Reservas, Registros y Empleados. 

En reservas, se despliega un menú en donde se crean, desactivan o modifican reservas. Por último, se muestran listas de reservas activas y habitaciones.
En la sección Registros, se pueden mostrar el registro histórico de reservas (activas y consumadas) o de forma individual para una búsqueda más precisa y rápida, así como también de huéspedes.

Por último, en Empleados permite administrar toda la información acerca del personal y la lista de este. Añadir, cambiar información de , buscar o despedir empleado, además de mostrar la lista de los mismos son las funciones que esta sección realiza.

