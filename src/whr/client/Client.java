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

	//�ϴ��ļ�
	public void sendFile(File file) {
		try {
			socket = new Socket();
			socket.connect(new InetSocketAddress("172.26.147.17", 8866));//ͼ���IP��172.27.81.60������IP��172.26.147.17
		} catch (IOException e) {
			System.out.println("���ӷ��������ɹ���");
			JOptionPane.showMessageDialog(null, "����������ʧ�ܣ�", "��ʾ", JOptionPane.ERROR_MESSAGE); 
			return;
		}
		int length = 0;
		DataOutputStream dos = null;
		FileInputStream fis = null;
		try {
			dos = new DataOutputStream(socket.getOutputStream());
			fis = new FileInputStream(file);
			byte[] sendByte = new byte[1024];
			//���ļ�����utf-8��ʽ�������ļ���ͷ�����Զ�����2�ֽڳ���ֵ���Ա��ȡ
			dos.writeUTF(file.getName());
			while ((length = fis.read(sendByte, 0, sendByte.length)) > 0) {
				dos.write(sendByte, 0, length);
				dos.flush();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			//�ر���Դ
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
