/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deepspace;
import static java.lang.Integer.min;
import java.util.ArrayList;

/**
 *
 * @author miguelcant
 * Los objetos de esta clase representan el daño producido a una estación espacial por una nave
 * enemiga cuando se pierde un combate
 */
public abstract class Damage {

    private int nShields;
    
    Damage(int s){
        nShields=s;
    }
    
    public int getNShields(){
        return nShields;
    }
    
    public abstract Damage copy();
    
    abstract DamageToUI getUIversion();
    
    abstract public Damage adjust(ArrayList<Weapon> w, ArrayList<ShieldBooster> s);
    
    abstract public void discardWeapon(Weapon w);
    
    public boolean hasNoEffect(){
        return nShields==0;
    }
 
    public void discardShieldBooster(){
        if (nShields>0)
            nShields--;
    }
    
    int adjustShields(ArrayList<ShieldBooster> s){
        return Math.min(s.size(), nShields);
    }

        
    public String toString(){
        return "\nEscudos: "+nShields;
    }
/*   
        
    public String toString(){
        if(nWeapons==NOTUSED){
         return"DAÑO: \nEscudos: "+nShields +
               
               "\nLista de armas: " + weapons.toString() ;           
        }else{
         return "DAÑO: \nEscudos: "+nShields +
                "\nArmas: "+ nWeapons;
        }

    }
    */
}
