package beings.person;

/**
 * @author ZHONG Ming
 */

import beings.Being;
import  static beings.State.*;

public class Person extends Being {

    public Person(){
        this.states.add(Healthy);
        this.incubationTime = 3;
    }
    public String toString() {
        return "Person";
    }
}
