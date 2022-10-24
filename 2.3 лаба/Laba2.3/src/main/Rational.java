package main;

public class Rational implements Comparable<Rational>{

    private int a,q;

    Rational(int a1, int q1) throws ArithmeticException{ // конструктор
        if(q1 == 0){
            throw new ArithmeticException("Знаменатеь равен 0");
        }
        if(q1 < 0){
            a1 = -a1;
            q1 = -q1;
        }
        int NOD = Math.abs(nod(a1,q1));  //для сокращения дроби
        a = a1/NOD;
        q = q1/NOD;
    }

    private static int nod(int a, int b){  // алгоритм евклида
        int c = a%b;
        while (c != 0){
            a = b;
            b = c;
            c = a%b;
        }
        return b;
    }

    @Override
    public int compareTo(Rational obj) {
        return (a* obj.q) - (q * obj.a);
    }

    @Override
    public String toString(){
        if(a == 0 || q == 1)
            return a +"";
        else if(Math.abs(a) < Math.abs(q))
            return a + "/" + q;
        else
            return a/q + " " + Math.abs(a%q) + "/" + Math.abs(q);
    }
}
