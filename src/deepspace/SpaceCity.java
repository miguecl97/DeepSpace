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
public class SpaceCity extends SpaceStation{
    
    private SpaceStation base;
    private ArrayList<SpaceStation> collaborators;
    
    SpaceCity (SpaceStation base, ArrayList<SpaceStation> rest){
        super(base);
        this.base=base;
        this.collaborators=rest;
    }
    
    public ArrayList<SpaceStation> getCollaborators(){
        return collaborators;
    }
    
    @Override
    public float fire(){
        float factor=super.fire();
        
        for (SpaceStation station : collaborators)
            factor+=station.fire();
        
        return factor;
    }
    
    @Override
    public float protection(){
        float factor=super.protection();
        
        for (SpaceStation station : collaborators)
            factor+=station.protection();
        
        return factor;
    }
    
    @Override
    public Transformation setLoot(Loot loot){
        super.setLoot(loot);
        return Transformation.NOTRANSFORM;
    }
    
    @Override
    public String getName () {
        return super.getName()+" (CIUDAD ESPACIAL)";
    }
    
}
