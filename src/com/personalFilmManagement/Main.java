package com.personalFilmManagement;

import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Main {

	public static void main(String[] args) {
		Pattern  msgsPa =Pattern.compile(CompressionScheme.toReguasrExpression());
		Matcher  msgsMa =msgsPa.matcher("Assassins.Creed.2016.1080p.3D.BluRay.AVC.DTS-HD.MA.7.1");
		msgsMa.find();
		
		//System.out.println(msgsMa.group(0));
		
		Movie m=new Movie();
		m.setAudioCoding(AudioCoding.AAC);
		//DBoperator.update(m,()->"ENGname='Assassins Creed'");
		//DBoperator.insert(m);
		//LinkedList<Movie> res= DBoperator.select(()->"*",()->"",new OrderBy(OrderType.ASC, "ENGname"));
		//Requestprocesser.autoInsertPath("刺客信条.Assassins.Creed.2016.1080p.3D.BluRay.AVC.DTS-HD.MA.7.1");
		//LinkedList<Movie> lm;
		//lm=Requestprocesser.orderBy(new OrderBy(OrderType.ASC,"ENGname"));
		//lm.forEach(System.out::println);
		Requestprocesser.autoInstertDirectory("C:\\Users\\user\\Desktop\\sd");
		LinkedList<Movie> outSet;
		outSet=Requestprocesser.orderBy(new OrderBy(OrderType.ASC,"ENGname"));
		outSet.forEach(System.out::println);
	}

}
