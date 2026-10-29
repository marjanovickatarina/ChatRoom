import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class ReadMsg implements Runnable {
    private Socket socket;
    public ReadMsg(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try(
                Scanner in = new Scanner(socket.getInputStream());
        ){

            while(in.hasNext()){
                String read = in.nextLine();
                System.out.println(read);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
