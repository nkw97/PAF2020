package com.codes.projec.mangepatients;
	
	import java.sql.SQLException;
	import java.util.ArrayList;
	import java.util.Arrays;
	import java.util.List;

	import javax.ws.rs.Consumes;
	import javax.ws.rs.DELETE;
	import javax.ws.rs.GET;
	import javax.ws.rs.POST;
	import javax.ws.rs.PUT;
	import javax.ws.rs.Path;
	import javax.ws.rs.PathParam;
	import javax.ws.rs.Produces;
	import javax.ws.rs.core.MediaType;

	@Path("patientservice")
	public class PtientService {
		
		Patientmokup mockup = new Patientmokup();
		
		@GET
		@Produces(MediaType.APPLICATION_JSON)
		public List<Patient> getPatient() throws SQLException{
			
			
			return mockup.getPatients();
			
			
		}
		
		@POST
		@Path("patient")
		public Patient createnewPatient(Patient p) throws Exception {
			mockup.createpatient(p);
			return p;
		}

		
	/*	@GET
		@Path("patient/{pname}")
		public Patient getPatient(@PathParam("pname") String pname) {
			
			
			return mockup.getPatient(pname);
		}
		
		*/
		
		
		
		@PUT
		@Path("patient")
		@Consumes(MediaType.APPLICATION_JSON)
		public Patient updatePatient(Patient p) throws Exception  {
			mockup.updatepatient(p);
			return p;
		}
		
		
		@DELETE
		@Path("patient/{name}")
		@Consumes(MediaType.APPLICATION_JSON)
		public void dropPatient(@PathParam("name") String name) throws Exception  {
			mockup.droppatient(name);
		
		}

}
