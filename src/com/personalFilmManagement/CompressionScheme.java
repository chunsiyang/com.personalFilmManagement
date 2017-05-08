package com.personalFilmManagement;
public enum CompressionScheme{
	
	BLURAY,WEBRIP,WEB_DL,BRRIP,BDRIP;
	public static CompressionScheme valueOf(int index)
	{
		switch (index){
		case 0:
			return BLURAY;
		case 1:
			return WEBRIP;
		case 2:
			return WEB_DL;
		case 3:
			return BRRIP;
		case 4:
			return BDRIP;
		default:
			return null;
		}
	}
	public static String toReguasrExpression()
	{
		String regularExpression="(BluRay)|(WEBRip)|(WEB_DL)|(BRRIP)|(BDRIP)";
		return regularExpression;
		
	}
}
