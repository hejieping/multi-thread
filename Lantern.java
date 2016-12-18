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
	private static Point GREENPOSITION; // �̵ƿؼ�λ��
	private static Point REDOISITION;// ��ƿؼ�λ��
	private static Point GREENSIZE;// �̵ƿؼ��ߴ�
	private static Point REDSIZE;// ��ƿռ�ߴ�
	private JLabel greenLanternJLabel; // �̵ƿؼ�
	private JLabel redLanternJLabel; // ��ƿؼ�
	private static boolean greenLantern; // ���̵�״̬

	// ���캯����Ϊframe����˺��̵ƿؼ�����ʹ���̵���������

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

	public static void lanternDataInit() // ��ʼ�����̵Ƶ�����
	{
		GREENPOSITION = new Point(122, 85);
		GREENSIZE = new Point(35, 35);
		REDOISITION = new Point(122, 123);
		REDSIZE = new Point(36, 36);
	}

	// ���̵����к�����ÿ8���л�һ�κ��̵�
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

	// ��ȡ�ϱ�������̵Ƶ�״̬
	public static boolean getNorthLanternState()
	{
		return greenLantern;
	}

	// ��ȡ����������̵Ƶ�״̬
	public static boolean getEastLanternState()
	{
		return !greenLantern;
	}

}
