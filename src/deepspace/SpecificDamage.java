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
public class SpecificDamage extends Damage{
    private ArrayList<WeaponType> weapons= new ArrayList<>();
    
    SpecificDamage(ArrayList<WeaponType> w, int s){
        super(s);
        weapons=w;
    }
    
    public SpecificDamage copy(){
        return new SpecificDamage(weapons,getNShields());
    }
    
    SpecificDamageToUI getUIversion(){
        return new SpecificDamageToUI(this);
    }
    
    public ArrayList<WeaponType> getWeapons(){
        return weapons;
    }
    
    private int arrayContainsType(ArrayList<Weapon> w, WeaponType t){
        for (Weapon weapon : w)
            if (weapon.getType() == t)
                return w.indexOf(weapon);
        return -1;
    }
    
    public SpecificDamage adjust(ArrayList<Weapon> w, ArrayList<ShieldBooster> s){
        ArrayList<WeaponType> adjusted = new ArrayList<WeaponType>();
        ArrayList<Weapon> receivedCopy = new ArrayList<Weapon> (w);
            
        for(WeaponType wtReceived : weapons){
            int i = arrayContainsType(receivedCopy,wtReceived);
            if(i != -1){
                adjusted.add(wtReceived);
                receivedCopy.remove(i); 
            }
        }
 
        return new SpecificDamage(adjusted, adjustShields(s));
    }

    @Override
    public void discardWeapon(Weapon w){
        int index=weapons.indexOf(w.getType());
        if (index != -1)
            weapons.remove(index);       
    }
    
    @Override
    public boolean hasNoEffect(){
        if(super.hasNoEffect() && weapons.isEmpty())
            return true;
        else
            return false;
    }
    
    @Override
    public String toString(){
 
        String mensaje= "\nLista de armas: " + weapons.toString() ;  
        mensaje += super.toString();
        
        return mensaje;
    }
}