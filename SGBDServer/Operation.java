package ope;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.*;
import java.io.*;
import java.awt.List;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
public class Operation{
    String database_Name ;
    public String getDatabase_Name() {
        return database_Name;
    }

    public void setDatabase_Name(String database_Name) {
        this.database_Name = database_Name;
    }
    public Object Execute(String requette)throws Exception
    {
        try {

            String[] mot=requette.split(" ");
            String data="";
            Object valiny=null;
            String[][] resp=null;
            String[] resp2=null; 
            System.out.println("Significatif req " + getSignificatif(requette));
            if(levelBase(requette) == true){
                String nom_base=mot[getNameBase(requette)];
                if(getSignificatif(requette) == -1){
                    create_database(nom_base);
                    valiny= "Database "+ nom_base +" created";
                    return valiny;
                }
                else if(getSignificatif(requette) == -2){
                    use_database(nom_base);
                    valiny= "Database "+ nom_base +" used";
                    return valiny;
                }
                else if(getSignificatif(requette) == -3){
                    resp2=showdatabase();
                    valiny= resp2;
                    mprint(resp2);
                    return resp2;
                }
            }
            else{
                String nom_table=mot[getNameTab(requette)];
                System.out.println(nom_table);
                if(getSignificatif(requette) == -1){
                   // System.out.println("CReation tablec");
                    String[] values=getValues(requette); 
                    create_table(nom_table, values);
                    valiny= "Table "+ nom_table +" created";
                    return valiny;
                }else{
                    nom_table=mot[getNameTab(requette)];
                    String primarykey= mot[getCondition(requette)];
                    String condition=mot[getValor(requette)];
                    int atrr=getAttribu(requette,nom_table);
                    String attribu= mot[atrr];
                    String setAtr=mot[getSetAttribu(requette)];
                    System.out.println("----------------------------------------------");
                   
                    if(getSignificatif(requette) == 1){
                        String[] values=getValues(requette); 
                        insert(nom_table, values);
                        valiny= "OK Insert "+ nom_table;
                        return valiny;
                    }
                    else if(getSignificatif(requette) == 2){
                        System.out.println(requette);
                        if(searchEtoile(requette) == true){
                            System.out.println(nom_table);
                            resp=select_Etoile(nom_table);
                            valiny=getVector(resp);
                            print(resp);
                            return resp;
                        }
                        else if(BeCondition(requette)==true){
                            System.out.println(nom_table);
                            String[][] valcond=getvalcond(requette);
                            resp=select(nom_table, valcond[0], valcond[1]);
                            valiny=getVector(resp);
                            print(resp);
                            return resp;
                        }
                        else if(atrr != 1){
                            // System.out.println("condi ray ihany");
                            // System.out.println(primarykey);
                            // System.out.println(condition);
                            resp=select(nom_table, primarykey, condition);
                            valiny=getVector(resp);
                            print(resp);
                            return resp;
                            
                        }else{
                            resp2=select(attribu,nom_table, primarykey,condition);
                            valiny=getVector(resp2);
                            mprint(resp2);
                            return resp2;
                        }
                        
                    }
                    else if(getSignificatif(requette) == 3){
                        System.out.println(requette);
                        delete(nom_table, primarykey, condition);
                        valiny= "Table "+ nom_table +" Deleted";
                        return valiny;
                    }
                    else if(getSignificatif(requette) == -3){
                        System.out.println(requette);
                        resp2=showTable(this.getDatabase_Name());
                        valiny= resp2;
                        mprint(resp2);
                        return resp2;
                    }
                    else if(getSignificatif(requette) == 4){
                        System.out.println(requette);
                        update(nom_table, attribu, setAtr, primarykey, condition);
                        valiny= "Table "+ nom_table +" modified";
                        return valiny;
            
                    }
                    else if(getSignificatif(requette) == 5){
                        System.out.println(requette);
                       resp= describe(nom_table);
                       //valiny=getVector(resp);
                       print(resp);
                       return resp2;
                        
            
                    }
                    else if(getSignificatif(requette) == 6){
                        System.out.println(requette);
                        String nomTable2= mot[getOtherNameTab(requette)];
                        System.out.println("le table ray dia " + nomTable2);
                        resp2 = Difference(attribu, nom_table, nomTable2);
                        valiny=getVector(resp2);
                        mprint(resp2);
                        return resp2;
            
                    }
                    else if(getSignificatif(requette) == 7){
                        System.out.println(requette);
                        String nomTable2= mot[getOtherNameTab(requette)];
                        System.out.println("le table ray dia " + nomTable2);
                        resp = produit(nom_table, nomTable2);
                        valiny=getVector(resp);
                        print(resp);
                        return resp;
            
                    }
                    else if(getSignificatif(requette) == 8){
                        System.out.println(requette);
                       
                        String nomTable2= mot[getOtherNameTab(requette)];
                        int atr2=getAttribu(requette, nomTable2);
                        String attribu2=mot[atr2];
                        System.out.println(attribu2);
                        System.out.println(attribu);
                        System.out.println("le table ray dia " + nomTable2);
                        resp2 = division(nom_table, nomTable2,attribu);
                        valiny=getVector(resp2);
                        mprint(resp2);
                        return resp2;
            
                    }
                }
            }
            return "Echec";
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            // TODO: handle exception
        }
        return "Echec";
    }



