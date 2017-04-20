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

	private JButton jb1 = new JButton("浏览");
	private JButton jb2 = new JButton("上传");
	private JButton jb3 = new JButton("清空");
	private JTextField jtf = new JTextField();
	
	public MainFrame() {
		setTitle("文件上传客户端");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		init();
		setSize(400, 200);
		// 获取屏幕尺寸
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		// 获取主界面的窗体尺寸
		Dimension frameSize = this.getSize();
		// 令主界面窗体居中
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

		// 面板布局
		// 第一行
		JPanel jpr1 = new JPanel();
		boxLayoutX = new BoxLayout(jpr1, BoxLayout.X_AXIS);
		jpr1.setLayout(boxLayoutX);
		jpr1.add(Box.createHorizontalStrut(30));
		jpr1.add(this.jtf);
		jpr1.add(Box.createHorizontalStrut(10));
		jpr1.add(this.jb1);
		jpr1.add(Box.createHorizontalStrut(30));
		jb1.addActionListener(this);

		// 第二行
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
		if (e.getActionCommand().equals("上传")) {
			File file = new File(jtf.getText().trim());
			if (file.exists())
				client.sendFile(file);
			else
				JOptionPane.showMessageDialog(null, "上传文件不存在！", "提示", JOptionPane.ERROR_MESSAGE); 
		} else if (e.getActionCommand().equals("清空")) {
			jtf.setText("");
		} else if (e.getActionCommand().equals("浏览")) {
			JFileChooser fd = new JFileChooser();
			fd.showOpenDialog(null);
			File f = fd.getSelectedFile();
			if (f != null) {
				jtf.setText(f.getPath());
			} else
				JOptionPane.showMessageDialog(null, "选择的文件不存在！", "提示", JOptionPane.ERROR_MESSAGE); 
		}
	}

}
