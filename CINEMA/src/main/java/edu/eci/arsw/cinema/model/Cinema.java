/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.cinema.model;

import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author cristian
 */
public class Cinema {
    private String name;
    private List<CinemaFunction> functions; 
    
    
    public Cinema(){}
    
    public Cinema(String name,List<CinemaFunction> functions){
        this.name=name;
        this.functions=functions;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<CinemaFunction> getFunctions() {
        return this.functions;
    }

    public void setSchedule(List<CinemaFunction> functions) {
        this.functions = functions;
    }
    
    public CinemaFunction getFunctionByMovieAndDate(String movie, String date) throws CinemaModelException{
        for(CinemaFunction cf: functions){
            if(cf.getMovie().getName().equals(movie) && cf.getDate().equals(date)){
                return cf;
            }
        }
        throw new CinemaModelException("No exists a function with that name or date");
    }
    
    public List<CinemaFunction> getFunctionsByDate(String date) throws CinemaModelException {
        List<CinemaFunction> cinemaFunctions = new ArrayList<>();
        for(CinemaFunction cf: functions){
            if(cf.getDate().equals(date)){
                cinemaFunctions.add(cf);
            }
        }
        if (cinemaFunctions.size() == 0){
            throw new CinemaModelException("No exists a function with that date");
        }
        return cinemaFunctions;

    }
    
    public String toString(){
        String cinema =  "Cinema: "+name+"\n  Funciones:";
        for(CinemaFunction f: functions){
            cinema+="\n    "+f.toString();
        }
        return cinema;
    }
}
