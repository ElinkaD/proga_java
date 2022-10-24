package main;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

class Sklad implements Queue{

    private int[] mas = new int[SIZE];
    private int front,rear;
    private final static int SIZE = 10;

    //переменные для сериализации доступа к очереди
    private ReentrantLock locker;
    private Condition conempty;
    private Condition confull;

    public boolean ready; //указывает на то, что потребитель потребил значение со склада
    public boolean ready(){
        return ready;
    }

    public Sklad(){  //конструктор склада
        front = 0;
        rear = SIZE - 1; //не может быть больше 9

        locker = new ReentrantLock(); // создаем блокировку
        conempty = locker.newCondition(); // получаем условие, связанное с блокировкой
        confull = locker.newCondition();
    }

    private static int next(int i){ //возврат след номер эл массива после конечного
        return ((i + 1) % SIZE);
    }

    @Override
    public void put(int znach) throws InterruptedException{
        locker.lock();   //устанавливаем блокировку
        try {
            ready = false;
            while (full())     //пока склад полон, ждем освобождения места
                confull.await();
            rear = next(rear);          // увеличиваем номер конца очереди
            mas[rear] = znach;            //добавляем полученный элемент в массив
            System.out.println("Производитель добавил - " + znach);
            show();
            conempty.signalAll();   // сигнализируем о том, что потоки потребители могут начать работать
        } finally {
            locker.unlock();     //обязательно в конце снимаем блокировку
        }
    }

    @Override
    public int get() throws InterruptedException{
        locker.lock(); //устанавливаем блокировку
        try{
            while (empty()) {   //пока склад пустой, ждем добавления элементов
                conempty.await();
            }
            ready = true;          // Производитель может увеличить значение, так как потребитель потребил
            int pastf = mas[front];
            front = next(front); // увеличиваем номер начала очереди
            System.out.print(Thread.currentThread().getName() + " потребил - " + pastf +"\n");
            show();
            confull.signalAll(); // сигнализируем о том, что поток производителя может начать работать
            return pastf;
        }
        finally{
            locker.unlock(); //обязательно в конце снимаем блокировку
        }
    }

    @Override
    public boolean full() {
        return next(next(rear)) == front; //если в конец добавить 2 эл и он станет равен начальному, то очередь полная
    }
    @Override
    public boolean empty() {
        return next(rear) == front; //если в конец добавить элемент и он станет равен начальному, то очередь пустая
    }
    public void show() throws InterruptedException { //
        locker.lock();
        int i = front;
        int r = next(rear);
        while(i != r){
            System.out.print(" " + mas[i]);
            i = next(i);
        }
        System.out.println();
        locker.unlock();

    }
}
