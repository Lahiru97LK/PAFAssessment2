package com;

import com.Researcher;
import java.io.IOException; 
import java.util.HashMap; 
import java.util.Map; 
import java.util.Scanner;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



/**
* Servlet implementation class PaymentService
*/
@WebServlet("/ResearcherService")
public class ResearcherService extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	Researcher researcherObj = new Researcher();
   /**
    * @see HttpServlet#HttpServlet()
    */
   public ResearcherService() {
       super();
       // TODO Auto-generated constructor stub
   }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String output = researcherObj.insertResearcher(request.getParameter("researcherId"),      
				request.getParameter("name"),     
				request.getParameter("emailaddress"),
				request.getParameter("workOnProduct"),
				request.getParameter("productCategory"),
				request.getParameter("purposeOfResearch"),
				request.getParameter("previousProducts"));
	 
				response.getWriter().write(output);
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method

		Map paras = getParasMap(request); 
		 
		 String output = researcherObj.updateResearcher(paras.get("hideResearcherIDSave").toString(),     
		    		paras.get("name").toString(),     
		    		paras.get("emailaddress").toString(),
		    		paras.get("workOnProduct").toString(), 
		    		paras.get("productCategory").toString(), 
		    		paras.get("purposeOfResearch").toString(), 
		    		paras.get("previousProducts").toString()); 
		 
		 			response.getWriter().write(output);
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		Map paras = getParasMap(request); 
		 
		 String output = researcherObj.deleteResearcher(paras.get("researcherId").toString());  
		 
		 response.getWriter().write(output);
	}
	
	// Convert request parameters to a Map
		private static Map getParasMap(HttpServletRequest request)
		{
		 Map<String, String> map = new HashMap<String, String>();
		try
		 { 
		 Scanner scanner = new Scanner(request.getInputStream(), "UTF-8");
		 String queryString = scanner.hasNext() ?
		 scanner.useDelimiter("\\A").next() : "";
		 scanner.close();
		 String[] params = queryString.split("&");
		 for (String param : params)
		 { 
		
		String[] p = param.split("=");
		 map.put(p[0], p[1]);
		 }
		 }
		catch (Exception e)
		 {
		 }
		return map;
		}

}
