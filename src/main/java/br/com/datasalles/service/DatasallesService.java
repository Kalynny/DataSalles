package br.com.datasalles.service;


import javax.ws.rs.GET;
import javax.ws.rs.Path;

// http://localhost:8080/Datasalles/rest/datasalles

@Path("datasalles")
public class DatasallesService {
	@GET
	public String exibir(){
		return "Sistema Data Salles";
	}
}