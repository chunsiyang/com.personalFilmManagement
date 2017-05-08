package com.personalFilmManagement;

public enum AudioCoding {
	DTS_HD,DTS_FGT,AAC,DD,TRUEHD;
	public static AudioCoding valueOf(int index)
	{
		switch (index){
		case 0:
			return DTS_HD;
		case 1:
			return DTS_FGT;
		case 2:
			return AAC;
		case 3:
			return DD;
		case 4:
			return TRUEHD;
		default:
			return null;
		}
	}
	public static String toReguasrExpression()
	{
		String regularExpression="(DTS-HD)|(DTS-FGT)|(AAC)|(DD)|(TrueHD)";
		return regularExpression;
		
	}

}
