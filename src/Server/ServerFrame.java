package Server;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Vector;

import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

public class ServerFrame extends javax.swing.JFrame {

	public ServerFrame(Server server) {
		this.server = server;
		thread = new Thread(this.server);
		thread.start();
		port = server.getPort();
		ip = server.getIp();
	}

	public void go() {
		jPanel1 = new javax.swing.JPanel();
		jRadioButton1 = new javax.swing.JRadioButton();
		jScrollPane2 = new javax.swing.JScrollPane();
		jTextArea2 = new javax.swing.JTextArea();
		jScrollPane1 = new javax.swing.JScrollPane();
		jTextArea1 = new javax.swing.JTextArea();
		jButton1 = new javax.swing.JButton();
		jScrollPane3 = new javax.swing.JScrollPane();
		jList1 = new javax.swing.JList<>();
		jButton2 = new javax.swing.JButton();
		jLabel1 = new javax.swing.JLabel();
		jLabel2 = new javax.swing.JLabel();
		jLabel3 = new javax.swing.JLabel();
		jLabel3.setText(ip);
		jLabel4 = new javax.swing.JLabel();
		jLabel4.setText("" + port);
		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

		jTextArea2.setColumns(20);
		jTextArea2.setRows(5);

		jScrollPane2.setViewportView(jTextArea2);
		jScrollPane2.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		jTextArea2.append(server.getwelcome());
		jTextArea2.setEditable(false);
		jTextArea1.setColumns(20);
		jTextArea1.setRows(4);
		jTextArea1.setLineWrap(true);
		jTextArea1.setWrapStyleWord(true);
		jTextArea2.setLineWrap(true);
		jTextArea2.setWrapStyleWord(true);
		jScrollPane1.setViewportView(jTextArea1);
		jScrollPane1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		jButton1.setText("发送");
		jButton1.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				try {
					jButton1ActionPerformed(evt);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		jButton2.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				try {
					jButton2ActionPerformed(evt);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}); // 断开连接

		jScrollPane3.setViewportView(jList1);

		jButton2.setText("中断...");

		jLabel1.setText("IP:");

		jLabel2.setText("Port:");

		jRadioButton1.setText("群发");
		jRadioButton1.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jRadioButton1ActionPerformed(evt);
			}
		});

		javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
		jPanel1.setLayout(jPanel1Layout);
		jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jPanel1Layout.createSequentialGroup()
						.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING).addComponent(jLabel2,
										javax.swing.GroupLayout.Alignment.TRAILING))
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
						.addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(jLabel3).addComponent(jLabel4))
						.addGap(58, 58, 58))
				.addGroup(jPanel1Layout.createSequentialGroup().addContainerGap()
						.addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
								.addGroup(jPanel1Layout.createSequentialGroup().addComponent(jRadioButton1)
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
												javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 71,
												javax.swing.GroupLayout.PREFERRED_SIZE))
								.addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 165,
										javax.swing.GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING,
										javax.swing.GroupLayout.DEFAULT_SIZE, 350, Short.MAX_VALUE)
								.addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
								.addGroup(jPanel1Layout.createSequentialGroup()
										.addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 73,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(0, 0, Short.MAX_VALUE)))));
		jPanel1Layout
				.setVerticalGroup(
						jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addGroup(jPanel1Layout.createSequentialGroup().addGroup(jPanel1Layout
										.createParallelGroup(
												javax.swing.GroupLayout.Alignment.LEADING)
										.addGroup(jPanel1Layout.createSequentialGroup()
												.addGroup(jPanel1Layout
														.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(jLabel1).addComponent(jLabel3))
												.addGap(19, 19, 19))
										.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout
												.createSequentialGroup().addGap(8, 8, 8)
												.addGroup(jPanel1Layout
														.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(jLabel2).addComponent(jLabel4))
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
										.addGroup(jPanel1Layout
												.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
												.addGroup(jPanel1Layout.createSequentialGroup()
														.addComponent(jScrollPane2,
																javax.swing.GroupLayout.PREFERRED_SIZE, 244,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addPreferredGap(
																javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
														.addComponent(jScrollPane1,
																javax.swing.GroupLayout.PREFERRED_SIZE, 104,
																javax.swing.GroupLayout.PREFERRED_SIZE))
												.addComponent(jScrollPane3))
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10,
												Short.MAX_VALUE)
										.addGroup(jPanel1Layout
												.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(jButton1).addComponent(jButton2)
												.addComponent(jRadioButton1))
										.addContainerGap()));

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(
				jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE,
				javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(
				jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
		MouseListener Danxuan = new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 1) {
					if (!Morechooes) { // 单发状态
						jList1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
						if (jList1.isSelectionEmpty()) {
							System.out.println("没有客户端");
							return;
						} else {
							index = jList1.locationToIndex(e.getPoint());
							// 实验二用得上
							// jTextArea2.setText(vt.get(index).getstring());
							flag = true;
							System.out.println(vt.get(index).toString() + " 被选中");
							// index是选择的客户的标志
						}
					}
				}
			}
		};
		jList1.addMouseListener(Danxuan);

		MouseListener Duoxuan = new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 1) {
					if (Morechooes) { // 群发状态
						jList1.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
						if (jList1.isSelectionEmpty()) {
							System.out.println("没有客户端");
							return;
						}
					}
				}
			}
		};
		jList1.addMouseListener(Duoxuan);

		pack();
	}// </editor-fold>
	
	private void jRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {
		if (jRadioButton1.isSelected()) {
			Morechooes = true;
			jTextArea2.append("请把您要群发的客户选上...\r\n");
		} else
			Morechooes = false;
	}
	// 发送按钮
	private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) throws IOException {
		if (jList1.isSelectionEmpty()) {
			JOptionPane.showMessageDialog(null, "Error：没有选中要发送的客户", "WARNING", JOptionPane.WARNING_MESSAGE);
			return;
		} else if (!Morechooes)
			Danfa(index, jTextArea1.getText());
		else
			Qunfa();
	}

	public void Qunfa() throws IOException {
		Vector<User> vtusers = new Vector<>();
		Vector vector = new Vector<>();
		Object[] usernames = jList1.getSelectedValues();
		for (int i = 0; i < usernames.length; i++) {
			vector.add((String) usernames[i]);
		}
		for (int i = 0; i < vt.size(); i++) {
			if (vector.contains(vt.get(i).toString())) {
				System.out.println(vt.get(i).toString()); // 成功群发的第一步，选中客户
				vtusers.add(vt.get(i)); // 成功群发的第一步，选中客户
			}
		}
		String send = jTextArea1.getText();
		for (int i = 0; i < vtusers.size(); i++) {
			Danfa(i, send);
		}

	}

	public void Danfa(int i, String send) throws IOException {
		Socket socket = vt.get(i).getSocket(); // 选中的用户对应socket的输出流
		if (socket.isConnected() && !socket.isClosed()) {
			// 判断是否断开连接
			String send1 = "Send for " + vt.get(i).toString() + ": " + send;// 服务器显示
			String send2 = "Server says: " + send + "\n";
			OutputStream os = socket.getOutputStream();
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os));
			bw.write(send2);
			bw.flush();
			jTextArea2.append(send1 + "\r\n");
			jTextArea1.setText("");
			jTextArea2.selectAll();

		} else {
			jTextArea2.append("发送失败,已与用户 " + vt.get(index).toString() + " 断开连接");
			jTextArea2.selectAll();
		}
	}

	private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) throws IOException, InterruptedException {

		System.exit(1);
	}

	// 客户端退出
	public static void ClientClose(int UserID,String username) throws IOException, InterruptedException {

		count--;
		index = UserID - 1; // id并没有改变
		for (int i = index + 1; i < vt.size(); i++) {
			vt.get(i).idjian();
		}
		vt1.remove(index); // vt1是JList的组件
		vtt1.remove(index);
		vt.remove(index); // vt乃是对象对象
		vtt.remove(index);
		jList1.setListData(vt1);
		for(int i = 0;i<Server.sockets.size();i++)  //告知所有用户要刷新好友列表了
		{
			BufferedWriter bw = new BufferedWriter(
					new OutputStreamWriter(Server.sockets.get(i).getOutputStream()));			
			bw.write("qwertyuiop[]clientexit\r\n");//  客户端退出，刷新好友列表信号		
			bw.write(username +"\r\n");//  客户端退出，刷新好友列表信号
			bw.flush();
			Thread.sleep(100);

			ObjectOutputStream op = new ObjectOutputStream(Server.sockets.get(i).getOutputStream());
			op.writeObject(ServerFrame.vtt);
			op.writeObject(ServerFrame.vtt1);				
			op.flush();
		}

	}

	public static void main(String args[]) throws InterruptedException {

		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(ServerFrame.class.getName()).log(java.util.logging.Level.SEVERE, null,
					ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(ServerFrame.class.getName()).log(java.util.logging.Level.SEVERE, null,
					ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(ServerFrame.class.getName()).log(java.util.logging.Level.SEVERE, null,
					ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(ServerFrame.class.getName()).log(java.util.logging.Level.SEVERE, null,
					ex);
		}

		Server server = new Server();
		ServerFrame serverFrame = new ServerFrame(server);
		serverFrame.setResizable(false);
		serverFrame.setTitle("Server");
		serverFrame.go();
		serverFrame.setVisible(true);

		while (true) {
			thread.sleep(100); // 利用线程的睡眠减轻cpu的负担，不用这个方法的时候cpu占30%
			if (server.getIsadd()) {
				jList1.setListData(vtt1);
				server.setIsadd(false);
			}
		}

	}

	private Server server = null;
	private String ip = "";
	private int port = 0;
	private boolean Morechooes = false;
	public static int index = 0;
	public static boolean flag = false;
	public static int count = 0;
	public static Vector<User> vt = new Vector<>();
	public static Vector vt1 = new Vector();
	public static Vector<User2> vtt = new Vector<>();
	public static Vector vtt1 = new Vector();
	// Variables declaration - do not modify
	public static Thread thread = null;
	private javax.swing.JRadioButton jRadioButton1;
	private javax.swing.JButton jButton1;
	private javax.swing.JButton jButton2;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JLabel jLabel3;
	private javax.swing.JLabel jLabel4;
	public static javax.swing.JList<String> jList1;
	private javax.swing.JPanel jPanel1;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JScrollPane jScrollPane2;
	private javax.swing.JScrollPane jScrollPane3;
	private javax.swing.JTextArea jTextArea1; // 输入文本
	public static javax.swing.JTextArea jTextArea2; // 显示
	// End of variables declaration
}


