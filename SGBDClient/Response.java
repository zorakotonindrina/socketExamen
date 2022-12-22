package frame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import frame.Fenetre;
public class Response extends JPanel { 
    Object myobject;
    JTable jt;
    int levely;
    Fenetre f;

    public Response(Object obj,int lev,Fenetre f) {

        this.myobject = obj;
        this.levely=lev;
        this.f=f;
        //.out.println("efa le reponseeee ");
        //System.out.println(myobject);
        this.setLayout(null);
        String[][] resp=null;
        String [] resp1=null;
        String resp2=null;
        if(myobject instanceof String[][]){
            resp=(String[][])myobject;
            jt=new JTable(resp, resp[0]);
            jt.setBounds(10, 10 + levely, 400, 400);
            levely=400 + 10 + levely;
            print(resp);
            this.add(jt);
            f.getPanel().add(jt);
            f.setVisible(true);
        }

        if(myobject instanceof String[]){
            resp1=(String[])myobject;
            int xl=10;
            JLabel[] tabjl=new JLabel[resp1.length];
            for (int i = 0; i < resp1.length; i++) {
                tabjl[i]=new JLabel(resp1[i]);
                tabjl[i].setBounds(400, xl+ levely, 500, 50);
                this.add(tabjl[i]);
                f.getPanel().add(tabjl[i]);
                 xl=xl + 10;
            }
            levely=50 + 10 + levely;
            mprint(resp1);
            f.setVisible(true);
        }
        
        else if(myobject instanceof String){
            resp2=(String)myobject;
            JLabel jl=new JLabel(resp2);
            jl.setBounds(400, 10 + levely, 400, 100);
            jl.setSize(20,20);
            levely=100 + 10 + levely;
            System.out.println(myobject);
            this.add(jl);
            f.getPanel().add(jl);
            f.setVisible(true);
        }
        
    }

    public Object getMyobject() {
        return myobject;
    }

    public void setMyobject(Object myobject) {
        this.myobject = myobject;
    }

    public JTable getJt() {
        return jt;
    }

    public void setJt(JTable jt) {
        this.jt = jt;
    }

    public void print(String[][] tab){
        for (int i = 0; i < tab.length; i++) {
            for (int j = 0; j < tab[i].length; j++) {
                System.out.println(tab[i][j]);
            }
            System.out.println("----------------------------------------------");
        }
    }


    public void mprint(String[] tab){
        for (int i = 0; i < tab.length; i++) {
                System.out.println(tab[i]);
        }
    }

    public int getLevely() {
        return levely;
    }

    public void setLevely(int levely) {
        this.levely = levely;
    }
    
}
