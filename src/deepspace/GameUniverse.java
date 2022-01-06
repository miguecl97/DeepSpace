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
public class GameUniverse {
    
    private static final int WIN =10;
    
    private int currentStationIndex;
    private int turns;
    private boolean haveSpaceCity;
    
    private GameStateController gameState;
    private Dice dice;
    private SpaceStation currentStation;
    private ArrayList<SpaceStation> spaceStations = new ArrayList();
    private EnemyStarShip currentEnemy;
    
    
    public GameUniverse(){
        
        gameState = new GameStateController();
        dice = new Dice();
        turns = 0;
        currentStationIndex=0;
        haveSpaceCity=false;
        
    }
    
    
    private void createSpaceCity(){
        if (haveSpaceCity==false ) {
           ArrayList<SpaceStation> collaborators = new ArrayList<>();

            for (SpaceStation station : spaceStations)
                if ( station != currentStation )
                    collaborators.add(station);

            currentStation = new SpaceCity(currentStation, collaborators);
            spaceStations.remove(currentStationIndex);
            spaceStations.add(currentStationIndex, currentStation);
            haveSpaceCity = true;
        }
    }
    
    private void makeStationEfficient(){
        
        if (dice.extraEfficiency())
            currentStation = new BetaPowerEfficientSpaceStation(currentStation);
        else
            currentStation =new PowerEfficientSpaceStation(currentStation);
            
            
        spaceStations.remove(currentStationIndex);
        spaceStations.add(currentStationIndex, currentStation);
    }
    
    
    public CombatResult combat(){
        GameState state=gameState.getState();
    
        if((state==GameState.BEFORECOMBAT)||(state==GameState.INIT))
            return combat(currentStation,currentEnemy);
        else
            return CombatResult.NOCOMBAT;
    }
    
    CombatResult combat(SpaceStation station, EnemyStarShip enemy){
        GameCharacter ch= dice.firstShot();
        boolean enemyWins;
        float fire;
        CombatResult combatResult = CombatResult.NOCOMBAT;
        ShotResult result;
        
        if(ch ==GameCharacter.ENEMYSTARSHIP){
            fire = enemy.fire();
            result = station.receiveShot(fire);
            
            if(result == ShotResult.RESIST){
                fire = station.fire();
                result = enemy.receiveShot(fire);
                
                enemyWins = (result==ShotResult.RESIST);
            }else{
                enemyWins=true;
            }
        }else{
            fire = station.fire();
            result =enemy.receiveShot(fire);
            enemyWins=(result==ShotResult.RESIST);
        }
        
        if(enemyWins){
            float s= station.getSpeed();
            boolean moves= dice.spaceStationMoves(s);
            
            if(!moves){
                Damage damage=enemy.getDamage();
                station.setPendingDamage(damage);
                combatResult=CombatResult.ENEMYWINS;
                
            }else{
                station.move();
                combatResult=CombatResult.STATIONESCAPES;
                
            }
            
            
        }else{
            Loot aLoot= enemy.getLoot();
            Transformation transformation = station.setLoot(aLoot);
            
            if (transformation==Transformation.GETEFFICIENT){
                makeStationEfficient();
                combatResult=CombatResult.STATIONWINSANDCONVERTS;
            } 
            else if (transformation==Transformation.SPACECITY){
                createSpaceCity();
                combatResult=CombatResult.STATIONWINSANDCONVERTS;
            }
            else{
                combatResult=CombatResult.STATIONWINS;
            }
        }
        
        gameState.next(turns, spaceStations.size());
        
        return combatResult;
        
       
    }
    
    public void discardHangar(){
        if(gameState.getState() == GameState.INIT || gameState.getState() == GameState.AFTERCOMBAT){
            currentStation.discardHangar();
        }
    }
    
    public void discardShieldBooster(int i){
        if(gameState.getState() == GameState.INIT || gameState.getState() == GameState.AFTERCOMBAT){
            currentStation.discardShieldBooster(i);
        }
    }
    
    public void discardShieldBoosterInHangar(int i){
        if(gameState.getState() == GameState.INIT || gameState.getState() == GameState.AFTERCOMBAT){
            currentStation.discardShieldBoosterInHangar(i);
        }
    }
    
    public void discardWeapon(int i){
        if(gameState.getState() == GameState.INIT || gameState.getState() == GameState.AFTERCOMBAT){
            currentStation.discardWeapon(i);
        }
    }
    
    public void discardWeaponInHangar(int i){
        if(gameState.getState() == GameState.INIT || gameState.getState() == GameState.AFTERCOMBAT){
            currentStation.discardWeaponInHangar(i);
        }
    }
    
    
    public GameState getState(){
        return gameState.getState();
    }
    
    public GameUniverseToUI getUIversion(){
        return new GameUniverseToUI(this.currentStation, this.currentEnemy);
    }
    
    public boolean haveAWinner(){
        if(currentStation.getNMedals()>=WIN)
            return true;
        else
            return false;
    }
    
    public void init(ArrayList<String> names){
        GameState state = gameState.getState();
        
        if(state == GameState.CANNOTPLAY){        
            CardDealer dealer = CardDealer.getInstance();
            
            for(int i =0 ; i < names.size(); i++){
                
                SuppliesPackage supplies =  dealer.nextSuppliesPackage();
                SpaceStation station = new SpaceStation(names.get(i),supplies);
                spaceStations.add(station);
                int nh = dice.initWithNHangars();
                int nw = dice.initWithNWeapons();
                int ns = dice.initWithNShields();
                Loot lo = new Loot(0,nw,ns,nh,0);
                station.setLoot(lo);
                
             }
            
            currentStationIndex=dice.whoStarts(names.size());
            currentStation= spaceStations.get(currentStationIndex);
            currentEnemy = dealer.nextEnemy();
            
            gameState.next(turns, spaceStations.size());
        }
    }
    
    public void mountShieldBooster(int i){
       
        if( getState() == GameState.AFTERCOMBAT ||  getState() == GameState.INIT)
            currentStation.mountShieldBooster(i);
    }
    
    public void mountWeapon(int i){
        
        if( getState() == GameState.AFTERCOMBAT ||  getState() == GameState.INIT)
            currentStation.mountWeapon(i);
    }
    
    public boolean nextTurn(){
        GameState state = gameState.getState();
        if(state== GameState.AFTERCOMBAT){
            boolean stationState = currentStation.validState();
            if(stationState){
                currentStationIndex= (currentStationIndex+1)%spaceStations.size();
                turns++;
                currentStation=spaceStations.get(currentStationIndex);
                currentStation.cleanUpMountedItems();
                CardDealer dealer= CardDealer.getInstance();
                currentEnemy= dealer.nextEnemy();
                
                gameState.next(turns, spaceStations.size());
                
                return true;
            }
            
        }
        
        return false;
    }
    
    public String toString(){
        return  "El universo del juego tiene: "+
                "\nIndice Estacion Actual: "+currentStationIndex+
                //"\nEstacion Actual: "+currentStation.toString()+
                "\nTurnos: "+turns+
                //"\nEstaciones Espaciales del Universo: "+spaceStations.toString()+
                //"\nEnemigo Actual: "+currentEnemy.toString()+ 
                "\nDado: "+dice.toString();
    }
    
}
