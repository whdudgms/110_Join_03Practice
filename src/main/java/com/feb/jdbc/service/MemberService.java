package com.feb.jdbc.service;

import java.util.HashMap;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.feb.jdbc.dao.MemberDao;
import com.feb.jdbc.entity.Member;
import com.feb.jdbc.util.Sha512Encoder;

@Service
public class MemberService {
	
	@Autowired
	MemberDao memberDao;
	
	
	/**
	 * 
	 * @param params  memberName을 ID처럼 생각해고 다루기 
	 * @return login처리결과 true false로 응답 
	 */
	public boolean login(HashMap<String,String> params) {
		
		Member member = memberDao.selectMember(params.get("memberName"));
		if(Objects.isNull(member))
			return false;
		String passwd = member.getPasswd(); 
		Sha512Encoder encoder = Sha512Encoder.getInstance();
		String inputPasswd = encoder.getSecurePassword(params.get("passwd"));
		
		return inputPasswd.equals(passwd);
		
	}
	
	/**
	 * 
	 * @param params passwd1 passwd2같은지 체크 
	 * @return join처리결과 true false로 응답  
	 */
	public boolean join(HashMap<String,String> params) {

	Sha512Encoder encoder = Sha512Encoder.getInstance();
	
	if(!(params.get("passwd1").equals(params.get("passwd2")))) {
		System.out.println("false1");
		return false;
	}
	params.put("memberId", params.get("memberName"));
	params.put("passwd",encoder.getSecurePassword(params.get("passwd1")));
	
	return memberDao.insertMember(params) == 1;

		
	}
	

}