    public Vector getVector(String[][] tab){
        Vector v=new Vector<>();
        v.add(tab);
        return v;

    }

    public Vector getVector(String[] tab){
        Vector v=new Vector<>();
        v.add(tab);
        return v;

    }



    // public String[] show_database{

    // }

    public String[] showdatabase(){
        try {
            File fich=new File("Database");
            File[] table=fich.listFiles();
            String[] rep=new String[table.length];
            for (int i = 0; i < rep.length; i++) {
                    rep[i]=table[i].getName();             
            }
            //mprint(rep);
            return rep;
        } catch (Exception e) {
            // TODO: handle exception
        }
        return null;
    }


    public String[] showTable(String nom_base){
        try {
            File fich=new File("Database/"+nom_base);
            File[] table=fich.listFiles();
            String[] rep=new String[table.length];
            for (int i = 0; i < rep.length; i++) {
                rep[i]=table[i].getName();
                rep[i]=rep[i].substring(0, rep[i].length()-4);
            }
            //mprint(rep);
            return rep;
        } catch (Exception e) {
            // TODO: handle exception
        }
        return null;
    }



    public void create_database(String nom_base) throws Exception
    {
        try {
            File fichier=new File("Database/"+ nom_base);
            boolean b=fichier.mkdir();
            if( b == true){
                System.out.println("database " + nom_base + " dia voaforona soa aman tsara");
            }
            if( b == false){
              throw new Exception("database " + nom_base + " dia efa misy");
            }
        } catch (Exception e) {
            //TODO: handle exception
            e.printStackTrace();
        }
    }

   

    public void use_database(String nom_base){
        this.database_Name = nom_base;
        this.setDatabase_Name(nom_base);
        System.out.println("Tafiditra ato anaty base " + nom_base);
    }


   public void create_table(String nom_table,String[] attribu) throws Exception
   {
        File fichier=new File("Database/"+this.database_Name+"/"+nom_table + ".txt");
        //fichier.createNewFile();
        FileWriter sorato;
        FileReader vakio;
        try{
            // if(b == true){
                sorato =new FileWriter(fichier, true);
                vakio = new FileReader("Database/"+ this.database_Name+"/"+nom_table + ".txt");
                BufferedReader mdonnee = new BufferedReader(vakio);
                BufferedWriter donnee = new BufferedWriter(sorato);
                String anatiny = mdonnee.readLine();
                if(anatiny!=null)
                {
                    donnee.write("\n");

                }
                for(int i=0; i< attribu.length ;i++){
                    donnee.write(attribu[i] + ",");   
                }
                donnee.close();
                System.out.println("table voaforona soa aman tsara");
            // }
            // if(b == false){
            //     throw new Exception("Table already exist");
            // }
            
        } catch (IOException e) {
            //e.printStackTrace();
        }
   }

   public String[][] describe(String nom_table){
       String[][] strtab= select_Etoile(nom_table);
        String[][] val=new String[strtab[0].length][2];
       for (int i = 0; i < strtab[0].length; i++) {

           val[i][0]=strtab[0][i];
           val[i][1]="String";
       } 
       return val;
   }




