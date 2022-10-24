package main;

import ru.ifmo.se.pokemon.*;

public class DoubleTeam extends StatusMove {
    public DoubleTeam(){
        super(Type.NORMAL,0.0D, 1.0D);
    }
    protected void applySelfEffects(Pokemon var1){
        var1.setMod(Stat.EVASION, +1);
    }
    public String describe() {
        return ("использует аттаку Double Team");
    }
}
