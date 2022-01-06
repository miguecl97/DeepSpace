/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deepspace;


import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author miguelcant
 */
public class SpaceStation implements SpaceFighter {
    
    private static final float MAXFUEL = 100f; //Máxima cantidad de unidades de combustible que puede tener una estación espacial.
    private static final float SHIELDLOSSPERUNITSHOT = 0.1f; //Unidades de escudo que se pierden por cada unidad de potencia de disparo recibido.
    
    private float ammoPower;
    private float fuelUnits;
    private String name;
    private int nMedals;
    private float shieldPower;
    
    private ArrayList<Weapon> weapons = new ArrayList<>();
    private ArrayList<ShieldBooster> shieldBoosters = new ArrayList<>();
    private Hangar hangar;   
    private Damage pendingDamage;
    
    
    SpaceStation(String n, SuppliesPackage supplies){
        name = n;
        ammoPower=supplies.getAmmoPower();
        assignFuelValue(supplies.getFuelUnits());
        shieldPower=supplies.getShieldPower();
        pendingDamage=null;
        hangar=null;
    }
    
    SpaceStation(SpaceStation station){
        name=station.getName();
        ammoPower=station.getAmmoPower();
        //fuelUnits=station.getFuelUnits();
        assignFuelValue(station.getFuelUnits());
        shieldPower=station.getShieldPower();
        nMedals=station.getNMedals();
        pendingDamage=station.getPendingDamage();
        
        for(Weapon w : station.weapons){
            weapons.add(w);
        }
        for(ShieldBooster s : station.shieldBoosters){
            shieldBoosters.add(s);
        }
        
        hangar=station.getHangar();
    }
    
    SpaceStationToUI getUIversion(){
        return new SpaceStationToUI(this);
    }
    public float getAmmoPower() {
        return ammoPower;
    }

    public float getFuelUnits() {
        return fuelUnits;
    }

    public String getName() {
        return name;
    }

    public int getNMedals() {
        return nMedals;
    }

    public float getShieldPower() {
        return shieldPower;
    }

    public float getSpeed(){
        return (fuelUnits/MAXFUEL);
    }
    
    public ArrayList<Weapon> getWeapons() {
        return weapons;
    }

    public ArrayList<ShieldBooster> getShieldBoosters() {
        return shieldBoosters;
    }

    public Hangar getHangar() {
        return hangar;
    }

    public Damage getPendingDamage() {
        return pendingDamage;
    }
    
    @Override
    public float fire(){
        int size = weapons.size();
        float factor = 1.0f;
        
        for(int i=0; i<size; i++){
            Weapon w = weapons.get(i);
            factor = factor * w.useIt();
        }
        
        return ammoPower*factor;
    }
 
    @Override
    public float protection(){
        //int size = shieldBoosters.size()
        float factor = 1.0f;
        
        for(ShieldBooster s : shieldBoosters){
            factor*=s.useIt();
        }
        
        return shieldPower*factor;
    }
    
    @Override
    public ShotResult receiveShot(float shot){
        float myProtection= protection();
        
        if(myProtection >=shot){
            shieldPower= Math.max(shieldPower-(SHIELDLOSSPERUNITSHOT*shot),0.0f);
            return ShotResult.RESIST;
        }else{
            shieldPower=0.0f;
            return ShotResult.DONOTRESIST;
        }
    }
    
 
    private void assignFuelValue( float f){
        fuelUnits= Math.min(f,MAXFUEL);
    }
    
    private void cleanPendingDamage(){
        if(pendingDamage.hasNoEffect())
            pendingDamage = null;
    }
    
    
    
    public void cleanUpMountedItems(){
        
        for (Iterator<Weapon> iterator = weapons.iterator(); iterator.hasNext();) {
            Weapon w = iterator.next();
            if (w.getUses()==0) {
                // Remove the current element from the iterator and the list.
                iterator.remove();
            }
        }
        
        for (Iterator<ShieldBooster> iterator = shieldBoosters.iterator(); iterator.hasNext();) {
            ShieldBooster s = iterator.next();
            if (s.getUses()==0) {
                // Remove the current element from the iterator and the list.
                iterator.remove();
            }
        }        
        /*
        esto da error de concurrencia ya que cuando eliminas desordenas el array. se podria hacer recorriendo desde size a 0
        for(Weapon w : weapons){
            if(w.getUses()==0)
                weapons.remove(w);
        }
        for(ShieldBooster s : shieldBoosters){
            if(s.getUses()==0)
                shieldBoosters.remove(s);
        }*/
    }
    
    public void discardHangar(){
        hangar = null;
    }
    
