import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class kServer {
    private ServerSocket ss;
    private ArrayList<String> users;
    private Map<String, Socket> loggedUsers;

    public kServer(ServerSocket ss) {
        this.ss = ss;
        users = new ArrayList<>();
        users.add("a");
        users.add("b");
        loggedUsers = new HashMap<>();
    }

    public void logInUser(String user, Socket socket) {
        loggedUsers.put(user, socket);
    }

    public void logOutUser(String user) {
        loggedUsers.remove(user);
    }


    public void Dodaj(String user, String a, String b) {
            Socket s = loggedUsers.get(user);
            try {
                OutputStream os = s.getOutputStream();
                PrintWriter pw = new PrintWriter(os, true);
                pw.println(a + "" + b);
                pw.flush();
                pw.println(b + "" + a);
            } catch (IOException e) {
                e.printStackTrace();
            }

    }


    public void start() throws IOException{
        while(true){
            Socket s=ss.accept();
            ClientHandler ch=new ClientHandler(this,s);
            Thread t=new Thread(ch);
            t.start();
        }
    }

}
