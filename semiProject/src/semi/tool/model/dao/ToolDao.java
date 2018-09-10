package semi.tool.model.dao;

import java.sql.Connection;
import java.util.ArrayList;

import semi.tool.model.vo.Tool;

public class ToolDao {

	public int selectTool(Connection conn, Tool tool) {
		return 0;
	}
	
	public String selectIma(Connection conn, Tool tool) {
		return null;
	}
	
	public Tool selectProduct(Connection conn, String productNo) {
		Tool tool = null;
		
		return tool;
	}
	
	public ArrayList<Tool> listView(Connection conn) {
		ArrayList<Tool> list = null;
		
		return list;
	}
	
	public ArrayList<Tool> productNameSelectTool(Connection conn, String productName) {
		ArrayList<Tool> list = null;
		
		return list;
	}
	
	public ArrayList<Tool> categorySelectTool(Connection conn, String categoryName) {
		ArrayList<Tool> list = null;
		
		return list;
	}
	
	public int selectDeleteTool(Connection conn, int memberNo) {
		int result = 0;
		
		return result;
	}
	
	public int deleteTool(Connection conn, Tool tool) {
		return 0;
	}
	
	public int updateTool(Connection conn, Tool tool) {
		return 0;
	}
	
	public int insertTool(Connection conn, Tool tool) {
		return 0;
	}
}
