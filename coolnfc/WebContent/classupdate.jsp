<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*"   import="memo.Memo"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:useBean id="Memo" class="memo.Memo" scope="page"/>
<jsp:useBean id="Bean" class="memo.Bean" scope="page"/>
 <jsp:setProperty property="*" name="Memo"/>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="design.css" type="text/css" media="screen"/>
<title>수정 </title>
</head>
<body>
  <!-- Main Page Container -->
  <div class="page-container">
 
   <!-- Implement this header in your Multiflex-3 Basic or Full Layout:                        -->
   <!-- 1. Copy the marked rows below                                                          -->
   <!-- 2. Paste and replace marked rows in "layoutNN_basic.html" or "layoutNN_full.html" file -->
   <!-- 3. Open CSS-file "header3_setup.css", and follow its instructions                      -->

   <!-- START COPY here -->

    <!-- A. HEADER -->      
    <div class="header">
      
      <!-- A.1 HEADER TOP -->
      <div class="header-top">
        
        <!-- Sitelogo and sitename -->
        <a class="sitelogo" href="#" title="Go to Start page"></a>
        <div class="sitename">
          <h1><img src="http://cfile2.uf.tistory.com/image/273BDD3A5779F1271D7DEA" height="50" width="55"><span style="font-weight:normal;font-size:50%;"></span></h1>
        </div>			
		
        <!-- Navigation Level 1 -->
        <div class="nav1">
          <ul>
            <li><a href=sight.jsp title="Go to Start page">Home</a></li>																		
          </ul>
        </div>              
      </div>
      
      <!-- A.2 HEADER BOTTOM -->
      <div class="header-bottom">
      
        <!-- Navigation Level 2 (Drop-down menus) -->
        <div class="nav2">
	
          <!-- Navigation item -->
          <ul>
            <li><a href=openoffer.jsp>열림 신청</a></li>
          </ul>
          
          <!-- Navigation item -->
          <ul>
            <li><a href=openreal.jsp>실제 열림</a></li>
          </ul>          

          <!-- Navigation item -->
          <ul>
            <li><a href=closeoffer.jsp>닫힘 신청</a></li>
          </ul> 
          
          <ul>
          	<li><a href=closereal.jsp>실제 닫힘</a></li>
          </ul>
		  <ul>
          	<li><a href=classupdate.jsp>수업 수정</a> </li>
          </ul>   
          <ul>
          	<li><a href=lecupdate.jsp>강의실 수정</a></li>
          </ul>      
        </div>
	  </div>
<table border="1">
<tr bgcolor="#99CCFF" ><th>수업 코드</th><th>이름</th><th>강의실</th><th>날짜</th><th>시작</th><th>끝</th><th>삭제</th></tr>
<%
		for(Memo memo : (ArrayList<Memo>)Bean.getClass_table()){
	%>
<tr>
	<td><%=memo.getClass_code() %></td>
	<td><%=memo.getClass_name() %></td>
	<td><%=memo.getClass_place() %></td>
	<td><%=memo.getClass_day() %></td>
	<td><%=memo.getClass_start() %></td>
	<td><%=memo.getClass_end() %></td>
	
	<td><a href="control.jsp?action=delete&class_code=<%=memo.getClass_code()%>">X</a></td>

</tr>
<%
		}
%>

</tr>
</table>
<a href="lecadd.jsp"><p><img src="http://cfile6.uf.tistory.com/image/27082F40577B44B115B922" height=56px width=56px  ></p></a>
  </div>  
</body>
</html>
