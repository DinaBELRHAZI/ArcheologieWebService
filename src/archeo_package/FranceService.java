package archeo_package;

import java.awt.List;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONArray;
import org.json.JSONObject;

import com.google.gson.Gson;

import archeo_package.France;

@Path("/sites")
public class FranceService {


	@GET
	@Produces({ MediaType.APPLICATION_JSON })
    public String  getListeSites() throws ClassNotFoundException, SQLException, ServletException, IOException {


			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("chargement du pilote : ");
			
			Connection conn = null;
			
			String url = "jdbc:oracle:thin:@localhost:1521:Xe";
			
			String Passwd = "archeo";
			String userId ="ARCHEO";
			
			conn = DriverManager.getConnection(url,userId,Passwd);
			if(conn!=null)System.out.println("connexion");
			
			//
			Statement statement = conn.createStatement();
			//execution d'une requête
						
			String query ="select * from  archeo.FRANCE";
			ResultSet resulset = statement.executeQuery(query);
		
			 
			JSONArray arraySites = new JSONArray();
			
			JSONObject jsonObject = new JSONObject();

			
			while(resulset.next()){
				
				JSONObject record = new JSONObject();
				
				String Lambert_X = resulset.getString(2);
	            String Lambert_Y = resulset.getString(3);
	            
//	            Remplacement de la virgule en point
				String X = Lambert_X.replace(',', '.');
				String Y = Lambert_Y.replace(',', '.');
				
//				Convertion Lambert93 vers WSG84
				Map<String, Double> coord = Convert.convert(X, Y);
				Double lat = coord.get("lat");
				Double lng = coord.get("lng");
				System.out.println(X + " & " + Y);

//				Récupère les données de la bdd
				record.put("ID", resulset.getInt(1));
				record.put("Lambert_X", lat);
				record.put("Lambert_Y", lng);
				record.put("Region", resulset.getString(4));
				record.put("Departement", resulset.getString(5));
				record.put("Commune", resulset.getString(6));
				record.put("Nom_du_site", resulset.getString(7));
				record.put("Date_debut", resulset.getString(8));
				record.put("Date_fin", resulset.getString(9));
				record.put("Periodes", resulset.getString(10));
				record.put("Themes", resulset.getString(11));
				record.put("Type_intervention", resulset.getString(12));
				arraySites.put(record);
				
				
				System.out.println(resulset.getString(2)+ " "+ resulset.getString(3));
				
			} 
			String Json =  jsonObject.put("liste des sites archéologiques", arraySites).toString();

			return Json;
		
	}
	
	
	
	
	@GET
	@Path("/{param}")
	public String getMsg(@PathParam("param") String msg) throws ClassNotFoundException, SQLException, ServletException, IOException {


			ArrayList<France> listePizzas = new ArrayList<France>();
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("chargement du pilote : ");
			
			Connection conn = null;
			
			String url = "jdbc:oracle:thin:@localhost:1521:Xe";
			
			String Passwd = "archeo";
			String userId ="ARCHEO";
			
			conn = DriverManager.getConnection(url,userId,Passwd);
			if(conn!=null)System.out.println("connexion");
			
			
			Statement statement = conn.createStatement();
			

			String query ="select * from  archeo.FRANCE WHERE \"id\" =" + msg;
			//execution d'une requête
			ResultSet resulset = statement.executeQuery(query);
			
			 
			JSONArray arraySites = new JSONArray();
			
			JSONObject jsonObject = new JSONObject();

			
			while(resulset.next()){
				
JSONObject record = new JSONObject();
				
				String Lambert_X = resulset.getString(2);
	            String Lambert_Y = resulset.getString(3);
	            
//	            Remplacement de la virgule en point
				String X = Lambert_X.replace(',', '.');
				String Y = Lambert_Y.replace(',', '.');
				
//				Convertion Lambert93 vers WSG84
				Map<String, Double> coord = Convert.convert(X, Y);
				Double lat = coord.get("lat");
				Double lng = coord.get("lng");
				System.out.println(X + " & " + Y);

//				Récupère les données de la bdd
				record.put("ID", resulset.getInt(1));
				record.put("Lambert_X", lat);
				record.put("Lambert_Y", lng);
				record.put("Region", resulset.getString(4));
				record.put("Departement", resulset.getString(5));
				record.put("Commune", resulset.getString(6));
				record.put("Nom_du_site", resulset.getString(7));
				record.put("Date_debut", resulset.getString(8));
				record.put("Date_fin", resulset.getString(9));
				record.put("Periodes", resulset.getString(10));
				record.put("Themes", resulset.getString(11));
				record.put("Type_intervention", resulset.getString(12));
				arraySites.put(record);
				
				
				System.out.println(resulset.getString(2)+ " "+ resulset.getString(3));
				
			} 
			String Json =  jsonObject.put("liste des sites archéologiques", arraySites).toString();

			return Json;
			
	}
	
	
	
