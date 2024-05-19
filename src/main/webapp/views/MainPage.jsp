<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<title>Document</title>
<head>

<script src="https://code.jquery.com/jquery-3.7.1.min.js"
	integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo="
	crossorigin="anonymous"></script>


<link rel="stylesheet" href="css/header.css">



</head>
<body>
	<header>
		<div class="head">
			<div class="headinner">
				<div class="logo">
					<a href="../views" class="logo"> <img
						src="/image/Follow8°_white.svg" class="logo">
					</a>
				</div>
				<div class="profile">
					<a href="member_mypage"> <img src="/image/profile.svg"
						class="profile">
					</a>
				</div>
			</div>
		</div>
	</header>


	<title>네이버 날씨</title>

	<link rel="shortcut icon"
		href="https://ssl.pstatic.net/static/weather/image/nFavicon32.svg"
		type="image/x-icon">
	<link rel="preload"
		href="https://ssl.pstatic.net/static/weather/font/NotoSans-Regular.woff2"
		as="font" type="font/woff2" crossorigin="anonymous">
	<link rel="preload"
		href="https://ssl.pstatic.net/static/weather/font/NotoSans-Light.woff2"
		as="font" type="font/woff2" crossorigin="anonymous">
	<link rel="preload"
		href="https://ssl.pstatic.net/static/weather/font/NotoSans-Medium.woff2"
		as="font" type="font/woff2" crossorigin="anonymous">
	<link rel="preload"
		href="https://ssl.pstatic.net/static/weather/font/NotoSans-Bold.woff2"
		as="font" type="font/woff2" crossorigin="anonymous">
	<link rel="preload"
		href="https://ssl.pstatic.net/static/weather/font/Roboto-Light.woff2"
		as="font" type="font/woff2" crossorigin="anonymous">
	<link rel="preload"
		href="https://ssl.pstatic.net/static/weather/font/Roboto-Regular.woff2"
		as="font" type="font/woff2" crossorigin="anonymous">
	<link rel="preload"
		href="https://ssl.pstatic.net/static/weather/font/Roboto-Medium.woff2"
		as="font" type="font/woff2" crossorigin="anonymous">
	<link rel="preload"
		href="https://ssl.pstatic.net/static/weather/font/Roboto-Bold.woff2"
		as="font" type="font/woff2" crossorigin="anonymous">
	<link rel="apple-touch-icon-precomposed"
		href="https://ssl.pstatic.net/static/weather/web_icon/web_iOS_iPhone_2x_120X120.png" />
	<link rel="apple-touch-icon-precomposed" sizes="152x152"
		href="https://ssl.pstatic.net/static/weather/web_icon/web_iOS_iPad_2x_152X152.png" />
	<link rel="apple-touch-icon-precomposed" sizes="167x167"
		href="https://ssl.pstatic.net/static/weather/web_icon/web_iOS_iPadPro_2x_167X167.png" />
	<link rel="apple-touch-icon-precomposed" sizes="180x180"
		href="https://ssl.pstatic.net/static/weather/web_icon/web_iOS_iPhone_3x_180X180.png" />
	<link rel="stylesheet" href="weather.css">


	<!-- Naver Tag Manager -->
	<script type="text/javascript">
		!function(t, n) {
			n = n || "ntm";
			window["ntm_" + t] = n, window[n] = window[n] || [], window[n]
					.push({
						"ntm.start" : +new Date
					});
			t = document.getElementsByTagName("script")[0], n = document
					.createElement("script");
					n.async = !0,
					n.src = "https://ntm.pstatic.net/scripts/ntm_f6905c3401d4.js",
					t.parentNode.insertBefore(n, t)
		}("f6905c3401d4", "ntm");
	</script>
	<!-- End Naver Tag Manager -->
	<script>
		var devCssBranch = "240321";
	</script>

	<link rel="stylesheet" type="text/css"
		href="https://cdnjs.cloudflare.com/ajax/libs/billboard.js/3.7.1/billboard.min.css">
	<link rel="stylesheet" type="text/css"
		href="https://ssl.pstatic.net/staticv2.weather/responsive/css/block_weather_webfont_202403211504.css">
