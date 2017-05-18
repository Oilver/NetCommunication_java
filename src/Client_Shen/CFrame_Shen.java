package Client_Shen;

import java.awt.AWTException;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import Client_Shen.C_Shen;
import Server.FileSend;
import Server.User2;

public class CFrame_Shen extends javax.swing.JFrame {

	public static boolean UDPExit = false;
	public static boolean isOnline = false;
	public static boolean ClientExit = false;
	public static boolean flag = true;
	public static boolean sendPoint = true;
	private C_Shen client = null;
	public static Vector<User2> vt = new Vector<>();
	public static Vector vt1 = new Vector();
	public static SystemTray sysTray;
	public static TrayIcon trayIcon;
	public static ImageIcon icon = null;
	public static Image image = null;
	// Variables declaration - do not modify
	public static javax.swing.JList<String> jList1;
	private javax.swing.JButton jButton1;
	private javax.swing.JButton jButton2;
	private javax.swing.JButton jButton3;
	private javax.swing.JButton jButton4;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JPanel jPanel1;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JScrollPane jScrollPane2;
	private javax.swing.JScrollPane jScrollPane3;
	private javax.swing.JTextArea jTextArea1;
	public static javax.swing.JTextArea jTextArea2;
	private javax.swing.JRadioButton jRadioButton1;
	private javax.swing.JFileChooser jFileChooser;
	private boolean Morechooes = false;
	public static int index = 0;
	public static boolean flag2 = false; //
	public static int count = 0;
	public static int file_port_count = 0;

