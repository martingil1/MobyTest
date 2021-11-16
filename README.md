Dado el siguiente enunciado...

“Un Candidato tiene conocimiento de ciertas Tecnologías. Los datos de interés de un Candidato son:
nombre, apellido, tipo (DNI, LE, LC) y número de documento, fecha de nacimiento y las tecnologías que
maneja. Para el caso de las Tecnologías, interesa persistir el nombre (java, python, maven, hibernate,
spring) y versión. De la relación entre un Candidato y una Tecnología surge un atributo, el de “años de
experiencia”, el cual indica cuántos años de experiencia posee el Candidato en dicha Tecnología. La
relación es claramente de N a M ya que un candidato puede tener experiencia en múltiples tecnologías y
una tecnología puede estar asociada con múltiples candidatos”

Crear un API Rest para cargar los datos de un Candidato y sus conocimientos cumpliendo los siguientes
requisitos:

- CRUD Candidato y Tecnología. Esto implica: Crear controladores, servicios, repositorios y entidades.
- Test unitarios
- Funcionalidad: listar Candidatos de X Tecnología. Recibe un texto por ejemplo “Java” y devuelve los
datos del Candidato y años de experiencia en dicha Tecnología.
- Creación y uso de al menos 1 excepción propia.
- Crear Logs en donde consideres.
- Añadir manejo de excepciones centralizado usando aspectos. Manejar las excepciones que consideres
necesarias.
- Añadir algún tipo de seguridad para la API.
