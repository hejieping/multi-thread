package Cross;

public class CrossMutex
{

	/**
	 * @param args
	 */
	private static boolean mutex[];

	public static void crossMutexInit() // ��ʼ��ʮ��·�ڵ��ź���
	{
		mutex = new boolean[16];
		for (int i = 0; i < 16; i++)
		{
			mutex[i] = true;
		}
	}

	public static int getMutex(Point point) // ��ȡ��x,y�����ź���
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

	public static void returnMutex(int mutexNumber) // �ͷ����ΪmutexNumber���ź���
	{
		mutex[mutexNumber] = true;
	}

	private static int getLocation(Point point) // ��ȡ(x,y)��Ӧ���ź���λ��
	{
		int location = ((point.y - 200) / 50) * 4 + (point.x - 200) / 50;
		return location;

	}

}
