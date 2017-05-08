package com.personalFilmManagement;
//��Ƶ�ֱ���
public enum Resolution {
	E720P,E1080P,E2160P;
	public static Resolution valueOf(int index)
	{
		switch (index){
		case 0:
			return E720P;
		case 1:
			return E1080P;
		case 2:
			return E2160P;
		default:
			return null;
		}
	}
	public static String toReguasrExpression()
	{
		String regularExpression="(720p)|(1080p)|(2160p)";
		return regularExpression;
		
	}
}
