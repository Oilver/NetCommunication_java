package Server;

import java.net.Socket;

public class User implements java.io.Serializable{
	
		private String string;
		private int id = 0;
		private String username;
		private int userport = 0;
		private Socket socket = null;
		public User(String username,int userport,int count,Socket socket){
			this.string = "*************服务器即将启动，等待客户端的连接*************\r\n\r\n";
			this.id = count;
			this.username = username;
			this.socket = socket;
			this.userport = userport;
		}
		
		public String getstring(){
			return string;
		}
		public void setstring(String string){
			this.string += string;
		}
		public String toString(){
			
			return username ;
		}
		public void idjian(){
			id--;
		}
		public int getid(){
			return id;
		}

		public Socket getSocket() {
			return socket;
		}

		public void setSocket(Socket socket) {
			this.socket = socket;
		}

		public int getUserport() {
			return userport;
		}

		public void setUserport(int userport) {
			this.userport = userport;
		}
		
}
