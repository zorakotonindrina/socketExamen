package frame;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import ope.*;
import socket.SocketClient;
public class Login extends JFrame{
    String host;
    int port;

    public Login() throws Exception{
        JPanel jp=new JPanel();
        JLabel hoslbl=new JLabel("Host");
        JTextField hos=new JTextField();
        JLabel porlbl=new JLabel("Port");
        JTextField por=new JTextField();
        
        hoslbl.setBounds(10, 100, 100, 50);
        hos.setBounds(100, 100, 250, 50);
        
        porlbl.setBounds(10, 200, 100, 50);
        por.setBounds(100, 200, 250, 50);
        jp.setLayout(null);
        jp.add(hoslbl);
        jp.add(hos);
        jp.add(porlbl);
        jp.add(por);
        jp.setBackground(Color.gray);
        //this.setLayout(null);
        JButton boot=new JButton("Se Connecter");
        boot.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                String hostt=hos.getText();
                int portt=Integer.valueOf(por.getText());
                try {
                    SocketClient client=new SocketClient(hostt,portt) ;
                    Fenetre f=new Fenetre(client);
                    //System.out.println("bouton ao");
                    
                } catch (Exception ex) {
                    ex.printStackTrace();
                    // TODO: handle exception
                }
                
                
            }
        });
        JPanel connect=new JPanel();
        connect.add(boot);
        connect.setBounds(200, 300, 150, 50);
        jp.setBounds(200, 50, 400, 400);
        jp.add(connect);
        this.add(jp);
        this.setSize(500,500);
        this.setVisible(true);


    }
    
}
