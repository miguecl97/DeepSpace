/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deepspace;

import java.util.Random;

/**
 *
 * @author miguelcant
 */
class Dice {
     
    private final float NHANGARSPROB;
    private final float NSHIELDSPROB;
    private final float NWEAPONSPROB;
    private final float FIRSTSHOTPROB;
    private final float EXTRAEFFICIENCYPROB;
    private Random generator;
    
    Dice(){
        
        this.NHANGARSPROB= 0.25f;
        this.NSHIELDSPROB=0.25f;
        this.NWEAPONSPROB=0.33f;
        this.FIRSTSHOTPROB=0.5f;
        this.EXTRAEFFICIENCYPROB=0.8f;
        generator = new Random();
        
    }
    

    public int initWithNHangars(){
        float prob = generator.nextFloat();
        if (prob < NHANGARSPROB)
            return 0;
        else 
            return 1;
    }
    public boolean extraEfficiency(){
        float prob = generator.nextFloat();
        if (prob < EXTRAEFFICIENCYPROB)
            return true;
        else 
            return false;
    }
    
    public int initWithNWeapons(){
        float prob = generator.nextFloat();
        if(prob<NWEAPONSPROB)
            return 1;
        else if(prob<2*NWEAPONSPROB)
            return 2;
        else 
            return 3;
            
    }
    
    public int initWithNShields(){
        float prob = generator.nextFloat();
        if(prob<NSHIELDSPROB)
            return 0;
        else
            return 1;
    }
    
    public int whoStarts(int nPlayers){
        int starter = generator.nextInt(nPlayers);
        return starter;
    }
    
    public GameCharacter firstShot(){
        float prob = generator.nextFloat();
        
        if(prob<FIRSTSHOTPROB)
            return GameCharacter.SPACESTATION;
        else
            return GameCharacter.ENEMYSTARSHIP;
    }
    
    public boolean spaceStationMoves(float speed){
        float prob = generator.nextFloat();
        
        if(prob<speed)
            return true;
        else
            return false;
    }
    
    public String toString(){
        String mensaje = "Numero de hangares inicial: "+initWithNHangars()+
                "\nNumero de armas inicial: "+initWithNWeapons()+
                "\nNumero de escudos inicial: "+initWithNShields()+
                "\nDispara primero: "+firstShot()+
                "\nExtra efficient: " + extraEfficiency();
        return mensaje;
    }
}
