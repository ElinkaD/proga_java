package main;

public class Main {

    public static void main(String[] args) {
        AnyList<Integer> listI = new AnyList<>(new Integer[10]);
        listI.Insert(1, listI.End());
        listI.Insert(2, listI.End());
        listI.Insert(1, listI.End());
        listI.Insert(3, listI.End());
        listI.Printlist();
        deleteSame(listI);
        listI.Printlist();


        AnyList<String> listS = new AnyList<>(new String[10]);
        listS.Insert("один", listS.End());
        listS.Insert("два", listS.End());
        listS.Insert("два", listS.End());
        listS.Printlist();
        deleteSame(listS);
        listS.Printlist();

        AnyList<Rational> listR = new AnyList<>(new Rational[10]);
        listR.Insert(new Rational(5,1), listR.End());
        listR.Insert(new Rational(0,35), listR.End());
        listR.Insert(new Rational(35,7), listR.End());
        listR.Insert(new Rational(-56,46), listR.End());
        listR.Printlist();
        deleteSame(listR);
        listR.Printlist();
    }

    public static <T extends Comparable<T>> void deleteSame(AnyList<T> L){ //метод удаляющий повторные элементы
        int i = L.First();
        int j;

        while(i != L.End()){
            j = L.Next(i);
            while(j != L.End()){
                if ((L.Retrieve(i).compareTo(L.Retrieve(j))) == 0)
                    L.Delete(j);
                else
                    j = L.Next(j);
            }
            i = L.Next(i);
        }
    }
}
