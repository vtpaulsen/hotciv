package hotciv.variants.alphaCiv;

import hotciv.standard.AgingStrategy;

public class LinearAgingStrategy implements AgingStrategy {

    @Override
    public int calculateAge(int age) {
        return age + 100;
    }
}
