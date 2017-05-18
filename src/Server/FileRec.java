package Server;
import java.io.BufferedInputStream;  
import java.io.BufferedOutputStream;  
import java.io.File;  
import java.io.FileInputStream;  
import java.io.FileNotFoundException;  
import java.io.FileOutputStream;  
import java.io.IOException;  
import java.net.DatagramPacket;  
import java.net.DatagramSocket;  
import java.net.InetSocketAddress;  
import java.net.SocketException;  
  
public class FileRec extends Thread {  
    private DatagramSocket receive;  
    private String ip;  
    private int serverPort;  
    private int clientPort;  
    private File file;  
    private String path;  
    private DatagramPacket pkg;  
    private DatagramPacket messagepkg;  
  
  
    public FileRec(String ip, int serverPort, int clientPort, String path) {  
        super();  
        this.ip = ip;  
        this.serverPort = serverPort;  
        this.clientPort = clientPort;  
        this.path = path;  
    }  
  
    public String getPath() {  
        return path;  
    }  
  
    public String getip() {  
        return ip;  
    }  
  
    public void setPath(String path) {  
        this.ip = path;  
    }  
  
    public File getFile() {  
        return file;  
    }  
  
    public void setFile(File file) {  
        this.file = file;  
    }  
  
    public void receive() {  
        try {  
            // �����ļ������˿�  
            receive = new DatagramSocket(serverPort);  
            // д�ļ�·��  
            BufferedOutputStream bos = new BufferedOutputStream(  
                    new FileOutputStream(new File(path)));  
  
            // ��ȡ�ļ���  
            byte[] buf = new byte[1024 * 63];  
            pkg = new DatagramPacket(buf, buf.length);  
            // �����յ��ļ��� ȷ����Ϣ��  
            byte[] messagebuf = new byte[1024];  
            messagebuf = "ok".getBytes();  
            messagepkg = new DatagramPacket(messagebuf, messagebuf.length,  
                    new InetSocketAddress(ip, clientPort));  
            // ѭ�����հ���ÿ�ӵ�һ������ظ��Է�һ��ȷ����Ϣ���Է��ŷ���һ����(���ⶪ��������)��ֱ���յ�һ��������������ѭ���������ļ����䣬�ر���  
            while (true) {  
                receive.receive(pkg);  
                if (new String(pkg.getData(), 0, pkg.getLength()).equals("end")) {  
                    System.out.println("�ļ��������");  
                    bos.close();  
                    receive.close();  
                    break;  
                }  
                receive.send(messagepkg);  
                System.out.println(new String(messagepkg.getData()));  
                bos.write(pkg.getData(), 0, pkg.getLength());  
                bos.flush();  
            }  
            bos.close();  
            receive.close();  
        } catch (SocketException e) {  
            e.printStackTrace();  
        } catch (FileNotFoundException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
    }  
  
    public void run() {  
        receive();  
    }  
    
} 