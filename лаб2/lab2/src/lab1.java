
import pokemons.*;
import ru.ifmo.se.pokemon.*;
public class lab1{
    public static void main(String[] args){
        Battle b = new Battle();
        Dedenne p1 = new Dedenne("Олег", 3);
        Claydol p2 = new Claydol("Кирилл", 1);
        Baltoy p3 = new Baltoy("Денис", 2);
        Starly p4 = new Starly("Victor", 1);
        Staravia p5 = new Staravia("Evgeniy", 2);
        Staraptor p6 = new Staraptor("Anton", 3);      
        b.addAlly(p1);
        b.addAlly(p2);
        b.addAlly(p3);
        b.addFoe(p4);
        b.addFoe(p5);
        b.addFoe(p6);
        b.go();  
    }
}
