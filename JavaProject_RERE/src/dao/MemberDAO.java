package dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import util.ConvertUtils;
import util.JDBCUtil;
import vo.MemberVo;

// 데이터베이스로 쿼리를 날려서 결과를 얻는다.
public class MemberDAO {
	// 싱글톤 패턴을 만든다.
	private static MemberDAO singleTon = null;
	
	private MemberDAO(){};
	
	public static MemberDAO getInstance() {
		if(singleTon == null) {
			singleTon = new MemberDAO();
		}
		return singleTon;
	}
	
	// JDBC를 부른다.
	JDBCUtil jdbc = JDBCUtil.getInstance();

	public List<MemberVo> memberList() {
		String sql = "select mem_no, mem_id, mem_pw,\r\n" + 
				"mem_name, mem_old, mem_room, delyn\r\n" + 
				"from member";
		List<Map<String, Object>> memlist = jdbc.selectList(sql);
		return ConvertUtils.convertToList(memlist, MemberVo.class);
	}
	
	
}
