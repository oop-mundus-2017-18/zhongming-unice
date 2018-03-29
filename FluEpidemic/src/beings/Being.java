package beings;

import java.util.*;

import virus.*;
import static beings.State.*;
/**
 * @author ZHONG Ming
 */
public abstract class Being {
    public Set<State> states = new HashSet<State>();
    public Virus virus = new Virus();
    protected int incubationTime;
    protected int infectedDay = 0;
    public String kind;

    public int getInfectedDay() {
        return infectedDay;
    }
    
    public void zeroInfectedDay() {
        this.infectedDay = 0;
    }
    public void InfectedDayPlus() {
        this.infectedDay++;
    }

    public int getIncubationTime() {
        return incubationTime;
    }

    public String condition() {
        StringBuffer condition = new StringBuffer();
        for (State item : states) {
            condition.append(item);
            condition.append(' ');
        }
        if(states.contains(Infected)) {
            condition.append("infected by ");
            condition.append(virus.name);
        }
            
        return condition.toString();
    }
}
