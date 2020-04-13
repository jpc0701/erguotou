package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.RecordService;

/**
 * Servlet implementation class getLastestRecord
 */
@WebServlet("/getLastestRecord")
public class getLastestRecord extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public getLastestRecord() {
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
		if (paramters.containsKey("startDate") && paramters.containsKey("endDate")) {
			out.println(new RecordService().getRecordsByDate(paramters.get("startDate")[0], paramters.get("endDate")[0]).toString());
		}else if (paramters.containsKey("startDate") && !paramters.containsKey("endDate")) {
			out.println(new RecordService().getRecordsToNow(paramters.get("startDate")[0]).toString());
		}else if (paramters.containsKey("type")){
			out.println(new RecordService().get24hRecords().toString());
		} else {
			String tString = new RecordService().getLstestStatus().toString();
			out.println(tString);
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
