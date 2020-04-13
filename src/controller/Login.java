package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		Map<String, String[]> paramters = request.getParameterMap();
		JSONObject info = new JSONObject();
		int code = 0;
		String message = "登陆成功！";
		if (paramters.containsKey("username") && paramters.containsKey("password")) {
			String username = paramters.get("username")[0];
			String password = paramters.get("password")[0];
			if (username != "jpc0701") {
				code = -2;
				message = "用户名错误！";
			} else if (password != "jpc19930701") {
				code = -3;
				message = "密码错误！";
			} else {
				code = 0;
				message = "登陆成功！";
				
			}
		} else {
			code = -1;
			message = "请填写正确格式的参数！";
		}
		info.put("code", code);
		info.put("message", message);
		out.println(info.toJSONString());
	}

}
