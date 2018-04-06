package beings.animals;

import static beings.State.Healthy;
import static beings.State.Infected;

import virus.*;

/**
 * @author ZHONG Ming
 */
public class Chicken extends Animal {
    public Chicken() {
        this.mortalityRate = 0.01;
        if(Math.random() < 0.7)
        this.states.add(Healthy);
        else {
            this.states.add(Infected);
            this.virus = new H5N1();
        }
        this.incubationTime = 5;
    }

    public String toString() {
        return "Chicken";
    }
}
