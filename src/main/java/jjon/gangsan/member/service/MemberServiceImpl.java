package jjon.gangsan.member.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import jjon.gangsan.member.mapper.MemberDaoImpl;
import jjon.gangsan.model.MemberVo;
import jjon.gangsan.model.ZzimList;



@Service
public class MemberServiceImpl implements MemberDaoImpl{
	
	@Autowired
	private	MemberDaoImpl memberDao;
	
	// 회원가입
	public int member_join_go(MemberVo member) {
		return memberDao.member_join_go(member);
	}

	// 로그인 인증
	public MemberVo user_check(String user_id) {
		return memberDao.user_check(user_id);
	}

	// id 중복검사
	public int user_id_check(String user_id) {
		int result = memberDao.user_id_check(user_id);
		
		return result;
	}

	// 회원수정
	public void member_edit_go(MemberVo member) {
		memberDao.member_edit_go(member);
	}
	// 회원탈퇴
	public void member_delete_go(MemberVo member) {
		memberDao.member_delete_go(member);
	}
	
	// 카카오 간편로그인을 위한 select문
	@Override
	public Integer selectKakao(String kakaoEmail) {
		return memberDao.selectKakao(kakaoEmail);
	}
	
	// 카카오 연동&간편로그인을 위한 select문
	@Override
	public String selectEmail(String kakaoEmail) {
		return memberDao.selectEmail(kakaoEmail);
	}

	// 카카오 연동 확인 정보를 넣기 위한 update문
	@Override
	public void updateKakao(String kakaoEmail) {
		memberDao.updateKakao(kakaoEmail);
	}

	// 간편 로그인에서 user_id값을 받아오기 위한 select문
	@Override
	public String selectUser_id(String kakaoEmail) {
		return memberDao.selectUser_id(kakaoEmail);
	}
	
	// 현재 로그인한 유저의 찜목록 수
	@Override
	public int countZzimList(String user_id) {
		return memberDao.countZzimList(user_id);
	}
	
	// 찜목록 데이터 가져오기
	@Override
	public List<ZzimList> getZzimList(Map m) {
		return memberDao.getZzimList(m);
	}

