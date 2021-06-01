//package archeo_package;
//
//import java.io.IOException;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//import java.util.ArrayList;
//
//import javax.servlet.ServletException;
//import javax.ws.rs.Consumes;
//import javax.ws.rs.GET;
//import javax.ws.rs.POST;
//import javax.ws.rs.PUT;
//import javax.ws.rs.Path;
//import javax.ws.rs.PathParam;
//import javax.ws.rs.Produces;
//import javax.ws.rs.core.MediaType;
//import javax.ws.rs.core.Response;
//
//import org.json.JSONArray;
//import org.json.JSONObject;
//
//import com.google.gson.Gson;
//
//import archeo_package.France;
//
//@Path("/sites")
//public class FranceService2 {
//
//	
//	
//
//	/*
//	@GET
//	public Response getMsg() {
//
//		String output = "Jersey repond  depuis pizza package pizza liste: " ;
//
//		return Response.status(200).entity(output).build();
//
//	}*/
//	@GET
//	@Produces({ MediaType.TEXT_HTML })
//    public String  getListeMonuments() throws ClassNotFoundException, SQLException, ServletException, IOException {
//
//
//			
//			Class.forName("oracle.jdbc.driver.OracleDriver");
//			System.out.println("chargement du pilote : ");
//			
//			Connection conn = null;
//			
//			String url = "jdbc:oracle:thin:@localhost:1521:Xe";
//			
//			String Passwd = "dina9599";
//			String userId ="SYSTEM";
//			
//			conn = DriverManager.getConnection(url,userId,Passwd);
//			if(conn!=null)System.out.println("connexion");
//			
//			//
//			Statement statement = conn.createStatement();
//			//execution d'une requête
//						
//			String query ="select * from  archeo.FRANCE";
//			ResultSet resulset = statement.executeQuery(query);
//		
//			 
//			JSONArray arrayMonuments = new JSONArray();
//			
//			JSONObject jsonObject = new JSONObject();
//
//			
//			while(resulset.next()){
//				
//				JSONObject record = new JSONObject();
//				record.put("ID", resulset.getInt(1));
//				record.put("Lambert_X", resulset.getString(2));
//				record.put("Lambert_Y", resulset.getString(3));
//				record.put("Region", resulset.getString(4));
//				record.put("Departement", resulset.getString(5));
//				record.put("Commune", resulset.getString(6));
//				record.put("Nom_du_site", resulset.getString(7));
//				arrayMonuments.put(record);
//				
//				
//				System.out.println(resulset.getString(2)+ " "+ resulset.getString(3));
//				
//			} 
//			String Json =  jsonObject.put("liste des sites archéologiques", arrayMonuments).toString();
//
//			return Json;
//		
//}
//	
//	
////	@GET
////	@Produces({ MediaType.TEXT_HTML })
////    public String  getListePizza() throws ClassNotFoundException, SQLException, ServletException, IOException {
////
////
////ArrayList<Pizza> listePizzas = new ArrayList<Pizza>();
////			
////			Class.forName("oracle.jdbc.driver.OracleDriver");
////			System.out.println("chargement du pilote : ");
////			
////			Connection conn = null;
////			
////			String url = "jdbc:oracle:thin:@localhost:1521:Xe";
////			
////			String Passwd = "dina9599";
////			String userId ="SYSTEM";
////			
////			conn = DriverManager.getConnection(url,userId,Passwd);
////			if(conn!=null)System.out.println("connexion");
////			
////			//
////			Statement statement = conn.createStatement();
////			//execution d'une requête
////						
////			String query ="select * from  pizzaweb.PIZZA";
////			ResultSet resulset = statement.executeQuery(query);
////			
////			// traitement des résultats
////			
////			String jsonword ;
////			jsonword ="{\"liste des pizza en json\" :[";
////			 
////			
////			while(resulset.next()){
////				
////				System.out.println(resulset.getString(2)+ " "+ resulset.getString(3));
////				
////				Pizza maPizza= new Pizza(resulset.getInt(1),resulset.getString(2),resulset.getFloat(3) );
////				listePizzas.add(maPizza);
////			}
////			jsonword = jsonword.substring(0,jsonword.length()-1);
////			jsonword +="]}";
////			//fermeture de la connexion
////			System.out.println(jsonword);
////			conn.close();
////			String jsonPizza = new Gson().toJson(listePizzas);   
////			System.out.print(jsonPizza);
////			return jsonPizza;
////		
////		
////}
//	
//	
//	@GET
//	@Path("/{param}")
//	public String getMsg(@PathParam("param") String msg) throws ClassNotFoundException, SQLException, ServletException, IOException {
//
//
//			ArrayList<France> listePizzas = new ArrayList<France>();
//			
//			Class.forName("oracle.jdbc.driver.OracleDriver");
//			System.out.println("chargement du pilote : ");
//			
//			Connection conn = null;
//			
//			String url = "jdbc:oracle:thin:@localhost:1521:Xe";
//			
//			String Passwd = "dina9599";
//			String userId ="SYSTEM";
//			
//			conn = DriverManager.getConnection(url,userId,Passwd);
//			if(conn!=null)System.out.println("connexion");
//			
//			//
//			Statement statement = conn.createStatement();
//			//execution d'une requête
//			
////			String query ="select * from  pizzaweb.PIZZA WHERE \"id\" =" + msg;
//			String query ="select * from  archeo.FRANCE WHERE \"id\" =" + msg;
//			ResultSet resulset = statement.executeQuery(query);
//			
//			 
//			JSONArray arrayMonuments = new JSONArray();
//			
//			JSONObject jsonObject = new JSONObject();
//
//			
//			while(resulset.next()){
//				
//				JSONObject record = new JSONObject();
//				record.put("ID", resulset.getInt(1));
//				record.put("Lambert_X", resulset.getString(2));
//				record.put("Lambert_Y", resulset.getString(3));
//				record.put("Region", resulset.getString(4));
//				record.put("Departement", resulset.getString(5));
//				record.put("Commune", resulset.getString(6));
//				record.put("Nom_du_site", resulset.getString(7));
//				arrayMonuments.put(record);
//				
//				
//				System.out.println(resulset.getString(2)+ " "+ resulset.getString(3));
//				
//			} 
//			String Json =  jsonObject.put("liste des sites archéologiques", arrayMonuments).toString();
//
//			return Json;
//			
//			
//			
//			
//			
////			ResultSet resulset = statement.executeQuery(query);
////			
////			// traitement des résultats
////			
////			String jsonword ;
////			jsonword ="{\"liste des SITES en json\" :[";
////			 
////			
////			while(resulset.next()){
////				
////				System.out.println(resulset.getString(2)+ " "+ resulset.getString(3)+ " "+ resulset.getString(4)+ " "+ resulset.getString(5));
////				
////				
//////				France maPizza= new France(resulset.getInt(1),resulset.getString(2),resulset.getFloat(3) );
//////				listePizzas.add(maPizza);
////			}
////			jsonword = jsonword.substring(0,jsonword.length()-1);
////			jsonword +="]}";
////			//fermeture de la connexion
////			System.out.println(jsonword);
////			conn.close();
////			String jsonPizza = new Gson().toJson(listePizzas);   
////			System.out.print(jsonPizza);
////			return jsonPizza;
//		
//		
//
//	}
//	
//	
//	
//	
//	@POST
//	@Path("/creation")
//	@Consumes(MediaType.APPLICATION_JSON)
//	@Produces(MediaType.APPLICATION_JSON)
//	public France postCreation(France site) {
//
//		return site;
//			
//			
//	}
//	
//	
//	@PUT
//	@Path("/modifier")
//	@Consumes(MediaType.APPLICATION_JSON)
//	@Produces(MediaType.APPLICATION_JSON)
//	public France postModif(France site) {
//
//		site.setNom_du_site(site.getNom_du_site());
//		return site;	
//			
//	}
//	
//	
////	
////	@DELETE
////	@Path("delete/{param}")
////	public String deletePizza(@PathParam("param") String msg) throws ClassNotFoundException, SQLException, ServletException, IOException {
////
////			Class.forName("oracle.jdbc.driver.OracleDriver");
////			System.out.println("chargement du pilote : ");
////			
////			Connection conn = null;
////			
////			String url = "jdbc:oracle:thin:@localhost:1521:Xe";
////			
////			String Passwd = "dina9599";
////			String userId ="SYSTEM";
////			
////			conn = DriverManager.getConnection(url,userId,Passwd);
////			if(conn!=null)System.out.println("connexion");
////			
////			//
////			Statement statement = conn.createStatement();
////			//execution d'une requête
////						
////			String query ="delete from  pizzaweb.PIZZA WHERE \"NroPizz\" =" + msg;
////			ResultSet resulset = statement.executeQuery(query);
////			
////			// traitement des résultats
////			
////			
////			conn.close();
////			return getListePizza();
////		
////	}
//	
//	
//	
//	@GET
//	@Path("/{param1}/{param2}")
//	public Response getMsg2(@PathParam("param1") String msg, @PathParam("param2") int num) {
//
//		String output = "Jersey repond  depuis pizza package  fn2: " + msg + " "+ num;
//
//		return Response.status(200).entity(output).build();
//
//	}
//	
//	
//	
//	
//}
