package lib.defines;

import lib.element.ElementSound;

public interface MfxDefines 
{
	int MUSIC_GP_JUNGLE = 0;
	int MUSIC_MAINMENU	= MUSIC_GP_JUNGLE + 1;

	public final static ElementSound CONTAINER[] = 
	{
		new ElementSound("mfx/gameplay.ogg", true),
		new ElementSound("mfx/mainmenu.ogg", true),
	};
}
