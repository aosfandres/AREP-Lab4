# TALLER DE ARQUITECTURAS DE SERVIDORES DE APLICACIONES, META PROTOCOLOS DE OBJETOS, PATRÓN IOC, REFLEXIÓN DESCRIPCIÓN

Para este taller los estudiantes deberán construir un servidor Web (tipo Apache) en Java. El servidor debe ser capaz de entregar páginas html e imágenes tipo PNG. Igualmente el servidor debe proveer un framework IoC para la construcción de aplicaciones web a partir de POJOS. Usando el servidor se debe construir una aplicación Web de ejemplo y desplegarlo en Heroku. El servidor debe atender múltiples solicitudes no concurrentes.

Para este taller desarrolle un prototipo mínimo que demuestre capcidades reflexivas de JAVA y permita por lo menos cargar un bean (POJO) y derivar una aplicación Web a partir de él. Debe entregar su trabajo al final del laboratorio.

## SUGERENCIA
1. Cargue el POJO desde la línea de comandos , de manera similar al framework de TEST. Es decir pásela como parámetro cuando invoke el framework. Ejemplo de invocación:

```java -cp target/classes co.edu.escuelaing.reflexionlab.MicroSpringBoot co.edu.escuelaing.reflexionlab.FirstWebService```

2. Atienda la anotación @ResuestMapping publicando el servicio en la URI indicada, limítelo a tipos de retorno String,  ejemplo:
``` 
public class HelloController {

	@RequestMapping("/")
	public String index() {
		return "Greetings from Spring Boot!";
	}
}
```

# REQUISITOS
- Java 
- Maven 


# DISEÑO
El despliegue de la aplicacion esta hecho en Heroku el cual usa un Dyno para soportar la app y por medio del protocolo HTTP el cliente o usuario final puede hacer uso de la aplicacion.

![](https://github.com/aosfandres/AREP-Lab4/blob/master/images/diagrama.PNG)

# USO
- clonar el repositorio ``` git clone https://github.com/aosfandres/AREP-Lab4```
- construir el proyecto ```mvn package```
- ejecutar el proyecto ```java -cp target/classes edu.escuelaing.arep.mySpring.MySpring  edu.escuelaing.arep.App``` 
- ingresar a http://localhost:36000/ 

![](https://github.com/aosfandres/AREP-Lab4/blob/master/images/1.PNG)

- alli podra ver y acceder a los recursos disponibles

- imagen

![](https://github.com/aosfandres/AREP-Lab4/blob/master/images/2.PNG)

- imagen

![](https://github.com/aosfandres/AREP-Lab4/blob/master/images/3.PNG)

- saludo

![](https://github.com/aosfandres/AREP-Lab4/blob/master/images/4.PNG)

- archivo js de prueba

![](https://github.com/aosfandres/AREP-Lab4/blob/master/images/5.PNG)


# DOCUMENTO LATEX
[TALLER DE ARQUITECTURAS DE SERVIDORES DE APLICACIONES](https://github.com/aosfandres/AREP-Lab4/blob/master/LatexDocument.pdf)

# DOCUMENTACION (JAVADOC)
Para generar la documentacion con maven uasr mvn javdoc:javadoc en consola
[JAVADOC](https://github.com/aosfandres/AREP-Lab4/blob/master/JAVADOC.lnk)

# DESPLIEGUE

[![Deployed to Heroku](https://www.herokucdn.com/deploy/button.png)](https://areplab4-spring.herokuapp.com/)

# AUTOR
Andres Orlando Sotelo Fajardo 

# LICENCIA

[LICENSE](https://github.com/aosfandres/AREP-Lab4/blob/master/LICENSE)