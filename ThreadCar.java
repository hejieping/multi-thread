package Cross;

import javax.swing.JFrame;

public class ThreadCar extends Thread
{

	/**
	 * @param args
	 */

	private JFrame frame; // 此程序的框架
	private Point position; // 生成的车类的坐标
	private String direction; // 该车类的方向
	private int parkSpaceNumber; // 该车的停车位序号
	private int flag; // flag为1，则生成急救车，为0生成普通车

	public ThreadCar(JFrame frame, Point position, String direction,
			int parkSpaceNumber, int flag)
	{
		this.frame = frame;
		this.position = position;
		this.direction = direction;
		this.parkSpaceNumber = parkSpaceNumber;
		this.flag = flag;
	}

	public void run()
	{
		if (flag == 1)
		{
			new EmergencyCar(position, direction, parkSpaceNumber, frame);
		} else
		{
			new NormalCar(position, direction, parkSpaceNumber, frame);
		}

	}
}
