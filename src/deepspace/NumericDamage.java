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

public class NumericDamage extends Damage {
    private int nWeapons;
    
    public NumericDamage(int w, int s){
        super(s);
        nWeapons=w;
    }
    
        
    NumericDamageToUI getUIversion(){
        return new NumericDamageToUI(this);
    }
    
        
    public int getNWeapons(){
        return nWeapons;
    }
    
    @Override
    public NumericDamage copy(){
        return new NumericDamage(this.nWeapons,getNShields());
    }

    
    @Override
    public void discardWeapon(Weapon w){
        if (nWeapons > 0)
            nWeapons--;
    }

    @Override
    public boolean hasNoEffect(){
        if( super.hasNoEffect() && nWeapons==0)
            return true;
        else
            return false;
    }
    
    public NumericDamage adjust(ArrayList<Weapon> w ,ArrayList<ShieldBooster> s){
        return new NumericDamage(Math.min(nWeapons,w.size()), adjustShields(s));
    }
    
    @Override
    public String toString(){
        return  "\nArmas: "+nWeapons+super.toString();
    }
    
}

