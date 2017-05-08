package com.personalFilmManagement;

public class OrderBy {
	public OrderType obt;
	public String obId;
	public OrderBy(OrderType obt,String obId) {
		this.obt=obt;
		this.obId=obId;
	}
	public OrderBy() {
		obt=null;
		obId=null;
	}
}