    public void discardShieldBooster(int i){
        int size= shieldBoosters.size();
        
        if(i>=0 && i<size){
            ShieldBooster s = shieldBoosters.remove(i);
            
            if(pendingDamage!=null){
                pendingDamage.discardShieldBooster();
                cleanPendingDamage();
            }
        }
    }
    
    public void discardShieldBoosterInHangar(int i){
        if(hangar != null)
            hangar.removeShieldBooster(i);
    }
    
    public void discardWeapon(int i){
        int size= weapons.size();
        
        if(i>=0 && i<size){
            Weapon w = weapons.remove(i);
            
            if(pendingDamage!=null){
                pendingDamage.discardWeapon(w);
                cleanPendingDamage();
            }
        }
        
    }
    
    
    public void discardWeaponInHangar(int i){
        if(hangar != null){
            hangar.removeWeapon(i);
        }
    }

    
    public void mountShieldBooster(int i){
        ShieldBooster s;
        if(hangar != null && i>=0){
            s = hangar.removeShieldBooster(i);
            if(s != null)
                shieldBoosters.add(s);
        }
    }
    
    public void mountWeapon(int i){       
        Weapon w;
        if(hangar != null && i >= 0){
            w = hangar.removeWeapon(i);
            if(w != null)
                weapons.add(w);
        }
    }
    
    public void move(){
        
        fuelUnits = Math.max(0, fuelUnits - getSpeed());

    }
   
    
    public void receiveHangar(Hangar h){
        if( hangar == null)
            hangar = h;
    }  
    
    public boolean receiveShieldBoosters(ShieldBooster s){
        
        if( hangar != null)
            return hangar.addShieldBooster(s);
        else
            return false;
    }
       

    
    public void receiveSupplies(SuppliesPackage s){
        
        ammoPower += s.getAmmoPower();
        shieldPower += s.getShieldPower();
        
        assignFuelValue(fuelUnits+s.getFuelUnits());
        

    }
    
    public boolean receiveWeapon(Weapon w){
        if( hangar != null)
            return hangar.addWeapon(w);
        else
            return false;
    }
    
    public boolean receiveShieldBooster(ShieldBooster s){
        if( hangar != null)
            return hangar.addShieldBooster(s);
        else
            return false;
    }
    
    public Transformation setLoot(Loot loot){
        
        CardDealer dealer = CardDealer.getInstance();
        int h = loot.getNHangars();
        
        if (h>0){
            hangar= dealer.nextHangar();
            receiveHangar(hangar);
        }
        
        int elements= loot.getNSupplies();
        
        for(int i=0; i< elements; i++){
            SuppliesPackage sup = dealer.nextSuppliesPackage();
            receiveSupplies(sup);
        }
        
        elements= loot.getNWeapons();
        
        for(int i=0; i< elements; i++){
            Weapon weap = dealer.nextWeapon();
            receiveWeapon(weap);
        }
        
        elements= loot.getNShields();
        
        for(int i=0; i< elements; i++){
            ShieldBooster sh = dealer.nextShieldBooster();
            receiveShieldBooster(sh);
        }
        
        int medals= loot.getNMedals();
        nMedals+=medals;
        
        
        if (loot.spaceCity())
            return Transformation.SPACECITY;
        else if (loot.getEfficient())
            return Transformation.GETEFFICIENT;
        
        
        return Transformation.NOTRANSFORM;
    }
    
    public void setPendingDamage(Damage d){
        
        pendingDamage = d.adjust(weapons, shieldBoosters);
        cleanPendingDamage();
    }
    
    public boolean validState(){
        if (pendingDamage ==  null || pendingDamage.hasNoEffect())
            return true;
        else
            return false;
    }

    public String toString(){
        String cout ="\nEstación Espacial: "+
                "\nNombre: "+getName()+ 
                "\nPotencia Disparo: "+ammoPower+
                "\nUnidades Combustible: "+fuelUnits+
                "\nMedallas: "+nMedals+
                "\nPotencia Escudo: "+shieldPower+ 
                "\nARMAS:\n";
                for (Weapon arma : weapons)
                    cout+=arma.toString()+"\n";
        
                cout+="\nPOTENCIADORES ESCUDO:\n";
                
                for (ShieldBooster potenciador:shieldBoosters)
                    cout+=potenciador.toString()+"\n";
        
                cout+="\nDaño Pendiente: ";
        
                if(pendingDamage!=null) 
                    cout+=pendingDamage.toString();
                else
                    cout+="No tiene";
        
                if(hangar!=null)
                    cout+="\nHangar: "+hangar.toString();
                else
                    cout+="\nNo tiene ningún hangar\n";
        return cout;
    }
    
}
