import java.io.*;
import java.sql.*;

public class test {
    public static void main(String[] args) throws SQLException, IOException {
        ResultSet rs = null;
        Statement stmt = null;
        Connection conn = null;
        PreparedStatement pstm=null;

        try {
            //1.注册驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            //2.建立连接
            conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/test?useUnicode=true&serverTimezone=Asia/Shanghai&characterEncoding=UTF-8&useSSL=FALSE" ,"root","");
            //3.处理结果集
//            stmt = conn.createStatement();
//            rs = stmt.executeQuery("select * from tablename1");
//
//
//            while (rs.next()) {
//                int age = rs.getInt("age");
//
//// 输出查到的记录的各个字段的值
//                System.out.println( " " + age);
//            }



            //预编译语句
//            String sql="select * from tablename1 where age = ?";
//            pstm=conn.prepareStatement(sql);
//            pstm.setInt(1,21);
//            rs=pstm.executeQuery();
//            while (rs.next()) {
//                int age = rs.getInt("age");
//
//// 输出查到的记录的各个字段的值
//                System.out.println( " " + age);
//            }

            //addbatch方法 执行一组sql语句
          //  String sql1="insert into tablename1(age) values(?)";
//            stmt = conn.createStatement();
//            stmt.addBatch(sql1);
//            stmt.addBatch(sql2);
//            stmt.addBatch(sql3);


            //psmt的addbatch方法
//            PreparedStatement psmt1=conn.prepareStatement(sql1);
//            psmt1.setInt(1,40);
//            psmt1.addBatch();
//            psmt1.setInt(1,45);
//            psmt1.addBatch();
//            int[] upRowS=psmt1.executeBatch();




            //执行命令所影响数据库中行数的更新计数
           // int[] upRowS=stmt.executeBatch();

//            for(int tmp:upRowS)
//            System.out.println(tmp);

            //psmt查询到的结果集 进行previous last等取值
//            String sql=("select * from tablename1");
//            PreparedStatement psmt=conn.prepareStatement(sql,rs.TYPE_SCROLL_INSENSITIVE,rs.CONCUR_READ_ONLY);
//            rs=psmt.executeQuery();
//            System.out.println("先按顺序输出所有内容");
//            while(rs.next())
//            {
//                int tmp=rs.getInt(1);
//                System.out.print(tmp+"  ");
//            }
//            System.out.println("\n输出第一个值：");
//            rs.first();
//            int tmp=rs.getInt(1);
//            System.out.println(tmp);
//            System.out.println("输出最后一个值：");
//            rs.last();
//            tmp=rs.getInt(1);
//            System.out.println(tmp);


            //获得数据库的元信息
//            DatabaseMetaData dbmd = null;
//            try {
//                dbmd = conn.getMetaData();
//            } catch (SQLException throwables) {
//                throwables.printStackTrace();
//            }
//
//            String [] tableTypes = {"TABLE", "VIEW"};
//
//
//            rs = dbmd.getTables(null, null, "%",tableTypes);
//            System.out.println("元信息为：");
//            while (rs.next()) {
//                String s = rs.getString(1);
//                System.out.println("\nCatalog Name: " + s + " Schema Name: " + rs.getString(2) +
//                        " Table Name: " + rs.getString(3) );
//
//        }  // End try

        //插入图片（二进制大文件BLOB）
//            String sql="update tablename1 set PHOTO_LONG=? where age=?";
//            pstm=conn.prepareStatement(sql);
//            FileInputStream fis=new FileInputStream("src/getin.png");
//            pstm.setInt(2,34);
//            pstm.setBlob(1,fis);
//            int updateRow=pstm.executeUpdate();
//            System.out.println("已更新"+updateRow+"条数据");

            //查询图片 输出到新文件里面
            String sql="select PHOTO_LONG  from tablename1 where age=?";
            pstm=conn.prepareStatement(sql);
            pstm.setInt(1,34);
            rs=pstm.executeQuery();
            if(rs.next()) {
                InputStream inputStream = rs.getBinaryStream(1);
                FileOutputStream fos = new FileOutputStream("src\\copy.jpg");
                byte[] b = new byte[1024];
                int len = -1;
                while ((len = inputStream.read(b)) != -1) {
                    fos.write(b, 0, len);
                }

                fos.close();
                inputStream.close();


            }

            } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                //4.关闭资源
                if (rs != null) {
                    rs.close();
                    rs = null;
                }
                if (stmt != null) {
                    stmt.close();
                    stmt = null;
                }
                if (conn != null) {
                    conn.close();
                    conn = null;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}