package com.doers.resource;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.doers.model.TextAnalysis;
import com.doers.model.TextData;
import com.doers.utility.AnalysisTask;

@Path("team")
public class Team {
	
	Map<Integer, TextData> textDataMap = new HashMap<Integer, TextData>();
	Map<Integer, TextAnalysis> textAnalysisMap = new HashMap<Integer, TextAnalysis>();
	
	public Team(){		
	}
	
	@POST
	@Path("v1")
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addText(TextData textdata){
		int id = textDataMap.size()+1;
		textdata.setId(id);
		TextAnalysis textAnalysis =  new TextAnalysis();
		textAnalysis.setId(id);
		textAnalysis.setText(textdata.getText());	
		textAnalysis.setStatus("in progress");
		textDataMap.put(id, textdata);
		textAnalysisMap.put(id, textAnalysis);	
		Thread analysisThread = new Thread(new AnalysisTask(textAnalysis,4));
		analysisThread.start();
		return Response.ok("{ \"id\":\""+id+"\"").build();		
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("v1/{id}")
	public TextAnalysis getAnalysis(@PathParam("id") int id){
		TextAnalysis textAnalysis = textAnalysisMap.get(id);
		return textAnalysis;		
	}
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("v2")
	public Response getHello(){		
		return Response.ok("Hello World").build();		
	}
	
	

}
