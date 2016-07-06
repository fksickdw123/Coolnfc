<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"  import="memo.*"%>
<jsp:useBean id="ab" scope="request" class="memo.Memo"/>
<jsp:setProperty name="ab" property="*"/>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="design.css" type="text/css" media="screen"/>


<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="design.css" type="text/css" media="screen"/>
<title>강의실:수정화면</title>
</head>
<body>
<div align="center">
<h2>로그인</h2>
<hr>

<form name=form1 method=post action=login.jsp>

 <input type=hidden name="action" value="submit">

<table border="1">
	<tr>
		<td>아이디</td><td><input type="text" name="user_id"></td>
	</tr>
	<tr>
		<td>비밀번호</td><td><input type="password" name="user_pw"></td>
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