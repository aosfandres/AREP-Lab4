package edu.escuelaing.arep.mySpring;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)

/**
 * Representacion de RequestMapping
 */
public @interface RequestMapping {
 
    String value();
}