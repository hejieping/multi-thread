package Cross;

import java.awt.GridBagLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class MainControl
{

	/**
	 * @param args
	 */
	final int EMERGENCY = 1;// 创建急救车的标志
	final int NORMAL = 0;// 创建正常车的标志
	private JFrame frame;

	MainControl()
	{
		frame = new JFrame();
		frame.setSize(600, 600);
		frame.setLocation(new Point(100, 100));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		dataInit();
		setBackground();
		addButton();
		ThreadLantern lantern = new ThreadLantern(frame);
		lantern.start();
		frame.setVisible(true);

	}

	public JFrame getJFrame()
	{
		return frame;

	}

	// 设置主界面背景图片
	private void setBackground()
	{
		ImageIcon background = new ImageIcon(getClass().getResource(
				"background.jpg"));// 背景图片
		JLabel label = new JLabel(background);// 把背景图片显示在一个标签里面
		// 把标签的大小位置设置为图片刚好填充整个面板
		label.setBounds(0, 0, background.getIconWidth(),
				background.getIconHeight());
		// 把内容窗格转化为JPanel，否则不能用方法setOpaque()来使内容窗格透明
		JPanel imagePanel = (JPanel) frame.getContentPane();
		imagePanel.setOpaque(false);
		// 内容窗格默认的布局管理器为BorderLayout
		GridBagLayout gr = new GridBagLayout();
		imagePanel.setLayout(null);

		frame.getLayeredPane().setLayout(null);
		// 把背景图片添加到分层窗格的最底层作为背景
		frame.getLayeredPane().add(label, new Integer(Integer.MIN_VALUE));
		frame.setSize(background.getIconWidth(), background.getIconHeight());
		frame.setResizable(false);
	}

	private void addButton()
	{
		JPanel imagePanel = (JPanel) frame.getContentPane();
		// 设置八个按钮的位置
		JButton emergencyButtonNorth = new JButton("emergency");
		JButton emergencyButtonWest = new JButton("emergency");
		JButton emergencyButtonSouth = new JButton("emergency");
		JButton emergencyButtonEast = new JButton("emergency");
		JButton normalButtonNorth = new JButton("normal");
		JButton normalButtonWest = new JButton("normal");
		JButton normalButtonSouth = new JButton("normal");
		JButton normalButtonEast = new JButton("normal");

		emergencyButtonNorth.setBounds(80, 21, 100, 20);
		imagePanel.add(emergencyButtonNorth);
		normalButtonNorth.setBounds(80, 40, 100, 20);
		imagePanel.add(normalButtonNorth);

		emergencyButtonWest.setBounds(20, 420, 100, 20);
		imagePanel.add(emergencyButtonWest);
		normalButtonWest.setBounds(20, 440, 100, 20);
		imagePanel.add(normalButtonWest);

		emergencyButtonSouth.setBounds(410, 500, 100, 20);
		imagePanel.add(emergencyButtonSouth);
		normalButtonSouth.setBounds(410, 520, 100, 20);
		imagePanel.add(normalButtonSouth);

		emergencyButtonEast.setBounds(490, 150, 100, 20);
		imagePanel.add(emergencyButtonEast);
		normalButtonEast.setBounds(490, 170, 100, 20);
		imagePanel.add(normalButtonEast);

		normalButtonNorth.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				System.out.println("区停车位坐标1");
				Cross.Point location = new Cross.Point();
				int parkSpaceNumber = Park.getParkMutex(location, "NORTH");// 获取停车位序号
				if (parkSpaceNumber > 0)
				{
					ThreadCar threadCar = new ThreadCar(frame, location,
							"NORTH", parkSpaceNumber, NORMAL);
					threadCar.start();

				} else
				{
					JOptionPane.showMessageDialog(null, "停车位已经满了", "错误提示",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		normalButtonEast.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				System.out.println("区停车位坐标2");
				Cross.Point location = new Cross.Point();
				int parkSpaceNumber = Park.getParkMutex(location, "EAST");
				if (parkSpaceNumber > 0)
				{
					ThreadCar threadCar = new ThreadCar(frame, location,
							"EAST", parkSpaceNumber, NORMAL);
					threadCar.start();

				} else
				{
					JOptionPane.showMessageDialog(null, "停车位已经满了", "错误提示",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		normalButtonWest.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				System.out.println("区停车位坐标3");
				Cross.Point location = new Cross.Point();
				int parkSpaceNumber = Park.getParkMutex(location, "WEST");
				if (parkSpaceNumber > 0)
				{
					ThreadCar threadCar = new ThreadCar(frame, location,
							"WEST", parkSpaceNumber, NORMAL);
					threadCar.start();

				} else
				{
					JOptionPane.showMessageDialog(null, "停车位已经满了", "错误提示",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		normalButtonSouth.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				System.out.println("区停车位坐标4");
				Cross.Point location = new Cross.Point();
				int parkSpaceNumber = Park.getParkMutex(location, "SOUTH");
				if (parkSpaceNumber > 0)
				{
					ThreadCar threadCar = new ThreadCar(frame, location,
							"SOUTH", parkSpaceNumber, NORMAL);
					threadCar.start();

				} else
				{
					JOptionPane.showMessageDialog(null, "停车位已经满了", "错误提示",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		emergencyButtonSouth.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				System.out.println("区停车位坐标4");
				Cross.Point location = new Cross.Point();
				int parkSpaceNumber = Park.getParkMutex(location, "SOUTH");
				if (parkSpaceNumber > 0)
				{
					ThreadCar threadCar = new ThreadCar(frame, location,
							"SOUTH", parkSpaceNumber, EMERGENCY);
					threadCar.start();

				} else
				{
					JOptionPane.showMessageDialog(null, "停车位已经满了", "错误提示",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		emergencyButtonEast.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				System.out.println("区停车位坐标4");
				Cross.Point location = new Cross.Point();
				int parkSpaceNumber = Park.getParkMutex(location, "EAST");
				if (parkSpaceNumber > 0)
				{
					ThreadCar threadCar = new ThreadCar(frame, location,
							"EAST", parkSpaceNumber, EMERGENCY);
					threadCar.start();

				} else
				{
					JOptionPane.showMessageDialog(null, "停车位已经满了", "错误提示",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		emergencyButtonWest.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				System.out.println("区停车位坐标4");
				Cross.Point location = new Cross.Point();
				int parkSpaceNumber = Park.getParkMutex(location, "WEST");
				if (parkSpaceNumber > 0)
				{
					ThreadCar threadCar = new ThreadCar(frame, location,
							"WEST", parkSpaceNumber, EMERGENCY);
					threadCar.start();

				} else
				{
					JOptionPane.showMessageDialog(null, "停车位已经满了", "错误提示",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		emergencyButtonNorth.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				System.out.println("区停车位坐标4");
				Cross.Point location = new Cross.Point();
				int parkSpaceNumber = Park.getParkMutex(location, "NORTH");
				if (parkSpaceNumber > 0)
				{
					ThreadCar threadCar = new ThreadCar(frame, location,
							"NORTH", parkSpaceNumber, EMERGENCY);
					threadCar.start();

				} else
				{
					JOptionPane.showMessageDialog(null, "停车位已经满了", "错误提示",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
	}

	private void dataInit()
	{
		Park.parkInit();
		CrossMutex.crossMutexInit();
		Lantern.lanternDataInit();
	}

	public static void main(String[] args)
	{

		MainControl control = new MainControl();

	}
}
