package EvolvingPlants;

import java.io.IOException;

import javax.imageio.ImageIO;

import tCode.TCode;
import tools.WindowTools;

public class Main extends TCode
	{
		public static World world;

		public static void main(String[] args)
			{
				new Main(1200, 600, true, false);
			}

		public Main(int width, int height, boolean framed, boolean resizable)
			{
				super(width, height, framed, resizable);
				programName = "Evolving Plants";
				versionNumber = "1.5";
				DEBUG = true;
				FORCE_SINGLE_THREAD = true;

//				try
//					{
//						// TODO add icons for frame
//						// frame.icons.add(ImageIO.read(Main.class.getResource("/assets/taskBar.png")));
//						// frame.icons.add(ImageIO.read(Main.class.getResource("/assets/titleBar.png")));
//					}
//				catch (IOException e)
//					{
//						if (!DEBUG)
//							WindowTools.debugWindow("Assets not present");
//						else
//							e.printStackTrace();
//					}

				world = new World();
				begin(world);
			}
	}