   public void re_manoratra(String nom_table,String primarykey,String condition, String[] modif){
        try{
                int numline=0;
                String[][] strtab=select_Etoile(nom_table);
                int ind=getIndiceAtr(primarykey, nom_table);
                for (int i = 0; i < strtab.length; i++) {
                    if(strtab[i][ind].compareToIgnoreCase(condition) == 0){
                        numline = i;
                    }
                }
                Path path= Paths.get("Database/"+ this.database_Name+"/"+nom_table + ".txt");
                java.util.List<String> lines= Files.readAllLines(path);
                String ins="";
                for(int i=0; i<modif.length; i++){
                    if(i!=modif.length){
                        modif[i]=modif[i]+",";
                    }
                    ins=ins.concat(modif[i]);
                
                }
                lines.add(numline, ins);
                Files.write(path, lines);
        } catch (IOException ioe){

        }
    }

    public void update(String nom_table, String attribu,String valeur,String primaryKey,String condition)throws Exception
    {
        String[][] data=select(nom_table, primaryKey, condition);
        System.out.println(data.length);
        int ind=getIndiceAtr(attribu, nom_table);
        data[0][ind] = valeur;
        delete(nom_table,primaryKey,condition);
        insert(nom_table, data[0]);
        //re_manoratra(nom_table, primaryKey, condition, data[0]);
        System.out.println("Voaova soa aman tsara");

    } 



   public void delete(String nom_table, String primarykey, String condition){
        try{
            int numline=0;
            String[][] strtab=select_Etoile(nom_table);
            int ind=getIndiceAtr(primarykey, nom_table);
            for (int i = 0; i < strtab.length; i++) {
                if(strtab[i][ind].compareToIgnoreCase(condition) == 0){
                    numline = i;
                }
            }
            BufferedReader rea= new BufferedReader(new InputStreamReader(new FileInputStream(nom_table + ".txt")));
            StringBuffer strbf= new StringBuffer();
            String lg;
            int nbl= 0;
            while((lg=rea.readLine()) != null)
            {
                if(nbl != numline){
                    strbf.append(lg + "\n");
                }
                nbl ++;
            }
            rea.close();
            BufferedWriter bfw= new BufferedWriter(new FileWriter("Database/"+ this.database_Name+"/"+nom_table + ".txt"));
            bfw.write(strbf.toString());
            bfw.close();
            System.out.println("Voafafa soa aman tsara");
        } catch(Exception e) {
            e.printStackTrace();
        }
    }



   public void insert(String nom_table, String[] values){
    File fichier=new File("Database/"+ this.database_Name+"/"+nom_table + ".txt");
    FileWriter sorato;
    FileReader vakio;
    try{
        sorato =new FileWriter(fichier, true);
        vakio = new FileReader("Database/"+ this.database_Name+"/"+nom_table + ".txt");
        BufferedReader mdonnee = new BufferedReader(vakio);
        BufferedWriter donnee = new BufferedWriter(sorato);
        String anatiny = mdonnee.readLine();
        if(anatiny!=null)
        {
            donnee.write("\n");

        }
        for(int i=0; i< values.length ;i++){
            donnee.write(values[i] + ",");   
        }
        donnee.close();
        System.out.println("Voapiditra soa aman tsara");
    } catch (IOException e) {
        e.printStackTrace();
    }

   }


   public String[][] select_Etoile(String nom_table){
            int lign=count_liste(nom_table);
            int col =count_Field(nom_table);
            String[][] rep= new String[lign][col];
            File fesh= new File("Database/"+ this.database_Name+"/"+nom_table + ".txt");
            try{
                FileReader r= new FileReader(fesh);
                BufferedReader br = new BufferedReader(r);
                String f=br.readLine();
                int k = 0;
                while( f!= null){
                rep[k]= f.split(",");
                f = br.readLine();
                k ++;
                } 
            }
            catch (Exception e){
                e.printStackTrace();
            }
        return rep;
   }

   public String[][] select(String nom_table,String attribu, String condition) throws Exception
    {
        int col =count_Field(nom_table);
        String[][] strtab=select_Etoile(nom_table);
        int indice_atr=getIndiceAtr(attribu, nom_table);
        int k=0;
        for (int i = 0; i < strtab.length; i++) {
            if(strtab[i][indice_atr].compareToIgnoreCase(condition) == 0){
                k++;
            }
        }
        int h=0;
        String[][] result=new String[k][col];
        for (int j = 0; j < strtab.length; j++) {
            if(strtab[j][indice_atr].compareToIgnoreCase(condition) == 0){
                result[h] = strtab[j];
                h++;
            }
        }
        return result;
    } 


