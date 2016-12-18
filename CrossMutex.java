package Cross;

public class CrossMutex
{

	/**
	 * @param args
	 */
	private static boolean mutex[];

	public static void crossMutexInit() // 初始化十字路口的信号量
	{
		mutex = new boolean[16];
		for (int i = 0; i < 16; i++)
		{
			mutex[i] = true;
		}
	}

	public static int getMutex(Point point) // 获取（x,y）的信号量
	{

		try
		{
			if (mutex[getLocation(point)] == true)
			{
				mutex[getLocation(point)] = false;
				return getLocation(point);
			} else
			{

			}
		} catch (ArrayIndexOutOfBoundsException e)
		{
		}
		return -1;
	}

	public static void returnMutex(int mutexNumber) // 释放序号为mutexNumber的信号量
	{
		mutex[mutexNumber] = true;
	}

	private static int getLocation(Point point) // 获取(x,y)对应的信号量位置
	{
		int location = ((point.y - 200) / 50) * 4 + (point.x - 200) / 50;
		return location;

	}

}
