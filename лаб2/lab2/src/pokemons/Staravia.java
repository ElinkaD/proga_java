package pokemons;

import moves.*;
import ru.ifmo.se.pokemon.*;

public class Staravia extends Starly {
    public Staravia(String name, int level) {
        super(name, level);
        setStats(55, 75, 50, 40, 40, 80);
        setType(Type.NORMAL, Type.FLYING);
        addMove(new Crunch());
    }
}
