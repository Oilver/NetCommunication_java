package Server;

import java.net.Socket;

public class User2 implements java.io.Serializable{
	
		private String string;
		private int id = 0;
		private String username;
		private int userport = 0;
		
		private int message = 0;
		private boolean hasmessage = false;
		public User2(String username,int userport,int count){
			this.string = "";
			this.id = count;
			this.username = username;
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

		public int getUserport() {
			return userport;
		}

		public void setUserport(int userport) {
			this.userport = userport;
		}

		public int getMessage() {
			return message;
		}

		public void setMessage(int message) {
			this.message = message;
		}
		public void AddMessage() {
			message++;
		}

		public boolean isHasmessage() {
			return hasmessage;
		}

		public void setHasmessage(boolean hasmessage) {
			this.hasmessage = hasmessage;
		}
		
		
}
