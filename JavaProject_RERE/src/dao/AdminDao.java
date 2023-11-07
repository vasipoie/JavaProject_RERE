package dao;

import java.util.Map;

import exception.IdChkException;
import util.ConvertUtils;
import util.JDBCUtil;
import vo.AdminVo;

public class AdminDao {
	
	private static AdminDao singleTon = null;
	
	private AdminDao(){};
	
	public static AdminDao getInstance() {
		if(singleTon == null) {
			singleTon = new AdminDao();
		}
		return singleTon;
	}

		JDBCUtil jdbc = JDBCUtil.getInstance();

		
		/*
		 * public AdminVo adminLogin(String id, String pass) {
			String sql = "select adm_id, adm_pw\r\n" + 
						 "from admin\r\n" + 
						 "where adm_id='"+id+"'"+"and adm_pw='"+pass+"'";
			Map<String, Object> map = jdbc.selectOne(sql);
			if(map == null) throw new IdChkException();
			AdminVo ad = ConvertUtils.convertToVo(map, AdminVo.class);
			return ad; 
		}
		 */
		
		public AdminVo adminLogin(String id, String pass) {
			String sql = "select *\r\n" + 
					 "from admin\r\n" + 
					 "where adm_id='"+id+"'"+"and adm_pw='"+pass+"'";
			Map<String, Object> map = jdbc.selectOne(sql);
			if(map == null) return null;
			AdminVo ad = ConvertUtils.convertToVo(map, AdminVo.class);
			return ad; 
		}

		public boolean adminLoginErr(String id, String pass) {
			String sql = "select adm_id, adm_pw\r\n" + 
					 "from admin\r\n" + 
					 "where adm_id='"+id+"'";
			Map<String, Object> map = jdbc.selectOne(sql);
			String sql2="";
			Map<String, Object> map2 = null;
			if(map == null) {
				System.out.println("아이디를 찾을 수 없습니다");
				return false;
			}
			else if(map != null){
				sql2 = "select adm_id, adm_pw\r\n" + 
						"from admin\r\n" + 
						"where adm_id='"+id+"'"+"and adm_pw='"+pass+"'";
				map2 = jdbc.selectOne(sql2);
				if(map2 == null) {
					System.out.println("비밀번호가 올바르지 않습니다");
					return false;
				}
				else {
					adminLogin(id, pass);
				}
			}
			return true;
		}
}