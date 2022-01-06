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
 * La clase Loot representael botín que se obtiene al vencer a una nave enemiga
 */

class Loot {
    
    private int nSupplies; 
    private int nWeapons;
    private int nShields;
    private int nHangars;
    private int nMedals;
    private boolean getEfficient;
    private boolean spaceCity;    
    
    
    Loot (int nSupplies, int nWeapons, int nShields, int nHangars, int nMedals){
        
        this.nSupplies = nSupplies;
        this.nWeapons = nWeapons;
        this.nShields = nShields;
        this.nHangars = nHangars;
        this.nMedals = nMedals;
        this.getEfficient = false;
        this.spaceCity = false;
    }
   
    Loot(Loot l){
        this(l.nSupplies,l.nWeapons,l.nShields,l.nHangars,l.nMedals,l.getEfficient,l.spaceCity);
    }
    
    
    Loot(int nSupplies, int nWeapons, int nShields, int nHangars, int nMedals, boolean ef, boolean sc){
        this.nSupplies = nSupplies;
        this.nWeapons = nWeapons;
        this.nShields = nShields;
        this.nHangars = nHangars;
        this.nMedals = nMedals;
        this.getEfficient = ef;
        this.spaceCity = sc;
    }
    
    public boolean getEfficient(){
        return getEfficient;
    }
    
    public boolean spaceCity(){
        return spaceCity;
    }
    
    LootToUI getUIversion(){
        return new LootToUI(this);
    }
    public int getNSupplies(){
        return nSupplies;
    }
    
    public int getNWeapons(){
        return nWeapons;
    }
    
    public int getNShields(){
        return nShields;
    }
    
    public int getNHangars(){
        return nHangars;
    }
    
    public int getNMedals(){
        return nMedals;
    }

    
    public String toString(){
        String mensaje = "Recursos: "+getNSupplies()+
                "\nArmas: "+getNWeapons()+
                "\nEscudos: "+getNShields()+
                "\nHangares: "+getNHangars()+
                "\nMedallas: "+getNMedals()+
                "\nEstación eficiente: "+ getEfficient()+
                "\nCiudad espacial: "+ spaceCity();
        return mensaje;
    }   
    
}
