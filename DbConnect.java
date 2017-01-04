/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.sql.*;



public class DbConnect{

Connection con=null;
Statement st=null;
ResultSet rs=null;

public DbConnect()
{
  try{
       Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
       con=DriverManager.getConnection("jdbc:odbc:PrisonDSN2003");
      }
  catch(Exception e){}

}

public void insertdata(String str)
{
  try{
       System.out.println(str);
       st=con.createStatement();
       st.executeUpdate(str);
     }
  catch(Exception ex)
  {
       System.out.println(ex);


  }

}

public ResultSet selectData(String str)
   {
      try{
      System.out.println(str);
      st=con.createStatement();
      rs=st.executeQuery(str);
      }

      catch(Exception ex)
      {
         System.out.println(ex);
      }
      return rs;

    }




}

