import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ChatClient {
    public static void main(String[] args){
        try(
                Socket socket = new Socket("localhost", 12321);
                Scanner in = new Scanner(System.in);
        ){
            System.out.println("Enter username:");
            String username = in.nextLine();

            Thread citac = new Thread(new ReadMsg(socket));
            Thread pisar = new Thread(new WriteMsg(username, socket));

            citac.start();
            pisar.start();

            citac.join();
            pisar.join();


        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
