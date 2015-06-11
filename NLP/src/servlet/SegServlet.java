package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mmseg.algorithm.Dictionary;
import com.mmseg.algorithm.MMsegComplex;
import com.mmseg.algorithm.MMsegSimple;

public class SegServlet extends HttpServlet {
private  Dictionary dict;
private MMsegComplex complex;
private MMsegSimple simple;
	public SegServlet() {
		super();
	}


	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request,response);
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		 request.setCharacterEncoding("utf-8");
		 response.setContentType("text/html;charset=utf-8");
		 String text=request.getParameter("text");
		 String select=request.getParameter("select");
		 List<String>result=null;
		 if(select.equals("Complex")){
			 result=complex.analysis(text,dict);
		 }else if(select.equals("Simple")){
			 result=simple.analysis(text, dict);
		 }
		 StringBuilder sb=new StringBuilder();
		 for(int i=0;i<result.size();i++){
			 sb.append(result.get(i)+"  |  ");
		 }
		 PrintWriter out = response.getWriter();
		 out.write(sb.toString());
		 out.flush();
		 out.close();
	}

	
	public void init() throws ServletException {
		System.out.println("it is initing");
		dict=new Dictionary();
		String path=this.getServletContext().getRealPath("/");
		dict.loadDictionary(path+"\\data\\");
		complex=new MMsegComplex();
		simple=new MMsegSimple();
	}

}
