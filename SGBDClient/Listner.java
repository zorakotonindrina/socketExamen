package listner;
import javax.swing.*;
import frame.*;
import socket.*;
import java.awt.Color;
import java.awt.event.*;
import java.io.*;
import java.util.Date;
public class Listner implements MouseListener{
   
    SocketClient kill;
    String req;
    Fenetre fenetre;
    JScrollBar  js;
    public Listner(Fenetre f) {
        this.fenetre=f;
        try {
            kill = fenetre.getKill();
            kill.setSocket();
        } catch (Exception e) {
            // TODO: handle exception
        }
       
       
    } 
    public void mouseClicked(MouseEvent e){ 
        try {
            
               
                req = fenetre.getJtext().getText();
                //System.out.println("ok booot");
                System.out.println(req);
                kill.setFromServer(req);
                Object res=kill.getFromServer();
                fenetre.setRes(res);
                Response rep=new Response(res,fenetre.getMyrep().getLevely(),fenetre);
                //rep.setBounds(450, 10, 600, 600);
                rep.setBackground(Color.WHITE);
                fenetre.getMyrep().setLevely(rep.getLevely());
                System.out.println("levell "+ rep.getLevely());
                fenetre.setMyrep(rep);

                //fenetre.getPanel().add(rep);
                //fenetre.add(fenetre.getMyrep());
                //fenetre.add(rep);
                //fenetre.add(rep);
                //fenetre.add(fenetre.getPanel());
                fenetre.setVisible(true);

                // kill.getOis().close();
                // kill.getOos().close();
           
           
        } catch (Exception ex) {
            ex.printStackTrace();
            //TODO: handle exception
        }
        
    }
    public void mouseEntered(MouseEvent e){ }
    public void	mouseExited(MouseEvent e){ }
    public void	mousePressed(MouseEvent e){
        
    }
    public void	mouseReleased(MouseEvent e){ }
    public SocketClient getKill() {
        return kill;
    }
    public void setKill(SocketClient kill) {
        this.kill = kill;
    }
    public String getReq() {
        return req;
    }
    public void setReq(String req) {
        this.req = req;
    }
   


    

}
