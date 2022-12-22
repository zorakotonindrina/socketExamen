package affiche;
import frame.*;
import ope.*;
import java.net.InetAddress;
public class Main {
    public static void main (String[] args) throws Exception
    {
        Login lg=new Login();
        // System.out.println(InetAddress.getLocalHost().getHostName());
        // System.out.println(9876);
        lg.setVisible(true);
    }
}
