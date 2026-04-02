package Downloader;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Downloader {
    public Downloader(String Url){
        try {
            URL url=new URL(Url);
            HttpURLConnection conn =(HttpURLConnection) url.openConnection();
            String filename = getFileName(conn, Url);
            Path path = Paths.get(filename);
            if (Files.exists(path)) {
                filename = "copy- "+filename;
            }
            InputStream in = conn.getInputStream();
            FileOutputStream out = new FileOutputStream(filename);
            byte[] buffer =new byte[8192];
            int bytesRead;
            while ((bytesRead = in.read(buffer))!=-1) {
                out.write(buffer, 0,bytesRead);
            }     
            in.close();
            out.close();
            System.out.println("==========================================================");
            System.out.println("Note:- File "+filename +" Downloaded");       
            System.out.println("==========================================================");
            
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