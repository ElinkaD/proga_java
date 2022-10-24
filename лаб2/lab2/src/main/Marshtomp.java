package main;

public class Marshtomp extends Mudkip{
    public Marshtomp(String name, int level){
        super(name,level);
        setStats(70.0, 85.0, 70.0, 60.0, 70.0, 50.0);
        addMove(new BulkUp());
    }
}
