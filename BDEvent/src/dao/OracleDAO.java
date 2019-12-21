package dao;
import java.sql.Connection;

public abstract class OracleDAO<T> {
  protected Connection connect = null;
   
  public OracleDAO(Connection conn){
    this.connect = conn;
  }
   

  public abstract int create(T obj);


  public abstract boolean delete(T obj);

  
  public abstract boolean update(int i, T obj);
  
  public abstract boolean update(T obj);


  public abstract T find(String id);
  
  public abstract T findById(int id);
}
