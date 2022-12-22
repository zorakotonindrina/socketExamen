package socket;
import java.lang.Thread;
import ope.*;
import socket.SocketServer;

public class EcouteClient  extends Thread{

    
    public EcouteClient() {
    }

    public void run() {
        try {
            SocketServer server=new SocketServer();
            System.out.println("Waiting for the client request");
            server.connet();
            Operation op=new Operation();
            while(true){
                        Object req=server.getMessage();
                        String requet=String.valueOf(req);
                        System.out.println(requet);
                        if(requet.equalsIgnoreCase("exit")) break;
                        Object valiny=op.Execute(requet);
                        server.setMessage(valiny);
            }
            server.getOis().close();
            server.getOis().close();

            // Operation op=new Operation();
            // op.setDatabase_Name("exemple");
            // op.Execute("select from dept where loc tana");
            

        } catch (Exception e) {
            e.printStackTrace();
            // TODO: handle exception
        }
    }
    
}
