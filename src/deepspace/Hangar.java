/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deepspace;
import java.util.ArrayList;

/**
 *
 * @author miguelcant
 */
public class Hangar {
    
    private int maxElements;
    private ArrayList <ShieldBooster> shieldBoosters = new ArrayList();
    private ArrayList <Weapon> weapons = new ArrayList();
       
    Hangar(int capacity){
        maxElements = capacity;
    }
       
       
    Hangar(Hangar h){
        this.maxElements = h.maxElements;
        this.shieldBoosters = h.shieldBoosters;
        this.weapons = h.weapons;
    }
       
    HangarToUI getUIversion(){
        return new HangarToUI(this);

    }
       
    private boolean spaceAvaible(){
        if((weapons.size() + shieldBoosters.size()) < maxElements)
            return true;
        else
            return false;
    }
       
    public boolean addWeapon( Weapon w) {
        if(spaceAvaible()){
            weapons.add(w);
            return true;
        }else
            return false;
    }
       
    public boolean addShieldBooster(ShieldBooster s){
        if(spaceAvaible()){
            shieldBoosters.add(s);
            return true;
        }else
            return false;           
    }
       
    public int getMaxElements(){
        return maxElements;
    }
    
    public ArrayList<ShieldBooster> getShieldBoosters(){
        return shieldBoosters;
    }
    
    public ArrayList<Weapon> getWeapons(){
        return weapons;
    }
     
    public ShieldBooster removeShieldBooster(int s){
        if(s < shieldBoosters.size() && s >= 0)
            return shieldBoosters.remove(s);
        else
            return null;         
    }
       
    public Weapon removeWeapon(int w){
        if(w < weapons.size() && w >= 0)
            return weapons.remove(w);
        else
            return null;
    }
    
    public String toString(){
        return  "\nArmas Hangar: \n"+weapons.toString()+
                "\nPotenciadores Escudo Hangar: \n"+shieldBoosters.toString()+
                "\nCapacidad: "+maxElements;
    }   
}
