package main;

import ru.ifmo.se.pokemon.*;

public class BulkUp extends StatusMove {
    public BulkUp(){
        super(Type.FIGHTING,0.0D, 1.0D);
    }
    protected void applyOppEffects(Pokemon var1) {
        Effect eff = new Effect().stat(Stat.ATTACK, +1);
        var1.addEffect(eff);
        Effect eff1 = new Effect().stat(Stat.DEFENSE, +1);
        var1.addEffect(eff1);
    }
    public String describe() {
        return ("использует аттаку Bulk Up");
    }
}
