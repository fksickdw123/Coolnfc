<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="memo.*" import="java.sql.*" import="java.util.*" %>

<%request.setCharacterEncoding("UTF-8");%>
    
<jsp:useBean id="Memo" class="memo.Memo" scope="page"/>
<jsp:useBean id="Bean" class="memo.Bean" scope="page"/>
 <jsp:setProperty property="*" name="Memo"/>
 <% 
 String id = request.getParameter("user_id");                        // request에서 id 파라미터를 가져온다


	if(Bean.logink(Memo)){
		session.setAttribute("id",id);


	if(Bean.logink(Memo)){

		pageContext.forward("sight.jsp");
	}
		else{
	        System.out.println( Memo.getUser_id() + " " + Memo.getUser_pw());
	        String strTemp = Bean.login(Memo);
			out.println(strTemp);
	}
	%>