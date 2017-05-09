package com.personalFilmManagement;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class DBoperator {
	private static Connection conn=null;
	private static String table ="personalfilmmanagement.movie";
	private static boolean connect() {
	    String driver = "com.mysql.jdbc.Driver";
	    String url ="jdbc:mysql://localhost:3306"
	    		+ "/personalfilmmanagement?characterEncoding=utf-8"; 
	    String username = "root";
	    String password = "";
	    try {
	        Class.forName(driver); //classLoader,加载数据库驱动.
	        conn = (Connection) DriverManager.getConnection(url, username, password);
	    } catch (ClassNotFoundException e) {
	        //e.printStackTrace();
	        return false;
	    } catch (SQLException e) {
	        //e.printStackTrace();
	        return false;
	    }
	    return true;
	}//end getConn()
	private static boolean closeConn(){
		
		try {
			conn.close();
		} catch (SQLException e) {
			//e.printStackTrace();
			return false;
		}
		return true;
	}
	public static int update(Movie m, Whereable where) {
	    connect();
	    int influenceLine = 0;//影响的行数
	    StringBuilder set=new StringBuilder("");
	    if(m.getENGname()!=null){
	    	set.append("ENGname =");
	    	set.append("'"+m.getENGname()+"'");
	    }
	    if(m.getCHSname()!=null){
	    	if(!set.toString().equals(""))
	    		set.append(",");
	    	set.append("CHSname =");
	    	set.append("'"+m.getCHSname()+"'");
	    }
	    if(m.getReleaseDate() !=null){
	    	if(!set.toString().equals(""))
	    		set.append(",");
	    	set.append("releaseDate =");
	    	set.append("'"+m.getReleaseDate()+"'");
	    }
	    if(m.getCompressionScheme()!=null){
	    	if(!set.toString().equals(""))
	    		set.append(",");
	    	set.append("compressionScheme =");
	    	set.append(m.getCompressionScheme().ordinal());
	    }
	    if(m.getResolution()!=null){
	    	if(!set.toString().equals(""))
	    		set.append(",");
	    	set.append("resolution =");
	    	set.append(m.getResolution().ordinal());
	    }
	    if(m.getVideoCoding()!=null){
	    	if(!set.toString().equals(""))
	    		set.append(",");
	    	set.append("videoCoding =");
	    	set.append(m.getVideoCoding().ordinal());
	    }
	    if(m.getAudioCoding()!=null){
	    	if(!set.toString().equals(""))
	    	{	
	    		//System.out.println(set.toString());
	    		set.append(",");
	    	}
	    	set.append("audioCoding =");
	    	set.append(m.getAudioCoding().ordinal());
	    }
	    if(m.getPhysicalAddress()!=null){
	    	if(!set.toString().equals(""))
	    		set.append(",");
	    	set.append("physicalAddress =");
	    	set.append("'"+m.getPhysicalAddress()+"'");
	    }
		String sql = "update "+table+" set "+set+
				" where "+where.where();	
		//System.out.println(sql);
	    PreparedStatement pstmt;
	    try {
	        pstmt = (PreparedStatement) conn.prepareStatement(sql);
	        pstmt.executeUpdate();
	        //System.out.println("resutl: " + influenceLine);
	        pstmt.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    finally{
	    	closeConn();
	    }
	    return influenceLine;
	}//end update()
	
	public static int insert(Movie m) throws Exception{
	    connect();
	    int influenceLine = 0;
	    String sql = "INSERT INTO "+table
	    		+ " (`ENGname`, `CHSname`,`releaseDate`, `physicalAddress`, "
	    		+ "`compoessionScheme`, "
	    		+ "`resolution`, `videoCoding`, `audioCoding`) "
	    		+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
	    PreparedStatement pstmt;
	    try {
	        pstmt = (PreparedStatement) conn.prepareStatement(sql);
	        pstmt.setString(1, m.getENGname());
	        pstmt.setString(2, m.getCHSname());
	        pstmt.setString(3, m.getReleaseDate());
	        pstmt.setString(4, m.getPhysicalAddress());
	        if(m.getCompressionScheme()==null)
	        	pstmt.setNull(5, java.sql.Types.INTEGER);
	        else 
	        	pstmt.setInt(5, m.getCompressionScheme().ordinal());
	        if(m.getResolution()==null)
	        	pstmt.setNull(6, java.sql.Types.INTEGER);
	        else
	        	pstmt.setInt(6, m.getResolution().ordinal());
	        if(m.getAudioCoding()==null)
	        	pstmt.setNull(7, java.sql.Types.INTEGER);
	        else
	        	pstmt.setInt(7, m.getVideoCoding().ordinal());
	        if(m.getAudioCoding()==null)
	        	pstmt.setNull(8, java.sql.Types.INTEGER);
	        else
	        	pstmt.setInt(8, m.getAudioCoding().ordinal());
	        influenceLine = pstmt.executeUpdate();
	        pstmt.close();
	    } catch (SQLException e) {
	        //e.printStackTrace(); 
	        throw new Exception();
	    }
	    finally{
	    	closeConn();
	    }
	    return influenceLine;
	}//end insert()
	
	public static LinkedList<Movie> select(Selectable s,Whereable w,OrderBy ob){
		LinkedList<Movie> result=new LinkedList<>();
		ResultSet resSet;
		String sql="select "+s.select()+" from "+table;
		if(!w.where().equals(""))
			sql=sql+" where "+w.where();
		//System.out.println(sql);
		if(ob!=null)
		{
			if(ob.obt==OrderType.ASC)
				sql=sql+" order by "+ob.obId+" asc";
			if(ob.obt==OrderType.DESC)
				sql=sql+" order by "+ob.obId+" desc";
		}
		//System.out.println(sql);
		connect();
		PreparedStatement pstmt;
		try {
			pstmt = (PreparedStatement) conn.prepareStatement(sql);
			resSet=pstmt.executeQuery();
			while(resSet.next())
			{
				Movie m=new Movie(resSet.getString(1),
						resSet.getString(2),
						resSet.getString(3),
						resSet.getString(4),
						Resolution.valueOf(resSet.getInt(5)),
						CompressionScheme.valueOf(resSet.getInt(6)),
						VideoCoding.valueOf(resSet.getInt(7)),
						AudioCoding.valueOf(resSet.getInt(8))
						); 
				result.add(m);
			} 
		} catch (SQLException e) {
			//e.printStackTrace();
		}
		finally{
			closeConn();
		}
		return result;
		
	}//end select()
	
	
}
