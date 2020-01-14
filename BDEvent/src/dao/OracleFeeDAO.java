package dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


import buisnessLogic.Fee;
import buisnessLogic.User;

public class OracleFeeDAO extends OracleDAO<Fee> {
public OracleFeeDAO(Connection conn) {
  super(conn);
}

@Override
public int create(Fee obj) {
	int id = getLastId()+1;

	  String SQL_INSERT = "Insert into fee " + "Values (" + id +",'" + obj.getTitle_fee()+ "',"
			  +"'" + obj.getComment_fee() + "',"
					   + obj.getAmount_fee()+ "," + 0 + "," + obj.getId_user_fee()+ ")";
	  System.out.println(SQL_INSERT);
	  // auto close connection and preparedStatement
	  try {
		  
		  Connection conn = DriverManager.getConnection(ORACLE_DB_PATH, ORACLE_DB_USER, ORACLE_DB_PASSWORD);
		  Statement st = conn.createStatement();

	      st.executeUpdate(SQL_INSERT);
		  
		  conn.close();
		  return 1;

	  } catch (SQLException e) {
	      System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
	  } catch (Exception e) {
	      e.printStackTrace();
	  }
	return -1;
}

private int getLastId() {
	
	int id_fee=0;
	String SQL_SELECT = "Select MAX(ID_FEE)from fee";

	  // auto close connection and preparedStatement
	  try (Connection conn = DriverManager.getConnection(
	          ORACLE_DB_PATH, ORACLE_DB_USER, ORACLE_DB_PASSWORD);
	       PreparedStatement preparedStatement = conn.prepareStatement(SQL_SELECT)) {

	      ResultSet resultSet = preparedStatement.executeQuery();
	      while (resultSet.next()) {
	          id_fee = resultSet.getInt("MAX(ID_FEE)"); 
	      }
	      return id_fee;

	  } catch (SQLException e) {
	      System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
	  } catch (Exception e) {
	      e.printStackTrace();
	  }
	  return id_fee;
	
}


@Override
public boolean delete(Fee fee) {
	int id = fee.getId_fee();
	String SQL_DELETE = "DELETE from fee WHERE ID_FEE='"+id+"'";
	 try {
		  Connection conn = DriverManager.getConnection(ORACLE_DB_PATH, ORACLE_DB_USER, ORACLE_DB_PASSWORD);
		  
		  
		  PreparedStatement ps = conn.prepareStatement(SQL_DELETE);
		  // call executeUpdate to execute our sql update statement
		  ps.executeUpdate(); 
		  ps.close();
		  
		  return true;

	  } catch (SQLException e) {
	      System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
	  } catch (Exception e) {
	      e.printStackTrace();
	  }
	return false;
}


@Override
public boolean update(Fee obj) {
	
	int id = obj.getId_fee();
	System.out.println(id);
	  
	  try {
		
		  Connection conn = DriverManager.getConnection(ORACLE_DB_PATH, ORACLE_DB_USER, ORACLE_DB_PASSWORD);
		  PreparedStatement ps = conn.prepareStatement(
			      "UPDATE fee SET TITLE_FEE = ?, COMMENT_FEE = ?, AMOUNT_FEE = ? , STATE_FEE = 0 WHERE ID_FEE = ? ");
		  
			    // set the preparedstatement parameters
			    ps.setString(1,obj.getTitle_fee());
			    ps.setString(2, obj.getComment_fee());
			    ps.setInt(3, obj.getAmount_fee());
			    ps.setInt(4,id);
			   System.out.println(obj.getTitle_fee());
			   System.out.println(obj.getComment_fee());
			   System.out.println(obj.getAmount_fee());
			   System.out.println(id);
			    // call executeUpdate to execute our sql update statement
			    ps.executeUpdate();
			    ps.close();
		  
		  return true;

	  } catch (SQLException e) {
	      System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
	  } catch (Exception e) {
	      e.printStackTrace();
	  }
	return false;
	
}

public Fee find(String titreFee) {
	Fee obj = new Fee();      
    
	  String SQL_SELECT = "Select * from fee where TITLE_FEE='"+titreFee+"'";

	  // auto close connection and preparedStatement
	  try (Connection conn = DriverManager.getConnection(
			  ORACLE_DB_PATH, ORACLE_DB_USER, ORACLE_DB_PASSWORD);
	       PreparedStatement preparedStatement = conn.prepareStatement(SQL_SELECT)) {

	      ResultSet resultSet = preparedStatement.executeQuery();
	      
	      while (resultSet.next()) {
	          int id_fee = resultSet.getInt("ID_FEE");
	          String title_fee = resultSet.getString("TITLE_FEE");
	          String comment_fee = resultSet.getString("COMMENT_FEE");
	          int amount_fee = resultSet.getInt("AMOUNT_FEE");
	          int state_fee = resultSet.getInt("STATE_FEE");
	          int id_user_fee = resultSet.getInt("ID_USER_FEE");


	          obj.setId_fee(id_fee);
	          obj.setTitle_fee(title_fee);
	          obj.setComment_fee(comment_fee);
	          obj.setAmount_fee(amount_fee);
	          obj.setState_fee(state_fee);
	          obj.setId_user_fee(id_user_fee);
	          

	      }
		  conn.close();
	      return obj;

	  } catch (SQLException e) {
	      System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
	  } catch (Exception e) {
	      e.printStackTrace();
	  }
	return obj;
}




public ArrayList<Fee> findAllFeeByUser(User user) {
	ArrayList<Fee> ret = new ArrayList<Fee>();
	 int id = user.getId_user();
	  String SQL_SELECT = "Select * from fee where ID_USER_FEE="+id;
	  // auto close connection and preparedStatement
	
	  try (Connection conn = DriverManager.getConnection(
			  ORACLE_DB_PATH, ORACLE_DB_USER, ORACLE_DB_PASSWORD);
	       PreparedStatement preparedStatement = conn.prepareStatement(SQL_SELECT)) {
		  
	      ResultSet resultSet = preparedStatement.executeQuery();
	      
	      while (resultSet.next()) {
	    	  
	    	  Fee obj = new Fee();
	          int id_fee = resultSet.getInt("ID_FEE");
	          String title_fee = resultSet.getString("TITLE_FEE");
	          String comment_fee = resultSet.getString("COMMENT_FEE");
	          int amount_fee = resultSet.getInt("AMOUNT_FEE");
	          int state_fee = resultSet.getInt("STATE_FEE");
	          int id_user_fee = resultSet.getInt("ID_USER_FEE");


	          obj.setId_fee(id_fee);
	          obj.setTitle_fee(title_fee);
	          obj.setComment_fee(comment_fee);
	          obj.setAmount_fee(amount_fee);
	          obj.setState_fee(state_fee);
	          obj.setId_user_fee(id_user_fee);

	          ret.add(obj);

	      }
		  conn.close();

	      return ret;

	  } catch (SQLException e) {
	      System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
	  } catch (Exception e) {
	      e.printStackTrace();
	  }
	return ret;
	}

public ArrayList<Fee> findAllFee(User user) {
	ArrayList<Fee> ret = new ArrayList<Fee>();
    	int idbde = user.getCurrentBDE();
	  String SQL_SELECT = "Select DISTINCT(ID_FEE),TITLE_FEE,COMMENT_FEE,AMOUNT_FEE,STATE_FEE,ID_USER_FEE from fee,users where ID_USER_FEE = ID_USER AND ID_BDE="+idbde;
	  // auto close connection and preparedStatement
	  System.out.println("avant try");
	  try (Connection conn = DriverManager.getConnection(
			  ORACLE_DB_PATH, ORACLE_DB_USER, ORACLE_DB_PASSWORD);
	       PreparedStatement preparedStatement = conn.prepareStatement(SQL_SELECT)) {
		  
	      ResultSet resultSet = preparedStatement.executeQuery();
	      while (resultSet.next()) {
	    	  Fee obj = new Fee();
	          int id_fee = resultSet.getInt("ID_FEE");
	          String title_fee = resultSet.getString("TITLE_FEE");
	          String comment_fee = resultSet.getString("COMMENT_FEE");
	          int amount_fee = resultSet.getInt("AMOUNT_FEE");
	          int state_fee = resultSet.getInt("STATE_FEE");
	          int id_user_fee = resultSet.getInt("ID_USER_FEE");


	          obj.setId_fee(id_fee);
	          obj.setTitle_fee(title_fee);
	          obj.setComment_fee(comment_fee);
	          obj.setAmount_fee(amount_fee);
	          obj.setState_fee(state_fee);
	          obj.setId_user_fee(id_user_fee);


	          ret.add(obj);

	      }
		  conn.close();

	      return ret;

	  } catch (SQLException e) {
	      System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
	  } catch (Exception e) {
	      e.printStackTrace();
	  }
	return ret;
	}



@Override
public boolean acceptFee(Fee obj) {
	int id = obj.getId_fee();
	System.out.println("id : "+ id);
	  
	  try {
		
		  Connection conn = DriverManager.getConnection(ORACLE_DB_PATH, ORACLE_DB_USER, ORACLE_DB_PASSWORD);
		  
		  PreparedStatement ps = conn.prepareStatement(
			      "UPDATE fee SET STATE_FEE = 1 WHERE ID_FEE =? ");
		  
			    // set the preparedstatement parameters
			    
			    ps.setInt(1,id);
			    System.out.println("id : "+ id);

			   
			    // call executeUpdate to execute our sql update statement
			    ps.executeUpdate();
			    ps.close();
		  
		  return true;

	  } catch (SQLException e) {
	      System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
	  } catch (Exception e) {
	      e.printStackTrace();
	  }
	return false;
	
}

public int getNumber() {
	 String SQL_SELECT = "Select * from fee";
	  // auto close connection and preparedStatement
	
	  try (Connection conn = DriverManager.getConnection(
			  ORACLE_DB_PATH, ORACLE_DB_USER, ORACLE_DB_PASSWORD);
	       PreparedStatement preparedStatement = conn.prepareStatement(SQL_SELECT)) {
		  
	      ResultSet resultSet = preparedStatement.executeQuery();
	      int nb=0;
	      while (resultSet.next()) {
	    	  nb++;
	      }
		  conn.close();

	      return nb;

	  } catch (SQLException e) {
	      System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
	  } catch (Exception e) {
	      e.printStackTrace();
	  }
	
	return 0;
	
}

@Override
public ArrayList<Fee> findAll() {
	// TODO Auto-generated method stub
	return null;
}

@Override
public Fee findById(int id) {
	// TODO Auto-generated method stub
	return null;
}

}
