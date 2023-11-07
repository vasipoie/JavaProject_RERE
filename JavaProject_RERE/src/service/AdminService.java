package service;

import java.util.Map;

import controller.ReController;
import dao.AdminDao;
import vo.AdminVo;


public class AdminService {
	//sessionStorage 얕은 복사
	static public Map<String, Object> sessionStorage = ReController.sessionStorage;

	private static AdminService singleTon = null;
	
	private AdminService(){};
	
	public static AdminService getInstance() {
		if(singleTon == null) {
			singleTon = new AdminService();
		}
		return singleTon;
	}
	
	AdminDao adminDao = AdminDao.getInstance();
	
	public boolean adminLogin(String id, String pass) {
		boolean adLogin = adminDao.adminLoginErr(id, pass);
		if(adLogin == false) {
			return false;
		} else {
			AdminVo ad = adminDao.adminLogin(id, pass);
			if(ad != null) {
				sessionStorage.put("adminLogin", ad);
				return true;
			}
		}
		return true;
	}
}