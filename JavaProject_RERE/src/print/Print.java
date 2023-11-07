package print;

import java.util.List;

import vo.MemberVo;

public class Print {
	
	public void printVar() {
		System.out.println("-----------------------------------------------");
	}
	public void printLn(int num) {
		for(int i=0; i<num; i++)
			System.out.println();
	}
	

	public void printHome() {
		printVar();
		System.out.println("1. 관리자");
		System.out.println("2. 일반 회원");
		printLn(5);
		printVar();
	}
	
	public void printAdmin() {
		printVar();
		System.out.println("1. 회원 전체 조회");
		System.out.println("2. 홈");
		printLn(5);
		printVar();
	}
	
	public void printMemberList(List<MemberVo> memlist) {
		printVar();
		System.out.println("회원번호\t아이디\t비밀번호\t이름\t나이\t호실\t탈퇴여부");
		for(MemberVo memberlist : memlist) {
			System.out.printf("%d\t%s\t%s\t%s\t%d\t%s\t%s\t\n",
					memberlist.getMem_no(), memberlist.getMem_id(),
					memberlist.getMem_pw(), memberlist.getMem_name(),
					memberlist.getMem_old(), memberlist.getMem_room(),
					memberlist.getDelyn());
		}
		printVar();
	}
	public void printMemberListAfter() {
		System.out.println("1. 홈");
		printLn(5);
		printVar();
	}
	
	public void printMember() {
		printVar();
		System.out.println("1. 회원 가입");
		System.out.println("2. 회원 정보 수정");
		System.out.println("3. 회원 탈퇴");
		System.out.println("4. 홈");
		printLn(3);
		printVar();
	}
	
	
}
