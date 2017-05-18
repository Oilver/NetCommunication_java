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
				//����������⣬Ϊʲô���˻�����֮���ȡ�����׳��쳣
				if(string.equals("qwertyuiop[]asd")){
					System.out.println(user.toString()+ "�����Ͽ�����");
					ServerFrame.jTextArea2.append(user.toString() + " �Ѿ��Ͽ�����...\r\n");
					ServerFrame.jTextArea2.selectAll();	
					try {
						ServerFrame.ClientClose(user.getid(),user.toString());   //�ͻ��˶Ͽ���Ĳ���
					} catch (IOException | InterruptedException e) {
					}
					Server.sockets.remove(user.getSocket());
				}
				else{
					ServerFrame.jTextArea2.append(string + "\r\n");
					ServerFrame.jTextArea2.selectAll();
				}
			}

		} catch (IOException e) // socket�Ͽ��쳣
		{	
			try {
				Server.sockets.remove(user.getSocket());
				ServerFrame.ClientClose(user.getid(),user.toString());   //�ͻ��˶Ͽ���Ĳ���
			} catch (IOException | InterruptedException e1) {
			}
			
			ServerFrame.jTextArea2.append(user.toString() + " �쳣�˳�...\r\n");
			ServerFrame.jTextArea2.selectAll();
		}
	}
}
