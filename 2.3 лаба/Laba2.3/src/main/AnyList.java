package main;

public class AnyList<T extends Comparable<T>>{

    private T[] L;
    private int last; //позиция последнего занятого

    public AnyList(T[] L){
        this.L = L;
        last = -1;
    }

    public int End(){
        return last+1; //позиция после последнего занятого
    }

    public void Insert(T x, int p){ //
        if(p < last || p >= 0){ //перемещение элементов с позиции p на p+1
            for(int i = (p+1); i <= last; i++){
               L[i] = L[i+1];
            }
            L[p] = x;
        }
        L[p] = x;
        last++;
    }

    public int Locate(T x){ //возвращает позицию в списке L объекта x
        for(int i = 0; i <= last; i++){
            if (L[i].compareTo(x) == 0){
                return i;
            }
        }
        return End(); // если элемент не найден
    }

    public T Retrieve(int p){ //возвращает элемент в позицию р списка L.
        if(p == End() || p > L.length){
            throw new IndexOutOfBoundsException();
        }
        return L[p];
    }

    public void Delete(int p){ //удаляет эелемент в позиции р списка L.
        if(p > 0 && p < End() ) {
            while (p != last){
                L[p] = L[++p];
            }
        }
        last--;
    }

    public int Next(int p){ //возвращает позицию следующего за р
        if(p >= End() || p < 0){ //Если p = End(L) или p нет в списке, то результат неопределен (выбросить исключение).
            throw new IndexOutOfBoundsException();
        }
        return ++p;
    }

    public int Previous(int p){ //возвращает предыдущую перед p позицию в списке L
        if(p == 1 || p >= End()){ //Результат неопределен, если p = 1, p = End(L) или позиции p нет в списке L
            throw new IndexOutOfBoundsException();
        }
        return --p;
    }

    public void Makenull(){  //делает список пустым
        last = -1;
    }

    public int First(){ //возвращает позицию первого элемента в списке,
        return 0;
    }

    public void Printlist(){ //вывод списка на печать в порядке расположения элементов в списке.
        for(int i = 0; i <= last; i++){
            System.out.print(L[i]+ " ");
        }
        System.out.println();
    }

}
