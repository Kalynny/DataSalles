package br.com.datasalles.util;



import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;

//http://localhost:8080/datasalles/rest
@ApplicationPath("rest")
public class DatasallesResourceConfig extends ResourceConfig {
	public DatasallesResourceConfig(){
		packages("br.com.datasalles.service");
	}
}