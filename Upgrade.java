
public abstract class Upgrade
{   
    private String name;
    private String description;
    
    private int cost;
    
    public Upgrade(String name, int upgradeCost){
        this.name = name;
        cost = upgradeCost;
    }
    
    //use anonymous inner classes to define upgrades
    public abstract void applyUpgrade(Plane player);
    
    public String getName(){
        return name;
    }
    public void setName(String n){
        name = n;
    }
    
    public String getDescription(){
        return description;
    }
    public void setDescription(String d){
        description = d;
    }
    
    public int getCost(){
        return cost;
    }
    public void setCost(int c){
        cost = c;
    }
}
