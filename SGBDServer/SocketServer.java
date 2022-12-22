package socket;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.io.*;
public class SocketServer {
    
    private static ServerSocket server;
    private static int port = 9876;
    public Socket socket;
    ObjectOutputStream oos;
    ObjectInputStream ois;
    public SocketServer() throws IOException, ClassNotFoundException {
        server = new ServerSocket(port);
          //output ahhafahana manoratra ao @ le socket 
    }

    

    public void setServer() throws IOException, ClassNotFoundException{
        server = new ServerSocket(port);
        
    }

    public void connet() throws IOException, ClassNotFoundException{
        socket = server.accept();
        //System.out.println("ooooooooooooooossssssssssssssssSERVER");
        oos = new ObjectOutputStream(socket.getOutputStream());
        //System.out.println("OooooooooooooiiiiiiiiiisssssssssssssssssssSERVER");
        ois = new ObjectInputStream(socket.getInputStream());
    }

    public void setMessage(Object o) throws IOException, ClassNotFoundException {
        //    if(socket.isClosed() == true){
        //        this.connet();
        //     }
            
            System.out.print("serveur: " + "Voaray chefooo");
            oos.writeObject(o);
           
            
    }


    public  Object getMessage()  throws IOException, ClassNotFoundException{
            // if(socket.isClosed() == true){
              //this.connet();
            // }
           
            
            Object message = ois.readObject();
            System.out.println("client: " + message);
            
        return message;
    }



    public static ServerSocket getServer() {
        return server;
    }



    public static void setServer(ServerSocket server) {
        SocketServer.server = server;
    }



    public static int getPort() {
        return port;
    }



    public static void setPort(int port) {
        SocketServer.port = port;
    }



    public Socket getSocket() {
        return socket;
    }



    public void setSocket(Socket socket) {
        this.socket = socket;
    }



    public ObjectOutputStream getOos() {
        return oos;
    }



    public void setOos(ObjectOutputStream oos) {
        this.oos = oos;
    }



    public ObjectInputStream getOis() {
        return ois;
    }



    public void setOis(ObjectInputStream ois) {
        this.ois = ois;
    }
    

 
}