	// 연동을 위한 로직
	public String getAccessToken (String authorize_code) {
	        // 토큰 값을 저장할 문자열 변수를 초기화
	    	String access_Token = "";
	        String refresh_Token = "";
	        // 필수 파라미터를 포함해서 POST방식으로 요청할 경로
	        String reqURL = "https://kauth.kakao.com/oauth/token"; 
	        try {
	        	// URL 객체를 생성해야만 URL 주소로 연결하고 데이터를 전송하거나 가져올 수 있다.
	            URL url = new URL(reqURL);
	            // HttpURLConnection : Java에서 HTTP요청을 보내는 데 사용되는 클래스
	            // 범용 클래스인 URL 객체를 명시적으로 HttpURLConnection 객체로 다운캐스팅
	            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	            // 요청 방식을 POST 방식으로 설정
	            conn.setRequestMethod("POST");
	            // POST 요청을 위해 기본값이 false인 setDoOutput을 true로 변경
	            conn.setDoOutput(true); 
	            // POST 요청에 필요로 요구하는 파라미터 스트림을 통해 전송
	            // 요청 바디에 데이터를 쓰기 위한 BufferedWirter 생성
	            // BufferedWriter : 텍스트 데이터를 쓰는 데 사용된다. 데이터를 버퍼에 모은 다음에 한 번에 출력하기 때문에 입출력 성능이 향상된다.
	            // OutputStreamWriter : 데이터를 바이트 스트림에서 문자 스트림으로, 또는 반대로 변환하는 데 사용된다.
	            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
	            // 요청 바디를 생성하기 위한 StringBuilder 생성
	            // StringBuilder : StringBuffer와 유사하지만, 동기화되지 않기 때문에 멀티스레드 환경에서 안전하지 않다. 따라서 단일 스레드 환경에서 문자열 조작이 필요한 경우에 주로 사용된다.
	            StringBuilder sb = new StringBuilder();
	            // 요청 파라미터
	            sb.append("grant_type=authorization_code");
	            sb.append("&client_id=59c7a8dc4caff6501f3bf846c82a34b5");
	            sb.append("&redirect_uri=http://localhost/kakaoConnection");
	            sb.append("&code=" + authorize_code);
	            bw.write(sb.toString());
	            // 버퍼를 비워 데이터를 전송
	            // flush() : 버퍼에 쌓인 데이터를 출력 스트림에 즉시 전송하는 역할
	            bw.flush();
	            // 결과 코드가 200이라면 성공
	            int responseCode = conn.getResponseCode();
	            // 결과 코드를 콘솔 창에 출력
	            System.out.println("responseCode : " + responseCode);
	            // 요청을 통해 얻은 JSON타입의 Response 메세지 읽어오기
	            // 응답을 읽기 위한 BufferedReader 생성
	            // BufferedReader : 텍스트 데이터를 읽는 데 사용된다. 데이터를 버퍼에 모은 다음에 한 번에 읽기 때문에 입출력 성능이 향상된다.
	            // InputStreamReader : 바이트 입력 스트림을 문자 입력 스트림으로 변환한다.
	            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	            String line = "";
	            String result = "";
	            // readLine() : 텍스트 파일에서 한 줄씩 문자열을 읽어오는 역할을 한다.
	            while ((line = br.readLine()) != null) {                
	            	result += line;
	            }
	            // 응답받은 값을 콘솔 창에 출력
	            System.out.println("response body : " + result);
	            // Gson 라이브러리에 포함된 클래스로 JSON파싱 객체 생성
	            JsonParser parser = new JsonParser();
	            // JsonElement : Json 데이터의 요소를 나타내는 클래스
	            JsonElement element = parser.parse(result);
	            // getAsJsonObject() : JsonElement 객체를 JsonObject 객체로 변환한다.
	            // get("access(refresh)_token") : JsonObject에서 "access(refresh)_token"이라는 키를 가진 값을 가져온다. 이 값은 JsonElement형식이다.
	            // getAsString() : JsonElement를 문자열로 변환한다. 따라서 "access(refresh)_token"의 값을 문자열로 추출한다.
	            // element객체를 Object로 변환해야하는 이유는 JSON 데이터의 구체적인 형태를 파악하고 내부 데이터에 접근하기 위함이다.
	            access_Token = element.getAsJsonObject().get("access_token").getAsString();
	            refresh_Token = element.getAsJsonObject().get("refresh_token").getAsString();
	            
	            System.out.println("access_token : " + access_Token);
	            System.out.println("refresh_token : " + refresh_Token);
	            
	            br.close();
	            bw.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	        } 
	        return access_Token;
	    }
	
