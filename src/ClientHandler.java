import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.StringTokenizer;

public class ClientHandler implements Runnable {
    private Socket userSocket;
    private String loggedUser;
    private kServer server;
    private boolean sessionFinished = false;

    public ClientHandler(kServer server, Socket userSocket) {
        super();
        this.server = server;
        this.userSocket = userSocket;
    }

    public void run() {
        try (InputStream is = userSocket.getInputStream();
             OutputStream os = userSocket.getOutputStream();
             Scanner sc = new Scanner(is);
             PrintWriter pw = new PrintWriter(os, true)) {
            pw.println("Witaj na dodaj serwerze. Podaj login.");
            pw.flush();
            String linia = sc.nextLine();
            loggedUser=linia;
            server.logInUser(loggedUser, userSocket);
          //  linia=sc.nextLine();
            while (true)
            userData(linia,sc.nextLine(),sc.nextLine());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void userData(String user,String text1 , String text2){
        StringTokenizer st = new StringTokenizer(user);
        String firstToken=st.nextToken();
        server.Dodaj(user,text1,text2);
    }





}