/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.cinema.services;

import edu.eci.arsw.cinema.filter.CinemaFilter;
import edu.eci.arsw.cinema.model.Cinema;
import edu.eci.arsw.cinema.model.CinemaFunction;
import edu.eci.arsw.cinema.persistence.CinemaPersistenceException;
import edu.eci.arsw.cinema.persistence.CinemaPersitence;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 *
 * @author cristian
 */
@Service("CinemaServices")
public class CinemaServices {
    
    @Autowired
    @Qualifier("InMemoryCinemaPersistence")
    CinemaPersitence cps;
    
    @Autowired
    @Qualifier("AvailabilityFilter")
    CinemaFilter filter;
    
    public void addNewCinema(Cinema c) throws CinemaException{
        try{
            cps.saveCinema(c);
        }catch(CinemaPersistenceException e){
            throw new CinemaException(e.getMessage());
        }
    }
    
    public Set<Cinema> getAllCinemas(){
        return cps.getAllCinemas();
    }
    
    /**
     * 
     * @param name cinema's name
     * @return the cinema of the given name created by the given author
     * @throws CinemaException
     */
    public Cinema getCinemaByName(String name) throws CinemaException{
        try{
            return cps.getCinema(name);
        }catch(CinemaPersistenceException e){
            throw new CinemaException(e.getMessage());
        }
    }
    
    
    public void buyTicket(int row, int col, String cinema, String date, String movieName) throws CinemaException{
        try{
            cps.buyTicket(row, col, cinema, date, movieName);
        }catch(CinemaPersistenceException e){
            throw new CinemaException(e.getMessage());
        }
    }
    
    public List<CinemaFunction> getFunctionsbyCinemaAndDate(String cinema, String date)throws CinemaException {
        try{
            return cps.getFunctionsbyCinemaAndDate(cinema, date);
        }catch(CinemaPersistenceException e){
            throw new CinemaException(e.getMessage());
        }
    }
    
    public List<CinemaFunction> getFilteredFunctions(String cinema, String date, String p) throws CinemaException{
        try{
            return filter.filter(getFunctionsbyCinemaAndDate(cinema,date), p);
        }catch(CinemaException e){
            throw new CinemaException(e.getMessage());
        }
    }
}
