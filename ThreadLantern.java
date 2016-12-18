package Cross;

import javax.swing.JFrame;

public class ThreadLantern extends Thread
{

	/**
	 * @param args
	 */
	private JFrame frame;

	public ThreadLantern(JFrame frame)
	{
		this.frame = frame;
	}

	public void run()
	{
		new Lantern(frame);

	}

}
