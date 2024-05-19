<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>찜목록 페이지</title>
    <link href ="css/zzimList.css" rel="stylesheet" type="text/css" />
    <!-- j쿼리 파일저장 경로!!-->
    <script></script>
    
    <!-- vs코드와 스프링부트의 폴더명은 같아도 워크스페이스는 달라야합니다.-->
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
        <div class = "mainForm">
            <div class = "contentForm1">
            	<img src = "./image/zzimlogo.svg" class ="zzimLogo">
	            나의 찜 목록
	            <img src = "./image/zzimlogo.svg" class ="zzimLogo">
                <c:forEach items="${zzimList}" var="item" varStatus = "loop">
                <div class = "contentForm2">
                    <div class = "zzim_no">
                        ${item.zzimNo}
                    </div>
                    <div class = "first_image_url">
                        <img class="campImage" src="${item.firstImageUrl}">
                    </div>
                    <div class = "contentList">
                        <div>캠핑장 이름 : ${item.facltNm}<br><br></div>
                        <div>캠핑장 유형 : ${item.induty}<br><br></div>
                        <div>캠핑장 입지 : ${item.ictCl}<br><br></div>
                        <div>캠핑장 설명 : ${item.intro}</div>
                    </div>
                    <div class = "contentList">
                        <div>캠핑장 지역 : ${item.doNm}<br><br></div>
                        <div>캠핑장 상세 : ${item.addr1}<br><br></div>
                        <div>캠핑장 번호 : ${item.tel}<br><br></div>
                        <div>캠핑장 링크 : ${item.homepage}</div>
                    </div>
                </div>
                </c:forEach>
            </div>
        </div>
        <div class = "paging">
            <c:if test="${page > 1}">
                <a href="?page=${page - 1}">이전</a>
            </c:if>
            <c:forEach begin="${startpage}" end="${endpage}" var="pageNumber">
                <c:choose>
                    <c:when test="${pageNumber == page}">
                        ${pageNumber}
                    </c:when>
                    <c:otherwise>
                        <a href="?page=${pageNumber}">${pageNumber}</a>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
            <c:if test="${endpage < maxpage}">
                <a href="?page=${page + 1}">다음</a>
            </c:if>
        </div>
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
    <!-- 여기에 웹 페이지의 내용을 추가합니다. -->
</body>
</html>
