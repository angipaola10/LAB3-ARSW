package edu.eci.arsw.cinema.ui;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import edu.eci.arsw.cinema.model.Cinema;
import edu.eci.arsw.cinema.model.CinemaFunction;
import edu.eci.arsw.cinema.model.Movie;
import edu.eci.arsw.cinema.services.CinemaException;
import edu.eci.arsw.cinema.services.CinemaServices;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
/**
 *
 * @author Angi
 */
public class Main {
    
    public static void main(String a[]) throws CinemaException {
       ApplicationContext ac = new ClassPathXmlApplicationContext("cinemaContext.xml");
       
       //Instancia de CinemaServices a través de spring
       CinemaServices cs = ac.getBean(CinemaServices.class);
       
       //Registrar Cinema
       registerCinema("Cine Colombia", cs);
       
       //Consultar cinemas
       consultCinemas(cs);
       
       //Consultar cinemas por nombre
       consultCinemaByName(cs, "Cine Colombia");
       
       //Consulta funciones de determinado cinema por nombre y fecha
       getFunctionsOfCinema(cs, "Cine Colombia", "2020-09-02 15:30");
       
       //Comprar tickets
       buyTickets(cs, 0, 0, "Cine Colombia", "2020-09-02 15:30", "Avengers: EndGame");
       
       //Filtrar funciones por genero
       //filterFunctionsByGender(cs, "Cine Colombia", "2020-09-02 15:30", "Drama");
       
       //Filtrar funciones por disponibilidad
       filterFunctionsByAvailability(cs, "Cine Colombia", "2020-09-02 15:30", "100000");
       
    }
       
    
    private static void registerCinema(String name, CinemaServices cs) throws CinemaException{
        try{
            String functionDate = "2020-09-02 15:30";
            List<CinemaFunction> functions= new ArrayList<>();
            CinemaFunction funct1 = new CinemaFunction(new Movie("Avengers: EndGame","Action"),functionDate);
            CinemaFunction funct2 = new CinemaFunction(new Movie("Titanic","Drama"),functionDate);
            functions.add(funct1);
            functions.add(funct2);
            cs.addNewCinema(new Cinema(name, functions));
        }catch(CinemaException e){
            System.out.println(e.getMessage());
        }
    }
    
    private static void consultCinemas(CinemaServices cs){
        System.out.println("==============================================================");
        System.out.println("                 CONSULTA TODOS LOS CINEMAS");
        System.out.println("==============================================================");
        for (Cinema c: cs.getAllCinemas()){
            System.out.println(c.toString());
        }
        System.out.println("==============================================================\n");
    }
    
    private static void consultCinemaByName(CinemaServices cs, String nameCinema) throws CinemaException{
        System.out.println("==============================================================");
        System.out.println("                 CONSULTA CINEMA POR NOMBRE");
        System.out.println("==============================================================");
        System.out.println(cs.getCinemaByName(nameCinema).toString());
        System.out.println("==============================================================\n");
    }
    
    private static void getFunctionsOfCinema(CinemaServices cs, String nameCinema, String date) throws CinemaException{
        System.out.println("==============================================================");
        System.out.println("        CONSULTA FUNCIONES DE CINEMA POR NOMBRE Y FECHA");
        System.out.println("==============================================================");
        for(CinemaFunction cf: cs.getFunctionsbyCinemaAndDate(nameCinema, date)){
            System.out.println(cf.toString());
        }
        System.out.println("==============================================================\n");
    }

    private static void buyTickets(CinemaServices cs, int row, int col, String cinema, String date, String movieName) throws CinemaException{
        System.out.println("==============================================================");
        System.out.println("                       COMPRAR TICKET");
        System.out.println("==============================================================");
        try{
             cs.buyTicket(row, col, cinema, date, movieName);
             System.out.println("Ticket comprado");
        }catch(CinemaException e){
            System.out.println(e.getMessage());
        }
        System.out.println("==============================================================");
    }
    
    private static void filterFunctionsByGender(CinemaServices cs, String cinema, String date, String p){
        System.out.println("==============================================================");
        System.out.println("            FILTRANDO FUNCIONES POR GÉNERO");
        System.out.println("==============================================================");
        try{
            for(CinemaFunction cf: cs.getFilteredFunctions(cinema, date, p)){
                System.out.println(cf.toString());
            }
        }catch(CinemaException e){
            System.out.println(e.getMessage());
        }
        System.out.println("==============================================================");
    }
    
    private static void filterFunctionsByAvailability(CinemaServices cs, String cinema, String date, String p){
        System.out.println("==============================================================");
        System.out.println("           FILTRANDO FUNCIONES POR DISPONIBILIDAD");
        System.out.println("==============================================================");
        try{
            for(CinemaFunction cf: cs.getFilteredFunctions(cinema, date, p)){
                System.out.println(cf.toString());
            }
        }catch(CinemaException e){
            System.out.println(e.getMessage());
        }
        System.out.println("==============================================================");
    }
    
}
