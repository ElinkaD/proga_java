package main;

public class Consumer implements Runnable{
    Sklad sklad;

    Consumer(Sklad sklad,String name){
        this.sklad = sklad;
        new Thread(this,name).start(); //вызов потока потребителя
    }

    public void run(){
        try{
            while (true){
                int p = (int) (Math.random()*5);
                for(int i=1; i < p; i++){
                    sklad.get();
                }
                Thread.sleep(100);
            }
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
