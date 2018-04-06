package simulation;

import beings.*;
import beings.animals.*;
import beings.person.Person;
import virus.*;

import static beings.State.*;

/**
 * @author ZHONG Ming
 */
public class Main {

    public static void main(String[] args) {
        Field field = new Field(30, 30);
        for (int row = 0; row < field.getHeight(); row++) {
            for (int col = 0; col < field.getWidth(); col++) {
                double i = Math.random();
                if (i < 0.4)
                    field.place(row, col, new Person());
                else if (i < 0.6)
                    field.place(row, col, new Chicken());
                else if (i < 0.8)
                    field.place(row, col, new Duck());
                else
                    field.place(row, col, new Pig());
            }
        }

        for (int i = 0; i < 30; i++) {
            System.out.println("Day " + i + " : ");
            System.out.println("------------------------------------------------");
            for (int row = 0; row < field.getHeight(); row++) {
                for (int col = 0; col < field.getWidth(); col++) {
                    Being being = field.get(row, col);
                    if (being != null) {
                        Being[] neighbour = field.getNeighbour(row, col);
                        if (being.states.contains(Infected))
                            being.InfectedDayPlus();
                        if (being.getInfectedDay() > being.getIncubationTime()) {
                            being.states.add(Contagious);
                        }
                        if (being.states.contains(Healthy) && !being.states.contains(Recovering)) {
                            for (Being b : neighbour) {
                                if (b.states.contains(Contagious) && b.virus != null && b.virus.canInfect(b, being)) {
                                    if (b.virus.getInfectRate() > Math.random()) {
                                        being.states.remove(Healthy);
                                        being.states.add(Infected);
                                        being.virus = b.virus;
                                    }
                                }
                            }
                        }
                        if (being.getInfectedDay() > 6 && being.getClass() == Person.class) {
                            being.zeroInfectedDay();
                            being.states.remove(Infected);
                            being.states.remove(Contagious);
                            if (being.virus.getMortRate() < Math.random()) {
                                being.states.add(Recovering);
                                being.states.add(Healthy);
                            } else {
                                being.states.add(Dead);
                            }
                        }
                        if (being.getClass() == Animal.class) {
                            Animal item = (Animal) being;
                            if (item.getMortalityRate() > Math.random())
                                being.states.clear();
                            being.states.add(Dead);

                        }
                        System.out.print("[" + row + "][" + col + "]:");
                        System.out.print(being.toString() + " : ");
                        System.out.print(being.condition());
                        System.out.println();
                    }
                }
            }

        }

    }

}