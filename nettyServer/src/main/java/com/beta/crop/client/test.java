package com.beta.crop.client;

import java.io.*;
import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Enumeration;

public class test {
    public static String INTRANET_IP = getIntranetIp(); // 内网IP
    public static String INTERNET_IP = getInternetIp(); // 外网IP
	public test() {
		// TODO Auto-generated constructor stub
	}
	
	public static void main(String[] args) {
        try {
			Socket socket = new Socket("13.124.243.54", 50051);
			System.out.println("客户端启动成功");
		    OutputStream ops = socket.getOutputStream();    
		    OutputStreamWriter opsw = new OutputStreamWriter(ops);
		    BufferedWriter bw = new BufferedWriter(opsw);
		      
		    bw.write("hello world");
		    bw.flush();
		      
		    // 从服务端程序接收数据
		    InputStream ips = socket.getInputStream();
		    InputStreamReader ipsr = new InputStreamReader(ips);
		    BufferedReader br = new BufferedReader(ipsr);
		    String s = "";    
		    while((s = br.readLine()) != null)
		      System.out.println(s);    
		    socket.close();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
	}
    private static String getIntranetIp(){
        try{
            return InetAddress.getLocalHost().getHostAddress();
        } catch(Exception e){
            throw new RuntimeException(e);
        }
    }
	
    private static String getInternetIp(){
        try{
            Enumeration<NetworkInterface> networks = NetworkInterface.getNetworkInterfaces();
            InetAddress ip = null;
            Enumeration<InetAddress> addrs;
            while (networks.hasMoreElements())
            {
                addrs = networks.nextElement().getInetAddresses();
                while (addrs.hasMoreElements())
                {
                    ip = addrs.nextElement();
                    if (ip != null
                            && ip instanceof Inet4Address
                            && ip.isSiteLocalAddress()
                            && !ip.getHostAddress().equals(INTRANET_IP))
                    {
                        return ip.getHostAddress();
                    }
                }
            }

            // 如果没有外网IP，就返回内网IP
            return INTRANET_IP;
        } catch(Exception e){
            throw new RuntimeException(e);
        }
    }
    
    
    
    
}


