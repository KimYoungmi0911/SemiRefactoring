package semi.locationInfo.model.service;

import static semi.common.JDBCTemplat.close;
import static semi.common.JDBCTemplat.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import semi.locationInfo.model.dao.LocationInfoDao;
import semi.locationInfo.model.vo.LocationInfo;

public class LocationInfoService {

	public int getListCount() {
		Connection conn = getConnection();
		int listCount = new LocationInfoDao().getListCount(conn);
		close(conn);
		
		return listCount;
	}

	public ArrayList<LocationInfo> allSelectList(int currentPage, int limit) {
		Connection conn = getConnection();
		ArrayList<LocationInfo> list = new LocationInfoDao().allSelectList(conn, currentPage, limit);
		close(conn);
		return list;
	}

	public int getSelectCount(String selectGu, String inputText) {
		Connection conn = getConnection();
		int listCount = new LocationInfoDao().getSelectCount(conn, selectGu, inputText);
		close(conn);
		
		return listCount;
	}

	public ArrayList<LocationInfo> selectList(int currentPage, int limit, String selectGu, String inputText) {
		Connection conn = getConnection();
		ArrayList<LocationInfo> list = new LocationInfoDao().selectList(conn, currentPage, limit, selectGu, inputText);
		close(conn);
		return list;
	}

}
