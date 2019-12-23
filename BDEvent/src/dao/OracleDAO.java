package dao;
import java.sql.Connection;
import java.util.ArrayList;

public abstract class OracleDAO<T> {
  protected Connection connect = null;
  
  public static final String ORACLE_DB_PATH = "jdbc:oracle:thin:@localhost:1521:orcl";
   
  public OracleDAO(Connection conn){
    this.connect = conn;
  }
   

  public abstract int create(T obj);


  public abstract boolean delete(T obj);

  
  public abstract boolean update(int i, T obj);
  
  public abstract boolean update(T obj);


  public abstract T find(String id);
  
  public abstract ArrayList<T> findAll();
  
  
  public abstract T findById(int id);
}
