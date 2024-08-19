package com.principal.peliculascore.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.Map;
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
    public String obtenerPeliculaPorNombre(@PathVariable String nombre, Model model){
        if (listaPeliculas.containsKey(nombre)) {
            String director = listaPeliculas.get(nombre);
            model.addAttribute("pelicula", nombre);
            model.addAttribute("director", director);
            System.out.println("Película encontrada: " + nombre + ", Director: " + director);
            return "pelicula.jsp";
        }
        System.out.println("La película no se encuentra.");
        return "peliculaNoEncontrada.jsp";
    }

    @GetMapping("/peliculas/director/{nombre}")
    public String obtenerPeliculasPorDirector(@PathVariable String director, Model model){
        ArrayList<String> peliculasDirector = new ArrayList<>();

        for (Map.Entry<String, String> entry : listaPeliculas.entrySet()) {
            if (entry.getValue().equalsIgnoreCase(director)){
                peliculasDirector.add(entry.getKey());
            }
        }
        if (!peliculasDirector.isEmpty()) {
            model.addAttribute("director", director);
            model.addAttribute("peliculas", peliculasDirector);
            return "peliculasPorDirector.jsp";
        }
    return "peliculaNoEncontrada.jsp";
    }
}
