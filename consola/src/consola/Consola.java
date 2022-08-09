package consola;
import consola.MyJDBC;
import java.util.Scanner;
import java.util.Vector;
import java.sql.ResultSet;
import java.sql.Statement;

public class Consola {

    public static void main(String[] args) {
        MyJDBC conector = new MyJDBC("tienda", "root", "lospollitosdicen");
        
        if(conector.ConnectionMyDB()){
            System.out.println("Conectado a DB");
        }else{
            System.out.println("No se pudo conectar a la DB");
        }
        
        Scanner sc = new Scanner(System.in);
        String [] splitted;
        String entrada;
        String query;
        Statement statement;
        
        do{
            entrada = sc.nextLine();
            /*MENU DE 4 OPCIONES
            1: crear::  1&id&nombre&telefono
            2: listar:: 2
            3: eliminar:: 3&id
            4: actualizar:: 4&id&nombre&telefono
            5: salir
            */
            splitted = entrada.split("&");
            
            if (splitted[0].equals("1")){
                //crear
                query = "INSERT INTO persona VALUES("
                        +splitted[1]+",\""+splitted[2]+"\",\""+splitted[3]+"\");";
                if(conector.CREATE(query)){
                    System.out.println("Se ha creado exitosamente");
                }else{
                    System.out.println("No se ha podido crear");
                }
            }else if(splitted[0].equals("2")){
                //listar
                query = "SELECT * FROM persona;";
                Vector<String[]> tabla = new Vector<String[]>();
                tabla = conector.READ(query);
                if(tabla.size()<1){
                    System.out.println("No se pudo traer datos");
                }else{
                    for(String [] fila: tabla){
                        String filaTotal = "";
                        for(String columna: fila){
                            filaTotal = filaTotal + " | " + columna;
                        }
                        System.out.println(filaTotal);
                    }
                }
            }else if(splitted[0].equals("3")){
                //eliminar
                query = "DELETE FROM persona WHERE id="+splitted[1]+";";
                if(conector.DELETE(query)){
                    System.out.println("Se ha eliminado");
                }else{
                    System.out.println("No se ha podido eliminar");
                }
            }else if(splitted[0].equals("4")){
                //actualizar
                query = "UPDATE persona SET nombre='"+splitted[2]+"', telefono='"
                        +splitted[3]+"' WHERE id="+splitted[1]+";";
                if(conector.UPDATE(query)){
                    System.out.println("Se ha actualizado");
                }else{
                    System.out.println("No se pudo actualizar");
                }
            }
        }while(!splitted[0].equals("5"));
    }
}
