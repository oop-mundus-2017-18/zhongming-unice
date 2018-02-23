package virus;

import beings.Being;
import beings.animals.*;
import beings.person.Person;

/**
 * @author ZHONG Ming
 */
public class H5N1 extends Virus {

    public H5N1() {
        this.name = "H5N1";
        this.infectRate = 0.6;
        this.mortRate = 0.1;
    }

    public boolean canInfect(Being infected, Being victim) {
        if (infected.getClass() == Chicken.class || infected.getClass() == Duck.class)
            if (victim.getClass() == Chicken.class || victim.getClass() == Duck.class
                    || victim.getClass() == Person.class)
                return true;
        if (infected.getClass() == Person.class)
            if (victim.getClass() == Person.class)
                return true;
        return false;
    }
}
