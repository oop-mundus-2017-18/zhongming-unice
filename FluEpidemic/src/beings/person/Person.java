package beings.person;

/**
 * @author ZHONG Ming
 */

import beings.Being;
import  static beings.State.*;

public class Person extends Being {
    private boolean hand_washing;

    public Person(){
        this.states.add(Healthy);
        this.incubationTime = 3;
        this.kind = "Person";
    }

}
