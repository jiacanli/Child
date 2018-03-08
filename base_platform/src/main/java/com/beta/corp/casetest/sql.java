package com.beta.corp.casetest;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;
import java.util.concurrent.AbstractExecutorService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.commons.io.FileUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import redis.clients.util.Pool;





public class sql {
	public static Connection con;
	public static String driver = "com.mysql.jdbc.Driver";
	public static String url = "jdbc:mysql://192.168.8.172:3308/crop";
	public static String user = "root";
	public static Statement st;
	public sql() {
		// TODO Auto-generated constructor stub
	}
	
	static{
		String driver = "com.mysql.jdbc.Driver";
		try {
			Class.forName(driver);
			 con = DriverManager.getConnection(url,user,"");
			 st = con.createStatement();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	public static void main(String[] args) {
		ExecutorService fixedThreadPool = Executors.newFixedThreadPool(50);
		Long start  = System.currentTimeMillis();
		try {
			String json = FileUtils.readFileToString(new File("D:\\work\\crop\\province.txt"));
			JSONObject jo = new JSONObject(json);
			Iterator<String> province_codes = jo.keys(); 
			int count =1;// 
			while(province_codes.hasNext()){
				String code = province_codes.next();
				String province_name = jo.getJSONObject(code).getString("name");
				System.out.println(count+"--"+province_name);
				insert("insert into province(code,province_name) values('"+code+"','"+province_name+"')");
//				fixedThreadPool.execute(new sql_execute("insert into province(code,province_name) values('"+code+"','"+province_name+"')"));
				JSONObject citys = jo.getJSONObject(code).getJSONObject("child");
				Iterator<String> city_codes = citys.keys();
				while(city_codes.hasNext()){
					String city_code = city_codes.next();
					String city_name = citys.getJSONObject(city_code).getString("name");
					if(!city_name.equals("市辖区")){
					insert("insert into city(city_code,city_name,reference_province_code) values('"+
							city_code+"','"+city_name+"','"+code+"')");
//					fixedThreadPool.execute(new sql_execute("insert into city(city_code,city_name,reference_province_code) values('"+
//							city_code+"','"+city_name+"','"+code+"')"));
					}					
					JSONObject towns = citys.getJSONObject(city_code).getJSONObject("child");
					Iterator<String> town_codes = towns.keys();
					while(town_codes.hasNext()){
						String town_code = town_codes.next();
						String town_name = towns.getString(town_code);
						if(city_name.equals("市辖区")){
//							insert("insert into city(city_code,city_name,reference_province_code) values('"+
//									town_code+"','"+town_name+"','"+code+"')");
							fixedThreadPool.execute(new sql_execute("insert into city(city_code,city_name,reference_province_code) values('"+
									town_code+"','"+town_name+"','"+code+"')"));
						}
						else{
//						insert("insert into town(town_code,town_name,reference_city_code) values('"+
//						town_code+"','"+town_name+"','"+city_code+"')");
						fixedThreadPool.execute(new sql_execute("insert into town(town_code,town_name,reference_city_code) values('"+
						town_code+"','"+town_name+"','"+city_code+"')"));
						}
					}
					
					
				}
				count++;
			}
			fixedThreadPool.shutdown();
			while(true){
				if(fixedThreadPool.isTerminated()){
					con.close();
					break;
				}
			}
//			con.close();
			Long end = System.currentTimeMillis();
			double time = end-start;
			System.out.println((end-start)/1000);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			
		}

	}
	
	public static void insert(String sql){
		try {
			st.execute(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	static class sql_execute implements Runnable{
		String sql;
		public sql_execute(String sql){
			this.sql = sql;
		}
		public void run() {
			// TODO Auto-generated method stub
			insert(sql);
		}
	}

}
