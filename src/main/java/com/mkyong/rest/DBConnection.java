package com.mkyong.rest;

import com.mongodb.DB;
import com.mongodb.Mongo;

public class DBConnection {

	@SuppressWarnings("deprecation")
	public DB getDB() {
		Mongo mongo;
		try {
			mongo = new Mongo("localhost");
			return mongo.getDB("mydb");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
