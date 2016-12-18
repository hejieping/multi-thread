package Cross;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/*
 * 正常车辆，不能闯红灯
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
		moveStep(frame, 4); // 进入停车位的一段小位移
		int count_move = 0;// 在十字路口间移动的次数，当达到四次则已经驶出十字路口
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
		while (!getMutex())// 获取第一段位移的信号量
		{
			try
			{
				Thread.currentThread().sleep(50); // 每50ms检查一次是否有信号量
			} catch (Exception e)
			{
				// TODO: handle exception
			}
		}
		moveStep(frame, 50);
		count_move++;
		Park.returnParkMutex(this);
		// 在十字路口的位移
		while (count_move < 4)
		{
			try
			{
				Thread.currentThread().sleep(50); // 每50ms检查一次是否有可以向前移动
			} catch (Exception e)
			{
				// TODO: handle exception
			}
			if (getMutex())
			{
				returnMutex();
				moveStep(frame, 50);
				count_move++;// 移动一次+1
			}
		}
		returnMutex();
		// 驶出十字路口的位移
		while (!isFinish())
		{
			moveStep(frame, 50);
		}
		frame.remove(label);// 移动结束，删除车辆
	}
}
