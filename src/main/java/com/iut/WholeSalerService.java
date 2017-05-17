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
    public Response addStock(@QueryParam("corr") String corr,@QueryParam("isbn") String isbn,
    		@QueryParam("quantite") String quantite,@QueryParam("stock") String stock) {
		Client client = ClientBuilder.newClient( );
		int qte = Integer.parseInt(quantite);
		int bookStock = Integer.parseInt(stock);

		String message = " ";
		if(bookStock > qte){
			qte = bookStock - qte;
		}else{
			int diff = qte - bookStock;
			message = diff + "livres commandés pour le client + 10 supplémentaires";
			qte = 10;
		}
		
		String jsonObj = "{";
		jsonObj += "isbn : "+isbn;
		jsonObj += ",corr : "+corr;
		jsonObj += ",stock : "+qte;
		jsonObj += ",message : "+message;
		jsonObj += "}";
		
		WebTarget webTarget = client.target("http://1-dot-inf63app9.appspot.com/rest/shopping");
		Response r = webTarget.request().put(Entity.json(jsonObj),Response.class);
		return Response.status(r.getStatus()).type(MediaType.APPLICATION_JSON).entity(r.readEntity(String.class)).build();
    }
}
