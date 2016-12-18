package Cross;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Lantern
{

	/**
	 * @param args
	 */
	private static Point GREENPOSITION; // 绿灯控件位置
	private static Point REDOISITION;// 红灯控件位置
	private static Point GREENSIZE;// 绿灯控件尺寸
	private static Point REDSIZE;// 红灯空间尺寸
	private JLabel greenLanternJLabel; // 绿灯控件
	private JLabel redLanternJLabel; // 红灯控件
	private static boolean greenLantern; // 红绿灯状态

	// 构造函数中为frame添加了红绿灯控件，并使红绿灯运行起来

	Lantern(JFrame frame)
	{
		JPanel imagePanel = (JPanel) frame.getContentPane();

		ImageIcon greenLanternIcon = new ImageIcon(getClass().getResource(
				"greenLantern.png"));
		greenLanternJLabel = new JLabel(greenLanternIcon);
		greenLanternJLabel.setBounds(GREENPOSITION.x, GREENPOSITION.y,
				GREENSIZE.x, GREENSIZE.y);
		imagePanel.add(greenLanternJLabel);

		ImageIcon redLanternIcon = new ImageIcon(getClass().getResource(
				"redLantern.png"));
		redLanternJLabel = new JLabel(redLanternIcon);
		redLanternJLabel.setBounds(REDOISITION.x, REDOISITION.y, REDSIZE.x,
				REDSIZE.y);
		imagePanel.add(redLanternJLabel);
		runLantern();

	}

	public static void lanternDataInit() // 初始化红绿灯的数据
	{
		GREENPOSITION = new Point(122, 85);
		GREENSIZE = new Point(35, 35);
		REDOISITION = new Point(122, 123);
		REDSIZE = new Point(36, 36);
	}

	// 红绿灯运行函数，每8秒切换一次红绿灯
	private void runLantern()
	{
		greenLantern = true;
		while (true)
		{
			greenLanternJLabel.setVisible(greenLantern);
			redLanternJLabel.setVisible(!greenLantern);
			try
			{
				Thread.currentThread().sleep(8000);
			} catch (Exception e)
			{
				System.out.println(e.getMessage());
			}
			greenLantern = !greenLantern;
		}
	}

	// 获取南北走向红绿灯的状态
	public static boolean getNorthLanternState()
	{
		return greenLantern;
	}

	// 获取东西走向红绿灯的状态
	public static boolean getEastLanternState()
	{
		return !greenLantern;
	}

}
