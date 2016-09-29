package com.mkyong.rest;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Hospital {

	private int h_id;
	private String name;
	
	public int getH_id() {
		return h_id;
	}

	public void setH_id(int h_id) {
		this.h_id = h_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
