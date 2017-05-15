package com.iut;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("wholeSaler")
public class WholeSalerService {
	
	@GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt() {
        return "Hello, whole!";
    }
}
