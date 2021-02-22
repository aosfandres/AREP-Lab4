package edu.escuelaing.arep.mySpring;

import edu.escuelaing.arep.httpServer.HttpServer;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.*;

public class MySpring {

  HttpServer httpServer;
  Map<String, Method> diereccion = new HashMap<String, Method>();

  public MySpring() {}

  /**
   * start
   */
  public void start() {
    httpServer = new HttpServer();
    httpServer.processor("/app", this);
    try {
      httpServer.start();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Genera la respuesta correspondiente
   * @param path 
   * @param req 
   * @param resp 
   * @return 
   */
  public String response(String path, HttpRequest req, HttpResponse resp) {
	String response =null;
    if (diereccion.containsKey(path)) {
      try {
        if (path.equals("/hello")) {
          response=
            "HTTP/1.1 200 OK\r\n" +
            "Content-Type: text/html\r\n" +
            "\r\n" +
            "Saludos desde Spring <3";
        } else if (path.equals("/zapatillas")) {
          return zap();
        } else if (path.equals("/balon")) {
          return bal();
        } else if (path.equals("/js")) {
          return js();
        } else {
			response=
            "HTTP/1.1 200 OK\r\n" +
            "Content-Type: text/html\r\n" +
            "\r\n" +
            diereccion.get(path).invoke(null, null).toString();
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
    }else{
		response=error();
	}
    return response;
  }

  /**
   * Metodo encargado de actualizar componentes para iniciar
   * @param components
   * @throws ClassNotFoundException
   */
  public void update(String[] components) throws ClassNotFoundException {
    for (String compName : components) {
      Class component = Class.forName(compName);
      Method[] compMethods = component.getDeclaredMethods();
      for (Method m : compMethods) {
        if (m.isAnnotationPresent(RequestMapping.class)) {
          diereccion.put(m.getAnnotation(RequestMapping.class).value(), m);
        }
      }
    }
  }


  /**
   * Metodo encargado de mostar el archivo correspondiente
   * @return 
   * @throws IOException
   */
  private String js() throws IOException {
    String outputLine;
    File path = new File("src/main/resources/app.js");
    FileReader fileReader = new FileReader(path);
    BufferedReader bufferedReader = new BufferedReader(fileReader);
    outputLine =
      "HTTP/1.1 200 OK\r\n" + "Content-Type: text/javascript  \r\n" + "\r\n";
    String inputLine;
    while ((inputLine = bufferedReader.readLine()) != null) {
      outputLine += inputLine + "\n";
    }
    return outputLine;
  }

  /**
   * Metodo encargado de mostar la imagen correspondiente
   * @return 
   */
  private String zap() {
    return (
      "HTTP/1.1 200 OK\r\n" +
      "Content-Type: text/html\r\n" +
      "\r\n" +
      "<!DOCTYPE html>\n" +
      "<html>\n" +
      "<head>\n" +
      "<meta charset=\"UTF-8\">\n" +
      "<title>Zapatillas</title>\n" +
      "</head>\n" +
      "<body>\n" +
      "<img src=\"https://i.pinimg.com/736x/fe/da/62/feda6286041190d7962dbb99424268a4.jpg\" />\n" +
      "</body>\n" +
      "</html>\n"
    );
  }

  /**
   * Metodo encargado de mostar la imagen correspondiente
   * @return 
   */
  private String bal() {
    return (
      "HTTP/1.1 200 OK\r\n" +
      "Content-Type: text/html\r\n" +
      "\r\n" +
      "<!DOCTYPE html>\n" +
      "<html>\n" +
      "<head>\n" +
      "<meta charset=\"UTF-8\">\n" +
      " <title>Balon!!</title>\n" +
      "</head>\n" +
      "<body>\n" +
      "<img src=\"https://i.pinimg.com/originals/81/e2/74/81e2748b9b0ac737b232705827cb2572.jpg\" />\n" +
      "</body>\n" +
      "</html>\n"
    );
  }

  /**
   * metodo  que retorna error correspondiente 
   * @return 
   */
  private String error() {
    return (
      "HTTP/1.1 404 not Found\r\n" +
      "Content-Type: text/html\r\n" +
      "\r\n" +
      "<!DOCTYPE html>\n" +
      "<html>\n" +
      "<head>\n" +
      "<meta charset=\"UTF-8\">\n" +
      "<title>Error</title>\n" +
      "</head>\n" +
      "<body>\n" +
      "<h1>ERROR 404 PORFAVOR REVISA BIEN EL PATH</h1>\n" +
      "</body>\n" +
      "</html>\n"
    );
  }

  public static void main(String[] args) throws ClassNotFoundException {
    MySpring m = new MySpring();
    m.update(args);
    m.start();
  }
}
