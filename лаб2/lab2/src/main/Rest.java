package main;

import ru.ifmo.se.pokemon.*;

public class Rest extends StatusMove {
    public Rest(){
        super(Type.PSYCHIC,0.0D, 1.0D);
    }
    protected void applySelfEffects(Pokemon p) {
        Effect eff = new Effect().condition(Status.SLEEP).turns(2);
        p.setMod(Stat.HP, (int) -(p.getStat(Stat.HP) - p.getHP()));
        p.addEffect(eff);
    }
    public String describe() {
        return ("использует аттаку Rest");
    }
}