	@POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
	@Path("/add")
	public void creerSite(String site) throws ClassNotFoundException, SQLException, ServletException, IOException {

            
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			Connection conn = null;
			
			String url = "jdbc:oracle:thin:@localhost:1521:Xe";
			
			String Passwd = "archeo";
			String userId ="ARCHEO";
			
			conn = DriverManager.getConnection(url,userId,Passwd);
			if(conn!=null)
				System.out.println("connexion");
			
			Statement statement = conn.createStatement();
			
			JSONObject requestedJSON = new JSONObject(site);
            
            Gson g = new Gson(); 
            France france = g.fromJson(site, France.class);
            
//          Récupération des données envoyées par POST
            String Commune = france.getCommune();
            String Lambert_X = france.getLambert_X();
            String Lambert_Y = france.getLambert_Y();
            String Region = france.getRegion();
            String Departement = france.getDepartement();
            String Nom = france.getNom_du_site();
            String DateDebut = france.getDate_debut();
            String DateFin = france.getDate_fin();
            String Periodes = france.getPeriodes();
            String Themes = france.getThemes();
            String TypeIntervention = france.getType_intervention();
			
			
			
//			Récupère dernier id de la table FRANCE
			String lastId = "select * from archeo.FRANCE where \"id\" = (select max(\"id\") from archeo.FRANCE)";
			ResultSet LastId = statement.executeQuery(lastId);
			
			List test1 = new List();
			
//			Insertion id dans la list test1
			while(LastId.next()){

				test1.add(LastId.getString(1));
				System.out.println(LastId.getString(1));
				
			} 

//			Cast string en int
			Integer id = Integer.parseInt(test1.getItem(0).toString());	
//			Incrémentation
			int Idtoinsert = id +1;
			
			
			String query = "INSERT INTO archeo.FRANCE (\"id\", \"Lambert_X\", \"Lambert_Y\", \"Region\", \"Departement\", \"Commune\", \"Nom_du_site\", \"Date_debut\", \"Date_fin\", \"Periodes\", \"Themes\", \"Type_intervention\") values(?,?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement pstmt = conn.prepareStatement(query);
			
			pstmt.setInt (1, Idtoinsert);
			pstmt.setString(2, Lambert_X);
			pstmt.setString(3, Lambert_Y);
			pstmt.setString(4, Region);
			pstmt.setString(5, Departement);
			pstmt.setString(6, Commune);
			pstmt.setString(7, Nom);
			pstmt.setString(8, DateDebut);
			pstmt.setString(9, DateFin);
			pstmt.setString(10, Periodes);
			pstmt.setString(11, Themes);
			pstmt.setString(12, TypeIntervention);
			
//			Execution de la requête préparée
			pstmt.executeUpdate();
			conn.close();
			
	}
	
	
	
	
	@PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
	@Path("/update/{param}")
	public void modifierSite(String site, @PathParam("param") String msg) throws ClassNotFoundException, SQLException, ServletException, IOException {

            
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			Connection conn = null;
			
			String url = "jdbc:oracle:thin:@localhost:1521:Xe";
			
			String Passwd = "archeo";
			String userId ="ARCHEO";
			
			conn = DriverManager.getConnection(url,userId,Passwd);
			if(conn!=null)
				System.out.println("connexion");
			
			Statement statement = conn.createStatement();
			
			JSONObject requestedJSON = new JSONObject(site);
            
            Gson g = new Gson(); 
            France france = g.fromJson(site, France.class);
            
//          Récupération des données envoyées par POST
            String Commune = france.getCommune();
            String Lambert_X = france.getLambert_X();
            String Lambert_Y = france.getLambert_Y();
            String Region = france.getRegion();
            String Departement = france.getDepartement();
            String Nom = france.getNom_du_site();
            String DateDebut = france.getDate_debut();
            String DateFin = france.getDate_fin();
            String Periodes = france.getPeriodes();
            String Themes = france.getThemes();
            String TypeIntervention = france.getType_intervention();
			
           
			String query = "update archeo.FRANCE set \"Lambert_X\" = ?, \"Lambert_Y\"=?, \"Region\" = ?, \"Departement\" = ?, \"Commune\" = ?, \"Nom_du_site\" = ?, \"Date_debut\"=?,\"Date_fin\" = ?, \"Periodes\" = ?, \"Themes\" = ?, \"Type_intervention\" = ? where \"id\" = " + msg;
            PreparedStatement pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, Lambert_X);
			pstmt.setString(2, Lambert_Y);
			pstmt.setString(3, Region);
			pstmt.setString(4, Departement);
			pstmt.setString(5, Commune);
			pstmt.setString(6, Nom);
			pstmt.setString(7, DateDebut);
			pstmt.setString(8, DateFin);
			pstmt.setString(9, Periodes);
			pstmt.setString(10, Themes);
			pstmt.setString(11, TypeIntervention);
			
//			Execution de la requête préparée
			pstmt.executeUpdate();
			conn.close();
			
	}
	
}
