package Downloader;
import Downloader.Downloader;
class ThreadsM extends Thread {
    String url;
    ThreadsM(String url){
        this.url = url;
    }
    @Override
    public void run(){
        Downloader d = new Downloader(url);
    }    
}

public class ThreadMaker{
    ThreadsM t;
    public void StartThread(String url){
        t= new ThreadsM(url);
        t.start();
    }
}