package Server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class ServerThread extends Thread {

	private int count = 0;
	private User user;
	public static Thread thread;

	public ServerThread(User user) {
		this.user = user;
	}

	public void run() {
		InputStream is = null;
		InputStreamReader isr = null;
		BufferedReader br = null;
		OutputStream os = null;
		OutputStreamWriter osr = null;
		BufferedWriter bw = null;

		try {
			is = user.getSocket().getInputStream();
			br = new BufferedReader(new InputStreamReader(is));
			os = user.getSocket().getOutputStream();
			bw = new BufferedWriter(new OutputStreamWriter(os));
//			thread = new Thread(new isCon(user));
//			thread.start();

			for (String string = br.readLine(); string != null; string = br.readLine()) {
				//问题出现在这，为什么用了花生壳之后读取流会抛出异常
				if(string.equals("qwertyuiop[]asd")){
					System.out.println(user.toString()+ "主动断开连接");
					ServerFrame.jTextArea2.append(user.toString() + " 已经断开连接...\r\n");
					ServerFrame.jTextArea2.selectAll();	
					try {
						ServerFrame.ClientClose(user.getid(),user.toString());   //客户端断开后的操作
					} catch (IOException | InterruptedException e) {
					}
					Server.sockets.remove(user.getSocket());
				}
				else{
					ServerFrame.jTextArea2.append(string + "\r\n");
					ServerFrame.jTextArea2.selectAll();
				}
			}

		} catch (IOException e) // socket断开异常
		{	
			try {
				Server.sockets.remove(user.getSocket());
				ServerFrame.ClientClose(user.getid(),user.toString());   //客户端断开后的操作
			} catch (IOException | InterruptedException e1) {
			}
			
			ServerFrame.jTextArea2.append(user.toString() + " 异常退出...\r\n");
			ServerFrame.jTextArea2.selectAll();
		}
	}
}
