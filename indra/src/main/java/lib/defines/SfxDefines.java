package lib.defines;

import lib.element.ElementSound;

public interface SfxDefines 
{ 
	int SOUND_RUN_GRASS = 0;
	int SOUND_JUMP		= SOUND_RUN_GRASS + 1;
	int SOUND_DBL_JUMP	= SOUND_JUMP + 1;
	int SOUND_BITE		= SOUND_DBL_JUMP + 1;
	int SOUND_COLLISION	= SOUND_BITE + 1;
	int SOUND_BUKK		= SOUND_COLLISION + 1;
	
	public final static ElementSound CONTAINER[] = 
	{
		new ElementSound("sfx/running grass wet feet sfx loop2_Master.ogg", true),
		new ElementSound("sfx/lompat hap_hap.ogg", false),
		new ElementSound("sfx/lompat hup_hup.ogg", false),
		new ElementSound("sfx/Apple_Bite edited2.ogg", false),
		new ElementSound("sfx/fall.ogg", false),
		new ElementSound("sfx/bukk.ogg", false),
	};
}
