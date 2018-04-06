package beings.animals;

import static beings.State.*;

import virus.*;

/**
 * @author ZHONG Ming
 */
public class Pig extends Animal {
    public Pig() {
        this.mortalityRate = 0.08;
        if (Math.random() < 0.5)
            this.states.add(Healthy);
        else {
            this.states.add(Infected);
            this.virus = new H1N1();
        }
        this.incubationTime = 4;
    }

    public String toString() {
        return "Pig";
    }
}
