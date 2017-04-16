package com.agd.indra.state.value;

public class ConfigOption {
	private boolean usesound;
	private boolean usemusic;
	protected boolean isUsesound() {
		return usesound;
	}
	protected void setUsesound(boolean usesound) {
		this.usesound = usesound;
	}
	protected boolean isUsemusic() {
		return usemusic;
	}
	protected void setUsemusic(boolean usemusic) {
		this.usemusic = usemusic;
	}	

}
