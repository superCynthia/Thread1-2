package whr.client;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

import javax.swing.JOptionPane;

public class Client {

	private Socket socket = null;

	//上传文件
	public void sendFile(File file) {
		try {
			socket = new Socket();
			socket.connect(new InetSocketAddress("172.26.147.17", 8866));//图书馆IP：172.27.81.60；宿舍IP：172.26.147.17
		} catch (IOException e) {
			System.out.println("连接服务器不成功！");
			JOptionPane.showMessageDialog(null, "服务器连接失败！", "提示", JOptionPane.ERROR_MESSAGE); 
			return;
		}
		int length = 0;
		DataOutputStream dos = null;
		FileInputStream fis = null;
		try {
			dos = new DataOutputStream(socket.getOutputStream());
			fis = new FileInputStream(file);
			byte[] sendByte = new byte[1024];
			//将文件名用utf-8形式保存在文件开头，并自动加上2字节长度值，以便读取
			dos.writeUTF(file.getName());
			while ((length = fis.read(sendByte, 0, sendByte.length)) > 0) {
				dos.write(sendByte, 0, length);
				dos.flush();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			//关闭资源
			try {
				if (dos != null)
					dos.close();
				if (fis != null)
					fis.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}
