package Cross;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

/*
 * ���Ļ���
 */
public class Car_base
{

	/**
	 * @param args
	 */
	protected JFrame frame;
	protected String direction;// ����λ
	protected Point position; // ��λ��
	protected int parkSpaceNumber; // ͣ��λ��ţ������ͷ�ͣ��λ�ź���
	protected Point size; // ���ĳߴ�
	protected int mutexNumberNow; // Ŀǰ��ռ�ź���
	protected int mutexNumberNext; // ��һλ���ź���
	protected JLabel label;
	protected ImageIcon carImageIcon;

	// ��ʼ��������direction����ͣ���ķ�λ

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

	protected boolean getMutex()// ��ȡ��һ�ƶ�λ�õ��ź���
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

	protected void returnMutex()// ������һ�ƶ�λ�õ��ź���
	{
		CrossMutex.returnMutex(mutexNumberNow);
	}

	// �жϳ����Ƿ���ʻ����
	protected boolean isFinish() // �ж��Ƿ�λ�ƽ���
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

	protected void moveStep(JFrame frame, int moveCount)// ǰ��һ���ź�������
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
		this.mutexNumberNow = mutexNumberNext;// һ��λ�ƽ������ı��ź���״̬
		this.mutexNumberNext = 0;

	}
}
