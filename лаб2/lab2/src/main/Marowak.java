package main;

public class Marowak extends Cubone{
    public Marowak(String name,int l){
        super(name,l);
        setStats(60, 80, 110, 50, 80, 46);
        addMove(new ConfuseRay());
    }
}