	// 간편로그인을 위한 로직
	public String getAccessToken1 (String authorize_code) {
		// 토큰 값을 저장할 문자열 변수를 초기화
		String access_Token = "";
		String refresh_Token = "";
		// 필수 파라미터를 포함해서 POST방식으로 요청할 경로
		String reqURL = "https://kauth.kakao.com/oauth/token";
		try {
			// URL 객체를 생성해야만 URL 주소로 연결하고 데이터를 전송하거나 가져올 수 있다.
			URL url = new URL(reqURL);
			// HttpURLConnection : Java에서 HTTP요청을 보내는 데 사용되는 클래스
			// 범용 클래스인 URL 객체를 명시적으로 HttpURLConnection 객체로 다운캐스팅
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			// 요청 방식을 POST 방식으로 설정
			conn.setRequestMethod("POST");
			// POST 요청을 위해 기본값이 false인 setDoOutput을 true로 변경
			conn.setDoOutput(true); 
			// POST 요청에 필요로 요구하는 파라미터 스트림을 통해 전송
			// 요청 바디에 데이터를 쓰기 위한 BufferedWirter 생성
			// BufferedWriter : 텍스트 데이터를 쓰는 데 사용된다. 데이터를 버퍼에 모은 다음에 한 번에 출력하기 때문에 입출력 성능이 향상된다.
			// OutputStreamWriter : 데이터를 바이트 스트림에서 문자 스트림으로, 또는 반대로 변환하는 데 사용된다.
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
			// 요청 바디를 생성하기 위한 StringBuilder 생성
			// StringBuilder : StringBuffer와 유사하지만, 동기화되지 않기 때문에 멀티스레드 환경에서 안전하지 않다. 따라서 단일 스레드 환경에서 문자열 조작이 필요한 경우에 주로 사용된다.
			StringBuilder sb = new StringBuilder();
			// 요청 파라미터
			sb.append("grant_type=authorization_code");
			sb.append("&client_id=59c7a8dc4caff6501f3bf846c82a34b5");
			sb.append("&redirect_uri=http://localhost/kakaoLogin");
			sb.append("&code=" + authorize_code);
			bw.write(sb.toString());
			// 버퍼를 비워 데이터를 전송
			// flush() : 버퍼에 쌓인 데이터를 출력 스트림에 즉시 전송하는 역할
			bw.flush();
			// 결과 코드가 200이라면 성공
			int responseCode = conn.getResponseCode();
			// 결과 코드를 콘솔 창에 출력
			System.out.println("responseCode : " + responseCode);
			// 요청을 통해 얻은 JSON타입의 Response 메세지 읽어오기
			// 응답을 읽기 위한 BufferedReader 생성
			// BufferedReader : 텍스트 데이터를 읽는 데 사용된다. 데이터를 버퍼에 모은 다음에 한 번에 읽기 때문에 입출력 성능이 향상된다.
			// InputStreamReader : 바이트 입력 스트림을 문자 입력 스트림으로 변환한다.
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line = "";
			String result = "";
			// readLine() : 텍스트 파일에서 한 줄씩 문자열을 읽어오는 역할을 한다.
			while ((line = br.readLine()) != null) {                
				result += line;
			}
			// 응답받은 값을 콘솔 창에 출력
			System.out.println("response body : " + result);
			// Gson 라이브러리에 포함된 클래스로 JSON파싱 객체 생성
			JsonParser parser = new JsonParser();
			// JsonElement : Json 데이터의 요소를 나타내는 클래스
			JsonElement element = parser.parse(result);
			// getAsJsonObject() : JsonElement 객체를 JsonObject 객체로 변환한다.
			// get("access(refresh)_token") : JsonObject에서 "access(refresh)_token"이라는 키를 가진 값을 가져온다. 이 값은 JsonElement형식이다.
			// getAsString() : JsonElement를 문자열로 변환한다. 따라서 "access(refresh)_token"의 값을 문자열로 추출한다.
			// element객체를 Object로 변환해야하는 이유는 JSON 데이터의 구체적인 형태를 파악하고 내부 데이터에 접근하기 위함이다.
			access_Token = element.getAsJsonObject().get("access_token").getAsString();
			refresh_Token = element.getAsJsonObject().get("refresh_token").getAsString();
			
			System.out.println("access_token : " + access_Token);
			System.out.println("refresh_token : " + refresh_Token);
			
			br.close();
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		} 
		return access_Token;
	}

	// 유저 정보 구하는 로직
    public HashMap<String, Object> getUserInfo (String access_Token) {
        
        // 요청하는 클라이언트마다 가진 정보가 다를 수 있기에 HashMap타입으로 선언
        HashMap<String, Object> userInfo = new HashMap<>();
        String reqURL = "https://kapi.kakao.com/v2/user/me";
        try {
            URL url = new URL(reqURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            // 요청에 필요한 Header에 포함될 내용
            // Authorization: Bearer {access_token}
            conn.setRequestProperty("Authorization", "Bearer " + access_Token);
            
            int responseCode = conn.getResponseCode();
            System.out.println("responseCode : " + responseCode);
            
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            
            String line = "";
            String result = "";
            
            while ((line = br.readLine()) != null) {
                result += line;
            }
            
            System.out.println("response body : " + result);
            
            JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(result);
            
            JsonObject properties = element.getAsJsonObject().get("properties").getAsJsonObject();
            JsonObject kakao_account = element.getAsJsonObject().get("kakao_account").getAsJsonObject();
            
            String nickname = properties.get("nickname").getAsString();
            String email = kakao_account.get("email").getAsString();
            
            userInfo.put("nickname", nickname);
            userInfo.put("email", email);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        return userInfo;
    }
	
    // 카카오 로그아웃 로직
    public void kakaoLogout(String access_Token) {
        String reqURL = "https://kapi.kakao.com/v1/user/logout";
        try {
            URL url = new URL(reqURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Authorization", "Bearer " + access_Token);
            
            int responseCode = conn.getResponseCode();
            System.out.println("responseCode : " + responseCode);
            
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            
            String result = "";
            String line = "";
            
            while ((line = br.readLine()) != null) {
                result += line;
            }
            
            System.out.println(result);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
}