    public String[][] select(String nom_table,String[] attribu, String[] condition) throws Exception
    {
        int col =count_Field(nom_table);
        String[][] strtab=select_Etoile(nom_table);
        int[] ind=new int[attribu.length];
        for (int l = 0; l < ind.length; l++) {
            ind[l]=getIndiceAtr(attribu[l], nom_table);
        }
        int k=0;
        boolean f=false;
        for (int i = 0; i < strtab.length; i++) {
            for (int j = 0; j < ind.length; j++) {
                System.out.println( strtab[i][ind[j]]+" = "+ condition[j]);
                if(strtab[i][ind[j]].compareToIgnoreCase(condition[j]) == 0){
                   f=true; 
                }
            }
            if(f == true){
                k ++;
                f = false;
            }
        }
        int y=0;
        String[][] result=new String[k][col];
        for (int h = 0; h < strtab.length; h++) {
            for (int g = 0; g < ind.length; g++) {
                if(strtab[h][ind[g]].compareToIgnoreCase(condition[g]) == 0){
                   f=true; 
                }
            }
            if(f == true){
               result[y]=strtab[h];
               y++;
            }
        }
        return result;
    } 



    public String[] select(String select ,String nom_table, String attribu,String condition) throws Exception
    {
        int aseo=getIndiceAtr(select, nom_table);
        String[][] res=select(nom_table, attribu, condition);
        String[] result= new String[res.length];
        for (int i = 0; i < res.length; i++) {
            result[i] = res[i][aseo];
        }
        return result;
    } 

    public String[] project(String select ,String nom_table) throws Exception
    {
        int aseo=getIndiceAtr(select, nom_table);
        String[][] res=select_Etoile(nom_table);
        String[] result= new String[res.length];
        for (int i = 0; i < res.length; i++) {
            result[i] = res[i][aseo];
        }
        return result;
    } 

    public String[] project(String select ,String[][] res) throws Exception
    {
        int aseo=getIndiceAtr(select, res);
        String[] result= new String[res.length];
        for (int i = 0; i < res.length; i++) {
            result[i] = res[i][aseo];
        }
        return result;
    } 
    public String[][] project(String[] select ,String nom_table) throws Exception
    {
        int[] aseo=new int[select.length];
        for (int i = 0; i < aseo.length; i++) {
            aseo[i]=getIndiceAtr(select[i], nom_table);
        }
        String[][] res=select_Etoile(nom_table);
        String[][] result= new String[res.length][select.length];
        for (int i = 0; i < res.length; i++) {
            for (int j = 0; j < aseo.length; j++) {
                result[i][j] = res[i][aseo[j]];
            }
           
        }
        return result;
    } 



    public int getIndiceAtr(String attribu, String nom_table){
        int indice_atr=0;
        String[][] strtab=select_Etoile(nom_table);
        for (int j = 0; j < strtab[0].length ; j++) {
            if(strtab[0][j].compareTo(attribu) == 0){
                indice_atr = j;
            } 
        }
        return indice_atr;
    }

    public int getIndiceAtr(String attribu, String[][] strtab){
        int indice_atr=0;
        for (int j = 0; j < strtab[0].length ; j++) {
            if(strtab[0][j].compareTo(attribu) == 0){
                indice_atr = j;
            } 
        }
        return indice_atr;
    }


   public int count_Field(String nom_table){
    int k=0;
        try{
            File fesh= new File("Database/"+ this.database_Name+"/"+nom_table + ".txt");
            FileReader r= new FileReader(fesh);
            BufferedReader br = new BufferedReader(r);
            String f=br.readLine();
            String[] rep;
            rep= f.split(",");
            k = rep.length;
        }
        catch (Exception e){
            e.printStackTrace();
        }
    return k;
   }
   
