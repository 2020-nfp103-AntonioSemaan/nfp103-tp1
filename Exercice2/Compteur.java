package Exercice2;

import java.util.concurrent.atomic.AtomicInteger;

public class Compteur extends Thread{
    AtomicInteger rank;
    public Compteur(String name,AtomicInteger rank){
        super(name);
        this.rank=rank;
    }

    public void run(){
        for(int i=0;i<5;i++){
            System.out.println(currentThread().getName()+" = "+i);
            try{
                Thread.sleep(500);
            }
            catch(InterruptedException e){

            }
            if(i==4){
                synchronized(System.out){
                    System.out.printf("the thread %s has ended with %d rank\n",currentThread().getName(),rank.incrementAndGet());
                }
            }
        }
        
    }
    public static void main(String[] args) {
        AtomicInteger rank=new AtomicInteger(0);
        Compteur[] cpts = {
            new Compteur("TH1",rank),
            new Compteur("TH2",rank),
            new Compteur("TH3",rank),
            new Compteur("TH4",rank)
        };
        for(Compteur c:cpts){
            c.start();
        }
        
    }
}