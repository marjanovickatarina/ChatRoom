import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;

public class Worker extends Thread{
    private Socket client;
    private String username;
    private BufferedWriter out;

    public Worker(Socket client) {
        this.client = client;
        try {
            this.out = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void sendMessage(String msg){
        try {
            out.write(msg);
            out.newLine();
            out.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void run() {
        serve();

        if(client != null && !client.isClosed()) {
            try {
                client.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        if(out != null){
            try {
                out.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void serve() {
        try(
                Scanner sc = new Scanner(client.getInputStream());
        ){

            this.username = sc.nextLine();

            sendMessage("Friends available: " + ChatServer.getUsernames());
            ChatServer.broadcast(this, username + " connected! ");

            while(sc.hasNext()){
                String msg = sc.nextLine();
                ChatServer.broadcast(this, "[ " + username + " ]:" + msg);
                if(msg.equals("bye"))
                    break;
            }

            ChatServer.broadcast(this,username + " dissconected!");
            ChatServer.removeClient(this);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String getUsername() {
        return this.username;
    }
}