	public CFrame_Shen(C_Shen client) {
		this.client = client;

		jPanel1 = new javax.swing.JPanel();
		jScrollPane1 = new javax.swing.JScrollPane();
		jTextArea1 = new javax.swing.JTextArea();
		jScrollPane2 = new javax.swing.JScrollPane();
		jTextArea2 = new javax.swing.JTextArea();
		jScrollPane3 = new javax.swing.JScrollPane();
		jButton1 = new javax.swing.JButton();
		jButton2 = new javax.swing.JButton();
		jButton3 = new javax.swing.JButton();
		jButton4 = new javax.swing.JButton();
		jLabel1 = new javax.swing.JLabel();
		jList1 = new javax.swing.JList<>();
		jList1.setFixedCellHeight(31);
		jRadioButton1 = new javax.swing.JRadioButton();
		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

		jTextArea1.setColumns(20);
		jTextArea1.setRows(2);
		jScrollPane1.setViewportView(jTextArea1);
		jTextArea2.setColumns(20);
		jTextArea2.setRows(5);
		jScrollPane2.setViewportView(jTextArea2);
		jTextArea2.setEditable(false);
		jTextArea1.setLineWrap(true);
		jTextArea1.setWrapStyleWord(true);
		jTextArea2.setLineWrap(true);
		jTextArea2.setWrapStyleWord(true);

		jButton2.setText("��¼(E)");

		jButton2.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				try {
					jButton2ActionPerformed(evt);
				} catch (InterruptedException e) {

				}
			}
		});
		jButton3.setText("�˳�(Q)");
		jButton3.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				try {
					try {
						jButton3ActionPerformed(evt);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		jRadioButton1.setText("Ⱥ��(t)");
		jScrollPane3.setViewportView(jList1);		
		jRadioButton1.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jRadioButton1ActionPerformed(evt);
			}
		});

		jFileChooser = new javax.swing.JFileChooser();

		jButton4 = new javax.swing.JButton();
		jButton4.setText("�����ļ�");
		jButton4.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				try {
					jButton4ActionPerformed(evt);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
		jPanel1.setLayout(jPanel1Layout);
		jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
						jPanel1Layout.createSequentialGroup().addContainerGap()
								.addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
										.addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 144,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGroup(jPanel1Layout.createSequentialGroup().addComponent(jButton3)
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
												.addComponent(jButton2))
										.addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING,
												javax.swing.GroupLayout.PREFERRED_SIZE, 123,
												javax.swing.GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
										javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addGroup(jPanel1Layout
										.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
										.addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 351,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 351,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGroup(jPanel1Layout.createSequentialGroup().addGap(1, 1, 1)
												.addComponent(jButton4)
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
														javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(jRadioButton1)
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
												.addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 83,
														javax.swing.GroupLayout.PREFERRED_SIZE)))
								.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jPanel1Layout.createSequentialGroup().addGroup(jPanel1Layout
						.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(jPanel1Layout.createSequentialGroup()
								.addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(jPanel1Layout
										.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
										.addGroup(jPanel1Layout.createSequentialGroup()
												.addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 227,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
												.addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 75,
														javax.swing.GroupLayout.PREFERRED_SIZE))
										.addComponent(jScrollPane3)))
						.addGroup(jPanel1Layout.createSequentialGroup().addGap(350, 350, 350)
								.addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
										.addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 23,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(jButton3).addComponent(jButton1)
										.addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 23,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(jRadioButton1))))
						.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

		jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] { jButton2, jButton3 });

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(
				jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 524, javax.swing.GroupLayout.PREFERRED_SIZE));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(
				jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 374, javax.swing.GroupLayout.PREFERRED_SIZE));

		MouseListener Danxuan = new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 1) {
					if (!Morechooes) { // ����״̬
						jList1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
						Shen_ReadClient.isNewMessage = false;//�����������״̬//�����������״̬
						if (jList1.isSelectionEmpty()) {
							System.out.println("û�пͻ���");
							return;
						} else {
							index = jList1.locationToIndex(e.getPoint());
							jTextArea2.setText(vt.get(index).getstring());
							flag = true;
							System.out.println(vt.get(index).toString() + " ��ѡ��");
							// index��ѡ��Ŀͻ��ı�־
						}
					}
				}
			}
		};
		jList1.addMouseListener(Danxuan);

		MouseListener Duoxuan = new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 1) {
					if (Morechooes) { // Ⱥ��״̬

						jList1.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
						if (jList1.isSelectionEmpty()) {
							jList1.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
							System.out.println("û�пͻ���");
							return;
						}
						int index = jList1.locationToIndex(e.getPoint());
						jTextArea2.setText(vt.get(index).getstring());
					}
				}
			}
		};
		jList1.addMouseListener(Duoxuan);

		jButton1.setText("����");
		jButton1.addActionListener((new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				try {
					if (!isOnline) {
						JOptionPane.showMessageDialog(null, "Error�����ȵ�¼", "WARNING", JOptionPane.WARNING_MESSAGE);
						return;
					} else if (jList1.isSelectionEmpty()) {
						JOptionPane.showMessageDialog(null, "Error��û��ѡ��Ҫ���͵Ŀͻ�", "WARNING", JOptionPane.WARNING_MESSAGE);
						return;
					} else if (!Morechooes)
						Danfa(index, jTextArea1.getText());
					else
						Qunfa();
				} catch (IOException e) {

				}
			}
		}));
		jButton1.setMnemonic(java.awt.event.KeyEvent.VK_ENTER);
		jButton2.setMnemonic(java.awt.event.KeyEvent.VK_E);
		jButton3.setMnemonic(java.awt.event.KeyEvent.VK_Q);
		jRadioButton1.setMnemonic(java.awt.event.KeyEvent.VK_T);
		pack();
	}

	public void Qunfa() throws IOException {
		String send = jTextArea1.getText();
		String sendbyMe = "\t\t\t  Me: " + send + "\r\n";
		jTextArea2.append(sendbyMe);
		jTextArea2.selectAll();
		int[] indexs = jList1.getSelectedIndices();
		for (int i = 0; i < indexs.length; i++) {
			vt.get(indexs[i]).setstring(sendbyMe);
			InetAddress address = InetAddress.getByName("localhost");
			int port = vt.get(indexs[i]).getUserport();
			String send2 = client.getUsername() + " : " + send;
			byte[] bufsend = send2.getBytes();
			DatagramPacket packet = new DatagramPacket(bufsend, 0, bufsend.length, address, port);
			DatagramSocket socket = new DatagramSocket(); // ��̬�˿ڽ�����Ϊ�˷����ݱ�
			socket.send(packet);
			socket.close();
		}
		jTextArea1.setText("");
	}

	public void Danfa(int i, String send) throws IOException { // UDPͨ��
		String sendbyMe = "\t\t\t  Me: " + send + "\r\n";
		vt.get(i).setstring(sendbyMe);
		jTextArea2.append(sendbyMe);
		jTextArea2.selectAll();
		InetAddress address = InetAddress.getByName("localhost");
		int port = vt.get(i).getUserport(); // ѡ�е��û���Ӧsocket�������
		send = client.getUsername() + " : " + send;
		byte[] bufsend = send.getBytes();
		DatagramPacket packet = new DatagramPacket(bufsend, 0, bufsend.length, address, port);
		DatagramSocket socket = new DatagramSocket(); // ��̬�˿ڽ�����Ϊ�˷����ݱ�
		socket.send(packet);
		socket.close();
		jTextArea1.setText("");
	}

	// ��¼��ť
	private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) throws InterruptedException {

		if (client.getSocket() == null && !flag) {
			JOptionPane.showMessageDialog(null, "Error����ǰ���ڳ������ӣ����Ե�...", "WARNING", JOptionPane.WARNING_MESSAGE);
			return;
		} else if (client.getSocket() == null && flag) { // ��¼��ť
			// ��һ�ζ�û���ϵ�ʱ��������
			jTextArea2.append("��������...���Ժ�...\r\n");
			flag = false;
			ClientExit = false;
			sendPoint = true;
			Thread connecting = new Thread(client); // ���ӵ��߳�
			connecting.start();
			Thread sendPoint = new SendPoint(); // �첽��ʾ*****���߳�
			sendPoint.start();

		} else if (!(!client.getSocket().isClosed() && client.getSocket().isConnected())) {
			// ���ϵ�һ�κ��ٴζϿ���������
			jTextArea2.append("��������...���Ժ�...\r\n");
			jTextArea2.selectAll();
			flag = false;
			ClientExit = false;
			sendPoint = true;
			Thread connecting = new Thread(client); // ���ӵ��߳�
			connecting.start();
			Thread sendPoint = new SendPoint(); // �첽��ʾ*****���߳�
			sendPoint.start();

		} else {
			JOptionPane.showMessageDialog(null, "Error�����Ѿ������ˣ�", "WARNING", JOptionPane.WARNING_MESSAGE);
			return;
		}
	}

	// �˳���ť
	private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) throws IOException, InterruptedException {
		if (UDPExit) {
			JOptionPane.showMessageDialog(null, "Error��(1)��������Ͽ�����\n      (2)�Ѿ��ͷ�UDP�����Ķ˿�", "WARNING",
					JOptionPane.WARNING_MESSAGE);
			return;
		}
		if (!client.isInterruptWithServer()) { // ���ж��Ƿ�������ȹر�
			if (client.getSocket() == null) {
				JOptionPane.showMessageDialog(null, "Error����ǰû�����ӷ�����", "WARNING", JOptionPane.WARNING_MESSAGE);
				return;
			} else if (!(!client.getSocket().isClosed() && client.getSocket().isConnected())) {
				JOptionPane.showMessageDialog(null, "Error�����Ѿ���������Ͽ�����", "WARNING", JOptionPane.WARNING_MESSAGE);
				return;
			} else {
				System.out.println("�û������˳�������...");
				jTextArea2.append("�û������˳�������...\r\n");
				jTextArea2.selectAll();
				ClientExit = true;
				BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(client.getSocket().getOutputStream()));
				bw.write("qwertyuiop[]asd\r\n");// ����ˢ���ź�
				bw.flush();

				try {
					Thread.sleep(350); // ���뻺�壡��Ȼ����������
					client.getSocket().close();
					Shen_ReadClient.socket.close();
					isOnline = false;

				} catch (IOException e) {
				}
			}
		} else {
			Shen_ReadClient.socket.close(); // �ͷ�UDP�Ķ˿�
			jTextArea2.append("�û��ͷ�UDPͨ�ŵĶ˿�...\r\n");
			isOnline = false;
			UDPExit = true;
			jTextArea2.selectAll();
		}
		Thread.sleep(50);
		Vector Empty = new Vector();
		Empty.removeAllElements();
		jList1.setListData(Empty); // �˳�����պ����б�

	}

	// �����ļ�
	private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) throws IOException, InterruptedException {
		if (!isOnline) {
			JOptionPane.showMessageDialog(null, "Error�����ȵ�¼", "WARNING", JOptionPane.WARNING_MESSAGE);
			return;
		} else if (jList1.isSelectionEmpty()) {
			JOptionPane.showMessageDialog(null, "Error��û��ѡ��Ҫ���͵Ŀͻ�", "WARNING", JOptionPane.WARNING_MESSAGE);
			return;
		} else if (Morechooes) {
			JOptionPane.showMessageDialog(null, "Error�������ļ�����Ⱥ��", "WARNING", JOptionPane.WARNING_MESSAGE);
			return;
		} else // ��ʽ�����ļ�
		{
			file_port_count++;
			Thread thread = new Thread(new SendFile(client, jFileChooser));
			thread.start();
		}

	}

	// �Ƿ�Ⱥ����ť
	private void jRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {
		if (jRadioButton1.isSelected()) {
			Morechooes = true;
			jTextArea2.append("�����ҪȺ���Ŀͻ�ѡ��...\r\n");
		} else
			Morechooes = false;
	}

	public static void main(String args[]) throws InterruptedException, AWTException {
		
			if (System.getProperty("os.name").toUpperCase().indexOf("WINDOWS") != -1)
	        {
	            try {
					UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
				} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
						| UnsupportedLookAndFeelException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        }
		
		C_Shen client = new C_Shen();
		CFrame_Shen clientFrame = new CFrame_Shen(client);
		clientFrame.setTitle("Client_Shen");
		clientFrame.setResizable(false);
		clientFrame.setLocation(350, 200);
		clientFrame.setVisible(true);
		image = clientFrame.getToolkit().getImage(clientFrame.getClass().getClassLoader().getResource("com/img/shen.gif"));
		clientFrame.setIconImage(image);		//ͼ��
		clientFrame.createTrayIcon(clientFrame);//��������
		sysTray.add(trayIcon);
		new Thread(new notify2()).start();	//���������Զ��������߳�
		while (true) {			//����û�к����б��ˢ��
			Thread.sleep(100); // �����̵߳�˯�߼���cpu�ĸ������������������ʱ��cpuռ30%
			if (client.getIsadd()) {
				jList1.setListData(vt1);
				client.setIsadd(false);
			}
		}
	}
	
	public void createTrayIcon(final CFrame_Shen clientFrame) {
		sysTray = SystemTray.getSystemTray();
		PopupMenu popupMenu = new PopupMenu();
		MenuItem mi = new MenuItem("��");
		MenuItem exit = new MenuItem("�˳�");
		popupMenu.add(mi);
		popupMenu.add(exit);
		mi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Shen_ReadClient.isNewMessage = false;//�����������״̬
				clientFrame.setExtendedState(JFrame.NORMAL);
				clientFrame.setVisible(true); // ��ʾ����
				clientFrame.toFront(); // ��ʾ��������ǰ��
			}
		});
		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		trayIcon = new TrayIcon(image, "��Ϣ����", popupMenu);
		trayIcon.setImageAutoSize(true);
		trayIcon.addMouseListener(new MouseAdapter() {	//��������ʱ
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 1) { 
					Shen_ReadClient.isNewMessage = false;//�����������״̬
					clientFrame.setExtendedState(JFrame.NORMAL);
					clientFrame.setVisible(true); 
					clientFrame.toFront();
				}
			}
		});
	}
}

