<%@ page language="java" contentType="text/html; charset=UTF-8" 
    import="java.util.*"  import="memo.*"%>
    
<% request.setCharacterEncoding("UTF-8"); %>
<jsp:useBean id="ab" scope="page" class="memo.Bean" />
<jsp:useBean id="memo" class="memo.Memo"/>
<jsp:setProperty name="memo" property="*" />
<%
 String action=request.getParameter("action");

String class_code=request.getParameter("class_code");
String lecroom=request.getParameter("lecroom");
if(action.equals("list")){
	 ArrayList datas= new ArrayList();
	 datas = ab.getDB();
	  
	  request.setAttribute("datas", datas);
	  
	  pageContext.forward("sight.jsp");
	 
}
	else if(action.equals("insert")){
	 if(ab.insertDB(memo)){
		  response.sendRedirect("control.jsp?action=list");
	  }
		  else{
			  throw new Exception("DB 입력 오류");
		
		  }
	 }
	 else if(action.equals("insertlec")){
			 if(ab.insertlecDB(memo)){
				  response.sendRedirect("control.jsp?action=list");
			  }
			  else{
				  throw new Exception("DB 입력 오류");
			  }

	}
	
 	else if(action.equals("delete")){
	 if(ab.deleteDB(class_code)){
		 response.sendRedirect("control.jsp?action=list");
	 }
	 else
		 throw new Exception("DB 삭제 오류");
	 
 	}

 	else if(action.equals("deletelec")){
	 if(ab.deletelecDB(lecroom)){
		 response.sendRedirect("control.jsp?action=list");
	 }
	 else
		 throw new Exception("DB 삭제 오류");
	 
 	}

 	else if(action.equals("logout")){
 		session.invalidate();
 		 response.sendRedirect("loginsight.jsp");
 	}


 else{
 }
%>
