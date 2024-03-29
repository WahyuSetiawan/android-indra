package lib.defines;

import org.andengine.engine.options.ScreenOrientation;

public class GameEngineConfiguration 
{
	public static ScreenOrientation screenOrientation = ScreenOrientation.LANDSCAPE_FIXED;
	
	public static int masterWidth = 800;
	
	public static int masterHeight = 480;
	
	public static boolean isFullScreen = !false;
	
	public static boolean useSound = !false;
	
	public static boolean useMusic = !false;
	
	public static boolean useUsualCamera 	= !false;
	
	public static boolean useGameUpdate 	= !false;
	
	public static boolean useTouchScene 	= false;
	
	public static boolean useVibration 		= false;
	
	public static boolean showFpsCounter	= false;
	
	public static boolean useFpsLimiter		= !false;
	
	public static int	  fpsLimit			= 60;
	
	public static String locationsplash		= "gfx/loading/splash.jpg";
	
	public static String debugTagName = "DEBUG";
}
