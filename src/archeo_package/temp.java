package archeo_package;

public class temp {

	
//	@GET
//	@Path("/{id}")
//	@Produces(MediaType.APPLICATION_XML)
//	public Response updateEmployeeById(@PathParam("id") Integer id) 
//	{
//	    if(id  < 0){
//	        return Response.noContent().build();
//	    }
//	    France site = new France();
//	     
//	    site.setId(id);
//	    site.setLambert_X(site.getLambert_X());
//		site.setLambert_Y(site.getLambert_Y());
//		site.setRegion(site.getRegion());
//		site.setDepartement(site.getDepartement());
//		site.setCommune(site.getCommune());
//		site.setNom_du_site(site.getNom_du_site());
//		site.setDate_debut(site.getDate_debut());
//		site.setDate_fin(site.getDate_fin());
//		site.setPeriodes(site.getPeriodes());
//		site.setThemes(site.getThemes());
//		site.setType_intervention(site.getType_intervention());
//	     
//	    GenericEntity<France> entity = new GenericEntity<France>(site, France.class);
//	    return Response.ok().entity(entity).build();
//	}
	
	
	
//	Insertion en bdd
//	@POST
//	@Path("/creer")
//	@Consumes(MediaType.APPLICATION_XML)
//	@Produces(MediaType.APPLICATION_XML)
//	public Response creerSite( France site ) throws URISyntaxException 
//	{
//	    if(site == null){
//	        return Response.status(400).entity("Please add employee details !!").build();
//	    }
//	     
//	    if(site.getNom_du_site() == null) {
//	        return Response.status(400).entity("Please provide the employee name !!").build();
//	    }
//	     
//	    return Response.created(new URI("/webresources/sites/"+site.getId())).build();
//	}
//	
//	
////	Modification
//	@PUT
//	@Path("/modifier/{id}")
//	@Consumes(MediaType.APPLICATION_JSON)
//	@Produces(MediaType.APPLICATION_JSON)
//	public France postModif(@PathParam("id") Integer id, France site) {
//
//		France siteU = new France();
//		
//		siteU.setId(id);
//		siteU.setLambert_X(site.getLambert_X());
//		siteU.setLambert_Y(site.getLambert_Y());
//		siteU.setRegion(site.getRegion());
//		siteU.setDepartement(site.getDepartement());
//		siteU.setCommune(site.getCommune());
//		siteU.setNom_du_site(site.getNom_du_site());
//		siteU.setDate_debut(site.getDate_debut());
//		siteU.setDate_fin(site.getDate_fin());
//		siteU.setPeriodes(site.getPeriodes());
//		siteU.setThemes(site.getThemes());
//		siteU.setType_intervention(site.getType_intervention());
//		
//		
//		return site;	
//			
//	}
	
}
