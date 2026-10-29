import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

public class ChatServer {

    private static Set<Worker> clients = Collections.synchronizedSet(new HashSet<>());
    public static void main(String[] args){
        try(
                ServerSocket ss = new ServerSocket(12321);
        ){

            while(true){
                Socket client = ss.accept();
                Worker worker = new Worker(client);
                worker.start();
                clients.add(worker);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<String> getUsernames() {
        List<String> usernames = new LinkedList<>();
        synchronized(clients){
            for(Worker w : clients)
                usernames.add(w.getUsername());
        }
        return usernames;
    }

    public static void broadcast(Worker chatter, String s) {
        synchronized (clients){
            for (Worker c : clients){
                if(c.equals(chatter))
                    continue;
                c.sendMessage(s);
            }
        }
    }

    public static void removeClient(Worker chatter) {
        clients.remove(chatter);
    }
}
