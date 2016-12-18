package Cross;

/*
 * 十字路口斑马钱后的停车位，最多可以放置2辆车
 */
public class Park
{

	/**
	 * @param args
	 */
	// 四个方向的停车位，最多停两辆车
	private static boolean[] eastParkSpace;
	private static boolean[] westParkSpace;
	private static boolean[] northParkSpace;
	private static boolean[] southParkSpace;
	private static Point EASTPARKPOSITION1;
	private static Point EASTPARKPOSITION2;

	private static Point WESTPARKPOSITION1;
	private static Point WESTPARKPOSITION2;

	private static Point NORTHPARKPOSITION1;
	private static Point NORTHPARKPOSITION2;

	private static Point SOUTHPARKPOSITION1;
	private static Point SOUTHPARKPOSITION2;

	public static void parkInit()
	{
		// 停车位初始化

		eastParkSpace = new boolean[2];
		westParkSpace = new boolean[2];
		northParkSpace = new boolean[2];
		southParkSpace = new boolean[2];
		eastParkSpace[0] = true;
		westParkSpace[0] = true;
		northParkSpace[0] = true;
		southParkSpace[0] = true;

		eastParkSpace[1] = true;
		westParkSpace[1] = true;
		northParkSpace[1] = true;
		southParkSpace[1] = true;

		EASTPARKPOSITION1 = new Point(410, 213);
		EASTPARKPOSITION2 = new Point(410, 263);

		WESTPARKPOSITION1 = new Point(145, 313);
		WESTPARKPOSITION2 = new Point(145, 363);

		NORTHPARKPOSITION1 = new Point(263, 146);
		NORTHPARKPOSITION2 = new Point(213, 146);

		SOUTHPARKPOSITION1 = new Point(363, 413);
		SOUTHPARKPOSITION2 = new Point(313, 413);

	}

	public static int getParkMutex(Point location, String direction)// 获取停车位信号量，若有停车位，则通过location传回停车的位置
	{
		switch (direction)
		{
		case "EAST":
			if (eastParkSpace[0] == true)
			{

				location.x = EASTPARKPOSITION1.x;
				location.y = EASTPARKPOSITION1.y;
				eastParkSpace[0] = false;
				return 1;
			}
			if (eastParkSpace[1] == true)
			{
				location.x = EASTPARKPOSITION2.x;
				location.y = EASTPARKPOSITION2.y;
				eastParkSpace[1] = false;
				return 2;
			}
			return 0;
		case "WEST":
			if (westParkSpace[0] == true)
			{
				location.x = WESTPARKPOSITION1.x;
				location.y = WESTPARKPOSITION1.y;
				westParkSpace[0] = false;
				return 3;
			}
			if (westParkSpace[1] == true)
			{
				location.x = WESTPARKPOSITION2.x;
				location.y = WESTPARKPOSITION2.y;
				westParkSpace[1] = false;
				return 4;
			}
			return 0;
		case "NORTH":
			if (northParkSpace[0] == true)
			{
				location.x = NORTHPARKPOSITION1.x;
				location.y = NORTHPARKPOSITION1.y;
				northParkSpace[0] = false;
				return 5;
			}
			if (northParkSpace[1] == true)
			{
				location.x = NORTHPARKPOSITION2.x;
				location.y = NORTHPARKPOSITION2.y;
				northParkSpace[1] = false;
				return 6;
			}
			return 0;
		case "SOUTH":
			if (southParkSpace[0] == true)
			{
				location.x = SOUTHPARKPOSITION1.x;
				location.y = SOUTHPARKPOSITION1.y;
				southParkSpace[0] = false;
				return 7;
			}
			if (southParkSpace[1] == true)
			{
				location.x = SOUTHPARKPOSITION2.x;
				location.y = SOUTHPARKPOSITION2.y;
				southParkSpace[1] = false;
				return 8;
			}
			return 0;
		default:
			return 0;
		}
	}

	// 当车辆驶出停车位时，释放停车位信号量
	public static void returnParkMutex(Car_base car)
	{
		if (car.parkSpaceNumber == 1 || car.parkSpaceNumber == 2)
		{
			eastParkSpace[car.parkSpaceNumber - 1] = true;
			car.parkSpaceNumber = 0;// 清零该车的停车位信号量
		}

		if (car.parkSpaceNumber == 3 || car.parkSpaceNumber == 4)
		{
			westParkSpace[car.parkSpaceNumber - 3] = true;
			car.parkSpaceNumber = 0;
		}

		if (car.parkSpaceNumber == 5 || car.parkSpaceNumber == 6)
		{
			northParkSpace[car.parkSpaceNumber - 5] = true;
			car.parkSpaceNumber = 0;
		}

		if (car.parkSpaceNumber == 7 || car.parkSpaceNumber == 8)
		{
			southParkSpace[car.parkSpaceNumber - 7] = true;
			car.parkSpaceNumber = 0;
		}
	}

}
