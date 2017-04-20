package whr.c_ui;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import whr.client.Client;

public class MainFrame extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;

	private Client client = new Client();

	private JButton jb1 = new JButton("���");
	private JButton jb2 = new JButton("�ϴ�");
	private JButton jb3 = new JButton("���");
	private JTextField jtf = new JTextField();
	
	public MainFrame() {
		setTitle("�ļ��ϴ��ͻ���");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		init();
		setSize(400, 200);
		// ��ȡ��Ļ�ߴ�
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		// ��ȡ������Ĵ���ߴ�
		Dimension frameSize = this.getSize();
		// �������洰�����
		if (frameSize.height > screenSize.height)
			frameSize.height = screenSize.height;
		if (frameSize.width > screenSize.width)
			frameSize.width = screenSize.width;
		this.setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
		this.setVisible(true);
	}

	public void init() {
		JPanel jp = new JPanel();
		BoxLayout boxLayoutY = new BoxLayout(jp, BoxLayout.Y_AXIS);
		BoxLayout boxLayoutX;
		jp.setLayout(boxLayoutY);

		// ��岼��
		// ��һ��
		JPanel jpr1 = new JPanel();
		boxLayoutX = new BoxLayout(jpr1, BoxLayout.X_AXIS);
		jpr1.setLayout(boxLayoutX);
		jpr1.add(Box.createHorizontalStrut(30));
		jpr1.add(this.jtf);
		jpr1.add(Box.createHorizontalStrut(10));
		jpr1.add(this.jb1);
		jpr1.add(Box.createHorizontalStrut(30));
		jb1.addActionListener(this);

		// �ڶ���
		JPanel jpr2 = new JPanel();
		boxLayoutX = new BoxLayout(jpr2, BoxLayout.X_AXIS);
		jpr2.setLayout(boxLayoutX);
		jpr2.add(Box.createHorizontalStrut(30));
		jpr2.add(this.jb2);
		jpr2.add(Box.createHorizontalStrut(30));
		jpr2.add(this.jb3);
		jpr2.add(Box.createHorizontalStrut(30));
		jb2.addActionListener(this);
		jb3.addActionListener(this);

		jp.add(Box.createVerticalStrut(30));
		jp.add(jpr1);
		jp.add(Box.createVerticalStrut(20));
		jp.add(jpr2);
		jp.add(Box.createVerticalStrut(30));

		this.add(jp);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("�ϴ�")) {
			File file = new File(jtf.getText().trim());
			if (file.exists())
				client.sendFile(file);
			else
				JOptionPane.showMessageDialog(null, "�ϴ��ļ������ڣ�", "��ʾ", JOptionPane.ERROR_MESSAGE); 
		} else if (e.getActionCommand().equals("���")) {
			jtf.setText("");
		} else if (e.getActionCommand().equals("���")) {
			JFileChooser fd = new JFileChooser();
			fd.showOpenDialog(null);
			File f = fd.getSelectedFile();
			if (f != null) {
				jtf.setText(f.getPath());
			} else
				JOptionPane.showMessageDialog(null, "ѡ����ļ������ڣ�", "��ʾ", JOptionPane.ERROR_MESSAGE); 
		}
	}

}
