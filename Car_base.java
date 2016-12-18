package Cross;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

/*
 * 车的基类
 */
public class Car_base
{

	/**
	 * @param args
	 */
	protected JFrame frame;
	protected String direction;// 车方位
	protected Point position; // 车位置
	protected int parkSpaceNumber; // 停车位编号，方便释放停车位信号量
	protected Point size; // 车的尺寸
	protected int mutexNumberNow; // 目前所占信号量
	protected int mutexNumberNext; // 下一位移信号量
	protected JLabel label;
	protected ImageIcon carImageIcon;

	// 初始化车辆，direction代表停车的方位

	public Car_base(Point position, String direction, int parkSpaceNumber,
			JFrame frame)
	{
		this.parkSpaceNumber = parkSpaceNumber;
		this.position = position;
		this.frame = frame;
		this.direction = direction;
		if (direction == "NORTH" || direction == "SOUTH")
		{
			size = new Point(15, 27);
		} else
		{
			{
				size = new Point(27, 15);
			}
		}

	}

	protected void move()
	{
	}

	protected boolean getMutex()// 获取下一移动位置的信号量
	{
		int mutexNumber = 0;
		switch (direction)
		{
		case "EAST":
			mutexNumber = CrossMutex.getMutex(new Point(position.x - 21,
					position.y));
			if (mutexNumber >= 0)
			{
				mutexNumberNext = mutexNumber;
				return true;
			} else
			{
				return false;
			}
		case "WEST":
			mutexNumber = CrossMutex.getMutex(new Point(position.x + 56,
					position.y));
			if (mutexNumber >= 0)
			{
				mutexNumberNext = mutexNumber;
				return true;
			} else
			{
				return false;
			}
		case "NORTH":
			mutexNumber = CrossMutex.getMutex(new Point(position.x,
					position.y + 55));
			if (mutexNumber >= 0)
			{
				mutexNumberNext = mutexNumber;
				return true;
			} else
			{
				return false;
			}
		case "SOUTH":
			mutexNumber = CrossMutex.getMutex(new Point(position.x,
					position.y - 14));
			if (mutexNumber >= 0)
			{
				mutexNumberNext = mutexNumber;
				return true;
			} else
			{
				return false;
			}
		default:
			return false;
		}

	}

	protected void returnMutex()// 返回上一移动位置的信号量
	{
		CrossMutex.returnMutex(mutexNumberNow);
	}

	// 判断车辆是否行驶结束
	protected boolean isFinish() // 判断是否位移结束
	{
		if (position.x > 600 || position.x < 0 || position.y > 600
				|| position.y < 0)
		{
			return true;
		} else
		{
			return false;
		}
	}

	protected void moveStep(JFrame frame, int moveCount)// 前进一个信号量长度
	{
		int count = 0;
		switch (direction)
		{
		case "EAST":
			while (count < moveCount)
			{
				try
				{
					Thread.currentThread().sleep(50);
				} catch (Exception e)
				{
					// TODO: handle exception
				}
				label.setBounds(position.x--, position.y, size.x, size.y);
				count++;

			}
			break;
		case "WEST":
			while (count < moveCount)
			{
				try
				{
					Thread.currentThread().sleep(50);
				} catch (Exception e)
				{
					// TODO: handle exception
				}
				label.setBounds(position.x++, position.y, size.x, size.y);
				count++;

			}
			break;
		case "NORTH":
			while (count < moveCount)
			{
				try
				{
					Thread.currentThread().sleep(50);
				} catch (Exception e)
				{
					// TODO: handle exception
				}
				label.setBounds(position.x, position.y++, size.x, size.y);
				count++;

			}
			break;
		case "SOUTH":
			while (count < moveCount)
			{
				try
				{
					Thread.currentThread().sleep(50);
				} catch (Exception e)
				{
					// TODO: handle exception
				}
				label.setBounds(position.x, position.y--, size.x, size.y);
				count++;

			}
			break;
		default:
			;

		}
		this.mutexNumberNow = mutexNumberNext;// 一段位移结束，改变信号量状态
		this.mutexNumberNext = 0;

	}
}
