<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>일반 로그인 페이지</title>
    <!-- Include any necessary CSS files -->
    <link rel="stylesheet" href="/css/mypage.css">
</head>
<body>

    <header>
        <div class="head">
            <div class="headinner">
                <div class="logo">
                    <a href="../views" class="logo">
                        <img src="./image/Follow8°_white.svg" class="logo">
                    </a>
                </div>
                <div class="profile">
                    <a href="member_mypage">
                        <img src="./image/profile.svg" class="profile">
                    </a>
                </div> 
            </div>
        </div>
    </header>

    <main>
	    <!-- 연동 중 아님 -->
		<c:if test="${kakaoEmail eq null}">
		    <div class="container">
		        <div class="box1">
		            <div class="box2">
		                <div class="box3">
		                    <div class="title">마이페이지</div>
		                    <div class="box4">
		                        <div class="centerprofile">
		                            <img src="./image/profile.svg" class="centerprofile">
		                        </div>
		                    </div>
	                        <div class="insamal" >${id}님 환영합니다.</div>
	                        <div class="buttonbox">
	                            <button class="btn1" onclick="location.href='member_login'">로그아웃</button>
                                <button class="btn2" onclick="location.href='member_edit'">회원수정</button>
                                <button class="btn3" onclick="location.href='member_delete'">회원탈퇴</button>
                                <button class="btn4" onclick="location.href='member_zzim'">찜목록</button>
	                            <button class="btn6" onclick="location.href='https://kauth.kakao.com/oauth/authorize?client_id=59c7a8dc4caff6501f3bf846c82a34b5&redirect_uri=http://localhost/kakaoConnection&response_type=code&prompt=login'"><img src="./image/kakao_simbol.png" class="kakaoimg">  kakao 연동</button>
	                        </div>   
		                </div> 
		            </div>
		        </div>
		    </div>
		</c:if>

		<!--연동 중 -->
	   <c:if test="${kakaoEmail ne null}">
	        <div class="container">
	            <div class="box1">
	                <div class="box2">
	                    <div class="box3">
	                        <div class="title">마이페이지</div>
	                        <div class="box4">
	                            <div class="centerprofile">
	                                <img src="./image/profile.svg" class="centerprofile">
	                            </div>
	                        </div>
                            <div class="insamal" >${id}님 환영합니다.</div>
                            <div class="buttonbox">
                               <button class="btn1" onclick="location.href='/kakaoLogout'">로그아웃</button>
                               <button class="btn2" onclick="location.href='member_edit'">회원수정</button>
                               <button class="btn3" onclick="location.href='member_delete'">회원탈퇴</button>
                               <button class="btn4" onclick="location.href='member_zzim'">찜목록</button>
                               <div class="btn6"><img src="./image/kakao_simbol.png" class="kakaoimg">kakao 연동 중</div>
                            </div>   
	                    </div> 
	                </div>
	            </div>
	        </div>
	   </c:if>
    </main>

    <footer>
        <div class="foot">
            <div class="footinner">
                <div class="footleft">
                    <div class="footleft">
                        <img src="./image/Follow8°_black.svg" class="footleft">
                    </div>
                </div> 
                <div class="footright">
                    <div class="footright1">
                        회사소개 | 이용약관 | 고객센터 | 공지사항 
                        | 자주묻는질문 | 개인정보처리방침
                    </div> 
                    <div class="footright2">
                        (주)쫀드기컴퍼니 <br>
                        주소 : 서울특별시 강남구 테헤란로7길 7 에스코빌딩 5,6,7층 | TEL 02-561-1 911 | FAX 02-538-2613<br> 
                        대표이사 : 김현식 | 사업자등록번호 : 123-45-67890<br><br>
                        COPYRIGHT ⓒ ZZONDIGI CORP.  ALL RIGHT RESERVED. 
                    </div>
                </div> 
            </div>
        </div>
    </footer>

</body>
</html>