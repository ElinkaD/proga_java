package main;

public class Producer implements Runnable{

    private Sklad sklad;
    private int znach = 1;

    Producer(Sklad sklad){
        this.sklad = sklad;
        new Thread(this).start(); //вызов потока производителя
    }

    public void run(){
        try{
            while(true) {
                if (sklad.ready()){
                    znach ++;
                }
                sklad.put(znach);
            }
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
