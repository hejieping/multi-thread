package Cross;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/*
 * ���ȳ������Դ����
 */
public class EmergencyCar extends Car_base
{

	/**
	 * @param args
	 */

	EmergencyCar(Point position, String direction, int parkSpaceNumber,
			JFrame frame)
	{
		super(position, direction, parkSpaceNumber, frame);
		switch (direction)
		{
		case "EAST":
			carImageIcon = new ImageIcon(getClass().getResource(
					"emergencyCarEast.png"));
			break;
		case "WEST":
			carImageIcon = new ImageIcon(getClass().getResource(
					"emergencyCarWest.png"));
			break;
		case "NORTH":
			carImageIcon = new ImageIcon(getClass().getResource(
					"emergencyCarNorth.png"));
			break;
		case "SOUTH":
			carImageIcon = new ImageIcon(getClass().getResource(
					"emergencyCarSouth.png"));
			break;
		default:
			carImageIcon = new ImageIcon(getClass().getResource(
					"emergencyCarSouth.png"));
			break;
		}
		label = new JLabel(carImageIcon);
		JPanel imagePanel = (JPanel) frame.getContentPane();
		label.setBounds(position.x, position.y, size.x, size.y);
		imagePanel.add(label);
		move();
	}

	protected void move() // ���ȳ����Ӻ��̵�ֱ��λ��
	{
		moveStep(frame, 4); // ����ͣ��λ��һ��Сλ��
		int count_move = 0;// ��ʮ��·�ڼ��ƶ��Ĵ��������ﵽ�Ĵ����Ѿ�ʻ��ʮ��·��

		while (!getMutex())// ��ȡ��һ��λ�Ƶ��ź���
		{
			try
			{
				Thread.currentThread().sleep(50); // ÿ50ms���һ���Ƿ����ź���
			} catch (Exception e)
			{
				// TODO: handle exception
			}
		}
		moveStep(frame, 50); // ��һ��λ�ƣ�λ�ƽ����ͷ�ͣ��λ�ź���
		count_move++;
		Park.returnParkMutex(this);
		// ��ʮ��·�ڵ�λ��
		while (count_move < 4)
		{
			try
			{
				Thread.currentThread().sleep(50); // ÿ50ms���һ���Ƿ��п�����ǰ�ƶ�
			} catch (Exception e)
			{
				// TODO: handle exception
			}
			if (getMutex())
			{
				returnMutex();
				moveStep(frame, 50);
				count_move++;// �ƶ�һ��+1
			}
		}
		returnMutex();
		// ʻ��ʮ��·�ڵ�λ��
		while (!isFinish())
		{
			moveStep(frame, 50);
		}
		frame.remove(label);// �ƶ�������ɾ������
	}

}
