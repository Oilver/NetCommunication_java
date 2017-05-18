package Server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Vector;

public class Server implements Runnable{
	
	private String ip = "";
	private  int port = 0;
	private String welcome;
	private boolean isadd = false;
	private String username = "";
	private int userport = 0;
	private int UserID;
	private User user;
	private Socket socket = null;
	public static ArrayList<Socket> sockets =new  ArrayList<Socket>();
	private ServerSocket serverSocket = null;
	public Server(){
		ip = "localhost";
		port = 8888;	
		welcome = "*************服务器即将启动，等待客户端的连接*************\r\n\r\n";
	}
	public void run(){			
		try {
			//byte[] addr = {(byte)192,(byte) 168,1,100};   //IP
			//InetAddress inetAddress = InetAddress.getByAddress(addr);
			InetAddress inetAddress = InetAddress.getByName(ip);
			serverSocket = new ServerSocket(port);
		//	serverSocket = new ServerSocket(port,5,inetAddress);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

//		try {
//			socket = serverSocket.accept();
//			ServerFrame.jTextArea2.append("花生壳连接成功1");
//		} catch (IOException e1) {
//			System.out.println("花生壳无法连接");
//		}
//		try {
//			socket = serverSocket.accept();
//			ServerFrame.jTextArea2.append("花生壳连接成功2\r\n");
//		} catch (IOException e1) {
//			System.out.println("花生壳2无法连接");
//		}
	
		while(true){
			try {
				socket = serverSocket.accept();

				InputStream input = socket.getInputStream();

				BufferedReader br = new BufferedReader(new InputStreamReader(input));

				String[] userMessage = br.readLine().split(",");
				username = userMessage[0];
				userport = Integer.parseInt(userMessage[1]);
				System.out.println(username + " 已经上线,用户的监听的端口号是： " + userport);  //username
				ServerFrame.jTextArea2.append(username + " 已上线..." + username+" 监听的端口号是：" + userport + "\r\n");
				ServerFrame.jTextArea2.selectAll();
				ServerFrame.count++;

				user = new User(username,userport,ServerFrame.count,socket);			
				ServerFrame.vt.add(user);
				ServerFrame.vt1.add(user.toString());
				
				User2 user2 = new User2(username,userport,ServerFrame.count);
				ServerFrame.vtt.add(user2);
				ServerFrame.vtt1.add(user2.toString());
				
				isadd = true;
				sockets.add(socket);
				for(int i = 0;i<sockets.size();i++)  //告知所有用户要刷新好友列表了
				{
					System.out.println("asdasd");
					BufferedWriter bw = new BufferedWriter(
							new OutputStreamWriter(sockets.get(i).getOutputStream()));
					bw.write("qwertyuiop[]\r\n");//  好友刷新信号	
					bw.write(username + "\r\n");
					bw.flush();				
					Thread.sleep(100);
					ObjectOutputStream op = new ObjectOutputStream(sockets.get(i).getOutputStream());
					op.writeObject(ServerFrame.vtt);
					op.writeObject(ServerFrame.vtt1);				
					op.flush();
				}
				
			} catch (IOException e) {

			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ServerThread thread = new ServerThread(user);
			thread.start();			
		}		
	}
	
	public String getwelcome(){
		return welcome;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	public boolean getIsadd() {
		return isadd;
	}
	public void setIsadd(boolean isadd) {
		this.isadd = isadd;
	}
	public ServerSocket getServerSocket() {
		return serverSocket;
	}
	public void setServerSocket(ServerSocket serverSocket) {
		this.serverSocket = serverSocket;
	}
	
	
}
