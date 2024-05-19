package jjon.gangsan.member.controller;

import java.util.HashMap;
// import org.eclipse.tags.shaded.org.apache.xpath.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jjon.gangsan.member.service.MemberServiceImpl;

// 카카오 로그인 api 관련 컨트롤러
@Controller 
public class KakaoController {
	
	@Autowired
	private MemberServiceImpl kakaoService;
	
	// 연동을 위한 로직
	@GetMapping(value="/kakaoConnection")
	public String kakaoConnection(@RequestParam("code") String code, HttpSession session) {
		// 토큰 값
		String access_Token = kakaoService.getAccessToken(code);
		// 출력
		System.out.println("카카오 토큰 값 : " + access_Token);
		// 유저 정보
		HashMap<String, Object> userInfo = kakaoService.getUserInfo(access_Token);
		// 출력
		System.out.println("유저 정보 : " + userInfo);
		// 받은 유저 정보에서 email키의 값이 null인지 확인
		if (userInfo.get("email") != null) {
			// email키의 값이 null이 아니면 세션 "kakaoEmail"키 값에 넣기
			session.setAttribute("kakaoEmail", userInfo.get("email"));
			// access_Token 값을 세션 "access_Token"키 값에 넣기
			session.setAttribute("access_Token", access_Token);
			// 세션 "kakaoEmail"키 값을 문자열로 변환
			String kakaoEmail = (String) session.getAttribute("kakaoEmail");
			// 출력
			System.out.println("카카오 email : " + kakaoEmail);
			// DB에 저장된 email 값을 가져옴
			String userEmail = kakaoService.selectEmail(kakaoEmail);
			// userId
			System.out.println("유저 email : " + userEmail);
			// DB에 저장된 email 값과 세션에 저장된 email 값이 같은지 확인
			if (kakaoEmail.equals(userEmail)) {
				// 같다면 kakao 컬럼 값을 1로 변경
				kakaoService.updateKakao(kakaoEmail);
				// 출력
				System.out.println("카카오 인증이 성공하였습니다!");
				//return "redirect:/mypage?result=success";
				//return "redirect:/mypage?result=success";
				return "kakao_connpage";
			} else if (!kakaoEmail.equals(userEmail)) {
				kakaoService.kakaoLogout((String)session.getAttribute("access_Token"));
		        session.removeAttribute("access_Token");
		        session.removeAttribute("kakaoEmail");
		        //return "redirect:/mypage?result=failure";
		        //return "redirect:/mypage?result=failure";
		        return "kakao_connpage";
			}
		}
		return "kakao_connpage";
	}
	
	// 간편로그인을 위한 로직
	@GetMapping(value="/kakaoLogin")
	public String kakaoLogin(@RequestParam("code") String code, HttpSession session) {
		// 토큰 값
		String access_Token = kakaoService.getAccessToken1(code);
		// 출력
		System.out.println("카카오 토큰 값 : " + access_Token);
		// 유저 정보
		HashMap<String, Object> userInfo = kakaoService.getUserInfo(access_Token);
		// 출력
		System.out.println("유저 정보 : " + userInfo);
		// 받은 유저 정보에서 email키의 값이 null인지 확인
		if (userInfo.get("email") != null) {
			// email키의 값이 null이 아니면 세션 "kakaoEmail"키 값에 넣기
			session.setAttribute("kakaoEmail", userInfo.get("email"));
			// access_Token 값을 세션 "access_Token"키 값에 넣기
			session.setAttribute("access_Token", access_Token);
			// 세션 "kakaoEmail"키 값을 문자열로 변환
			String kakaoEmail = (String) session.getAttribute("kakaoEmail");
			
			String user_id = kakaoService.selectUser_id(kakaoEmail);
			
			System.out.println("일반 로그인 id : " + user_id);
			// 출력
			System.out.println("카카오 email : " + kakaoEmail);
			// DB에 저장된 email 값을 가져옴
			String userEmail = kakaoService.selectEmail(kakaoEmail);
			// 출력
			System.out.println("유저 email : " + userEmail);
			// DB에 저장된 email 값과 세션에 저장된 email 값이 같은지 확인
			if (kakaoEmail.equals(userEmail)) {
				// 같다면 kakao 컬럼 값을 구해옴
				int kakaoInt = (int) kakaoService.selectKakao(kakaoEmail);
				// kakao 컬럼 값이 1인지 확인
				if(kakaoInt == 1) {
					// 출력
					session.setAttribute("id", user_id);
					System.out.println("카카오 간편로그인 성공!");
					// 같다면 마이페이지로 이동
					return "member_mypage";
				}
			}
		}
		// 같지 않다면 다시 로그인폼으로 이동
		 return "member_login";
	}
    
	// 카카오 로그아웃을 위한 로직
    @GetMapping(value="/kakaoLogout")
    public String logout(HttpSession session) {
    	kakaoService.kakaoLogout((String)session.getAttribute("access_Token"));
        session.removeAttribute("access_Token");
        session.removeAttribute("kakaoEmail");
        return "redirect:member_login";
    }
}
