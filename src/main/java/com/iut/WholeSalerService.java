package com.iut;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("wholeSaler")
public class WholeSalerService {
	
	@GET
    @Produces(MediaType.APPLICATION_JSON)
    public String addStock(@QueryParam("corr") String corr,@QueryParam("isbn") String isbn,
    		@QueryParam("quantite") String quantite) {
		Client client = ClientBuilder.newClient( );
		int qte = Integer.parseInt(quantite);
		
		String jsonObj = "{";
		jsonObj += "isbn : "+isbn;
		jsonObj += ",corr : "+corr;
		jsonObj += ",stock : "+qte+1;
		jsonObj += "}";
		
		WebTarget webTarget = client.target("http://1-dot-inf63app9.appspot.com/rest/shopping");
		webTarget.request(MediaType.APPLICATION_JSON).put(Entity.json(jsonObj),Response.class);
		
		return jsonObj;
    }
}
