package com.mkyong.rest;
 
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.mkyong.rest.AppException;
import com.mkyong.rest.JsonUtil;


@Path("/myapp")
public class HelloWorldService {
 
	HospitalService hs = new HospitalService();
    
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON) 
	public String getHospitals()  {
		
		List<Hospital> hospitals =null;
		try {
			hospitals = hs.readAll();
			return JsonUtil.toJson(hospitals);
		} catch (Exception e) {
			throw new AppException(e);
		}
	} 
	
	@GET
	@Path("/{h_id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Hospital getHospitaById(@PathParam("h_id") int id) {
		Hospital hospitals = null;
		 try {
			 hospitals = hs.readOne(id);
		
		} catch (Exception e) {
			throw new AppException(e);
		}
		 return hospitals;
	}
	
	@POST
	@Path("/hosCreate")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String createHospital(Hospital hp) {

		String result = "Creation Failed";
		try {
			
//			Hospital hospital = JsonUtil.fromJson(reqJson.toString(),  Hospital.class);
			hs.create(hp);
			result = "Creation Success";
			return result;
			
		} catch (Exception e) {
			return result;
		}
	}
	
	@PUT
	@Path("/hosUpdate")
//	@Produces(MediaType.APPLICATION_JSON)
//	@Consumes(MediaType.APPLICATION_JSON)

	public String UpdateHospital (Hospital hp) {

		try {
//			Hospital hospital = JsonUtil.fromJson(reqJson.toString(),  Hospital.class);
			hs.update(hp.getH_id(), hp.getName());
			return "Successfully created";
		} catch (Exception e) {
			throw new AppException(e);
		}
	}
	
	@DELETE
	@Path("/{h_id}")
	@Produces(MediaType.APPLICATION_JSON)
	public void DeleteHospital(@PathParam("h_id") int id) {
		
		try {
			hs.delete(id);
		} catch (Exception e) {
			throw new AppException(e);
		}
	}
}