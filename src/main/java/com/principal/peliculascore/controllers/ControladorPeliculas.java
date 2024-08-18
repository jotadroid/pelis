package com.principal.peliculascore.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import org.springframework.ui.Model;

@Controller
@RequestMapping
public class ControladorPeliculas {

    private static HashMap<String, String> listaPeliculas = new HashMap<>();

    public ControladorPeliculas() {
        listaPeliculas.put("Winnie the Pooh", "Don Hall");    
        listaPeliculas.put("El zorro y el sabueso", "Ted Berman");
        listaPeliculas.put("Tarzán", "Kevin Lima");        
        listaPeliculas.put("Mulán", "Barry Cook");
        listaPeliculas.put("Oliver", "Kevin Lima");    
        listaPeliculas.put("Big Hero 6", "Don Hall");   
    }

    @GetMapping("/peliculas")
    public String obtenerTodasLasPeliculas(Model model){
        model.addAttribute("peliculas", listaPeliculas);
        return "peliculas.jsp";
    }

    @GetMapping("/peliculas/{nombre}")
    public String obtenerPeliculaPorNombre(@PathVariable String nombre){
        for (String pelicula : listaPeliculas.keySet()){
            if (pelicula.equalsIgnoreCase(obtenerPeliculaPorNombre(nombre))){
                return "Pelicula: " + pelicula + "Director: " + listaPeliculas.get(pelicula);
            }
        }
        return "La pelicula no se encuentra";
    }
}
