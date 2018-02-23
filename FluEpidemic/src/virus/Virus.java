package virus;

import beings.Being;

/**
 * @author ZHONG Ming
 */
public abstract class Virus {
    public final static int  incubationTime = 2;
    public final static int  recoverTime = 3;
    
    protected double infectRate;
    protected double mortRate;
    protected String name;
    
    
    public Virus() {
        super();
    }
    
    public double getInfectRate() {
        return infectRate;
    }
    public void setInfectRate(double infectRate) {
        this.infectRate = infectRate;
    }
    public double getMortRate() {
        return mortRate;
    }
    public void setMortRate(double mortRate) {
        this.mortRate = mortRate;
    }
    public boolean canInfect(Being infected, Being victim) {
        
        return false;
    }
    
}
