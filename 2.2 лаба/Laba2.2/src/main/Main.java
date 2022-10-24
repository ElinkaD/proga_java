package main;

public class Main {
    public static void main(String [] args) {
        Sklad sklad =new Sklad();
        Consumer consumer1 = new Consumer(sklad, "Потребитель1");
        Consumer consumer2 = new Consumer(sklad, "Потребитель2");
        Producer producer = new Producer(sklad);
    }
}
