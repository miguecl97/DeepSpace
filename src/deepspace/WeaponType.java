/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deepspace;

/**
 *
 * @author miguelcant
 * 
 * Enumerado que representa a los tipos de armas del juego,
 * cada arma tiene asociado un valor numérico que representa su potencia de disparo.
 * 
 * LASER : representa el arma de tipo láser, con potencia de disparo 2
 * MISSILE : representa el arma de tipo misil, con potencia de disparo  3
 * PLASMA: representa el arma de tipo plasma, con potencia de disparo 4
 * 
 */


public enum WeaponType { 
    
    LASER (WeaponType.LASERPOWER),
    MISSILE (WeaponType.MISSILEPOWER), 
    PLASMA (WeaponType.PLASMAPOWER);
    
    private final float power;
    //evitamos el uso de numeros magicos
    private static final float LASERPOWER = 2.0f;
    private static final float MISSILEPOWER = 4.0f;   
    private static final float PLASMAPOWER = 3.0f;   
    
    WeaponType(float power) {
    
    this.power = power;
    
    }
    
    
    //Consultor que devuelve el poder de un arma
    float getPower(){
        return power;
    }
    
    
}
