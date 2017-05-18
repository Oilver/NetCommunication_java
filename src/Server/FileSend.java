package Server;
import java.io.BufferedInputStream;  
import java.io.File;  
import java.io.FileInputStream;  
import java.io.FileNotFoundException;  
import java.io.IOException;  
import java.net.DatagramPacket;  
import java.net.DatagramSocket;  
import java.net.InetSocketAddress;  
import java.net.SocketException;  
  
public class FileSend extends Thread {  
    private DatagramSocket send;  
    private DatagramPacket pkg;  
    private DatagramPacket messagepkg;  
    private int serverPort;  
    private int clientPort;  
    private String path;  
    private File file;  
    private String ip;  
  
  
    public FileSend(int serverPort, int clientPort, String path, String ip) {  
        super();  
        this.serverPort = serverPort;  
        this.clientPort = clientPort;  
        this.path = path;  
        this.ip = ip;  
    }  
  
    public String getPath() {  
        return path;  
    }  
  
    public void setPath(String path) {  
        this.path = path;  
    }  
  
    public String getIp() {  
        return ip;  
    }  
  
    public void setIp(String ip) {  
        this.ip = ip;  
    }  
  
    public void send() {  
        try {  
            //文件发送者设置监听端口  
            send = new DatagramSocket(clientPort);  
            BufferedInputStream bis = new BufferedInputStream(  
                    new FileInputStream(new File(path)));  
            //确认信息包  
            byte[] messagebuf = new byte[1024];  
            messagepkg = new DatagramPacket(messagebuf, messagebuf.length);  
            //文件包  
            byte[] buf = new byte[1024 * 63];  
            int len;  
            while ((len = bis.read(buf)) != -1) {  
                  
                pkg = new DatagramPacket(buf, len, new InetSocketAddress(  
                        ip, serverPort));  
                //设置确认信息接收时间，3秒后未收到对方确认信息，则重新发送一次  
                send.setSoTimeout(3000);  
                while (true) {  
                    send.send(pkg);  
                    send.receive(messagepkg);  
                    System.out.println(new String(messagepkg.getData()));  
                    break;  
                }  
            }  
            // 文件传完后，发送一个结束包  
            buf = "end".getBytes();  
            DatagramPacket endpkg = new DatagramPacket(buf, buf.length,  
                    new InetSocketAddress(ip, serverPort));  
            System.out.println("文件发送完毕");  
            send.send(endpkg);  
            bis.close();  
            send.close();  
  
        } catch (SocketException e) {  
            e.printStackTrace();  
        } catch (FileNotFoundException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        }  
    }  
  
    public void run() {  
        send();  
    }  
} 