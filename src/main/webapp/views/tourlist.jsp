<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ page import="java.util.*"%>
<%@ page import="jjon.gangsan.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/css/tour.css" type="text/css">
<title>Insert title here</title>
</head>
<body>
<script src="http://code.jquery.com/jquery-latest.js"></script>
    <header>
        <div class="head">
            <div class="headinner">
                <div class="logo">
                    <a href="../views" class="logo">
                        <img src="/image/Follow8°_white.svg" class="logo">
                    </a>
                </div>
                <div class="profile">
                    <a href="member_mypage">
                        <img src="/image/profile.svg" class="profile">
                    </a>
                </div> 
            </div>
        </div>
    </header>
    
<!-- 글목록  -->
	<div class=container>
	
		<div class="region-area">
			<h1>${region } 관광지 리스트</h1>
		</div>
		<div>글 개수 : ${listcount }</div>
		<div>
			<c:forEach var="t" items="${tourlist }">
				<div class="content">
					<div class="img-area">
						<img src="${t.image }" class="tour-img">
					</div>
					
					<div class="content-area">
						<div class="title-area">
							<h2>${t.title }</h2>
						</div>
						<div class="addr-area">
							${t.addr1 }
						</div>
						<div class="tel-area">
							${t.tel }
						</div>
					</div>
				</div>
			</c:forEach>
			
	</div>

		<div class="page-area">
	
			<c:if test="${page <=1 }">
				[이전]&nbsp;
			</c:if>
			
			<c:if test="${page >1 }">
				<a href="?page=${page-1 }&region=${region}">[이전]</a>&nbsp;
			</c:if>
			
			<c:forEach var="a" begin="${startPage }" end="${endPage }">
				<c:if test="${a == page }">
				[${a }]
				</c:if>
				<c:if test="${a!= page }">
					<a href="?page=${a }&region=${region}">[${a }]</a>&nbsp;
				</c:if>
			</c:forEach>
			
			<c:if test="${page >= maxPage }">
				[다음]
			</c:if>
			
			<c:if test="${page < maxPage }">
				<a href="?page=${page+1 }&region=${region}">[다음]</a>
			</c:if>
		</div>
		<br><br>

<footer>
        <div class="foot">
            <div class="footinner">
                <div class="footleft">
                    <div class="footleft">
                        <img src="/image/Follow8°_black.svg" class="footleft">
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