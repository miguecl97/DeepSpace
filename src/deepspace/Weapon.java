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
class Weapon implements CombatElement {
    private String name;
    private WeaponType type;
    private int uses;
    
    Weapon(String name, WeaponType type, int uses){
        this.name = name;
        this.type = type;
        this.uses = uses;
    }
    
    Weapon(Weapon w){
        this(w.name,w.type,w.uses);
    }
    
    WeaponToUI getUIversion(){
        return new WeaponToUI(this);   
    }
    
    public WeaponType getType(){
        return type;
    }
    
    public float power(){
        return type.getPower();
    }
    
    @Override
    public int getUses(){
        return uses;
    }
   
    
    @Override
    public float useIt(){
        if(uses > 0){
            uses--;
            return power();
        }else
            return 1.0f;
   
    }
    
    
    public String toString(){
        
        String mensaje = "Nombre: "+name+
                "\nTipo: "+getType()+"("+power()+
                ")\nUsos: "+getUses()+"\n";
        return mensaje;
    }
}
