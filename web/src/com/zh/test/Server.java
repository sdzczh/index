package com.zh.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * 聊天室服务端
 * @author adminitartor
 *
 */
public class Server {
	/*
	 * java.net.ServerSocket
	 * 运行在服务端的ServerSocket主要职责:
	 * 1:向OS申请服务端口(客户端就是通过这个
	 *   端口与服务端建立连接的)
	 * 2:监听服务端口并建立与客户端的连接
	 * 
	 */
	private ServerSocket server;
	
	//存放所有客户端的输出流,用于广播消息
	private List<PrintWriter> allOut;
	/**
	 * 服务端构造方法,用来初始化服务端
	 * @throws Exception 
	 */
	public Server() throws Exception{
		try {
			/*
			 * 初始化ServerSocket的同时向
			 * 系统申请服务端口,若该端口已经
			 * 被其他程序占用则会抛出异常
			 */
			server = new ServerSocket(8080);
			
			allOut = new ArrayList<PrintWriter>();
			
		} catch (Exception e) {
			//将来在这里可以记录日志
			
			//异常若不应当在这里处理,继续抛出
			throw e;
		}
	}
	/**
	 * 服务端开始工作的方法
	 */
	public void start(){
		try {
			/*
			 * ServerSocket提供方法:
			 * Socket accept()
			 * 该方法是一个阻塞方法,作用是
			 * 监听服务端口,等待客户端的连接
			 * 一旦一个客户端请求连接,那么
			 * accept方法会解除阻塞,并返回一个
			 * Socket实例,服务端就可以通过这个
			 * Socket实例与该客户端交互.
			 * 
			 */
			//循环接受所有客户端的连接
			while(true){
				
				System.out.println("等待一个客户端连接...");
				Socket socket = server.accept();
				System.out.println("一个客户端连接了!");
				
				//启动一个线程来负责与该客户端交互
				ClientHandler handler 
					= new ClientHandler(socket);
				Thread t = new Thread(handler);
				t.start();
			}
			
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		try {
			Server server = new Server();
			server.start();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 该线程任务负责与指定客户端交互工作
	 * @author adminitartor
	 *
	 */
	private class ClientHandler 
					implements Runnable{
		/*
		 * 当前线程就是通过这个Socket与
		 * 指定客户端交互的.
		 */
		private Socket socket;
		
		//当前客户端的地址信息
		private String host;
		
		public ClientHandler(Socket socket){
			this.socket = socket;
			//获取该客户端地址信息(远端计算机地址信息)
			InetAddress address 
				= socket.getInetAddress();
			host = address.getHostAddress();
		}
		
		public void run(){
			PrintWriter pw = null;
			try {		
				/*
				 * Socket提供方法:
				 * InputStream getInputStream()
				 * 通过这个流读取到的数据就是远端
				 * 发送过来的数据.
				 */
				InputStream in
					= socket.getInputStream();
				
				InputStreamReader isr
					= new InputStreamReader(in,"UTF-8");
				
				BufferedReader br
					= new BufferedReader(isr);
				
				OutputStream out 
					= socket.getOutputStream();
				
				OutputStreamWriter osw
					= new OutputStreamWriter(out,"UTF-8");
				
				pw = new PrintWriter(osw,true);
				
				
				//将该客户端的输出流存入共享集合
				synchronized (allOut) {
					allOut.add(pw);
				}
				
				//广播当前客户端上线
				sendMessage(host+"上线了!");
				
				String message = null;
				while((message = br.readLine())!=null){
//					System.out.println("客户端说:"+message);
//					//发送回给客户端
//					pw.println("客户端说:"+message);
					
					sendMessage(host+"说:"+message);
				}
				
			} catch (Exception e) {
				
			} finally{
				//处理客户端断开连接后的操作
				
				//将该客户端的输出流从共享集合中删除
				synchronized (allOut) {
					allOut.remove(pw);
				}
				
				sendMessage(host+"下线了!");
				try {
					/*
					 * 网络通讯完毕后关闭socket
					 */
					socket.close();
				} catch (IOException e) {
				}
			}
		}
		/**
		 * 将消息广播给所有客户端
		 * @param message
		 */
		private void sendMessage(String message){
			synchronized (allOut) {
				for(PrintWriter o : allOut){
					o.println(message);
				}
			}
		}
		
	}
}







