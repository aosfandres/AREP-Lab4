package edu.escuelaing.arep;

import edu.escuelaing.arep.mySpring.RequestMapping;


public class App {

    @RequestMapping("/hello")
    public static void saludo() {}
    @RequestMapping("/zapatillas")
    public static void zapatillas(){}
    @RequestMapping("/balon")
    public static void balon(){}
    @RequestMapping("/js")
    public static void js(){}

}