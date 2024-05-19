<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
<link rel="stylesheet" type="text/css" href="./css/member.css" />
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script src="./js/member.js"></script>
</head>
<body>
<header></header>

<main>
	<div class="inner">
		<div class="logo">
			<img alt="logo" src="<%=request.getContextPath()%>image/Follow8°_black.svg">
		</div>
		
		<form id="m_form" name="m_form" method="post" action="member_login_go"
			onsubmit ="return login_check()">																																																																																																																																																																																																																												
			<div class="form_list" >
				<div class="form_item_user" id="divid">
				<i class="fa-regular fa-user icon" id="iid"></i>
				<input type="text" id="login_id" name="userId" placeholder="아이디" class="input" value="" maxlength="20">
				</div>
				
				<div class="form_item_user" id="divpw">
				<i class="fa-solid fa-lock icon" id="ipw"></i>
				<input type="password" id="login_pw" name="userPw" placeholder="비밀번호" class="input" value="" maxlength="16">
				</div>
			</div>
			
			<div id="logincheck"></div>

			<div id="login_menu">
				<input type="submit" value="로그인" class="input_button"/>
				<div id="kakao_login" class="input_button">
					<a class="kakao_login_a" href="https://kauth.kakao.com/oauth/authorize?client_id=59c7a8dc4caff6501f3bf846c82a34b5&redirect_uri=http://localhost/kakaoLogin&response_type=code&prompt=select_account">
						<img class = "kakaoSimbol" src="../image/KakaoTalk_icon.png">
						<span>카카오 로그인</span>
					</a>
				</div>
				<input type="button" value="회원가입" class="input_button" onclick="location='member_join'"/>
		
			</div>
		</form>
	</div>
</main>

<footer> </footer>


</body>
</html>