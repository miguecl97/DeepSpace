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
public class TestP1 {
    
    
    public static void main(String[] args) {
        Loot botin = new Loot(1, 2, 3, 4, 5);
        SuppliesPackage suministros = new SuppliesPackage(1.0f, 2.0f, 3.0f);
        ShieldBooster shieldboost = new ShieldBooster("Escamas de pez", 2f, 1);
        Weapon arma = new Weapon("Rayo", WeaponType.MISSILE, 0);
        Dice dado = new Dice();
        
        System.out.format("\nTipo de arma: %s\n", arma.getType());
        System.out.format("\nUsos del arma: %d\n", arma.getUses());
        System.out.format("\nPoder del arma: %f\n", arma.power());
                
        System.out.format("\nSuministros obtenidos: %d\n", botin.getNSupplies());
        System.out.format("\nNumero de armas obtenidas: %d\n", botin.getNWeapons());
        System.out.format("\nNumero de escudos obtenidos: %d\n", botin.getNShields());
        System.out.format("\nNumero de medallas obtenidas: %d\n", botin.getNMedals());
        System.out.format("\nNumero de hangares obtenidos: %d\n", botin.getNHangars());

        System.out.format("\nPontenciador del escudo: %f\n", shieldboost.getBoost());
        System.out.format("\nUsos del escudo: %d\n", shieldboost.getUses());
        
        System.out.format("\nPoder del arma obtenida en los suministros: %f\n", suministros.getAmmoPower());
        System.out.format("\nPoder del escudo obtenido en los suministros: %f\n", suministros.getShieldPower());
        System.out.format("\nFuel obtenido en los suministros: %f\n", suministros.getFuelUnits());
        
        float nInitHangars = 0;
        float nInitWeapons = 0;
        float nInitShields = 0;
        float nFirstShotStarship = 0;
        float nMoves = 0;
        
        for(int i = 0; i < 100; i++){
            nInitHangars += dado.initWithNHangars();
            nInitWeapons += dado.initWithNWeapons();
            nInitShields += dado.initWithNShields();
            nFirstShotStarship += dado.firstShot() == GameCharacter.SPACESTATION ? 1 : 0;
            nMoves += dado.spaceStationMoves(0.5f) ? 1 : 0;
                    
        }
        
        nInitHangars = nInitHangars / 100;
        nInitWeapons = nInitWeapons /100;
        nInitShields = nInitShields/100;
        nFirstShotStarship = nFirstShotStarship/100;
        nMoves = nMoves/100;
        
        
        System.out.println("initWithNHangars " + nInitHangars + " (the average should be 0.75)");
        System.out.println("initWeapons " + nInitWeapons + " (the average should be 2)");
        System.out.println("initNShields " + nInitShields + " ( the average should be 0.75");
        System.out.println("nFirstShotStarship " + nFirstShotStarship + " ( the average should be 0.5");

        System.out.println("nMoves " + nMoves + " (the average should be 0.50)");
    }
 }
