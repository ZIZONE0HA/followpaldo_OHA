package jjon.gangsan.model;


import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("member")
public class MemberVo {
	private String userId;
	private String userPw;
	private String userPhone;
	private String userName;
	private String userJumin;
	private String userEmail;
	private String userGender;
	private int kakaoLogin;
}
