package com.personalFilmManagement;

import java.io.File;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Requestprocesser {
	public static Movie search(String name){
		Movie m;
		Pattern pStr;
		Matcher mStr;
		String regexpN="[\u0391-\uFFE50-9]+";//识别中文的正则表达式
		//确认输入的电影名为中文或是英文
		pStr=Pattern.compile(regexpN);
		mStr=pStr.matcher(name);
		LinkedList<Movie> res=new LinkedList<>();
		if(mStr.find())
			res=DBoperator.select(()->"*", ()->"CHSname="+name, null);
		else
			res=DBoperator.select(()->"*", ()->"ENGname="+name, null);
		m=res.get(0);
		return m;
	}

	public static boolean autoInsertPath(String url)
	{
		Movie m=new Movie();
		Pattern pStr;
		Matcher mStr;
		String regexNoD="\\.";//用来消除CHSname和ENGname中的.
		String regexTo_="-";//将AudioCoding中的-转换为_
		String regexpEN="([A-Za-z][A-Za-z1-9_-]+\\.)+";//识别ENGname的正则表达式
		String regexpCN="[\u0391-\uFFE50-9]+\\.";//识别CHSname的这则表达式
		String regexpYear="[1-2][0-9]{3}\\.";//识别year的正则表达式
		String regexpRes=Resolution.toReguasrExpression();//识别resolution的正则表达式ʽ
		String regexpComSch=CompressionScheme.toReguasrExpression();//识别CompressionScheme的正则表达式
		String regexpVC=VideoCoding.toReguasrExpression();//识别videoCoding的正则表达式
		String regexpAC=AudioCoding.toReguasrExpression();//识别audioCoding的正则表达式
		//匹配ENGname
		pStr=Pattern.compile(regexpEN);
		mStr=pStr.matcher(url);
		if(mStr.find()){
			m.setENGname(mStr.group());
			//消除ENGname中的.
			pStr=Pattern.compile(regexNoD);
			mStr=pStr.matcher(m.getENGname());
			if(mStr.find())
				m.setENGname(mStr.replaceAll(" "));
		}	
		else
			m.setENGname(null);
		//匹配CHSname
		pStr=Pattern.compile(regexpCN);
		mStr=pStr.matcher(url);
		if(mStr.find()){
			m.setCHSname(mStr.group());
			pStr=Pattern.compile(regexNoD);
			mStr=pStr.matcher(m.getCHSname());
			if(mStr.find())
				m.setCHSname(mStr.replaceAll(""));
		}
		else
			m.setCHSname(null);
		//匹配year
		pStr=Pattern.compile(regexpYear);
		mStr=pStr.matcher(url);
		if(mStr.find())
			m.setReleaseDate(mStr.group());
		else
			m.setReleaseDate(null);
		//匹配resolution
		pStr=Pattern.compile(regexpRes,Pattern.CASE_INSENSITIVE+Pattern.UNICODE_CASE);
		mStr=pStr.matcher(url);
		if(mStr.find())
			m.setResolution(Resolution.valueOf
					(new String("e"+mStr.group()).toUpperCase()));
		else
			m.setReleaseDate(null);
		//匹配compressionScheme
		pStr=Pattern.compile(regexpComSch,Pattern.CASE_INSENSITIVE+Pattern.UNICODE_CASE);
		mStr=pStr.matcher(url);
		if(mStr.find())
			m.setCompressionScheme(CompressionScheme.valueOf
					(new String(mStr.group()).toUpperCase()));
		else
			m.setCompressionScheme(null);
		//匹配VideoCoding
		pStr=Pattern.compile(regexpVC,Pattern.CASE_INSENSITIVE+Pattern.UNICODE_CASE);
		mStr=pStr.matcher(url);
		if(mStr.find())
			m.setVideoCoding(VideoCoding.valueOf
					(new String(mStr.group()).toUpperCase()));
		else
			m.setVideoCoding(null);
		//匹配audioCoding
		pStr=Pattern.compile(regexpAC,Pattern.CASE_INSENSITIVE+Pattern.UNICODE_CASE);
		mStr=pStr.matcher(url);
		if(mStr.find()){
			String temp=new String(mStr.group().toUpperCase());
			pStr=Pattern.compile(regexTo_);
			mStr=pStr.matcher(temp);
			if(mStr.find())
				temp=mStr.replaceAll("_");
			m.setAudioCoding(AudioCoding.valueOf(temp));
		}
		else
			m.setAudioCoding(null);
		//设置物理地址
		m.setPhysicalAddress(url);
		//System.out.println(m);
		//录入数据库
		try{
			DBoperator.insert(m);
		}
		catch (Exception e){
			System.out.println("录入失败dir");
			return false;
		}
		return true;
		
	}
	public static  HashSet<String> autoInstertDirectory(String url)
	{
		HashSet<String> tstr;	//待文件目录集合
		HashSet<String> fstr=new HashSet<>();	//无法自动插入的目录集合
		Iterator<String> tstrIt;
		tstr=getFileName(url);
		tstrIt=tstr.iterator();
		for(int i=tstr.size();i>0;i--)
		{
			String path=url+"\\"+tstrIt.next();//正在插入的地址
			//System.out.println(path);
			if(!autoInsertPath(path))
				fstr.add(path);
		}
		
		//tstr.forEach(System.out::println);
		if(fstr.size()==0)
			return null;
		return fstr;
	}
	public static boolean manualInsertion(String eNGname, String cHSname,
			String releaseDate, String physicalAddress, Resolution resolution,
			CompressionScheme compressionScheme, VideoCoding 
			videoCoding, AudioCoding audioCoding) 
	{
		Movie m=new Movie(eNGname, cHSname, releaseDate,
				physicalAddress, resolution, compressionScheme,
				videoCoding, audioCoding);
		try{
			DBoperator.insert(m);
		}
		catch (Exception e){
			System.out.println("录入失败");
			return false;
		}
		return true;
		
	}
	public static void update(String id,String eNGname, String cHSname,
			String releaseDate, String physicalAddress, Resolution resolution,
			CompressionScheme compressionScheme, VideoCoding 
			videoCoding, AudioCoding audioCoding) 
	{
		Movie m=new Movie(eNGname, cHSname, releaseDate,
				physicalAddress, resolution, compressionScheme,
				videoCoding, audioCoding);
		DBoperator.update(m,()->"ENGname=id" );

	}
	public static LinkedList<Movie> orderBy(OrderBy ob)
	{
		return DBoperator.select(()->"*", ()->"", ob);
	}
	//获取文件夹下全部文件名的工具方法
	private static HashSet<String> getFileName(String path) {
		HashSet<String> tstr=new HashSet<>();
		File f = new File(path);
		if (!f.exists()) {
			System.out.println(path + " not exists");
			return null;
		}

		File fa[] = f.listFiles();
		for (int i = 0; i < fa.length; i++) {
			File fs = fa[i];
			if (fs.isDirectory()) {
				tstr.add(fs.getName());
				//System.out.println(fs.getName() + " [目录]");
			} else {
				tstr.add(fs.getName());
				//System.out.println(fs.getName());
			}
		}
		return tstr;
	}

}
