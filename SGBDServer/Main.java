package affiche;
import ope.*;
import socket.EcouteClient;
import java.net.InetAddress;

public class Main {
    public static void main (String[] args) throws Exception
    {

        System.out.println(InetAddress.getLocalHost().getHostName());
        System.out.println(9876);
       EcouteClient ecoute=new EcouteClient();
       ecoute.start();

    // Operation op=new Operation();
    // //op.Execute("show database");
    // op.Execute("use database exemple");
    // //op.Execute("create table music ( artist,titre );");
    // op.Execute("create table test ( id,nom )");
    // op.select_Etoile("Emp");

    // op.print(op.select_Etoile("Emp"));
      
    }
}
