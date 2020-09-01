/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import edu.eci.arsw.cinema.model.Cinema;
import edu.eci.arsw.cinema.model.CinemaFunction;
import edu.eci.arsw.cinema.model.Movie;
import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;
import org.springframework.boot.test.context.SpringBootTest;
import edu.eci.arsw.cinema.services.*;
import java.util.ArrayList;
import java.util.List;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;


/**
 *
 * @author Angi
 */
@SpringBootTest
public class CinemaServicesTest {
    
    @Autowired
    CinemaServices cs;
    
    ApplicationContext ac;
    
    @Before
    public void setup(){
        ac = new ClassPathXmlApplicationContext("cinemaContext.xml"); 
        cs = ac.getBean(CinemaServices.class);
    }
    
    @Test
    public void deberiaAñadirUnNuevoCine(){
        try{
            registerCinema("ProcinalTest");
            assertNotEquals(cs.getCinemaByName("ProcinalTest"), null);
        }catch(CinemaException e){
            fail("Lanzó excepción");
        }
    }
   
    @Test
    public void deberiaAñadirConsultarCinemasPorNombre(){
        try{
            registerCinema("CinemaTest");
            assertTrue(cs.getCinemaByName("CinemaTest").getName().equals("CinemaTest"));
        }catch(CinemaException e){
            fail("Lanzó excepción");
        }
    }
    
    @Test
    public void deberiaComprarTicket(){
        try{
            cs.buyTicket(0, 0, "CinemaTest", "2020-09-02 15:00", "p1");
            cs.buyTicket(0, 0, "CinemaTest", "2020-09-02 15:00", "p1");
            fail("Compro dos veces el mismo ticket");
        }catch(CinemaException e){
            assertTrue(true);
        }
    }
    
    @Test
    public void deberiaConsultarFuncionesDeUnCinemaPorNombreYFecha(){
        try{
            String functionDate = "2020-09-02 15:00";
            List<CinemaFunction> functions= new ArrayList<>();
            CinemaFunction funct1 = new CinemaFunction(new Movie("p1","g1"),functionDate);
            CinemaFunction funct2 = new CinemaFunction(new Movie("p2","g2"),functionDate);
            functions.add(funct1);
            functions.add(funct2);
            cs.addNewCinema(new Cinema("Test2", functions));
            assertEquals(functions,cs.getFunctionsbyCinemaAndDate("Test2", "2020-09-02 15:00"));
        }catch(CinemaException e){
            fail("Lanzó excepción");
        }
    }
    
    /*
    @Test
    public void deberiaFiltrarPorGenero(){
        try{
            String functionDate = "2020-09-02 12:00";
            List<CinemaFunction> functions= new ArrayList<>();
            CinemaFunction funct1 = new CinemaFunction(new Movie("p1","generotest"),functionDate);
            CinemaFunction funct2 = new CinemaFunction(new Movie("p2","g2"),functionDate);
            functions.add(funct1);
            functions.add(funct2);
            cs.addNewCinema(new Cinema("CinemaTestGenero", functions));
            List<CinemaFunction> lcf = new ArrayList<>();
            lcf.add(funct1); 
            assertEquals(cs.getFilteredFunctions("CinemaTestGenero", "2020-09-02 12:00", "generotest"), lcf);
        }catch(CinemaException e){
            fail("Lanzó excepción");
        }
    }
    */
    
    @Test
    public void deberiaFiltrarPorDisponibilidad(){
        try{
            String functionDate = "2020-09-02 12:00";
            List<CinemaFunction> functions= new ArrayList<>();
            CinemaFunction funct1 = new CinemaFunction(new Movie("p1","disponibilidadtest"),functionDate);
            CinemaFunction funct2 = new CinemaFunction(new Movie("p2","g2"),functionDate);
            functions.add(funct1);
            functions.add(funct2);
            cs.addNewCinema(new Cinema("CinemaTestDisponibilidad", functions));
            int d = 0;
            for(List<Boolean> s: funct1.getSeats()){
               for(Boolean b: s) d++;
            }
            cs.buyTicket(0, 0, "CinemaTestDisponibilidad", "2020-09-02 12:00", "p1");
            functions.remove(funct2);
            assertEquals(cs.getFilteredFunctions("CinemaTestDisponibilidad", "2020-09-02 12:00", String.valueOf(d-1)), functions);
            assertEquals(cs.getFilteredFunctions("CinemaTestDisponibilidad", "2020-09-02 12:00", String.valueOf(d-4)), functions);
        }catch(CinemaException e){
            fail("Lanzó excepción");
        }
    }
    
    private void registerCinema(String name) throws CinemaException{
        try{
            String functionDate = "2020-09-02 15:00";
            List<CinemaFunction> functions= new ArrayList<>();
            CinemaFunction funct1 = new CinemaFunction(new Movie("p1","g1"),functionDate);
            CinemaFunction funct2 = new CinemaFunction(new Movie("p2","g2"),functionDate);
            functions.add(funct1);
            functions.add(funct2);
            cs.addNewCinema(new Cinema(name, functions));
        }catch(CinemaException e){
            System.out.println(e.getMessage());
        }
    }
    
    
    
}
