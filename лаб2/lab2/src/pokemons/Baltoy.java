package pokemons;

import moves.*;
import ru.ifmo.se.pokemon.*;

public class Baltoy extends Pokemon {
    public Baltoy(String name, int level) {
        super(name, level);
        setStats(40, 40, 55, 40, 70, 55);
        setType(Type.GROUND, Type.PSYCHIC);
        setMove(new Rest(), new DoubleTeam(), new Facade());
    }
}
