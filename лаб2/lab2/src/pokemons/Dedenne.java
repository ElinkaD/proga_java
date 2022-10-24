package pokemons;

import moves.*;
import ru.ifmo.se.pokemon.*;

public class Dedenne extends Pokemon {
    public Dedenne(String name, int level) {
        super(name, level);
        setStats(67, 58, 57, 81, 67, 101);
        setType(Type.ELECTRIC, Type.FAIRY);
        setMove(new NastyPlot(), new ChargeBeam(), new TakeDown(), new Astonish());
    }
}
