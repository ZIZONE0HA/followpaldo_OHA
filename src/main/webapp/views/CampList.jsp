<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="jjon.gangsan.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Camp List</title>
<link rel="stylesheet" href="/css/CampList.css">
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script type="text/javascript" src="/js/CampList.js"></script>

</head>
<body>
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
	<!-- 캠핑장 리스트 -->
	<div id="campListBox" name="campListBox">
		<div id="listTitle">🍀 ${doNm} 캠핑장 추천 🍀</div>
		<c:forEach var="camp" items="${campList}">
			<div id="camp" name="camp">
				<div id="campImage" name="campImage">
					<img src="${camp.firstImageUrl}">
				</div>
				
				<div id="campTitle" name="campTitle">
					<div id="faclt" name="faclt">${camp.facltNm}</div>
				</div>
				
				<div id="linkBox" name="linkBox">
					<div id="zzim" name="zzim">
					    <button id="zzimbutton" onclick="check('${camp.contentId}')">
					        <img id=zzimbuttonImage src="/image/like.png">
					        찜하기
					    </button>
					</div>

					<div id="homepage" name="homepage">
						<a href="${camp.homepage}" target="_blank">
						<img id="homepageImage" src="/image/homepage.png">
						  바로가기링크</a>
					</div>
				</div>
				
				<div id="campInfo" name="campInfo">
					<div id="induty" name="induty">${camp.induty}</div>
					<div id="addr" name="addr">${camp.addr1}</div>
					<div id="tel" name="tel">${camp.tel}</div>
					<div id="ictCL" name="ictCL">${camp.ictCl}</div>
				</div>
				
				<div id="intro" name="intro">${camp.intro}</div>
			</div>
		</c:forEach>	
		
		<div id=paging name="pagig">
			<!-- 1페이지에 위치하면 이전 글씨만 나오게,
				 1페이지 이상이면 이전 글씨에 링크를 연결해 전페이지로 이동하게 한다 -->
			<c:if test="${page<=1}"> 
				이전 
			</c:if>
			<c:if test="${page>1}">
				<a href="?page=${page-1}">이전</a>
			</c:if>
			
			<!-- startpage(첫페이지)부터 end페이지까지(마지막페이지)
				 반복문을 이용해 링크를 건다
				 단, 현재페이지는 링크가 걸리지 않게 -->
			<c:forEach var="p" begin="${startpage}" end="${endpage}">
				<c:if test="${p==page}">
					[${p}]
				</c:if>
				<c:if test="${p!=page}">
					<a href="?page=${p}">[${p}]</a>
				</c:if>
			</c:forEach>
			
			<!-- 마지막페이지에 위치하고 있으면 다음 글씨에 링크가 걸리지 않고
			 	 마지막페이지에 위치하고 있지 않으면 다음 글씨에 링크가 걸린다 (다음페이지로 이동) -->
			<c:if test="${page>=maxpage}">
				다음
			</c:if>
			<c:if test="${page<maxpage}">
				<a href="?page=${page+1}">다음</a>
			</c:if>
		</div>
	</div>
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