<body>

	<dlv class="searchpost">
	<div></div>

	<!-- 지역 선택하는 JSP문 시작  -->
	<h2>지역별 캠핑장 , 관광지 유형선택</h2>
	<select id="regionSelect" style="text-align: center;">

		<!-- 각 지역을 배열로 선언 -->
		<c:set var="regions"
			value='서울, 경기도, 충청북도 , 충청남도, 전라남도 , 전라북도, 경상북도 , 경상남도, 제주, 강원' />
		<!-- 배열에 있는 각 요소를 옵션으로 동적으로 추가 -->
		<c:forEach items="${regions}" var="region">
			<option value="${region}	">${region}</option>
		</c:forEach>

	</select> 
	
	<input type="button" value="캠핑장 검색" id="searchButton" onclick="search('camping')"> 
		
	<input type="button" value="관광지 검색" id="searchButton" onclick="search2('tourism')">

	</select> 




	</form>
	</dlv>


	<div id="wrap" data-region-code="08110580" class="">
		<!-- js 렌딩으로 개선 -->
		<div id="header" class="" role="banner">




			<div></div>
			<!-- 지도에 각 지역 올려주는 html 코드 -->
			<div class="section_right no_sticky" >
				<div id="nation"
					class="card card_nation _cnBlockTemplate common_loading"
					data-template="nationFcast" data-template-index="1">
					<h2 class="title">전국날씨</h2>
					<ul class="weekly_list">

					</ul>
					<div class="map_wrap">

						<div class="nation_map">
							<a class="zone z17"
								onclick="oCommon.fnSendClick(this, 'today.nationclick')"> <span
								class="text" id="제주도"></span> <a 
								class="zone z12"
								onclick="oCommon.fnSendClick(this, 'today.nationclick')"> <a
									href="/today/04170690" class="zone z13"
									onclick="oCommon.fnSendClick(this, 'today.nationclick')"> <span
										class="text" id="경상북도"></span> <a 
										class="zone z3"
										onclick="oCommon.fnSendClick(this, 'today.nationclick')">

											<span class="text" id="인천"></span> <a 
											class="zone z16"
											onclick="oCommon.fnSendClick(this, 'today.nationclick')">

												<span class="text" id="경상남도"></span> <span class="blind">흐림</span>
												<a class="zone z8"
												onclick="oCommon.fnSendClick(this, 'today.nationclick')">

													<span class="text" id="충청남도"></span> <span class="blind">흐림</span>
													<a  class="zone z15"
													onclick="oCommon.fnSendClick(this, 'today.nationclick')">


														<a  class="zone z9"
														onclick="oCommon.fnSendClick(this, 'today.nationclick')">

															<span class="text" id="전라남도"></span> <span class="blind">흐림</span>
															<a  class="zone z7"
															onclick="oCommon.fnSendClick(this, 'today.nationclick')">

																<span class="text" id="충청북도"></span> <span class="blind">흐림</span>
																<a class="zone z1"
																onclick="oCommon.fnSendClick(this, 'today.nationclick')">

																	<span class="text" id="서울"></span> <span class="blind">구름많음</span>

																	<a class="zone z4"
																	onclick="oCommon.fnSendClick(this, 'today.nationclick')">
																		<span class="text" id="강원도"></span> <span
																		class="blind">구름많음</span> <a 
																		class="zone z11"
																		onclick="oCommon.fnSendClick(this, 'today.nationclick')">


																			<a  class="zone z6"
																			onclick="oCommon.fnSendClick(this, 'today.nationclick')">
																				<span class="text" id="경기도"></span> <span
																				class="blind">흐림</span> <a 
																				class="zone z6"
																				onclick="oCommon.fnSendClick(this, 'today.nationclick')">

																					<a class="zone z10"
																					onclick="oCommon.fnSendClick(this, 'today.nationclick')">



																						<a  class="zone z2"
																						onclick="oCommon.fnSendClick(this, 'today.nationclick')">

																							<a  class="zone z5"
																							onclick="oCommon.fnSendClick(this, 'today.nationclick')">


																								<a class="zone z14"> <span class="text"
																									id="경상북도"></span> <span class="blind">흐림</span>
																							</a>
																						</a>
																					</a>
																				</a>

																			</a>
																		</a>
																	</a>
																</a>
															</a>
														</a>
													</a>
												</a>
											</a>
										</a>
									</a>
								</a>
							</a>
							</a>


						</div>
					</div>

					<p class="offer_area">
						<a href="https://www.weather.go.kr/w/index.do" class="link_go"
							target="_blank"
							onclick="oCommon.fnSendClick(this, `today.nationsource`)">기상청</a>
						<span class="margin_left margin_right">발표,</span> <a
							href="https://www.weatheri.co.kr" class="link_go" target="_blank"
							onclick="oCommon.fnSendClick(this, `today.nationsource`)">웨더아이</a>
						<span class="margin_left margin_right">제공</span>
					</p>
				</div>
					<!-- 날씨 지도 로직 종료 -->
				<footer>


					<div class="foot">
						<div class="footinner">
							<div class="footleft">
								<div class="footleft">
									<img src="/image/Follow8°_black.svg" class="footleft">
								</div>
							</div>
							<div class="footright">
								<div class="footright1">회사소개 | 이용약관 | 고객센터 | 공지사항 | 자주묻는질문
									| 개인정보처리방침</div>
								<div class="footright2">
									(주)쫀드기컴퍼니 <br> 주소 : 서울특별시 강남구 테헤란로7길 7 에스코빌딩 5,6,7층 | TEL
									02-561-1 911 | FAX 02-538-2613<br> 대표이사 : 김현식 | 사업자등록번호 :
									123-45-67890<br>
									<br> COPYRIGHT ⓒ ZZONDIGI CORP. ALL RIGHT RESERVED.
								</div>
							</div>
						</div>
					</div>
				</footer>

				<!-- 선택하는 코드 이벤트 J쿼리 코드 -->
				<script>

               function search(searchType) {
                  var selectedRegion = document
                        .getElementById("regionSelect").value.trim();
                  var searchUrl = "/CampList/"
                        +selectedRegion
                  window.location.href = searchUrl;
               }
               function search2(searchType) {
                   var selectedRegion = document
                         .getElementById("regionSelect").value.trim();
                   var searchUrl = "/tourlist/"
                         +selectedRegion
                   window.location.href = searchUrl;
                }
             
          
				</script>

				<!-- 두개의 JSP URL 로 각각 보낼예정 값은 같아도 버튼연동하는 J쿼리문 두개 만들어서 해당 JSP화면으로 보내버리고 
				 	컨트롤러는 해당해서 -->



				<!-- 날씨 받아오는 API Ajax 건들면 안됩니다. -->
				<script>
					// 날씨 APi 파싱하는 AJAX 부분.

					function updateTemperature(region, temperature) {
						// 대기중인 함수 호출 하여 html 에 값 넘기기 , 밑에 함수를 넣어주면 실행되지않아 위로올림.

						$('#' + region)
								.text(region + ': ' + temperature + " ℃");
						// j쿼리문 사용 ID선택자에 지역명과 일치한걸 찾아 지멱명 + 온도 문자를 넣어준다.
					}

					$(document)
							.ready(
									function() {
										let arr = [];
										let today = new Date();
										let week = new Array('일', '월', '화',
												'수', '목', '금', '토');
										let year = today.getFullYear();
										let month = today.getMonth() + 1;
										let day = today.getDate();
										let hours = today.getHours();
										let minutes = today.getMinutes();
										let hoursA1 = new Array('02', '05',
												'08', '11', '14', '17', '23');
										let korea = [ {
											'region ' : '서울',
											'nx' : 60,
											'ny' : 127
										}, {
											'region' : '인천',
											'nx' : 55,
											'ny' : 124
										}, {
											'region' : '경기도',
											'nx' : 60,
											'ny' : 121
										}, {
											'region' : '강원도',
											'nx' : 92,
											'ny' : 131
										}, {
											'region' : '충청북도',
											'nx' : 69,
											'ny' : 106
										}, {
											'region' : '충청남도',
											'nx' : 68,
											'ny' : 100
										}, {
											'region' : '전라북도',
											'nx' : 63,
											'ny' : 89
										}, {
											'region' : '전라남도',
											'nx' : 50,
											'ny' : 67
										}, {
											'region' : '경상남도',
											'nx' : 90,
											'ny' : 77
										}, {
											'region' : '경상북도',
											'nx' : 91,
											'ny' : 106
										}, {
											'region' : '제주도',
											'nx' : 52,
											'ny' : 38
										} ];
										/*
										 $*'weatherDate'),html(
										 month +"월 " +  day + "일" + week[today.getDay()] + "요일"); */

										// 동네에보 시간이 0200 0500 ... 3시간 단위로 23시까지
										// 가장 근접한 시간의 일기예보를 찾기위한코드 예를들어 00시 일때 00시의 일기예보가 아닌 23시의 일기예보를 찾기위한 코드
										// 9시 -> 09시 변경 필요 
										if (hours < 10) {
											hours = '0' + hours;
										}

										if (month < 10) {
											month = '0' + month;

										}
										if (day < 10) {
											day = '0' + day;
										}

										today = year + "" + month + "" + day
												- 1;

										// 좌표

										$
												.each(
														korea,
														function(j, k) {
															// function(인덱스값, value값)
															// 배열을 관리하고자 할때 사용하는 메서드, 매개변수로 받은 것을 사용해 for 반복문 같이 배열이나 객체의 요소를 검사할수 있는 메서드 
															// korea 배열변수에 저장된 값을 반복하기 위한 메서드  , 주어진 배열의 길이만큼 반복한다. 
															let nx = korea[j].nx, ny = korea[j].ny, region = korea[j].region,
															// 각 변수에 한국 좌표 와 도시명을 저장한다. JS의 구조분해 함수. 
															apikey = "Y%2BF7DCXFMMk8nIxQLgY67hQenETFkLN6qOAU0%2BNv3cboJEEDvWP8a6LtIqi%2BSx6kPv4A1Z3tXoLCT%2Bi1tNkUpQ%3D%3D", WeatherURL = "http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getVilageFcst";
															WeatherURL += "?serviceKey="
																	+ apikey;
															WeatherURL += "&numOfRows=285&pageNo=1&base_date="
																	+ today
																	+ "&base_time=0500";
															WeatherURL += "&nx="
																	+ nx
																	+ "&ny="
																	+ ny;
															arr
																	.push({
																		'url' : WeatherURL,
																		'region' : region
																	});
															// 키값 배열에 각각 url , 지역명을 저장해준다. 
															$
																	.ajax({
																		url : arr[j].url,
																		type : 'GET',
																		success : function(
																				data) {
																			let $data = $(
																					data)
																					.find(
																							"response>body>items>item");
																			let temp = '';
																			let fcstTime = '';
																			$
																					.each(
																							$data,
																							function(
																									i,
																									o) {
																								let category = $(
																										o)
																										.find(
																												"category")
																										.text();

																								let fcstValue = $(
																										o)
																										.find(
																												"fcstValue")
																										.text();
																								// fcstValue 에 해당하는 text 값을 받아 저장한다. 
																								let fcstTime = $(
																										o)
																										.find(
																												"fcstTime")
																										.text();
																								// TMP 값을 찾아서 온도 정보 가져오기
																								if (category === 'TMP') {
																									temp = fcstValue;
																									time = fcstTime;
																									// TMP가 온도를 나타내기 떄문에 카테고리에 TMP(속성) 가 있으면 fcstVAlue(온도값) temp 변수에 저장한다.
																									if (region == null
																											|| region === undefined) {
																										region = "서울";
																										// 서울은 좌표값을 맞게 줬는데 계속 undefined 뜨고 null 값이 떠 못찾을시 서울로 명시하도록 함. 
																									}
																									console
																											.log(region);
																									updateTemperature(
																											region,
																											temp,
																											time);
																									// 저장한 변수들 함수에 호출시킬 대기 함수
																								}
																							});
																			console
																					.log("ajax 호출 성공하였습니다.");
																			console
																					.log(
																							'온도:',
																							temp);
																			console
																					.log(
																							'시간:',
																							hours
																									+ "시"
																									+ minutes
																									+ "분 ");

																		} // success 함수 종료
																	});
														}); // each 함수 종료
										// 각 지역에 해당하는 온도를 업데이트하는 함수

									}); // ready 함수 종료
				</script>
</body>

</html>