   public int count_liste(String nom_table){
        int k = 0;
        try {
            File fi = new File("Database/"+ this.database_Name+"/"+nom_table + ".txt");
            FileReader r= new FileReader(fi);
            BufferedReader br=new BufferedReader(r);
            String rdl= br.readLine();
            while(rdl != null){
                rdl = br.readLine();
                k++;
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return k;
    }


    public int getValor(String req){
        int indice=0;
        int condi=getCondition(req);
        indice=condi +1;
        return indice;
    }
    public int getCondition(String req){
        String[] mot=req.split(" ");
        int indice=0;
        for(int i = 0; i < mot.length; i++) {
            if(mot[i].compareTo("where")== 0){
                indice=i + 1;
            }
        }
        return indice;
    }

    public int getSetAttribu(String req){
        String[] mot=req.split(" ");
        int indice=0;
        for(int i = 0; i < mot.length; i++) {
            if(mot[i].compareTo("=")== 0){
                indice=i + 1;
            }
        }
        return indice;
    }

    public int getAttribu(String req,String nom_table){
        String[] mot=req.split(" ");
        int ind=0;
        String[][] strtab=select_Etoile(nom_table);
        //print(strtab);
        //System.out.println(strtab[0][0]);
        for (int i = 0; i < mot.length; i++) {
            for (int j = 0; j < count_Field(nom_table); j++) {
                //System.out.println(mot[i] + " = " + strtab[0][0]);
                if((mot[i].compareToIgnoreCase(strtab[0][j])==0 )&&(i != getCondition(req))){
                    //System.out.println(" attribu trouve ");
                    ind = i;
                }
            }
        }
        return ind;
    }


    
    

    public int getNameTab(String req){
        String[] mot=req.split(" ");
        int indice=0;
        for(int i = 0; i < mot.length; i++) {
            if((mot[i].compareToIgnoreCase("table")== 0 )||(mot[i].compareToIgnoreCase("into")== 0) ||(mot[i].compareToIgnoreCase("from")== 0)) {
                indice=i + 1;
            }
        }
        return indice;
    }
    public int getOtherNameTab(String req){
        String[] mot=req.split(" ");
        int indice=0;
        for(int i = 0; i < mot.length; i++) {
            if(mot[i].compareToIgnoreCase("table")== 0 ) {
                if(i + 1 != getNameTab(req)){
                    indice=i + 1;
                } 
            }
        }
        return indice;
    }


    public int getNameBase(String req){
        String[] mot=req.split(" ");
        int indice=0;
        for(int i = 0; i < mot.length; i++) {
            if(mot[i].compareToIgnoreCase("database")== 0 ) {
                if(i+1 < mot.length){
                    indice=i + 1;
                }
                
            }
        }
        return indice;

    }
   

    public boolean levelBase(String req){
        String[] mot=req.split(" ");
        for(int i = 0; i < mot.length; i++) {
            if(mot[i].compareToIgnoreCase("database") == 0 ) {
               return true;
            }
        }
        return false;
    }

    public int getSignificatif(String req){
        String[] mot=req.split(" ");
        int val=0;
        for (int i = 0; i < mot.length; i++) {
            if(mot[i].compareToIgnoreCase("Create")==0){
                        return   val = -1;
            }
            if(mot[i].compareToIgnoreCase("Use")==0){
                return   val = -2;
            }
            if(mot[i].compareToIgnoreCase("Show")==0){
                return   val = -3;
            }
            if(mot[i].compareToIgnoreCase("Create")==0){
              return   val=0;
            }
             if(mot[i].compareToIgnoreCase("Insert")==0){
               return val=1;
             }
            if(mot[i].compareToIgnoreCase("select")==0){
              return val=2;
            }
            if(mot[i].compareToIgnoreCase("delete")==0){
                return val=3;
             }
             if(mot[i].compareToIgnoreCase("update")==0){
                return val=4;
             }
             if(mot[i].compareToIgnoreCase("describe")==0){
                return val=5;
             }
             if(mot[i].compareToIgnoreCase("Difference")==0){
                return val=6;
             }
             if(mot[i].compareToIgnoreCase("Product")==0){
                return val=7;
             }
             if(mot[i].compareToIgnoreCase("Divise")==0){
                return val=8;
             }

        }
        return val;
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

    public boolean searchEtoile(String req){
        String[] mot=req.split(" ");
        for (int i = 0; i < mot.length; i++) {
            if(mot[i].compareTo("*") == 0){
                return true;
            }
        }
        return false;
    }

    public String[] getValues(String req){
        String[] mot=req.split(" ");
        int parenth=0;
        for (int i = 0; i < mot.length; i++) {
            if(mot[i].compareToIgnoreCase("(") == 0){
                parenth=i;
            }
        }
        String values=mot[parenth + 1];
        String[] value=values.split(",");
        return value;
    }

    public String[][] getvalcond(String req){
        String[] mot=req.split(" ");
        int parenth=0;
        for (int i = 0; i < mot.length; i++) {
            if(mot[i].compareToIgnoreCase("Where") == 0){
                parenth=i;
            }
        }
        String values=mot[parenth + 1];
        String[] value=values.split("and");
        String[][] rep=new String[2][value.length]; 
        for (int k = 0; k < value.length; k++) {
            String[] test=value[k].split("=");
            rep[0][k]=test[0];
            rep[1][k]=test[1];
        }
        return rep;
    }

    public boolean BeCondition(String req){
       String[] mot=req.split("=");
        if(mot.length >= 3){
            return true;
        }
        return false;
    }




    public String[] Difference(String attribu,String premierT,String secondT) throws Exception
    {
       // System.out.println("oke be diff");
        Vector result= new Vector();
        boolean misy=false;
       int inp=getIndiceAtr(attribu, premierT);
       int ins=getIndiceAtr(attribu, secondT);
       System.out.println("ao amle volouh"+ premierT + inp);
       System.out.println("ao amle faaroa" + secondT + ins);
       String[][] premier=select_Etoile(premierT);
       print(premier);
       String[][] second= select_Etoile(secondT);
       print(second);
        for (int i = 1; i < premier.length; i++) {
            //System.out.println("ok");
            for (int j = 1; j < second.length; j++) {
                System.out.println(premier[i][inp] +" = "+ second[j][ins]);
                if(premier[i][inp].compareToIgnoreCase(second[j][ins]) == 0){
                    misy=true;
                }

            }
            if(misy == false){
                result.add(premier[i][inp]);
            }
            if(misy == true){
                misy = false;
            }
            
        }
        for (int i = 1; i < second.length; i++) {
           // System.out.println("ok");
            for (int j = 1; j < premier.length; j++) {
                System.out.println(premier[i][inp] +" = "+ second[j][ins]);
                if(second[i][ins].compareToIgnoreCase(premier[j][inp]) == 0){
                    misy=true;
                }

            }

            if(misy == false ){
                result.add(second[i][ins]);
            }
            if(misy == true){
                misy=false;
            }
        }
        String[] val=Change(result);
        return val;
    }

    public String[][] Diff(String[][] premier,String[][] second) throws Exception
    {
        //System.out.println("oke be diff");
        int h=manisa(premier, second);
        String[][] diff=new String[h][premier[0].length];
        boolean misy=false;
        int g=0;
        for (int i = 1; i < premier.length; i++) {
           // System.out.println("ok");
            for (int j = 1; j < second.length; j++) {
                for (int j2 = 0; j2 < premier[i].length; j2++) {
                    for (int k = 0; k < second[j].length; k++) {
                        if(premier[i][j2].compareToIgnoreCase(second[j][k]) == 0){
                           // System.out.println("difffr ray");
                            misy=true;
                        } 
                    }
                    
                }
            }
            if(misy == false){
                System.out.println("difffr okkkkkkkk");
                diff[g]=premier[i];
                g++;
            }
            if(misy == true){
                misy = false;
            }
            
        }
        for (int i = 1; i < second.length; i++) {
            //System.out.println("ok");
            for (int j = 1; j < premier.length; j++) {
                for (int j2 = 0; j2 < second[i].length; j2++) {
                    for (int k = 0; k < premier[j].length; k++) {
                        if(second[i][j2].compareToIgnoreCase(premier[j][k]) == 0){
                            System.out.println("difffr ray");
                            misy=true;
                        } 
                    }
                }
            }
            if(misy == false ){
                diff[g]=second[i];
                g++;
            }
            if(misy == true){
                misy=false;
            }
        }
        return diff;
    }


    public String[] Diff(String[] premier,String[] second) throws Exception
    {
       // System.out.println("oke be diff");
        int h=manisa(premier, second);
        String[] diff=new String[h];
        boolean misy=false;
        int g=0;
        for (int i = 1; i < premier.length; i++) {
            //System.out.println("ok");
            for (int j = 1; j < second.length; j++) {
                    if(premier[i].compareToIgnoreCase(second[j]) == 0){
                        misy=true;
                    }
                }
            
            if(misy == false){
                diff[g]=premier[i];
                g++;
            }
            if(misy == true){
                misy = false;
            }
        }    
        
        for (int i = 1; i < second.length; i++) {
            //System.out.println("ok");
            for (int j = 1; j < premier.length; j++) {
                
                    if(second[i].compareToIgnoreCase(premier[j]) == 0){
                        misy=true;
                    }
            }
            if(misy == false ){
                diff[g]=second[i];
                g++;
            }
            if(misy == true){
                misy=false;
            }
        }
        return diff;
    }


    public int manisa(String[] premier,String[] second) throws Exception
    {

        //System.out.println("oke  masisa kelybe diff ");
        int k=0;
        boolean misy=false;
        for (int i = 1; i < premier.length; i++) {
            //System.out.println("ok");
            for (int j = 1; j < second.length; j++) {
                    if(premier[i].compareToIgnoreCase(second[j]) == 0){
                        misy=true;
                   
                }
            }
            if(misy == false){
                k++;
            }
            if(misy == true){
                misy = false;
            }
            
        }
        for (int i = 1; i < second.length; i++) {
            //System.out.println("ok");
            for (int j = 1; j < premier.length; j++) {
                    if(second[i].compareToIgnoreCase(premier[j]) == 0){
                        misy=true;
                    }
            }
            if(misy == false ){
                k ++;
            }
            if(misy == true){
                misy=false;
            }
        }
        return k;


    }


    public int manisa(String[][] premier,String[][] second) throws Exception
    {

        //System.out.println("oke be diff");
        int k=0;
        boolean misy=false;
        for (int i = 1; i < premier.length; i++) {
            for (int j = 1; j < second.length; j++) {
                for (int j2 = 0; j2 < premier[i].length; j2++) {
                    for (int t = 0; t < second[j].length; t++) {
                        if(premier[i][j2].compareToIgnoreCase(second[j][t]) == 0){
                            System.out.println("difffr ray");
                            misy=true;
                        } 
                    }
                    
                }
            }
            if(misy == false){
                k++;
            }
            if(misy == true){
                misy = false;
            }
            
        }
        for (int i = 1; i < second.length; i++) {
           // System.out.println("ok"); 
           for (int j = 1; j < premier.length; j++) {
            for (int j2 = 0; j2 < second[i].length; j2++) {
                for (int l = 0; l < premier[j].length; l++) {
                    if(second[i][j2].compareToIgnoreCase(premier[j][l]) == 0){
                        System.out.println("difffr ray");
                        misy=true;
                    } 
                }
            }
            }
            if(misy == false ){
                k ++;
            }
            if(misy == true){
                misy=false;
            }
        }
        return k;


    }


    public String[] Change(Vector v){
        String[] valn=new String[v.size()];
        for (int h = 0; h < valn.length; h++) {
            valn[h]=String.valueOf( v.elementAt(h));
        }
        return valn;
    }

    public String[][] produit(String[][] premier, String[][] second){
        String[][] rep=new String[premier.length*second.length][premier[0].length + second[0].length];
        int h=0;
        for (int i = 0; i < premier.length; i++) {
            for (int j = 0; j < second.length; j++) {
                int j2;
                for (j2 = 0; j2 < premier[i].length; j2++) {
                    rep[h][j2]=premier[i][j2];
                }
                j2=premier[i].length ;
                for (int j3 = 0; j3 < second[j].length; j3++) {
                    rep[h][j2]=second[j][j3];
                    j2++;
                }
                h++;  
            }
            
        }
        //print(rep);
        return rep;
    }
    public String[][] produit(String[] premier, String[] second){
        String[][] rep=new String[premier.length*second.length][2];
        int h=0;
        for (int i = 0; i < premier.length; i++) {
            for (int j = 0; j < second.length; j++) {
                rep[h][0]=premier[i];
                rep[h][1]=second[j];
                h++;  
            }
            
        }
       // print(rep);
        return rep;
    }


    public String[][] produit(String premierT, String secondT){
        String[][] premier=select_Etoile(premierT);
        String[][] second=select_Etoile(secondT);
        // print(premier);
        // print(second);  
        String[][] rep=produit(premier, second);
        return rep;
    }



    public String[][] jointure(String premierT, String secondT)
    {
        String attibu ="";
        String[][] premier=select_Etoile(premierT);
        String[][] second=select_Etoile(secondT);
        for (int i = 0; i < premier[0].length; i++) {
            for (int j = 0; j < second[0].length; j++) {
                if(premier[0][i].compareToIgnoreCase(second[0][j])== 0){
                    attibu = premier[0][i];
                }
            }
        }
        int indp=getIndiceAtr(attibu, premierT);
        int inds=getIndiceAtr(attibu, secondT);
       // System.out.println(indp);
       // System.out.println(inds);


        String[][] p=produit(premierT, secondT);
        //System.out.println(p.length);
        
        int k=0;
        for (int i = 0; i < p.length; i++) {
           // System.out.println(p[i][indp]+" == "+p[i][premier[0].length + inds]);
            if(p[i][indp].compareToIgnoreCase(p[i][premier[0].length + inds]) == 0){
                //System.out.println("okkk");
               k++;
            }
        }
        String[][] reslut= new String[k][p[0].length];
        int h=0;
        for (int i = 0; i < p.length; i++) {
            if(p[i][indp].compareToIgnoreCase(p[i][premier[0].length + inds]) == 0){
                reslut[h]=p[i];
                h++;
            }
        }
       // print(reslut);
        return reslut;
    }








    public String[] division(String premierT, String secondT , String attribu)throws Exception
    {
        String[] res=null;
        String[] r1=project( "idEmployee",premierT);
        String[] r2= project("deptno",secondT);
        String[][] r3=produit(r1, r2);
        String[][] c=select_Etoile(premierT); 
       // print(c);  
        //System.out.println("--------------kkkkkkkkkkkkkkkkkkkkkkkk-------------------");
        //print(r3);
        String[][] r4=Diff(r3, c);
        //print(r4);
        String[] r5 =project("idEmployee", r4);
        //mprint(r5);
       // System.out.println("--------------yyyyyyyyyyyyyyyyyyy-----------------------");
        res=Diff(r5, r1);
        //String[] tenaizy=Diff(res, r1);
       //mprint(res);
        return res;
    }






    // public String[] division(String premierT, String secondT , String attribu)throws Exception
    // {
    //     String[] res=null;
    //     //String[] r1=project( attribu,premierT);
    //     //String[] r2= project(attribu,secondT);
    //    // String[] attr= new String[2];
    //     //attr[1]=attribu;
    //     String[] r1=project( "idEmployee",premierT);
    //     String[] r2= project("deptno",secondT);
    //     String[][] r3=produit(r1, r2);
    //     //print(r3);
    //     String[][] c=jointure(premierT, secondT);
    //    // System.out.println("-----------------4ryui-diffffffffffffffffffffffff-------------------");
    //    // print(c);
       
    //     String[][] r4=Diff(r3, c);
    //    // System.out.println("-----------------4ryui-diffffffffffffffffffffffff-------------------");
    //     //print(r3);
    //     //print(r4);
    //     String[] r5 =project("idEmployee", r4);
    //     //print(c);
    //    // print(r4);
    //    // mprint(r5);
    //     System.out.println("--------------yyyyyyyyyyyyyyyyyyy-----------------------");
    //    // mprint(r5);
    //     //mprint(r1);
    //     res=Diff(r5, r1);
    //     //ampifapidiviseo table dept sy table emp deptno
    //     //  System.out.println("-------------------------------------");
    //     String[] tenaizy=Diff(res, r1);
    //    // mprint(tenaizy);
    //     return tenaizy;
    // }



    // public String[][] division(String premierT, String secondT , String attribu){
    //     int indp=getIndiceAtr(attribu, premierT);
    //     int inds=getIndiceAtr(attribu, secondT);
    //     String[][] premier=select_Etoile(premierT);
    //     print(premier);
    //     String[][] second= select_Etoile(secondT);
    //     print(second);
    //     String[] test=project(attribu, secondT);
    //     return premier;

        

    // }



    
    
}






























