package edu.escuelaing.arep.httpServer;

import java.io.*;
import java.net.*;
import java.util.HashMap;
import java.util.Map;

import edu.escuelaing.arep.mySpring.MySpring;
public class HttpServer {

  private boolean run = true;
  Map<String, MySpring> dir = new HashMap();

  public HttpServer() {}

  /**
   * Servidor 
   * @throws IOException
   */
  public void start() throws IOException {
    ServerSocket serverSocket = null;
    try {
      serverSocket = new ServerSocket(getPort());
    } catch (IOException e) {
      System.err.println("Could not listen on port: 36000.");
      System.exit(1);
    }
    while (run) {
      Socket clientSocket = null;
      try {
        System.out.println("Listo para recibir ...");
        clientSocket = serverSocket.accept();
      } catch (IOException e) {
        System.err.println("Accept failed.");
        System.exit(1);
      }
      PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
      BufferedReader in = new BufferedReader(
        new InputStreamReader(clientSocket.getInputStream())
      );
      String inputLine = null;
      String outputLine = null;
      String resp = null;
      boolean flag = true;
      while ((inputLine = in.readLine()) != null) {
        System.out.println("Recibí: " + inputLine);
        if (!in.ready()) {
          break;
        }
        if (flag) {
          resp = response(inputLine, out);
          flag = false;
        }
      }
      if (resp == null) {
        String outLine =
          "HTTP/1.1 404 not Found\r\n" +"Content-Type: text/html\r\n" + "\r\n" + "<!DOCTYPE html>\n" +
          "<html>\n" +"<head>\n" + "<meta charset=\"UTF-8\">\n" +
          "<title>Error</title>\n" +"</head>\n" +"<body>\n" +
          "<h1>ERROR 404 PORFAVOR REVISA BIEN EL PATH</h1>\n" +
          "</body>\n" + "</html>\n";
        out.println(outLine);
        out.close();
      } else {
        outputLine = resp;
      }
      out.println(outputLine);
      out.close();
      in.close();
      clientSocket.close();
    }
    serverSocket.close();
  }

  /**
   * metodo encargado de traer la respuesta correspondiente
   * @param inputLine
   * @param  out
   * @return respuesta
   */
  public String response(String inputLine, PrintWriter out) throws IOException {
    String resp = null;
    String data;
    String[] typeData = inputLine.split(" ");
    data = typeData[1];
    if (data.equals("/")) {
      String outputLine;
      File path = new File("src/main/resources/index.html");
      FileReader fileReader = new FileReader(path);
      BufferedReader bufferedReader = new BufferedReader(fileReader);
      outputLine =
        "HTTP/1.1 200 OK\r\n" + "Content-Type: text/html  \r\n" + "\r\n";
      String entrada;
      while ((entrada = bufferedReader.readLine()) != null) {
        outputLine += entrada + "\n";
      }
      out.println(outputLine);
      out.close();
    } else {
      for (String key : dir.keySet()) {
        if (data.startsWith(key)) {
          String path = data.substring(key.length());
          resp = dir.get(key).response(path, null, null);
        }
      }
    }
    return resp;
  }

     
  /**
   * Processor del servidor
   * @param path 
   * @param MySpring 
   */
  public void processor(String path, MySpring mySpring) {
    dir.put(path, mySpring);
  }

  /**
   * Método de puerto 
   * @return el puerto del servicio.
   */
  private int getPort() {
    if (System.getenv("PORT") != null) {
      return Integer.parseInt(System.getenv("PORT"));
    }
    return 36000;
  }
}
