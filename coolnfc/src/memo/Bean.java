package memo;

import java.sql.*;
import java.util.ArrayList;

import org.apache.catalina.User;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import memo.Memo;;

public class Bean {
	Connection conn = null;
	PreparedStatement pstmt = null;
	PreparedStatement pstmt2=null;

	String jdbc_driver = "com.mysql.jdbc.Driver";
	String jdbc_url = "jdbc:mysql://noring.iptime.org:7011/nfc";
	
	void connect(){
		try{
			Class.forName(jdbc_driver);
			
			conn = DriverManager.getConnection(jdbc_url, "noriter", "1234");
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	void disconnect(){
		if(pstmt != null){
			try{
				pstmt.close();
			}
			catch(SQLException e){
				e.printStackTrace();
			}
		}
		if(conn != null){
			try{
				conn.close();
			}
			catch(SQLException e){
				e.printStackTrace();
			}
		}
	}
	
	public String openshut(Memo memo){
		connect();
		String sql1 = "select * from lecroom where lecroom=?";//강의실 개폐정보 반
		
		String openshut="";
			try{
				pstmt=conn.prepareStatement(sql1);
				pstmt.setString(1,memo.getLecroom());
				ResultSet rs = pstmt.executeQuery();
				while(rs.next()){
					openshut = rs.getString("openshut");
				}
			}catch(SQLException e){
				e.printStackTrace();
				return "0";
			}
			finally {
				disconnect();
			}
			return openshut;
	}
	
	public boolean insertDB(Memo memo){
		connect();
		
		String sql="insert into class_table(class_code ,class_name ,class_place ,class_day ,class_start ,class_end) values(?,?,?,?,?,?)";
		
		try{
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, memo.getClass_code());
			pstmt.setString(2, memo.getClass_name());
			pstmt.setString(3, memo.getClass_place());
			pstmt.setString(4, memo.getClass_day());
			pstmt.setString(5, memo.getClass_start());
			pstmt.setString(6, memo.getClass_end());
			pstmt.executeUpdate();
		}
		catch(SQLException e){
			e.printStackTrace();
			return false;
		}
		finally{
			disconnect();
		}
		return true;
	}
	
	public boolean insertlecDB(Memo memo){
		connect();
		
		String sql="insert into lecroom(lecroom,door_pw,door_pwclose) values(?,?,?)";
		try{
			
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, memo.getLecroom());
			pstmt.setString(2, memo.getDoor_pw());
			pstmt.setString(3, memo.getDoor_pwclose());
			pstmt.executeUpdate();
		}
		catch(SQLException e){
			e.printStackTrace();
			return false;
		}
		finally{
			disconnect();
		}
		return true;
	}

	public boolean deleteDB(String class_code){
		connect();
		System.out.println(class_code);
		String sql="delete from class_table where class_code=?";
		try{
			pstmt=conn.prepareStatement(sql);
			
			pstmt.setString(1,class_code);
			pstmt.executeUpdate();
		} catch (SQLException e){
			e.printStackTrace();
			return false;
		}
		finally{
			disconnect();
		}
		return true;
	}
	public boolean deletelecDB(String lecroom){
		connect();
		System.out.println(lecroom);
		String sql="delete from lecroom where lecroom=?";
		try{
			pstmt=conn.prepareStatement(sql);
			
			pstmt.setString(1,lecroom);
			pstmt.executeUpdate();
		} catch (SQLException e){
			e.printStackTrace();
			return false;
		}
		finally{
			disconnect();
		}
		return true;
	}
	
	public boolean logink(Memo memo){
		connect();
		
		String sql = "select user_id, user_pw from user";
		
		try{
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()){
				if((memo.getUser_id().equals("admin") && 
					(memo.getUser_pw().equals("1234")))){
					return true;
				}
			}
			rs.close();
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		finally {
			disconnect();
		}
		return false;
	}
	
