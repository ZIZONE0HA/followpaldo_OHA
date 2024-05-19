package jjon.gangsan.member.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


import jjon.gangsan.model.MemberVo;
import jjon.gangsan.model.ZzimList;


@Mapper
public interface MemberDaoImpl {
	
	//회원가입
	public int member_join_go(MemberVo member);

	//로그인 인증
	public MemberVo user_check(String user_id);

	//id중복검사
	public int user_id_check(String user_id);

	//회원수정
	public void member_edit_go(MemberVo member);

	//회원삭제
	public void member_delete_go(MemberVo member);
	
	// 카카오 간편로그인을 위한 select문
	public Integer selectKakao(String kakaoEmail);
	
	// 카카오 연동&간편로그인을 위한 select문
	public String selectEmail(String kakaoEmail);
	
	// 카카오 연동 확인 정보를 넣기 위한 update문
	public void updateKakao(String kakaoEmail);
	
	// 간편 로그인에서 user_id값을 받아오기 위한 select문
	public String  selectUser_id(String kakaoEmail);
	
	// 현재 로그인한 유저의 찜목록 수
	public int countZzimList(@Param("userId") String user_id);
	
	// 찜목록 데이터 가져오기
	public List<ZzimList> getZzimList(Map m);
	
	
}
