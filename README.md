<link rel="stylesheet" as="style" crossorigin href="https://cdn.jsdelivr.net/gh/orioncactus/pretendard@v1.3.9/dist/web/variable/pretendardvariable.min.css" />

# 팀프로젝트 - followpaldo
스프링부트 + JSP 날씨API 활용한 캠핑장, 관광지 검색
<br>
<p align="center"><img src="https://github.com/followpaldo/followpaldo_project/assets/151708233/333d689f-b45a-498f-9b56-4318beda2421" width="450" height="300"></p>




# 1. 프로젝트 소개
- 코로나 19이후 캠핑족 700만시대에 발맞춰 만들게된 웹서비스입니다.
- 캠핑족 인구 증가로 인한 안전사고 발생률도 급증
- 당일 날씨 공공데이터 API 를 활용한 캠핑장 검색, 관광지 검색을 하는 웹 서비스입니다.
<br>

## 2. 개발기간 
* 24.03.18 ~ 24.04.12

### 3. 멤버구성

<div align="center">

| **팀장 김동규** | **팀원 조하영** | **팀원 조계은** | **팀원 김현식** | **팀원 김솔지** |  **팀원 남영하** | 
| :------: |  :------: | :------: | :------: | :------: | :------: |


</div>
<br>

### 4. 개발 환경

#### 프론트엔드
- HTML, CSS, JavaScript(jQuery), JSP
#### 백엔드
- Spring Boot, JDK 17, MyBatis, MySQL
#### 버전 및 이슈 관리
- GitHub + SourceTree
#### 디자인
- 피그마


<br>

##  5. 프로젝트 구조

```
│  .classpath
│  .gitignore
│  .project
│  build.gradle
│  gradlew
│  gradlew.bat
│  README.md
│  settings.gradle
│  
├─.gradle
│  ├─8.6
├─.settings
│      org.eclipse.buildship.core.prefs
│      org.springframework.ide.eclipse.prefs
│      
├─gradle
│  └─wrapper
│          gradle-wrapper.jar
│          gradle-wrapper.properties
│          
└─src
    ├─main
    │  ├─java
    │  │  └─jjon
    │  │      └─gangsan
    │  │          │  MainController.java
    │  │          │  PollowGangsanApplication.java
    │  │          │  
    │  │          ├─camplist
    │  │          │      CampListController.java
    │  │          │      CampListMapper.java
    │  │          │      CampListService.java
    │  │          │      
    │  │          ├─member
    │  │          │  ├─controller
    │  │          │  │      KakaoController.java
    │  │          │  │      MemberController.java
    │  │          │  │      SessionCheckInter.java
    │  │          │  │      WebMvcConfig.java
    │  │          │  │      
    │  │          │  ├─mapper
    │  │          │  │      MemberDaoImpl.java
    │  │          │  │      
    │  │          │  └─service
    │  │          │          MemberServiceImpl.java
    │  │          │          
    │  │          ├─model
    │  │          │      CampListVO.java
    │  │          │      CampListZzimVO.java
    │  │          │      MemberVo.java
    │  │          │      Tour.java
    │  │          │      ZzimList.java
    │  │          │      
    │  │          └─tour
    │  │              ├─controller
    │  │              │      TourController.java
    │  │              │      
    │  │              ├─mapper
    │  │              │      TourDao.java
    │  │              │      
    │  │              └─service
    │  │                      TourService.java
    │  │                      
    │  ├─resources
    │  │  │  application.yml
    │  │  │  Mybatis-Config.xml
    │  │  │  
    │  │  ├─mapper
    │  │  │      CampListMapper.xml
    │  │  │      member.xml
    │  │  │      tour.xml
    │  │  │      
    │  │  ├─META-INF
    │  │  │      additional-spring-configuration-metadata.json
    │  │  │      
    │  │  └─static
    │  │      │  index.html
    │  │      │  
    │  │      ├─css
    │  │      │      CampList.css
    │  │      │      header.css
    │  │      │      member.css
    │  │      │      mypage.css
    │  │      │      reset.css
    │  │      │      tour.css
    │  │      │      zzimList.css
    │  │      │      
    │  │      ├─image
    │  │      │      Follow8°_black.svg
    │  │      │      Follow8°_white.svg
    │  │      │      homepage.png
    │  │      │      KakaoTalk_icon.png
    │  │      │      kakao_simbol.png
    │  │      │      like.png
    │  │      │      profile.svg
    │  │      │      zzim.png
    │  │      │      zzimlogo.svg
    │  │      │      
    │  │      └─js
    │  │              CampList.js
    │  │              member.js
    │  │              
    │  └─webapp
    │      ├─META-INF
    │      │      MANIFEST.MF
    │      │      
    │      └─views
    │              CampList.jsp
    │              kakao_connpage.jsp
    │              MainPage.jsp
    │              member_delete.jsp
    │              member_edit.jsp
    │              member_join.jsp
    │              member_join_result.jsp
    │              member_login.jsp
    │              member_logout.jsp
    │              member_mypage.jsp
    │              member_zzim.jsp
    │              tourlist.jsp
    │              
    └─test
        └─java
            └─com
                └─example
                    └─demo
                            ConectionTest.java
                            WeatherTestApplicationTests.java

```

<br>

## 6. 역할분담 

###  🍊김동규

- **UI**
  -  메인페이지 , 지도 온도표현 , 지역별 검색
- **기능**
  - 날씨 공공 API 데이터 Ajax 로 가져와 j쿼리로 Html지도와 연동
  - 캠핑장, 관광지 지역별 검색을 위해 캠핑장, 관광지 Controller 와 JSTL 과 J쿼리 사용해 연동

###  🍊조하영

- **UI**
  - 관광지 목록 
- **기능**
  - 관광지 공공  API 를 통한 캠핑장 목록 DB저장 및 화면구현 (JSP 를 이용한 MVC 패턴)

###  🍊조계은

- **UI**
  - 마이페이지
- **기능**
  
###  🍊김현식

- **UI**
  - 카카오 로그인 , 카카오 간편 로그인 , 찜목록 페이지
- **기능**
  - 카카오 로그인 API 활용한 로그인 연동(Controller통한 Session 활용)

###  🍊김솔지

- **UI**
  - 캠핑장 목록 , 찜하기
- **기능**
  - 캠핑장 공공 데이터 API 를 통한 캠핑장 목록 DB저장 및 화면구현(JSP 활용한 MVC패턴)
    
###  🍊남영하

- **UI**
  - 로그인, 로그아웃, 회원가입 , 수정 , 탈퇴
- **기능**
  - JSP 활용한 MVC 패턴으로 각 기능 구현, Session 활용한 로그인 유지 및 preHandle이용한 세션관리
  - J쿼리 이용한 유효성검사 


 
