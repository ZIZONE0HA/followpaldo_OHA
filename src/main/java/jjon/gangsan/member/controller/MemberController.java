package jjon.gangsan.member.controller;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jjon.gangsan.member.service.MemberServiceImpl;
import jjon.gangsan.model.MemberVo;
import jjon.gangsan.model.ZzimList;

import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class MemberController {
	
	@Autowired
	private MemberServiceImpl memberService;
	
	
	// 마이페이지
	@GetMapping("member_mypage")
	public String member_mypage() {
		return "member_mypage";
	}
	
	//회원가입폼
	@RequestMapping("/member_join")
	public String member_join() {
		return "member_join";
	}
	
	//로그인폼
	@RequestMapping("/member_login")
	public String member_login() {
		return "member_login";
	}
	
	//회원수정폼
	@RequestMapping("/member_edit")
	public String member_edit(HttpSession session, Model model) {
		String id = (String) session.getAttribute("id");
		
		MemberVo editm = memberService.user_check(id);
		model.addAttribute("editm", editm);
		
		return "member_edit";
	}
	
	
	//회원삭제 폼
	@RequestMapping("/member_delete")
	public String member_delete(HttpSession session, Model model) {
		
		String id = (String) session.getAttribute("id");
		MemberVo member = memberService.user_check(id);
		
		return "member_delete";
	}
	
	//회원가입
	@RequestMapping("/member_join_go")
	public String member_join_go(MemberVo member){
		System.out.println("controller in");
		
		int result = memberService.member_join_go(member);
		if(result == 1) System.out.println("회원가입 성공");
		
		return "member_join_result";
	}
	
	//회원가입 id 중복체크
	@RequestMapping("/member_idchek")
	@ResponseBody
	public Integer member_idcheck(@RequestParam("memid") String user_id) {
		System.out.println("id : "+ user_id);
		
		int result = memberService.user_id_check(user_id);
		System.out.println("result :"+result);
		return result;
	}
	
	//로그인
	@RequestMapping("/member_login_go")
	public String member_login_go(@RequestParam("userId") String user_id,
								  @RequestParam("userPw") String user_pw,
								  HttpSession session,
								  HttpServletResponse response,
								  Model model) {
		
		
		int result = 0;	
		MemberVo member =  memberService.user_check(user_id);
		
		
		if(member == null) {//등록 x회원일 때
			result = 1;
			return "member_login";
		}
		else {				// 등록된 회원일때
			if (member.getUserPw().equals(user_pw)) {			// 비번이 같을때
				
				// 세션 설정
				session.setAttribute("id", user_id);     
				
				System.out.println(user_id); //id 잘넘어왓나 확인
				String user_name = member.getUserName(); //view로 넘어갈 name

				//model객체 : controller -> view로 갈때 한번만 springboot에서 자체적으로 사용하는 인터페이스
				model.addAttribute("user_name", user_name);
				
				return "member_mypage";
				
			} else {									// 비번이 다를때
				result = 2;
				model.addAttribute("result", result);
				
				return "member_login";				
			}
		}
	}
	
	//회원정보수정
	@RequestMapping("/member_edit_go")
	public String member_edit_go(
								MemberVo member,
								HttpSession session, 
								HttpServletRequest request, 
								Model model	) {
		
		String id = (String) session.getAttribute("id");
		member.setUserId(id);
		
//		if(!member.getUser_pw().equals(user_pw)) { //비번 불일치시
//			
//		}
		
		//update sql문
		memberService.member_edit_go(member);
		
		model.addAttribute("user_name", member.getUserName());
		
		return "member_mypage";
	}
	
	
	//회원정보 삭제
	@RequestMapping("/member_delete_go")
	public String member_delete_go(@RequestParam("user_pw") String user_pw,
									HttpSession session) {
		
		String id = (String) session.getAttribute("id");
		MemberVo member = memberService.user_check(id);
		
		if(!member.getUserPw().equals(user_pw)) { //비번 불일치시
			return "delete_result";
		}else { //비번 일치시
			MemberVo db = new MemberVo();
			db.setUserId(id);
			
			//delete sql문
			memberService.member_delete_go(db);
			
			//세션삭제
			session.invalidate();
			
			return "redirct:/member_login";
		}
		
	}
	
	
	
	//로그아웃
	@RequestMapping("member_logout")
	public String logout(HttpSession session) {
		session.invalidate();
		
		return "member_logout";
	}
	
	// 찜목록 페이지로 매핑
		@GetMapping(value = "member_zzim")
		public String zzim(Model model, @RequestParam(value="page", defaultValue = "1") int page, HttpSession session, HttpServletRequest request) {
			session = request.getSession();
			// 현재 세션에 저장된 "user_id"키 값을 불러와 문자열 변수에 저장
			String user_id = (String) session.getAttribute("id");
			// 현재 로그인한 유저의 id 출력
			System.out.println(user_id);
			// 페이지 당 보여줄 데이터 개수
			int pageSize = 10;
			
			// 현재 로그인한 유저의 찜목록 수 조회
			int listCount = memberService.countZzimList(user_id);
			// 찜목록 수 출력
			System.out.println("listCount"+listCount);
			
			int start = (page - 1) * pageSize;
			
			Map m = new HashMap<>();
			m.put("start", start);		
			m.put("userId", user_id);		
			
			
			// 현재 페이지 / 페이디 장 보여줄 데이터 개수 / 현재 로그인한 유저 id 값을 넘겨줌
			List<ZzimList> zzimList = memberService.getZzimList(m);
			System.out.println("zzimList"+zzimList);
			
			// 전체 페이지 수를 계산합니다. 페이지당 게시글 수로 나눈 몫에 1을 더하고, 나머지가 있는 경우 1을 더해줍니다. 100 / 10 + ((100 % 10 == 0) ? 0 : 1 );
			int maxpage = listCount / pageSize + ((listCount % pageSize == 0) ? 0 : 1);
			// 시작 페이지 번호를 계산합니다. 현재 페이지가 속한 페이지 그룹의 첫 번째 페이지 번호를 구합니다.
			int startpage = ((page - 1) / 10) * pageSize + 1;
			// 끝 페이지 번호를 계산합니다. 현재 페이지가 속한 페이지 그룹의 마지막 페이지 번호를 구합니다. 한 페이지 그룹당 10개의 페이지를 보여주는 것으로 가정합니다.
			int endpage = startpage + 10 - 1;
			// 끝 페이지 번호가 전체 페이지 수를 초과하지 않도록 보정합니다.
			if (endpage > maxpage) endpage = maxpage;
			// view 페이지를 위함
			model.addAttribute("page", page);
			model.addAttribute("startpage", startpage);
			model.addAttribute("endpage", endpage);
			model.addAttribute("maxpage", maxpage);
			model.addAttribute("listcount", listCount);
			model.addAttribute("zzimList", zzimList);
			return "member_zzim";
		}
	
	
}
