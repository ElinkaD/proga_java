package main;

import ru.ifmo.se.pokemon.*;

public class Confide extends StatusMove {
    public Confide(){
        super(Type.NORMAL,0.0D, 1.0D);
    }
    protected void applyOppEffects(Pokemon var1) {
        var1.setMod(Stat.SPECIAL_ATTACK, -1);
    }
    public String describe() {
        return ("использует аттаку Confide");
    }
}
