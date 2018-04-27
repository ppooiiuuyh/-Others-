package com.ourproject.myapp.dbinterfaces;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by ppooi on 2017-05-01.
 */
public class MariaDBManager extends DBManager {
    String driver        = "org.mariadb.jdbc.Driver";
    String url           = "jdbc:mariadb://dohyeon-capstone.cfrrva5c1eub.ap-northeast-1.rds.amazonaws.com:3306/myDB";
    String uId           = "ppooiiuuyh";
    String uPwd          = "264df83630";

    Connection con;
    PreparedStatement pstmt;
    ResultSet rs;

    public MariaDBManager() {
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url, uId, uPwd);

            if( con != null ){ System.out.println("connected"); }

        } catch (ClassNotFoundException e) { System.out.println("error");    }
        catch (SQLException e) { System.out.println("error2"); }

    }

    @Override
    public void execQuery(String query){
        String sql    = query;

        try {
            pstmt                = con.prepareStatement(sql);
            rs                   = pstmt.executeQuery();
            rs.next();
            System.out.println(rs.getString("filepath"));
        } catch (SQLException e) { System.out.println("exec query error"); }
    }
    
    
    @Override
    public boolean execute(String query){
        String sql    = query;
        boolean result = false;
        try {
            pstmt               = con.prepareStatement(sql);
            result              = pstmt.execute();
            
        } catch (SQLException e) { System.out.println("exec query error"); }
        
        return result;
    }
    

/*
    public void select(){
        String sql    = "select * from datas";

        try {
            pstmt                = con.prepareStatement(query);
            rs                   = pstmt.executeQuery();
            rs.next();
            System.out.println(rs.getString("data"));
       *//*     while(rs.next()){
                System.out.println("idx       : " + rs.getInt("idx"));
                System.out.println("writer    : " + rs.getString("writer"));
                System.out.println("title     : " + rs.getString("title"));
                System.out.println("content   : " + rs.getString("content"));
            }*//*
        } catch (SQLException e) { System.out.println("荑쇰━ �닔�뻾 �떎�뙣"); }
    }*/


}
