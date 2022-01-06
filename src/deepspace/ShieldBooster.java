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
class ShieldBooster implements CombatElement {
    
    private String name;
    private float boost;
    private int uses;
    
    ShieldBooster(String name, float boost, int uses){
        this.name=name;
        this.boost=boost;
        this.uses=uses;
    }
    
    ShieldBooster(ShieldBooster s){
        this(s.name, s.boost, s.uses);
    }
    
    ShieldToUI getUIversion(){
        return new ShieldToUI(this);
    }
    
    public float getBoost(){
        return boost;
    }
    
    @Override
    public int getUses(){
        return uses;
    }
    
    @Override
    public float useIt(){
        if(uses>0){
            uses--;
            return boost;
        }else{
            return 1.0f;
        }
    }
    
    public String toString(){
        String mensaje="Nombre: "+name+
                "\nPotencia: "+getBoost()+
                "\nUsos: "+getUses()+"\n";
        return mensaje;
    }
}
