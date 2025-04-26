package socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;


public class TestServer {
    public static final Logger logger = LoggerFactory.getLogger(TestServer.class);

    public void start(int port){
        // create one socket and bind one port
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            Socket socket;
            // start to listen
            while((socket = serverSocket.accept())!= null){
                logger.info("Client Connected!");
                try (ObjectInputStream ois = new ObjectInputStream(socket.getInputStream())){
                    ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                    // read client message
                    Message message = (Message) ois.readObject();
                    logger.info("Server receives: " + message.getMessage());
                    message.setMessage("new content...");
                    // output response
                    oos.writeObject(message);
                    oos.flush();

                }catch (IOException | ClassNotFoundException e){
                    logger.error(e.getMessage());
                }
            }
        }catch (IOException e){
            logger.error(e.getMessage());
        }
    }

    public static void main(String[] args) {
        TestServer server = new TestServer();
        server.start(12345);
    }
}
