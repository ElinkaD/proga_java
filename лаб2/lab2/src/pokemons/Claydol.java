package pokemons;
import ru.ifmo.se.pokemon.*;
import moves.*;

public class Claydol extends Baltoy {
    public Claydol(String name, int level) {
        super(name, level);
        setStats(60, 70, 105, 70, 120, 75);
        setType(Type.GROUND, Type.PSYCHIC);
        addMove(new Submission());
    }
}
