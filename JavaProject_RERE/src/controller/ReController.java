package controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import exception.PassChkException;
import print.Print;
import service.AdminService;
import service.MemberService;
import util.ScanUtil;
import util.View;
import vo.AdminVo;
import vo.MemberVo;

public class ReController extends Print {
	// 세션
	static public Map<String, Object> sessionStorage = new HashMap<>();

	MemberService memberService = MemberService.getInstance();
	AdminService adminService = AdminService.getInstance();
	
	
	public static void main(String[] args) {
//		Controller c = new Controller();
//		c.start();
		new ReController().start();
	}

	 void start() {
		View view = View.HOME;
		while (true) {
			switch (view) {
			case HOME:
				view = home();
				break;
			case ADMINLOGIN: //관리자 로그인
				view = adminLogin();
				break;
			case ADMIN: //관리자 페이지
				view = admin();
				break;
			case MEMBERLIST: //회원 전체 조회
				view = memberList();
				break;
			case MEMBER: //회원 페이지
				view = member();
				break;
			case SIGNUP: //회원가입
				view = signUp();
				break;
//			case MEMBERMODIFY: //회원 정보 수정
//				view = memberModify();
//				break;
//			case MEMBERDELETE: //회원 탈퇴
//				view = memberDelete();
//				break;
			}
		}
	}

	 //2.일반회원 - 1.회원가입
	private View signUp() {
		System.out.println("회원가입 정보를 입력해주세요");
		String id = "";
		while(true) {
			id = ScanUtil.nextLine("회원 ID>>> ");
			boolean idPass = memberService.memIdChk(id);
			if(idPass) break;
			else {
				if(id.length()>10) {
					System.out.println("아이디를 10자 이내로 입력하세요");
				}else System.out.println("영문과 숫자만 입력하세요");
			}
		}
		
		String pass = ScanUtil.nextLine("회원 PW>>> ");
			if(pass.contains("admin")) {
				throw new PassChkException();
			}
		
		String name;
		while(true) {
			name = ScanUtil.nextLine("이름>>> ");
			boolean namePass = memberService.nameChk(name);
			if(namePass) break;
			else {
				System.out.println("이름은 2~4글자만 입력하세요");
			}
		}
		
		int old = ScanUtil.nextInt("나이>>> ");
		String room = "403호";
		
		List<Object> param = new ArrayList<Object>();
		param.add(id);
		param.add(pass);
		param.add(name);
		param.add(old);
		param.add(room);
		
//		boolean signPass = memberService.signUp(param);
		
			
			
			
			
			
		return null;
	}

	//2.일반회원
	private View member() {
		printMember();
		int select  = ScanUtil.nextInt("메뉴를 선택하세요\s");
		switch(select) {
		case 1:
			return View.SIGNUP;
		case 2:
			return View.MEMBERMODIFY;
		case 3:
			return View.MEMBERDELETE;
		case 4:
			return View.HOME;
		default :
			return View.MEMBER;
		}
	}
	
	//1.관리자-로그인확인-1.회원전체조회 후
	private View memberList() {
		List<MemberVo> list = memberService.memberList();
		printMemberList(list);
		printMemberListAfter();
		int select = ScanUtil.nextInt("메뉴를 선택하세요\s");
		switch (select) {
		case 1:
			return View.HOME;
		default :
			return View.MEMBERLIST;
		}
	}
	
	//관리자 로그인 확인 후
	private View admin() {
		printAdmin();
		int select = ScanUtil.nextInt("메뉴를 선택하세요\s");
		switch (select) {
		case 1:
			return View.MEMBERLIST; //회원전체조회
		default :
			return View.ADMIN;
		}
	}

	//관리자페이지 - 관리자 로그인 확인
	private View adminLogin() {
		//서비스로 보내서 로그인 하게 한 후
		String id   = ScanUtil.nextLine("관리자 ID>>> ");
		String pass = ScanUtil.nextLine("관리자 PW>>> ");
		
		/*
		 * List param으로 서비스로 보내는 경우
		 * List<Object> param = new ArrayList<Object>();
		 * param.add(id);
		 * param.add(pass);
		 * boolean loginChk = adminService.adminLogin(param);
		 */
		boolean loginChk = adminService.adminLogin(id, pass);
		if(loginChk) {
			AdminVo ad = (AdminVo) sessionStorage.get("adminLogin");
			System.out.println(ad.getAdm_name()+"님 환영합니다");
			return View.ADMIN;
		}else {
			System.out.println("다시 로그인해주세요");
			System.out.println();
			return View.ADMINLOGIN;
		}
	}
	
	//홈 -1.관리자 / 2.일반 회원
	 View home() {
		printHome();
		int select = ScanUtil.nextInt("메뉴를 선택하세요\s");
		switch (select) {
		case 1:
			return View.ADMINLOGIN;
		case 2:
			return View.MEMBER;
		default :
			return View.HOME;
		}
	}
}
