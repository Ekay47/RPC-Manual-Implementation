package socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class TestClient {
    private static final Logger log = LoggerFactory.getLogger(TestClient.class);

    public Object send(Message msg, String host, int port){
        // create socket and choose the host and port
        try (Socket socket = new Socket(host, port)){
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            // send message
            oos.writeObject(msg);
            oos.flush();
            // through the input stream to get response
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            return ois.readObject();
        }catch (IOException | ClassNotFoundException e){
            log.error(e.getMessage());
        }
        return null;
    }

    public static void main(String[] args) {
        TestClient testClient = new TestClient();
        Message msg = (Message) testClient.send(new Message("It's a new message."), "localhost", 12345);
        System.out.println("msg = " + msg.getMessage());
    }
}
