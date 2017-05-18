package Client_Oliver;

import java.applet.Applet;
import java.applet.AudioClip;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.SocketException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import com.sun.org.glassfish.external.statistics.impl.StatisticImpl;

import Server.FileRec;
import Server.User;
import Server.User2;

public class C_Oliver implements Runnable {

	private String ip = "";
	private int port = 0;
	private String flagString;
	private OutputStream os;
	private BufferedWriter bw;
	private InputStream input;
	private BufferedReader in;
	private Socket socket = null;
	private boolean sendOK = false;
	private String username;
	private int localPort = 0;
	private boolean isadd = false;
	private boolean interruptWithServer = false;

	public C_Oliver() {
		ip = "localhost";
		port = 8888;
		username = "Oliver";
		localPort = 7773;
	}

	public void run() { // 手动登录，后面有一个重新连接（类似）
		try {
			Thread.sleep(2000);

			InetAddress inetAddress = InetAddress.getByName(ip);
			socket = new Socket(inetAddress, port);
			CFrame_Oliver.sendPoint = false; // 不发点了

			// if (!socket.getOOBInline())
			// socket.setOOBInline(true); // 为了让服务器判断是否本地发生断开

			if (socket.isConnected() && !socket.isClosed()) {
				Thread.sleep(1000);
				flagString = "******************成功连接服务器******************";
				CFrame_Oliver.jTextArea2.append(flagString + "\r\n");
				CFrame_Oliver.jTextArea2.selectAll();
				CFrame_Oliver.isOnline = true; // 判断是否在线
				CFrame_Oliver.UDPExit = false; // 判断UDP是否监听
				setInterruptWithServer(false); // 判断是否服务器断开连接
				os = socket.getOutputStream();
				bw = new BufferedWriter(new OutputStreamWriter(os));
				bw.write(username + "," + localPort + "\n");
				bw.flush(); // 发送用户名 和本地监听的端口

				input = socket.getInputStream();
				in = new BufferedReader(new InputStreamReader(input));
				// 接收服务器信息的线程
				Thread thread = new Thread(new Oliver_Read(this));
				thread.start();
				//接收客户端发送信息的线程
				Thread thread2 = new Thread(new Oliver_ReadClient());
				thread2.start();

			} else {
				flagString = "连接失败...";
			}
		} catch (UnknownHostException e) {
			flagString = "连接失败...";

		} catch (IOException e) {

			CFrame_Oliver.sendPoint = false; // 不发点了
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e1) {
			}
			CFrame_Oliver.jTextArea2.append("Error：连接超时......\r\n");
			CFrame_Oliver.jTextArea2.selectAll();
			System.out.println("连接超时");
			CFrame_Oliver.flag = true; // flag用来控制能否点登录按钮

		} catch (InterruptedException e) {

		}
	}

	public void write(String send) {
		try {
			if (socket.isConnected() && !socket.isClosed()) { // 判断是否连接成功，是否传送成功
				bw.write(send);
				bw.flush();
				setsendOK(true);
				System.out.println("发送成功");
			}
		} catch (IOException e) {
			System.out.println("发送失败");
			CFrame_Oliver.jTextArea2.append("已经与客户端断开连接，发送失败...\r\n");
			CFrame_Oliver.jTextArea2.selectAll();
		}
	}

	public void read() throws IOException, InterruptedException {
		try {

			for (String string = in.readLine(); string != null; string = in.readLine()) {
				if (string.equals("qwertyuiop[]")) {
					System.out.println("刷新好友");
					String ClientUsername = in.readLine();// 读取上线的好友的名字
					if (!ClientUsername.equals(username)) {
						CFrame_Oliver.jTextArea2.append("您的好友 " + ClientUsername + " 已上线！\r\n");
					}
					ObjectInputStream oi = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
					try {
						Object obj = oi.readObject();
						Vector<User2> vt = (Vector<User2>) obj;
						Object obj2 = oi.readObject();
						Vector vt1 = (Vector) obj2;
						int RepeatIndex = vt1.indexOf(username);
						vt.remove(RepeatIndex);
						vt1.remove(RepeatIndex);
						CFrame_Oliver.vt = vt;
						CFrame_Oliver.vt1 = vt1;
						setIsadd(true);

					} catch (ClassNotFoundException e) {
					}
				} else if (string.equals("qwertyuiop[]clientexit")) {
					System.out.println("刷新好友");
					String ClientUsername = in.readLine();
					if (!ClientUsername.equals(username)) {
						CFrame_Oliver.jTextArea2.append("您的好友 " + ClientUsername + " 已下线！\r\n");
					}

					ObjectInputStream oi = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
					try {
						Object obj = oi.readObject();
						Vector<User2> vt = (Vector<User2>) obj;
						Object obj2 = oi.readObject();
						Vector vt1 = (Vector) obj2;
						int RepeatIndex = vt1.indexOf(username);
						try {
							vt.remove(RepeatIndex);
							vt1.remove(RepeatIndex);
						} catch (Exception e) {
						}
						CFrame_Oliver.vt = vt;
						CFrame_Oliver.vt1 = vt1;
						setIsadd(true);

					} catch (ClassNotFoundException e) {
					}
				} else {
					CFrame_Oliver.jTextArea2.append(string + "\r\n"); // 收到信息
					CFrame_Oliver.jTextArea2.selectAll();// 注意！
				}
			}
		} catch (IOException e) {
			if (!CFrame_Oliver.ClientExit) {
				CFrame_Oliver.jTextArea2.append("提示（1）：服务器已中断……不影响与好友通信\r\n");
				CFrame_Oliver.jTextArea2.append("提示（2）：与服务器失去连接，失去好友下线提示功能\r\n");
				CFrame_Oliver.jTextArea2.append("提示（3）：重新登录并且连上服务器才能刷新好友列表\r\n");
				CFrame_Oliver.jTextArea2.selectAll();
				setInterruptWithServer(true);
				socket.close();
			}
		}
	}

	public int getLocalPort() {
		return localPort;
	}

	public void setLocalPort(int localPort) {
		this.localPort = localPort;
	}

	public Socket getSocket() {
		return socket;
	}

	public void setSocket(Socket socket) {
		this.socket = socket;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getflagString() {
		return flagString;
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

	public boolean getsendOK() {
		return sendOK;
	}

	public void setsendOK(boolean sendOK) {
		this.sendOK = sendOK;
	}

	public String getFlagString() {
		return flagString;
	}

	public void setFlagString(String flagString) {
		this.flagString = flagString;
	}

	public boolean getIsadd() {
		return isadd;
	}

	public void setIsadd(boolean isadd) {
		this.isadd = isadd;
	}

	public boolean isInterruptWithServer() {
		return interruptWithServer;
	}

	public void setInterruptWithServer(boolean interruptWithServer) {
		this.interruptWithServer = interruptWithServer;
	}

}

class Oliver_Read implements Runnable {
	private C_Oliver client;

	public Oliver_Read(C_Oliver client) {
		this.client = client;
	}

	@Override
	public void run() {
		try {
			client.read();
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("错误1");
		} catch (InterruptedException e) {
			System.out.println("错误2");
		}
	}
}

class Oliver_ReadClient implements Runnable { // UDP通信

	public static DatagramSocket socket = null;
	public static int recFile_port_count = 0;
	public static boolean isNewMessage = false;

	@Override
	public void run() {

		try {
			socket = new DatagramSocket(7773); // 监听这个端口
		} catch (SocketException e1) {
			System.out.println("错误11");
		}

		while (true) {
			if (!socket.isClosed()) {
				byte[] buf = new byte[2048];
				DatagramPacket packet = new DatagramPacket(buf, 0, buf.length);
				try {
					socket.receive(packet);
					String info = new String(buf, 0, packet.getLength());
					isNewMessage = true;
					if (info.contains("qazxswedcvfr"))// 收到要接受文件的信息
					{
						try {
							AudioClip p = Applet.newAudioClip(new URL("file:sound/file.wav"));
							p.play();// 播放音频
						} catch (MalformedURLException e) {
							e.printStackTrace();
						}
						Thread thread = new Thread(new recFile(info));//接收文件的线程
						thread.start();
						continue;
					}

					try {
						AudioClip p = Applet.newAudioClip(new URL("file:sound/chat.wav"));
						p.play();// 播放音频
					} catch (MalformedURLException e) {
						e.printStackTrace();
					}
					// 如果不是文件，那就是接收文本信息:

					int temp = info.indexOf(':');
					String temp2 = info.substring(0, temp - 1); // temp2是用户名
					int temp3 = CFrame_Oliver.vt1.indexOf(temp2);

					Thread notifyThread = new Thread(new notify(temp3));
					notifyThread.start();
					CFrame_Oliver.jTextArea2.append("提示： " + temp2 + "向您发了一条消息……\r\n");
					CFrame_Oliver.vt.get(temp3).setstring(info + "\r\n");
					CFrame_Oliver.jTextArea2.selectAll();
				} catch (IOException e) {

				}
			} else {
				System.out.println("socket已关闭，并且关闭了端口");
				break;
			}
		}
	}
}

class recFile implements Runnable {

	private String info;

	public recFile(String info) {
		this.info = info;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		String dirname = "D:\\Oliver_file";
		File file = new File(dirname);
		// 如果文件夹不存在则创建
		if (!file.exists() && !file.isDirectory()) {
			file.mkdir();
		}
		String[] temp1 = info.split(",");
		int remotePort = Integer.parseInt(temp1[0]);
		String username = temp1[2];
		String filename = temp1[3];
		int result = JOptionPane.showConfirmDialog(null, "用户 " + username + " 向你发送文件： " + filename + "  \r\n是否接收？");
		if (result == 0) {// 确认接收文件
			int count = ++Oliver_ReadClient.recFile_port_count;
			byte[] bufsend = ("sureRec" + (7773 + count)).getBytes();
			DatagramPacket packet2 = null;
			try {
				packet2 = new DatagramPacket(bufsend, 0, bufsend.length, InetAddress.getByName("localhost"),
						Integer.parseInt(temp1[4]));
				DatagramSocket socket2 = null;
				socket2 = new DatagramSocket();
				socket2.send(packet2);
				socket2.close();
			} catch (Exception e) {
			}
			FileRec server = new FileRec("localhost", 7773 + count, remotePort, dirname + "\\" + filename);
			server.start();
			CFrame_Oliver.jTextArea2.append("文件 \"" + filename + "\" 接收成功！提示：存储在D盘\r\n");
		} else {
			byte[] bufsend = "noRec".getBytes();// 不接受
			DatagramPacket packet3 = null;
			try {
				packet3 = new DatagramPacket(bufsend, 0, bufsend.length, InetAddress.getByName("localhost"),
						remotePort + 1);
				DatagramSocket socket3 = null;
				socket3 = new DatagramSocket();
				socket3.send(packet3);
				socket3.close();
			} catch (Exception e) {
			}
		}
	}

}

class notify implements Runnable {
	private int index;

	public notify(int index) {
		this.index = index;
	}

	@Override
	public void run() {
		for(int i = 0;i<5;i++){  //闪动五次
			CFrame_Oliver.jList1.setSelectedIndex(index);
			try {
				Thread.sleep(90);
			} catch (InterruptedException e) {
			}
			CFrame_Oliver.jList1.clearSelection();
			try {
				Thread.sleep(90);
			} catch (InterruptedException e) {
			}
		}
			
		if (CFrame_Oliver.index == 0) {
			CFrame_Oliver.jList1.clearSelection();
		} else {
			CFrame_Oliver.jList1.setSelectedIndex(CFrame_Oliver.index);
		}
	}
}