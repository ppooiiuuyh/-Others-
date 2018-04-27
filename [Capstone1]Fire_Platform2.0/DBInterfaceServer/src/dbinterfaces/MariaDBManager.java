package dbinterfaces;

import java.sql.*;

/**
 * Created by ppooi on 2017-05-01.
 */
public class MariaDBManager extends DBManager {
    String driver        = "org.mariadb.jdbc.Driver";
    String url           = "jdbc:mariadb://capston-iotext-cluster.cluster-c1p6eb7f0xnq.us-west-2.rds.amazonaws.com:3306/test";
    String uId           = "ppooiiuuyh";
    String uPwd          = "264df83630";

    Connection con;
    PreparedStatement pstmt;
    ResultSet rs;

    public MariaDBManager() {
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url, uId, uPwd);

            if( con != null ){ System.out.println("데이터 베이스 접속 성공"); }

        } catch (ClassNotFoundException e) { System.out.println("드라이버 로드 실패");    }
        catch (SQLException e) { System.out.println("데이터 베이스 접속 실패"); }

    }

    @Override
    public void execQuery(String query){
        String sql    = "select * from datas";

        try {
            pstmt                = con.prepareStatement(query);
            rs                   = pstmt.executeQuery();

        } catch (SQLException e) { System.out.println("쿼리 수행 실패"); }
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
        } catch (SQLException e) { System.out.println("쿼리 수행 실패"); }
    }*/


}