	public String login(Memo memo){
		connect();
		String sql = "select count(*) from user where user_id=? and user_pw=?";
		String sql2 = "select b.class_code, b.class_name, b.class_place, b.class_day, b.class_start, b.class_end  from sup_table a, class_table b where a.user_id=? and a.class_code=b.class_code";
		String result = "0";
		try{
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memo.getUser_id());
			pstmt.setString(2, memo.getUser_pw());
			ResultSet rs = pstmt.executeQuery();
			int iResult = 0;
			while(rs.next()){
				iResult=rs.getInt(1);
			}
			System.out.println(iResult);
			if(iResult==1)
			{
				pstmt.close();
				pstmt = conn.prepareStatement(sql2);
				pstmt.setString(1, memo.getUser_id());
				rs = pstmt.executeQuery();
				JSONObject jsonObject = new JSONObject();
				JSONArray jsonArray = new JSONArray();
				while(rs.next())
				{
					JSONObject object = new JSONObject();
					object.put("class_code", rs.getString(1));
					object.put("class_name", rs.getString(2));
					object.put("class_place", rs.getString(3));
					object.put("class_day", rs.getString(4));
					object.put("class_start", rs.getString(5));
					object.put("class_end", rs.getString(6));
					jsonArray.add(object);
				}
				jsonObject.put("home",jsonArray);
				result=jsonObject.toString();
			}
			else{
				result="0";
			}
			rs.close();
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		finally {
			disconnect();
		}
		System.out.println(result);
		return result;
	}
	public ArrayList<Memo> getDB() {
		connect();
		ArrayList<Memo> datas = new ArrayList<Memo>();
		
		String sql = "select * from nfc";
		
		try{
			pstmt =  conn.prepareStatement(sql);
			ResultSet rs=pstmt.executeQuery();
			
			while(rs.next()){
				Memo memo = new Memo();
				memo.setLecroom(rs.getString("lecroom"));
				datas.add(memo);
			}
			rs.close();
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		finally {
			disconnect();
			
		}
		return datas;		
	}
	
	public String openoffer(Memo memo){
		connect();
		System.out.println(memo.getUser_id());
		System.out.println(memo.getLecroom());
		String sql1 = "insert into openoffer(user_id,lecroom) values(?,?)";//여는 신청
		String sql2 = "select * from lecroom where lecroom=?";//강의실 비밀번호 반환
		
		String door_pw="";
			try{
				//강의실 비번 반환
				pstmt2=conn.prepareStatement(sql2);
				pstmt2.setString(1, memo.getLecroom());
				ResultSet rs = pstmt2.executeQuery();
				while(rs.next()){
					door_pw = rs.getString("door_pw");
				}
				
				//로그
				pstmt=conn.prepareStatement(sql1);
				pstmt.setString(1, memo.getUser_id());
				pstmt.setString(2, memo.getLecroom());
				
				pstmt.executeUpdate();
				pstmt2.close();
				rs.close();
			}catch(SQLException e){
				e.printStackTrace();
				return "0";
			}
			finally {
				disconnect();
			}
			return door_pw;
	}
	
	public void openreal(Memo memo){
		connect();
		
		String sql1="insert into openreal(user_id,lecroom) values(?,?)";	//실제 여는..
		String sql2="update lecroom set openshut=1 where lecroom=? and openshut=0";	//상태 업데이트
		
		try{
			//로그
			pstmt=conn.prepareStatement(sql1);
			pstmt2=conn.prepareStatement(sql2);
			pstmt.setString(1, memo.getUser_id());
			pstmt.setString(2, memo.getLecroom());
			pstmt.executeUpdate();
			
			//상태 업뎃
			pstmt2.setString(1, memo.getLecroom());
			pstmt2.executeUpdate();
			
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		finally {
			disconnect();
		}
	}
	
	public String closeoffer(Memo memo){
		connect();
		String sql1 = "insert into closeoffer(user_id,lecroom) values(?,?)";//여는 신청
		String sql2 = "select * from lecroom where lecroom=?";//강의실 비밀번호 반환
		
		String door_pw="";
			try{
				//강의실 비번 반환
				pstmt2=conn.prepareStatement(sql2);
				pstmt2.setString(1, memo.getLecroom());
				ResultSet rs = pstmt2.executeQuery();
				while(rs.next()){
					door_pw = rs.getString("door_pwclose");
				}
				
				//로그
				pstmt=conn.prepareStatement(sql1);
				pstmt.setString(1, memo.getUser_id());
				pstmt.setString(2, memo.getLecroom());
				
				pstmt.executeUpdate();
				pstmt2.close();
				rs.close();
			}catch(SQLException e){
				e.printStackTrace();
				return "0";
			}
			finally {
				disconnect();
			}
			return door_pw;
	}
	
	public void closereal(Memo memo){
		connect();
		
		String sql1="insert into closereal(user_id,lecroom) values(?,?)";	//실제 여는..
		String sql2="update lecroom set openshut=0 where lecroom=? and openshut=1";	//상태 업데이트
		
		try{
			//로그
			pstmt=conn.prepareStatement(sql1);
			pstmt2=conn.prepareStatement(sql2);
			pstmt.setString(1, memo.getUser_id());
			pstmt.setString(2, memo.getLecroom());
			pstmt.executeUpdate();
			
			//상태 업뎃
			pstmt2.setString(1, memo.getLecroom());
			pstmt2.executeUpdate();
			
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		finally {
			disconnect();
		}
	}
	public ArrayList<Memo> getOpenoffer(){
		connect();
		ArrayList<Memo> datas=new ArrayList<Memo>();
		
		String sql="select * from openoffer";
		try{
			pstmt=conn.prepareStatement(sql);
			ResultSet rs=pstmt.executeQuery();
			while(rs.next()){
				Memo Memo =new Memo();
				
				Memo.setUser_id(rs.getString("user_id"));
				Memo.setLecroom(rs.getString("lecroom"));
				Memo.setDate(rs.getString("date"));
				datas.add(Memo);
			}
			rs.close();
		} catch(SQLException e){
			e.printStackTrace();
		}
		finally{
			disconnect();
		}
		return datas;
	}
	public ArrayList<Memo> getOpenreal(){
		connect();
		ArrayList<Memo> datas=new ArrayList<Memo>();
		
		String sql="select * from openreal";
		try{
			pstmt=conn.prepareStatement(sql);
			ResultSet rs=pstmt.executeQuery();
			while(rs.next()){
				Memo Memo =new Memo();
				
				Memo.setUser_id(rs.getString("user_id"));
				Memo.setLecroom(rs.getString("lecroom"));
				Memo.setDate(rs.getString("date"));
				datas.add(Memo);
			}
			rs.close();
		} catch(SQLException e){
			e.printStackTrace();
		}
		finally{
			disconnect();
		}
		return datas;
	}
	public ArrayList<Memo> getCloseoffer(){
		connect();
		ArrayList<Memo> datas=new ArrayList<Memo>();
		
		String sql="select * from closeoffer";
		try{
			pstmt=conn.prepareStatement(sql);
			ResultSet rs=pstmt.executeQuery();
			while(rs.next()){
				Memo Memo =new Memo();
				
				Memo.setUser_id(rs.getString("user_id"));
				Memo.setLecroom(rs.getString("lecroom"));
				Memo.setDate(rs.getString("date"));
				datas.add(Memo);
			}
			rs.close();
		} catch(SQLException e){
			e.printStackTrace();
		}
		finally{
			disconnect();
		}
		return datas;
	}
	public ArrayList<Memo> getClosereal(){
		connect();
		ArrayList<Memo> datas=new ArrayList<Memo>();
		
		String sql="select * from closereal";
		try{
			pstmt=conn.prepareStatement(sql);
			ResultSet rs=pstmt.executeQuery();
			while(rs.next()){
				Memo Memo =new Memo();
				
				Memo.setUser_id(rs.getString("user_id"));
				Memo.setLecroom(rs.getString("lecroom"));
				Memo.setDate(rs.getString("date"));
				datas.add(Memo);
			}
			rs.close();
		} catch(SQLException e){
			e.printStackTrace();
		}
		finally{
			disconnect();
		}
		return datas;
	}
	public ArrayList<Memo> getLecroom(){
		connect();
		ArrayList<Memo> datas=new ArrayList<Memo>();
		
		String sql="select * from lecroom";
		try{
			pstmt=conn.prepareStatement(sql);
			ResultSet rs=pstmt.executeQuery();
			while(rs.next()){
				Memo Memo =new Memo();
				
				Memo.setLecroom(rs.getString("lecroom"));	
				Memo.setOpenshut(rs.getString("openshut"));	
				Memo.setDoor_pw(rs.getString("door_pw"));				
				Memo.setDoor_pwclose(rs.getString("door_pwclose"));
				datas.add(Memo);
			}
			rs.close();
		} catch(SQLException e){
			e.printStackTrace();
		}
		finally{
			disconnect();
		}
		return datas;
	}
	public ArrayList<Memo> getsup_table(){
		connect();
		ArrayList<Memo> datas=new ArrayList<Memo>();
		
		String sql="select * from sup_table";
		try{
			pstmt=conn.prepareStatement(sql);
			ResultSet rs=pstmt.executeQuery();
			while(rs.next()){
				Memo Memo =new Memo();
				
				Memo.setUser_id(rs.getString("user_id"));		
				Memo.setClass_code(rs.getString("class_code"));				
				datas.add(Memo);
			}
			rs.close();
		} catch(SQLException e){
			e.printStackTrace();
		}
		finally{
			disconnect();
		}
		return datas;
	}
	
	public  ArrayList<Memo> getClass_table(){
		connect();
		ArrayList<Memo> datas=new ArrayList<Memo>();
		
		String sql="select * from class_table";
		try{
			pstmt=conn.prepareStatement(sql);
			ResultSet rs=pstmt.executeQuery();
			while(rs.next()){
				Memo Memo =new Memo();
				
				Memo.setClass_code(rs.getString("class_code"));				
				Memo.setClass_name(rs.getString("class_name"));
				Memo.setClass_place(rs.getString("class_place"));				
				Memo.setClass_day(rs.getString("class_day"));
				Memo.setClass_start(rs.getString("class_start"));				
				Memo.setClass_end(rs.getString("class_end"));
				datas.add(Memo);
			}
			rs.close();
		} catch(SQLException e){
			e.printStackTrace();
		}
		finally{
			disconnect();
		}
		return datas;
	}
}

	
	