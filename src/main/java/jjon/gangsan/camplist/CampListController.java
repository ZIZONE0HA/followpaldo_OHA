package jjon.gangsan.camplist;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.annotation.SessionScope;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jjon.gangsan.model.CampListVO;
import jjon.gangsan.model.CampListZzimVO;


@Controller
public class CampListController {	
	
	@Autowired
	private CampListService campListService;
	
	
	@RequestMapping("/save")
	public void saveCampList() {
        String result = "";
        
        try {
        	int totalPage = 380; 
        	int pageSize = 10;
        	
        	for(int pageNo=1; pageNo<=totalPage;pageNo++) {
	            URL url = new URL("https://apis.data.go.kr/B551011/GoCamping/basedList?"
	            		+ "serviceKey=Hhr14Q8W875seJ9A2UefmG2ndV3%2Ba%2Bi%2BQ%2B86zt1t0xxT%2BqXqArlTSIA0BBkEZwKSNeGaGfKtv%2Bk8UBxdORBLtg%3D%3D"
	            		+ "&numOfRows="+pageSize+"&pageNo="+pageNo+"&MobileOS=ETC&MobileApp=AppTest&_type=JSON");
	    
	            BufferedReader bf; //효율적으로 입출력하기 위한 입력도구
	    
	            bf = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
	            //url 객체의 하위 메소드로 url 정보를 읽어들인다
	    
	            result = bf.readLine();
	    
	            JSONParser jsonParser = new JSONParser();
	            JSONObject jsonObject = (JSONObject) jsonParser.parse(result);
	            JSONObject response = (JSONObject) jsonObject.get("response");
	            JSONObject body = (JSONObject) response.get("body");
	            JSONObject items = (JSONObject) body.get("items");
	            JSONArray itemList = (JSONArray) items.get("item");
	        
	            for (Object item : itemList) {
	                JSONObject camping = (JSONObject) item;
	                
	                CampListVO camp = new CampListVO();
	                
	                camp.setContentId((String) camping.get("contentId"));
	                camp.setFirstImageUrl((String) camping.get("firstImageUrl"));
	                camp.setFacltNm((String) camping.get("facltNm"));
	                camp.setDoNm((String) camping.get("doNm"));
	                camp.setAddr1((String) camping.get("addr1"));
	                camp.setTel((String) camping.get("tel"));
	                camp.setHomepage((String) camping.get("homepage"));
	                camp.setInduty((String) camping.get("induty"));
	                camp.setIctCl((String) camping.get("ictCl"));
	                camp.setIntro((String) camping.get("intro"));
	    
	                System.out.println(camp);
	                
	                // 새로운 캠핑장 정보를 데이터베이스에 저장
	                campListService.saveCampList(camp);  
	            }  
        	}
	
	      } catch (Exception e) {
	           e.printStackTrace();
	    }
     }
	

    @RequestMapping("/CampList/{regin}")
    public String callCampList(@RequestParam(value="page",defaultValue="1") int page,
    		@PathVariable("regin") String region, Model model) {
    	
   
    	String doNm = campListService.getDoNm(region);
    	
    	
    
    	int limit = 10;
    	
    	
    	int start = (page - 1) * limit;
    	

    	
  
    	CampListVO camp = new CampListVO();
    	camp.setDoNm(doNm);
    	camp.setStart(start);
    	
    
    	int listCount = campListService.getListCount(camp);
    	System.out.println("listCount:"+ listCount);
    	
 
    	int maxpage = listCount / limit + ((listCount % limit ==0)? 0 : 1);
    	
   
    	int startpage = ((page-1)/10)*limit+1;
    	
    
    	int endpage = startpage+10-1;
    	
    	
    	if(endpage > maxpage)
    		endpage = maxpage;
    	
    
    	List<CampListVO> campList = new ArrayList<CampListVO>();
    	
    
    	campList = campListService.callCampList(camp);
    	
    	//jsp 단에 값 넘겨주기
    	model.addAttribute("page",page);
    	model.addAttribute("doNm",doNm);
    	model.addAttribute("startpage",startpage);
    	model.addAttribute("endpage",endpage);
    	model.addAttribute("maxpage",maxpage);
    	model.addAttribute("listCount",listCount);
    	model.addAttribute("campList",campList);
    	
        return "CampList";
    }
	
    

	@PostMapping("/zzim")
	@ResponseBody
	public ResponseEntity<Integer> zzimClick(@RequestBody CampListZzimVO zzimVO,
			HttpServletRequest request, Model model, HttpSession session) {   

		String userId = (String) session.getAttribute("id");
		System.out.println("아디 찍어바"+userId);
		System.out.println(zzimVO.getContent_id());
		
		zzimVO.setUser_id(userId);
		
		//세션값에 저장된 user_id 사용자의 찜한 content_id list 형식으로 가져오기
		//sid 값을 가진 (contentid 리스트로 받기)
		//String userId = zzimVO.getUserId();
		List<String> zzimList = new ArrayList<String>();
		zzimList = campListService.getZzimList(userId);
		
		System.out.println(zzimList);
		
		int zzimstate = 0;
		
		boolean isDuplicate = false;
		
		if(zzimList != null && !zzimList.isEmpty()) {
			for(String zzimContentId : zzimList) {
				if(zzimContentId.equals(zzimVO.getContentId())) {
					campListService.deleteZzim(zzimVO);
					zzimstate = 0;
					isDuplicate = true;
					break;
				}
			}
		}
		if(!isDuplicate) {
			campListService.letZzim(zzimVO);
			zzimstate = 1;
		}
		
		model.addAttribute("zzimstate",zzimstate);
				
		return new ResponseEntity<Integer>(zzimstate, HttpStatus.OK);
	}
}
