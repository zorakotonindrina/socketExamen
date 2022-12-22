package socket;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.ClassNotFoundException;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.UnknownHostException;
import java.io.*;
public class SocketClient {
        String host ;
        int port;  
        Socket socket;
        ObjectOutputStream oos=null ;
        ObjectInputStream ois = null;

    public SocketClient(String hostt,int portt) {
            host = hostt;
            port=portt;  
    }

    public String message;

    
    public void setSocket() throws UnknownHostException, IOException, ClassNotFoundException, InterruptedException{
        socket = new Socket(this.host, this.port); 
        //System.out.println("ooooooooooooooossssssssssssssss");
        oos = new ObjectOutputStream(socket.getOutputStream());
        //System.out.println("Oooooooooooooiiiiiiiiiisssssssssssssssssss");
        ois = new ObjectInputStream(socket.getInputStream());
    }
    

    public Object  getFromServer()throws UnknownHostException, IOException, ClassNotFoundException, InterruptedException{
        Object resp = ois.readObject(); 
        //ois.close();
        return resp;
    } 

    public void setFromServer(Object o)throws UnknownHostException, IOException, ClassNotFoundException, InterruptedException{
        oos.writeObject(o);
        //oos.close();
           
    }



    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }


    public String getHost() {
        return host;
    }


    public void setHost(String host) {
        this.host = host;
    }


    public int getPort() {
        return port;
    }


    public void setPort(int port) {
        this.port = port;
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
