import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
	String driver = "com.mysql.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/orm";
	String db_id = "root";
	String db_pw = "";

	public void create(UserVO vo) throws Exception {
		Class.forName(driver);

		Connection c = DriverManager.getConnection(url, db_id, db_pw);

		Statement st = null;

		String sql = "insert into member(id, pw, name) values ('" + vo.getId()
				+ "','" + vo.getPw() + "','" + vo.getName() + "')"; // 쿼리문 끝에
																	// 세미콜론을 넣으면
																	// 안된다.
		st = c.createStatement();

		st.executeUpdate(sql);

		st.close();
		c.close();

	}

	public UserVO read(String id) throws Exception {
		UserVO result = null;

		Class.forName(driver);

		Connection c = DriverManager.getConnection(url, db_id, db_pw);

		Statement st = null;

		String query = "SELECT ID, PW, NAME FROM MEMBER WHERE ID='" + id + "'";

		st = c.createStatement();

		ResultSet rs = st.executeQuery(query);

		rs.next();

		if (rs.getRow() == 0) {
			return null;
		}

		result = new UserVO();
		result.setId(rs.getString(1));
		result.setPw(rs.getString(2));
		result.setName(rs.getString(3));
		System.out.println(result.toString());

		rs.close();
		st.close();
		c.close();

		return result;
	}

	public void update(UserVO vo) throws Exception {
		Class.forName(driver);

		Connection c = DriverManager.getConnection(url, db_id, db_pw);

		Statement st = null;

		String query = "UPDATE MEMBER " + "SET PW = '" + vo.getPw() + "',"
				+ "NAME='" + vo.getName() + "' " + "WHERE ID = '" + vo.getId()
				+ "'";
		st = c.createStatement();

		st.execute(query);

		st.close();
		c.close();
	}

	public void delete(String id) throws Exception {
		Class.forName(driver);

		Connection c = DriverManager.getConnection(url, db_id, db_pw);

		Statement st = null;
		
		String query = "DELETE FROM MEMBER WHERE ID = '" + id + "'";
		
		st = c.createStatement();
		
		st.execute(query);
		
		st.close();
		c.close();
	}

	public List<UserVO> listAll() throws Exception {
		List<UserVO> result = new ArrayList<UserVO>();
		
		UserVO vo = null;
		
		Class.forName(driver);

		Connection c = DriverManager.getConnection(url, db_id, db_pw);

		Statement st = null;
		
		String query = "SELECT ID, PW, NAME FROM MEMBER";
		
		st = c.createStatement();
		
		ResultSet rs = st.executeQuery(query);
		
		while(rs.next()){
			vo = new UserVO();
			vo.setId(rs.getString(1));
			vo.setPw(rs.getString(2));
			vo.setName(rs.getString(3));
			
			result.add(vo);
		}
		
		rs.close();
		st.close();
		c.close();

		return result;
	}
}
