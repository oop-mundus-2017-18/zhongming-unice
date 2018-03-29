package beings.animals;

import static beings.State.Healthy;
import static beings.State.Infected;

import virus.H5N1;

/**
 * @author ZHONG Ming
 */
public class Duck extends Animal {
    public Duck() {
        if (Math.random() < 0.2)
            this.states.add(Healthy);
        else {
            this.states.add(Infected);
            this.virus = new H5N1();
        }
        this.incubationTime = 1;
        this.kind = "Duck";
    }
}
