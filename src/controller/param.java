package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pojo.Param;
import service.ParamService;

/**
 * Servlet implementation class getLatestStatus
 */
@WebServlet("/param")
public class param extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public param() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html; charset=UTF-8");
		/* 允许跨域的主机地址 */
		response.setHeader("Access-Control-Allow-Origin", "*");  
		/* 允许跨域的请求方法GET, POST, HEAD 等 */
		response.setHeader("Access-Control-Allow-Methods", "*");  
		/* 重新预检验跨域的缓存时间 (s) */
		response.setHeader("Access-Control-Max-Age", "3600");  
		/* 允许跨域的请求头 */
		response.setHeader("Access-Control-Allow-Headers", "*");  
		/* 是否携带cookie */
		response.setHeader("Access-Control-Allow-Credentials", "true");  
		
		// 实际的逻辑是在这里
		PrintWriter out = response.getWriter();
		Map<String, String[]> paramters = request.getParameterMap();
		if (paramters.containsKey("keys")) {
			out.println(new ParamService().getParams(paramters.get("keys")[0].split(",")).toString());
		} else if (paramters.containsKey("param")) {
			String[] tempString = paramters.get("param")[0].split("-");
			Param param = new Param(tempString[0], tempString[1], tempString[2]);
			new ParamService().updateParam(param);
		} else {
			out.println(new ParamService().getNormalParams().toString());
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
