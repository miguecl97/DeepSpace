/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deepspace;

/**
 *
 * @author miguelcant
 */

public class BetaPowerEfficientSpaceStation extends PowerEfficientSpaceStation{
    private Dice dice;
    private static float EXTRAEFFICIENCY=1.2f;
    
    public BetaPowerEfficientSpaceStation(SpaceStation station){
        super(station);
        dice=new Dice();
    }
    
    @Override 
    public BetaPowerEfficientSpaceStationToUI getUIversion(){
        return new BetaPowerEfficientSpaceStationToUI(this);
    }
    
    @Override
    public float fire(){
        if (dice.extraEfficiency())
            return super.fire()*EXTRAEFFICIENCY;
        else
            return super.fire();
    }
    
    @Override
    public String toString(){
        return "*** Estación espacial tipo BETAPOWEREFFICIENT \n" + super.toString();
    }
}

