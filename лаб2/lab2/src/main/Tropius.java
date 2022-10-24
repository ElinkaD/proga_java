package main;

import ru.ifmo.se.pokemon.*;

public class Tropius extends Pokemon {

    public Tropius(String name, int level){
        super(name,level);
        setType(Type.GRASS,Type.FLYING);
        setStats(99.0, 68.0, 83.0, 72.0, 87.0, 51.0);
        setMove(new RockBlast(), new Constrict(), new StoneEdge(), new RockSlide());
    }

}
