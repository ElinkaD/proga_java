package main;

import ru.ifmo.se.pokemon.*;

public class RockSlide extends PhysicalMove {
    public RockSlide(){
        super(Type.ROCK,75,90);
    }

    public String describe() {
        return ("использует аттаку Rock Slide");
    }
}
