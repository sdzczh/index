package com.zh.test;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * 聊天室客户端
 * @author adminitartor
 *
 */
public class Client {
	/*
	 * java.net.Socket 套接字
	 * 封装了TCP通讯,使用它可以通过网络连接
	 * 到服务端并进行数据交换(通讯)
	 */
	private Socket socket;
	/**
	 * 用于初始化客户端
	 * @throws Exception 
	 */
	public Client() throws Exception{
		try {
			/*
			 * 实例化Socket的过程就是与远端
			 * 计算机建立连接的过程.
			 * 实际上建立连接是客户端应用程序
			 * 与服务端应用程序建立的连接.
			 * 那么,这里实例化Socket需要传入
			 * 连个参数:
			 * 1:服务端计算机的IP地址(用于在
			 *   网络中找到服务端计算机)
			 * 2:端口号(通过端口号可以找到
			 *   运行在服务器中的服务端应用
			 *   程序)  
			 */
			System.out.println("正在连接服务端...");
			socket = new Socket("172.16.1.100",8080);
			System.out.println("已建立连接!");
			
		} catch (Exception e) {			
			throw e;
		}
	}
	/**
	 * 客户端的启动方法
	 */
	public void start(){
		try {
			//用于获取用户输入
			Scanner scanner = new Scanner(System.in);		
			/*
			 * Socket提供方法:
			 * OutputStream getOutputStream()
			 * 该方法可以获取一个字节输出流,通过
			 * 该流写出的数据都会发送给远端计算机,
			 * 对于客户端而言,远端就是服务端.
			 */
			OutputStream out
				= socket.getOutputStream();
			
			OutputStreamWriter osw
				= new OutputStreamWriter(out,"UTF-8");
			
			PrintWriter pw
				= new PrintWriter(osw,true);
			
			//启动读取服务端发送过来消息的线程
			ServerHandler handler 
				= new ServerHandler();
			Thread t = new Thread(handler);
			t.start();
			
			System.out.println("开始聊天吧!");
			String message = null;
			while(true){
				message = scanner.nextLine();
				pw.println(message);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		try {
			Client client = new Client();
			client.start();						
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 该线程专门用来接收服务端发送过来的
	 * 消息并输出到控制台
	 * @author adminitartor
	 *
	 */
	private class ServerHandler 
					implements Runnable{
		public void run(){
			try {
				InputStream in 
					= socket.getInputStream();
				InputStreamReader isr
					= new InputStreamReader(in,"UTF-8");
				BufferedReader br
					= new BufferedReader(isr);
				String message = null;
				while((message=br.readLine())!=null){
					System.out.println(message);
				}
			} catch (Exception e) {
				
			}
		}
	}
}





