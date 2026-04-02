import java.util.Scanner;

import Downloader.*;
public class Main {
    public static void main(String[] args) {
        while (true) {
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter 1 to start new thread of Downloading\nEnter 2 to quet");
            int in = sc.nextInt();
            if(in ==1){
                System.out.println("Paste Download URL/Link");
                String url =sc.next();
                ThreadMaker tm = new ThreadMaker();
                tm.StartThread(url);
            }
            if (in == 2) {
                System.out.println("Closing Download Manager Safely");
                break;
            }

            
        }
        //Downloader downloader = new Downloader("https://dev.mysql.com/get/Downloads/MySQLInstaller/mysql-installer-web-community-8.0.45.0.msi");
    }
}
