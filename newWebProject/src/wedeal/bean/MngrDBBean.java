package wedeal.bean;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MngrDBBean {
	private Connection conn = null;
	private PreparedStatement pstmt;
	private ResultSet rs;

	private static MngrDBBean instance = new MngrDBBean();

	public static MngrDBBean getinstance() {
		return instance;
	}

	private MngrDBBean() {
		try {
			String dbURL = "jdbc:mysql://204.249.22.34:3306/se?autoReconnect=true&useSSL=false";
			String dbID = "jy";
			String dbPW = "1365";
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(dbURL, dbID, dbPW);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void deleteBoard(int cate_num) {
        try {
            PreparedStatement preparedStatement = conn.prepareStatement("delete from users where cate_num=?");
            preparedStatement.setInt(1, cate_num);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

	}

	public ArrayList<CateDataBean>  getAllBoard() {
		String SQL="SELECT * FROM cate WHERE cate_parent > 0 ORDER BY cate_num DESC";
		ArrayList<CateDataBean> list = new ArrayList<CateDataBean>();
		try {
			PreparedStatement pstmt=conn.prepareStatement(SQL);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				//여기도 테이블 몰라서 테이블에 맞게 추가해야대
				CateDataBean catedatabean = new CateDataBean();
				catedatabean.setCate_num(rs.getInt(1));
				catedatabean.setCate_name(rs.getString(2));
				catedatabean.setCate_date(rs.getString(3));
				catedatabean.setCate_parent(rs.getInt(4));
				list.add(catedatabean);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public CateDataBean getBoardById(int boardId) {
		CateDataBean board = new CateDataBean();
		try {
			// 여기 테이블 뭔지 모르겠어!
			PreparedStatement preparedStatement = conn.prepareStatement("select * from users where boardid=?");
			preparedStatement.setInt(1, boardId);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				board.setCate_num(rs.getInt("boardId"));
				board.setUpCategoryName("upBoardName");
				board.setCate_name("boardName");
				board.setAdminName("adminName");
				board.setSavePath("savePath");
				board.setFileName("fileName");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return board;
	}

	public void addBoard(CateDataBean cateDataBean) {
		try {
            PreparedStatement preparedStatement = conn.prepareStatement("insert into users(boardId,upBoardName,boardName,adminName,savePath,filepath) values (?, ?, ?, ?, ? )");
            preparedStatement.setInt(1, cateDataBean.getCate_num());
            preparedStatement.setString(2, cateDataBean.getUpCategoryName());
            preparedStatement.setString(3, cateDataBean.getCate_name());
            preparedStatement.setString(4, cateDataBean.getAdminName());
            preparedStatement.setString(5, cateDataBean.getSavePath());
            preparedStatement.setString(6, cateDataBean.getFileName());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }		
	}

	public void updateBoard(CateDataBean cateDataBean) {
		try {
            PreparedStatement preparedStatement = conn.prepareStatement("update users set upBoardName=?, boardName=?, adminName=?, filename=?, filepath=?" +
                            "where cate_num=?");
            
            preparedStatement.setString(1, cateDataBean.getUpCategoryName());
            preparedStatement.setString(2, cateDataBean.getCate_name());
            preparedStatement.setString(3, cateDataBean.getAdminName());
            preparedStatement.setString(4, cateDataBean.getSavePath());
            preparedStatement.setString(5, cateDataBean.getFileName());
            preparedStatement.setInt(6, cateDataBean.getCate_num());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
		
	}
}
