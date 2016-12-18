package Cross;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/*
 * �������������ܴ����
 */
public class NormalCar extends Car_base
{

	/**
	 * @param args
	 */

	NormalCar(Point position, String direction, int parkSpaceNumber,
			JFrame frame)
	{
		super(position, direction, parkSpaceNumber, frame);
		switch (direction)
		{
		case "EAST":
			carImageIcon = new ImageIcon(getClass().getResource(
					"normalCarEast.png"));
			break;
		case "WEST":
			carImageIcon = new ImageIcon(getClass().getResource(
					"normalCarWest.png"));
			break;
		case "NORTH":
			carImageIcon = new ImageIcon(getClass().getResource(
					"normalCarNorth.png"));
			break;
		case "SOUTH":
			carImageIcon = new ImageIcon(getClass().getResource(
					"normalCarSouth.png"));
			break;
		default:
			carImageIcon = new ImageIcon(getClass().getResource(
					"normalCarEast.png"));
			break;
		}

		label = new JLabel(carImageIcon);
		JPanel imagePanel = (JPanel) frame.getContentPane();
		label.setBounds(position.x, position.y, size.x, size.y);
		imagePanel.add(label);
		move();
	}

	protected void move()
	{
		moveStep(frame, 4); // ����ͣ��λ��һ��Сλ��
		int count_move = 0;// ��ʮ��·�ڼ��ƶ��Ĵ��������ﵽ�Ĵ����Ѿ�ʻ��ʮ��·��
		if (direction == "EAST" || direction == "WEST")
		{
			while (!Lantern.getEastLanternState())
			{
				try
				{
					Thread.currentThread().sleep(50);
				} catch (Exception e)
				{
					// TODO: handle exception
				}
			}
		} else
		{
			while (!Lantern.getNorthLanternState())
			{
				try
				{
					Thread.currentThread().sleep(50);
				} catch (Exception e)
				{
					// TODO: handle exception
				}
			}
		}
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
		moveStep(frame, 50);
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
