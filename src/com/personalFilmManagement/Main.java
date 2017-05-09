package com.personalFilmManagement;

import java.util.LinkedList;


public class Main {

	public static void main(String[] args) {
		//使用文件夹目录自动添加
		System.out.println("使用文件目录自动添加");
		Requestprocesser.autoInstertDirectory("C:\\Users\\user\\Desktop\\sd");
		//英文名升序输出
		System.out.println("英文名升序输出");
		LinkedList<Movie> outSet;
		outSet=Requestprocesser.orderBy(new OrderBy(OrderType.ASC,"ENGname"));
		outSet.forEach(System.out::println);
		//手动修改或更新数据
		System.out.println("更新colonia数据");
		String id="Colonia";
		Requestprocesser.update(id, null, null, null, null, null, null, null, AudioCoding.TRUEHD);
		//使用英文查询
		System.out.println("查询Coloin的信息");
		System.out.println(Requestprocesser.search("Colonia").toString());
		//使用中文查询
		System.out.println("查询尊严殖民地的信息");
		System.out.println(Requestprocesser.search("尊严殖民地").toString());

	}

}
