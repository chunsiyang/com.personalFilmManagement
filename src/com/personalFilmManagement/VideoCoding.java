package com.personalFilmManagement;
//��Ƶ�����ʽ
public enum VideoCoding {
	X264,H264,AVC;
	public static VideoCoding valueOf(int index)
	{
		switch (index){
		case 0:
			return X264;
		case 1:
			return H264;
		case 2:
			return AVC;
		default:
			return null;
		}
	}
	public static String toReguasrExpression()
	{
		String regularExpression="(x264)|(h264)|(AVC)";
		return regularExpression;
		
	}
}
