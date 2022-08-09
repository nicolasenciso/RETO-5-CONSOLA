package consola;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

public class MyJDBC {
    private String urlBase;
    private String portBase;
    private String dbNameBase;
    private String Url;
    private String userBase;
    private String passwordBase;
    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;
    
    
    public MyJDBC(String nombreDB, String userNameDB, String userPasswordDB){
        urlBase = "jdbc:mysql://localhost:";
        portBase = "3306";
        dbNameBase = "/" + nombreDB;
        Url = urlBase + portBase + dbNameBase;
        this.userBase = userNameDB;
        this.passwordBase = userPasswordDB;
    }
    
    public boolean ConnectionMyDB(){
        boolean salida = false;
        try{
            connection = DriverManager.getConnection(Url, userBase, passwordBase);
            statement = connection.createStatement();
            salida = true;
        }catch(Exception e){
            e.printStackTrace();
        }
        return salida;
    }
    
    
    public boolean CREATE(String myQuery){
        boolean salida = true;
        try{
            statement.executeUpdate(myQuery);
        }catch(Exception e){
            e.printStackTrace();
            salida = false;
        }
        return salida;
    }
    
    public Vector<String[]> READ(String MyQuery){
        Vector<String[]> listarFilas = new Vector<>();
        String [] data;
        try{
            resultSet = statement.executeQuery(MyQuery);
            while(resultSet.next()){
                data = new String[]{
                    String.valueOf(resultSet.getString("id")),
                    String.valueOf(resultSet.getString("nombre")),
                    String.valueOf(resultSet.getString("telefono"))
                };
                listarFilas.add(data);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return listarFilas;
    }
    
    public boolean UPDATE(String myQuery){
        return CREATE(myQuery);
    }
    
    public boolean DELETE(String myQuery){
        return CREATE(myQuery);
    }
}
