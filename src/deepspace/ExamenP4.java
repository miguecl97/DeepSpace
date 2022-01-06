/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deepspace;

import deepspace.CardDealer;
import deepspace.SpaceCity;
import deepspace.SpaceStation;
import java.util.ArrayList;

/**
 *
 * @author miguelcant
 */
public class ExamenP4 {

    static final int NUMEROESTACIONES = 20;
    /**
     * @param args the command line arguments
     */
    
    static void Examen(){
        CardDealer dealer = CardDealer.getInstance();
        ArrayList<SpaceStation> stations= new ArrayList<>();
        SpaceStation prueba = new SpaceStation("ESTACION", dealer.nextSuppliesPackage());
       
        //System.out.println(prueba.toString());
        
        for(int i =0 ; i<NUMEROESTACIONES; i++){
            stations.add(i, new SpaceStation("Estación " + i, dealer.nextSuppliesPackage()));
        }
        
        for(SpaceStation station : stations){
            //System.out.println(station.fire());
        }
        
        ArrayList<SpaceStation> collabs= new ArrayList<>(stations);
        collabs.remove(2);
        SpaceCity ciudad = new SpaceCity(stations.get(2), collabs);
        
        
        System.out.println(ciudad.fire());
        stations.set(2, ciudad);
        System.out.println(stations.get(2).fire());  
    }
    public static void main(String[] args) {
        
       Examen();
        
        
    }
    
}
