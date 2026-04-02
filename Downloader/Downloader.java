package Downloader;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class Downloader {
    public Downloader(String Url){
        try {
            URL url=new URL(Url);
            HttpURLConnection conn =(HttpURLConnection) url.openConnection();
            String filename = getFileName(conn, Url);
            InputStream in = conn.getInputStream();
            FileOutputStream out = new FileOutputStream(filename);
            byte[] buffer =new byte[8192];
            int bytesRead;
            while ((bytesRead = in.read(buffer))!=-1) {
                out.write(buffer, 0,bytesRead);
            }     
            in.close();
            out.close();
            System.out.println("File Saved as:"+filename);       
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e.getMessage());
        }

    }
    // public static void main(String[] args) throws Exception {
    //     String fileURL = "https://dev.mysql.com/get/Downloads/MySQLInstaller/mysql-installer-web-community-8.0.45.0.msi";

    //     URL url = new URL(fileURL);
    //     HttpURLConnection conn = (HttpURLConnection) url.openConnection();

    //     String fileName = getFileName(conn, fileURL);

    //     InputStream in = conn.getInputStream();
    //     FileOutputStream out = new FileOutputStream(fileName);

    //     byte[] buffer = new byte[8192];
    //     int bytesRead;

    //     while ((bytesRead = in.read(buffer)) != -1) {
    //         out.write(buffer, 0, bytesRead);
    //     }

    //     in.close();
    //     out.close();

    //     System.out.println("Saved as: " + fileName);
    // }

    private static String getFileName(HttpURLConnection conn, String urlStr) {
        // 1. Try header
        String disposition = conn.getHeaderField("Content-Disposition");

        if (disposition != null && disposition.contains("filename=")) {
            return disposition.split("filename=")[1].replace("\"", "");
        }

        // 2. Fallback → URL
        return urlStr.substring(urlStr.lastIndexOf("/") + 1);
    }
}