class SendFile implements Runnable {

	private C_Shen client;
	private javax.swing.JFileChooser jFileChooser;

	public SendFile(C_Shen client, javax.swing.JFileChooser jFileChooser) {
		this.client = client;
		this.jFileChooser = jFileChooser;
	}

	@Override
	public void run() {
		//���ļ��Ĳ���
		int count = CFrame_Shen.file_port_count;
		User2 c = CFrame_Shen.vt.get(CFrame_Shen.index);
		jFileChooser.setFileSelectionMode(jFileChooser.FILES_ONLY);
		int returnVal2 = jFileChooser.showDialog(new JLabel(), "����");
		if (returnVal2 == jFileChooser.APPROVE_OPTION) {
			byte[] bufsend = ((client.getLocalPort() + count) + "," + "qazxswedcvfr,"
					+ client.getUsername() + "," + jFileChooser.getSelectedFile().getName()
					+ "," + (client.getLocalPort() - count)).getBytes();
			try {
				DatagramPacket packet = new DatagramPacket(bufsend, 0, bufsend.length,
						InetAddress.getByName("localhost"), c.getUserport());
				DatagramSocket socket = new DatagramSocket(); 
				socket.send(packet);
				socket.close();
			} catch (Exception e) {
			}
		} else {
			return; // ��ӵ��������ť����¼�
		}
		DatagramSocket socket2 = null;  //�Է��Ƿ�����ļ������ڷ�����Ϣ
		try {
			socket2 = new DatagramSocket(client.getLocalPort() - count);
		} catch (SocketException e) {
		}
		byte[] buf = new byte[2048];
		DatagramPacket packet2 = new DatagramPacket(buf, 0, buf.length);
		try {
			socket2.receive(packet2);
		} catch (IOException e) {
		}
		String info = new String(buf, 0, packet2.getLength());//������Ϣ
		if (info.contains("sureRec"))// ��ʼ����
		{
			String[] temp = info.split("c");
			int remoteport = Integer.parseInt(temp[1]);
			String path = jFileChooser.getSelectedFile().getAbsolutePath();
			FileSend send = new FileSend(remoteport, client.getLocalPort() + count, path,
					"localhost");// ��ӵ���򿪰�ť����¼�
			send.start();
			CFrame_Shen.jTextArea2.append("�����û� " + c.toString() +"�����ļ���" + jFileChooser.getSelectedFile().getName()+"\r\n");
		}
		socket2.close();
	}

}

class SendPoint extends Thread { // �첽_��¼��ʾ��
	public void run() {
		while (CFrame_Shen.sendPoint)// ��������ɾ�ֹͣ���
		{
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {

			}
			CFrame_Shen.jTextArea2.append("��");
			CFrame_Shen.jTextArea2.selectAll();
		}
		CFrame_Shen.jTextArea2.append("\r\n");
	}
}


class notify2 implements Runnable {  //��������

	@Override
	public void run() {

		while (true) {
			if (Shen_ReadClient.isNewMessage) {
				try {
					CFrame_Shen.trayIcon.setImage(new ImageIcon("").getImage());
					Thread.sleep(400);
					CFrame_Shen.trayIcon.setImage(CFrame_Shen.image);
					Thread.sleep(400);
				} catch (Exception e) {
				}
			} else
				CFrame_Shen.trayIcon.setImage(CFrame_Shen.image);
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}