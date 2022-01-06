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

public class EnemyStarShip implements SpaceFighter{
    
    private float ammoPower;
    private String name;
    private float shieldPower;
    private Loot loot;
    private Damage damage;
    
    EnemyStarShip(String n, float a, float s, Loot l, Damage d){
        name = n;
        ammoPower = a;
        shieldPower=s;
        loot=l;
        damage=d;
    }
    
    
    EnemyStarShip(EnemyStarShip e){
        this(e.name, e.ammoPower, e.shieldPower, e.loot, e.damage);
    }
    
    EnemyToUI getUIversion(){
        return new EnemyToUI(this);   
    }
    
    public float getAmmoPower(){
        return ammoPower;
    }
    
    public float getShieldPower(){
        return shieldPower;
    }
    
    public Damage getDamage(){
        return damage;
    }
    
    public Loot getLoot(){
        return loot;
    }
    
    public String getName(){
        return name;
    }
    
    @Override
    public float fire(){
        return ammoPower;   
    }
    
    @Override
    public float protection(){
        return shieldPower;
        
    }
    
    @Override
    public ShotResult receiveShot(float shot){
        if(shieldPower<shot)
            return ShotResult.DONOTRESIST;
        else
            return ShotResult.RESIST;
    }
    

    public String toString(){
        
        return "\nNombre Nave Enemiga: "+name+
               "\nPoder Arma Nave Enemiga: "+fire()+
               "\nPoder Escudo: "+protection()+
               "\nBotin:"+loot.toString()+
               "\nDaño:"+damage.toString();
    }
    
}
