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
            System.out.println("=================================");
            System.out.println("File "+filename +"Downloaded");       
            System.out.println("=================================");
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    private static String getFileName(HttpURLConnection conn, String urlStr) {
        String disposition = conn.getHeaderField("Content-Disposition");

        if (disposition != null && disposition.contains("filename=")) {
            return disposition.split("filename=")[1].replace("\"", "");
        }
        return urlStr.substring(urlStr.lastIndexOf("/") + 1);
    }
}