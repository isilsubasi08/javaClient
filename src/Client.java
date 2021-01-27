import java.io.*;
import java.net.*;

public class Client {

    public static void main(String[] args) throws IOException {

        Socket socket = null;
        PrintWriter out = null;
        BufferedReader in = null;

        try {
            socket = new Socket("localhost", 5555); // "localhost" ya da sunucu IP adresi
            // input stream ve output stream yaratılıyor...
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (UnknownHostException e) {
            System.err.println("Sunucu bulunamadı");
            System.exit(1);
        } catch (IOException e) {
            System.err.println("I/O exception:" + e.getMessage());
            System.exit(1);
        }
        System.out.println("Sunucuya baglanildi.");
        // klavyeden girdi: stdIn
        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
        String userInput;
        System.out.println("Buyuk harflere cevrilmesi icin girdi bekleniyor (baglantiyi kesmek icin: bye) ...");
        while (!(userInput = stdIn.readLine()).equals("bye")) {
            out.println(userInput);
            System.out.println("Sunucudan gelen: " + in.readLine());
        }
        System.out.println("Baglanti kesiliyor...");

        out.close();
        in.close();
        stdIn.close();
        socket.close();
    }
}