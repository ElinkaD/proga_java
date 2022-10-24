package main;

import ru.ifmo.se.pokemon.*;

public class Constrict extends PhysicalMove {
    public Constrict(){
        super(Type.NORMAL,10,100);
    }
    protected void applyOppEffects(Pokemon var1) {
        Effect eff = new Effect().chance(0.1).stat(Stat.SPEED, -1);
        var1.addEffect(eff);
    }
    public String describe() {
        return ("использует аттаку Constrict");
    }
}
