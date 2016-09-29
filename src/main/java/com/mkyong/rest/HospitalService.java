package com.mkyong.rest;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jettison.json.JSONException;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mkyong.rest.AppErrCode;
import com.mkyong.rest.AppException;

public class HospitalService {

	DB myDb = new DBConnection().getDB();
	DBCollection collection = myDb.getCollection("hospital");
	static HospitalService hs = new HospitalService();
	

	public void create(Hospital hp) throws JSONException {
		
		try {
			BasicDBObject obj = new BasicDBObject();
			
			obj.put("h_id", hp.getH_id());
			obj.put("name", hp.getName());
		
			collection.insert(obj);
			
//			org.codehaus.jettison.json.JSONObject jsonObj = new org.codehaus.jettison.json.JSONObject();
//			jsonObj.put("hospitalId", obj.getLong("h_id"));
//			int id = jsonObj.getInt("h_id");

		} catch (AppException e) {
			throw e;
		} catch (Exception e) {
			throw new AppException(e); 
		}
	}
	
	public Hospital readOne(int h_id) throws JSONException {
		
		try {
			
			List<Hospital> hospitals = readAll();
			for (Hospital hospital : hospitals) {
				if (hospital.getH_id() == h_id) {
					return hospital;
				}
			}
			
			return null;
		} catch (AppException e) {
			throw e;
		} catch (Exception e) {
			throw new AppException(e);
		}
	}
		
	
	public List<Hospital> readAll() throws  JSONException {

		try {
			
			DBCursor documents = collection.find();
			ArrayList<Hospital> hospitals = new  ArrayList<Hospital>();
			while (documents !=null && documents.hasNext()) {
	
				 BasicDBObject obj = (BasicDBObject) documents.next();
				 org.codehaus.jettison.json.JSONObject jsonObj = new org.codehaus.jettison.json.JSONObject();
				 jsonObj.put("hospitalId", obj.getLong("h_id"));
				 jsonObj.put("hospitalName", obj.getString("name"));
				 System.out.println(jsonObj);
				 Hospital hp = new Hospital();
					hp.setH_id(jsonObj.getInt("hospitalId"));
					hp.setName(jsonObj.getString("hospitalName"));
					hospitals.add(hp);
					
			}
			
			return hospitals;

		} catch (AppException e) {
			throw e;
		} catch (Exception e) {
			throw new AppException(e);
		}
	} 
	
	
	public void update(int h_id, String name) {
		
		if (name == null) { throw new AppException(AppErrCode.HOSPITAL_NOT_FOUND); }
		BasicDBObject newDocument = new BasicDBObject();
		collection.update(new BasicDBObject().append( "h_id" , h_id), 
						  newDocument.append("$set", new BasicDBObject().append("name", name)));
		 
		System.out.println(newDocument);
	}
	
	public void delete(int h_id) {
		
		try {
		
			BasicDBObject deleteQuery = new BasicDBObject("h_id", h_id);
			collection.remove(deleteQuery);
		} catch (Exception e) {
			throw new AppException(AppErrCode.HOSPITAL_NOT_FOUND);
		}
	}
}
