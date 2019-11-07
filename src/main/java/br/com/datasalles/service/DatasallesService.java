package br.com.datasalles.service;


import java.sql.Date;
import java.text.SimpleDateFormat;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

// http://localhost:8080/Datasalles/rest/datasalles

@Path("datasalles")
public class DatasallesService {
	@GET
	public String exibir(){
		return "Sistema Data Salles";
	}
	
	public String formatData(String formato, java.util.Date data) {
		  
		return new SimpleDateFormat(formato).format(data); 
	}
}