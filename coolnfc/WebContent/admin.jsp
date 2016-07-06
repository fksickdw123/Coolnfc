<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*"%>
    <%request.setCharacterEncoding("UTF-8"); %>
<jsp:useBean id="Memo" class="memo.Memo" scope="page"/>
<jsp:useBean id="Bean" class="memo.Bean" scope="page"/>
 <jsp:setProperty property="*" name="Memo"/>
<%
	String type = request.getParameter("type");
	if(type.equals("1")){
		String result = Bean.openshut(Memo);
		System.out.print(result);
		out.println(result);
	}else if(type.equals("2")){
		String result = Bean.openoffer(Memo);
		System.out.print(result);
		out.println(result);
	}else if(type.equals("3")){
		Bean.openreal(Memo);
	}else if(type.equals("4")){
		String result = Bean.closeoffer(Memo);
		System.out.print(result);
		out.println(result);
	}
	else if(type.equals("5")){
		Bean.closereal(Memo);
	}else{
		out.println("0");
	}
%>