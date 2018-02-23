package virus;

import beings.Being;
import beings.animals.Pig;
import beings.person.Person;

/**
 * @author ZHONG Ming
 */
public class H1N1 extends Virus {

    public H1N1() {
        this.name = "H1N1";
        this.infectRate = 0.3;
        this.mortRate = 0.4;
    }

    public boolean canInfect(Being infected, Being victim) {
        if (infected.getClass() == Pig.class)
            if (victim.getClass() == Pig.class || victim.getClass() == Person.class)
                return true;
        if (infected.getClass() == Person.class)
            if (victim.getClass() == Person.class)
                return true;
        return false;
    }
}
