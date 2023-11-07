package service;

import java.util.List;

import dao.MemberDAO;
import vo.MemberVo;

public class MemberService {
	// 싱글톤 패턴을 만든다.
	private static MemberService singleTon = null;
	
	private MemberService(){};
	
	public static MemberService getInstance() {
		if(singleTon == null) {
			singleTon = new MemberService();
		}
		return singleTon;
	}
	
	public static int loginCount = 0;
	
	// Dao를 부른다
	MemberDAO reDao = MemberDAO.getInstance();
	
	
	public List<MemberVo> memberList() {
		return reDao.memberList();
	}

	public boolean memIdChk(String id) {
		boolean pass = true;
		for(char ch : id.toCharArray()) {
			if('0'<=ch&&ch<='9') {
				
			}
			else if('a'<=ch&&ch<='z') {
				
			}
			else if('A' <= ch && ch<='Z') {
				
			}else {
				return false;
			}
		}
		if(id.length()<=10) {
			
		}else return false;
		
		return pass;
	}

	public boolean nameChk(String name) {
		boolean pass = true;
		if(2<=name.length() && name.length()<=4) {
			
		}else {
			return false;
		}
		return pass;
	}

	
}
