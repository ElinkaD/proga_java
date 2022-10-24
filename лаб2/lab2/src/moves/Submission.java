package moves;
import ru.ifmo.se.pokemon.*;
public class Submission extends PhysicalMove{
    public Submission(){
        super(Type.FIGHTING, 80, 80);
    }
    @Override
    protected void applySelfDamage(Pokemon att, double damage){
        att.setMod(Stat.HP, (int) (damage/4));
    }
    @Override
    protected String describe(){
        return "использует Submission";
    }
}