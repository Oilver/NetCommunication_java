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

	public void run() { // �ֶ���¼��������һ���������ӣ����ƣ�
		try {
			Thread.sleep(2000);

			InetAddress inetAddress = InetAddress.getByName(ip);
			socket = new Socket(inetAddress, port);
			CFrame_Oliver.sendPoint = false; // ��������

			// if (!socket.getOOBInline())
			// socket.setOOBInline(true); // Ϊ���÷������ж��Ƿ񱾵ط����Ͽ�

			if (socket.isConnected() && !socket.isClosed()) {
				Thread.sleep(1000);
				flagString = "******************�ɹ����ӷ�����******************";
				CFrame_Oliver.jTextArea2.append(flagString + "\r\n");
				CFrame_Oliver.jTextArea2.selectAll();
				CFrame_Oliver.isOnline = true; // �ж��Ƿ�����
				CFrame_Oliver.UDPExit = false; // �ж�UDP�Ƿ����
				setInterruptWithServer(false); // �ж��Ƿ�������Ͽ�����
				os = socket.getOutputStream();
				bw = new BufferedWriter(new OutputStreamWriter(os));
				bw.write(username + "," + localPort + "\n");
				bw.flush(); // �����û��� �ͱ��ؼ����Ķ˿�

				input = socket.getInputStream();
				in = new BufferedReader(new InputStreamReader(input));
				// ���շ�������Ϣ���߳�
				Thread thread = new Thread(new Oliver_Read(this));
				thread.start();
				//���տͻ��˷�����Ϣ���߳�
				Thread thread2 = new Thread(new Oliver_ReadClient());
				thread2.start();

			} else {
				flagString = "����ʧ��...";
			}
		} catch (UnknownHostException e) {
			flagString = "����ʧ��...";

		} catch (IOException e) {

			CFrame_Oliver.sendPoint = false; // ��������
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e1) {
			}
			CFrame_Oliver.jTextArea2.append("Error�����ӳ�ʱ......\r\n");
			CFrame_Oliver.jTextArea2.selectAll();
			System.out.println("���ӳ�ʱ");
			CFrame_Oliver.flag = true; // flag���������ܷ���¼��ť

		} catch (InterruptedException e) {

		}
	}

	public void write(String send) {
		try {
			if (socket.isConnected() && !socket.isClosed()) { // �ж��Ƿ����ӳɹ����Ƿ��ͳɹ�
				bw.write(send);
				bw.flush();
				setsendOK(true);
				System.out.println("���ͳɹ�");
			}
		} catch (IOException e) {
			System.out.println("����ʧ��");
			CFrame_Oliver.jTextArea2.append("�Ѿ���ͻ��˶Ͽ����ӣ�����ʧ��...\r\n");
			CFrame_Oliver.jTextArea2.selectAll();
		}
	}

	public void read() throws IOException, InterruptedException {
		try {

			for (String string = in.readLine(); string != null; string = in.readLine()) {
				if (string.equals("qwertyuiop[]")) {
					System.out.println("ˢ�º���");
					String ClientUsername = in.readLine();// ��ȡ���ߵĺ��ѵ�����
					if (!ClientUsername.equals(username)) {
						CFrame_Oliver.jTextArea2.append("���ĺ��� " + ClientUsername + " �����ߣ�\r\n");
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
					System.out.println("ˢ�º���");
					String ClientUsername = in.readLine();
					if (!ClientUsername.equals(username)) {
						CFrame_Oliver.jTextArea2.append("���ĺ��� " + ClientUsername + " �����ߣ�\r\n");
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
					CFrame_Oliver.jTextArea2.append(string + "\r\n"); // �յ���Ϣ
					CFrame_Oliver.jTextArea2.selectAll();// ע�⣡
				}
			}
		} catch (IOException e) {
			if (!CFrame_Oliver.ClientExit) {
				CFrame_Oliver.jTextArea2.append("��ʾ��1�������������жϡ�����Ӱ�������ͨ��\r\n");
				CFrame_Oliver.jTextArea2.append("��ʾ��2�����������ʧȥ���ӣ�ʧȥ����������ʾ����\r\n");
				CFrame_Oliver.jTextArea2.append("��ʾ��3�������µ�¼�������Ϸ���������ˢ�º����б�\r\n");
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
			System.out.println("����1");
		} catch (InterruptedException e) {
			System.out.println("����2");
		}
	}
}

class Oliver_ReadClient implements Runnable { // UDPͨ��

	public static DatagramSocket socket = null;
	public static int recFile_port_count = 0;
	public static boolean isNewMessage = false;

	@Override
	public void run() {

		try {
			socket = new DatagramSocket(7773); // ��������˿�
		} catch (SocketException e1) {
			System.out.println("����11");
		}

		while (true) {
			if (!socket.isClosed()) {
				byte[] buf = new byte[2048];
				DatagramPacket packet = new DatagramPacket(buf, 0, buf.length);
				try {
					socket.receive(packet);
					String info = new String(buf, 0, packet.getLength());
					isNewMessage = true;
					if (info.contains("qazxswedcvfr"))// �յ�Ҫ�����ļ�����Ϣ
					{
						try {
							AudioClip p = Applet.newAudioClip(new URL("file:sound/file.wav"));
							p.play();// ������Ƶ
						} catch (MalformedURLException e) {
							e.printStackTrace();
						}
						Thread thread = new Thread(new recFile(info));//�����ļ����߳�
						thread.start();
						continue;
					}

					try {
						AudioClip p = Applet.newAudioClip(new URL("file:sound/chat.wav"));
						p.play();// ������Ƶ
					} catch (MalformedURLException e) {
						e.printStackTrace();
					}
					// ��������ļ����Ǿ��ǽ����ı���Ϣ:

					int temp = info.indexOf(':');
					String temp2 = info.substring(0, temp - 1); // temp2���û���
					int temp3 = CFrame_Oliver.vt1.indexOf(temp2);

					Thread notifyThread = new Thread(new notify(temp3));
					notifyThread.start();
					CFrame_Oliver.jTextArea2.append("��ʾ�� " + temp2 + "��������һ����Ϣ����\r\n");
					CFrame_Oliver.vt.get(temp3).setstring(info + "\r\n");
					CFrame_Oliver.jTextArea2.selectAll();
				} catch (IOException e) {

				}
			} else {
				System.out.println("socket�ѹرգ����ҹر��˶˿�");
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
		// ����ļ��в������򴴽�
		if (!file.exists() && !file.isDirectory()) {
			file.mkdir();
		}
		String[] temp1 = info.split(",");
		int remotePort = Integer.parseInt(temp1[0]);
		String username = temp1[2];
		String filename = temp1[3];
		int result = JOptionPane.showConfirmDialog(null, "�û� " + username + " ���㷢���ļ��� " + filename + "  \r\n�Ƿ���գ�");
		if (result == 0) {// ȷ�Ͻ����ļ�
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
			CFrame_Oliver.jTextArea2.append("�ļ� \"" + filename + "\" ���ճɹ�����ʾ���洢��D��\r\n");
		} else {
			byte[] bufsend = "noRec".getBytes();// ������
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
		for(int i = 0;i<5;i++){  //�������
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