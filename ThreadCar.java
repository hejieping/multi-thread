package Cross;

import javax.swing.JFrame;

public class ThreadCar extends Thread
{

	/**
	 * @param args
	 */

	private JFrame frame; // �˳���Ŀ��
	private Point position; // ���ɵĳ��������
	private String direction; // �ó���ķ���
	private int parkSpaceNumber; // �ó���ͣ��λ���
	private int flag; // flagΪ1�������ɼ��ȳ���Ϊ0������ͨ��

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
