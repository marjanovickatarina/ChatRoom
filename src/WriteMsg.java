import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;

public class WriteMsg implements Runnable {
    private Socket socket;
    private final String username;

    public WriteMsg(String username, Socket socket) {
        this.username = username;
        this.socket = socket;
    }

    @Override
    public void run() {
        try(
                BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                Scanner in = new Scanner(System.in);
        ){
            out.write(username);
            out.newLine();
            out.flush();
            while(true){
                String msg = in.nextLine();
                out.write(msg);
                out.newLine();
                out.flush();
                if(msg.equals("bye"))
                    break;
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}


