<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"  import="memo.*"%>
<jsp:useBean id="ab" scope="request" class="memo.Memo"/>
<jsp:setProperty name="ab" property="*"/>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="design.css" type="text/css" media="screen"/>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>강의실:수정화면</title>
</head>
<body>
<div align="center">
<h2>강의실:수정화면</h2>
<hr>
[<a href=sight.jsp>뒤로가기</a>]<P>

<form name=form1 method=post action=control.jsp>

 <input type=hidden name="action" value="insert">

<table border="1">
<tr>
	<th>강의실 코드</th>
	<td><input type="text" name="class_code" ></td>
	</tr>
	<tr>
	<th>이름</th>
	<td><input type="text" name="class_name" ></td>
	</tr>
	<tr>
	<th>강의실</th>
	<td><input type="text" name="class_place"></td>
	</tr>
	<tr>
	<th>요일</th>
	<td><input type="text" name="class_day" ></td>
	</tr>
	<tr>
	<th>시작시간</th>
	<td><input type="text" name="class_start" ></td>
	</tr>
	<tr>
	<th>끝시간</th>
	<td><input type="text" name="class_end" ></td>
	</tr>
	<tr>
	
		<td colspan=2 align=center><input type=submit value="저장">
		<input type=reset value="취소"></td>
		
	</tr>
	
</table>
</form>
</div>
</body>
</html>