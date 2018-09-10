package semi.board.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.servlet.ServletFileUpload;


import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import semi.board.model.service.BoardService;
import semi.board.model.vo.Board;


/**
 * Servlet implementation class BoardInsertServlet
 */
@WebServlet("/binsert")
public class BoardInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardInsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//파일 첨부 기능 공지글 등록 처리용 컨트롤러
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		//업로드할 파일의 용량 제한 : 예, 10Mbyte 로 정한다면
		 int maxSize = 1024 * 1024 * 10;
		
		 RequestDispatcher view =null;
		// enctype 속성이 "multipart/form-data"로 전송 체크
		 if(!ServletFileUpload.isMultipartContent(request)){
			 view = request.getRequestDispatcher("views/board/boardError.jsp");
			 request.setAttribute("message", "enctype 속성 값 에러!");
			 view.forward(request, response);
		 }
		
		// 파일이 업로드되어 저장될 폴더 지정
		String savePath = request.getSession().getServletContext().getRealPath("/semi/bupfiles");
		
		// request 를 MultipartRequest 로 변환함
		MultipartRequest mrequest = 
				new MultipartRequest(request, savePath, maxSize, "UTF-8", 
						new DefaultFileRenamePolicy());
				
		Board board = new Board();
		
		String qnoString = (String)mrequest.getParameter("pro");
		String[] array = qnoString.split(" . ");
		
		int qnoInt = Integer.parseInt(array[0]);
		for(int i=0;i<array.length;i++) {
			//System.out.println("array 출력 : " +array[0]);
			}
		
		board.setRb_title(mrequest.getParameter("btitle"));
		board.setM_id(mrequest.getParameter("bwriter"));
		board.setRb_content(mrequest.getParameter("bcontent"));
		board.setP_no(qnoInt);
		
		String originalFileName = mrequest.getFilesystemName("bupfile"); //writeform 에 있는 첨부파일 이름
		String originalFileName2 = mrequest.getFilesystemName("bupfile2");
		
		board.setRb_file1(originalFileName);
		board.setRb_file2(originalFileName2);
		
			
			try {

				if(new BoardService().insertNotice(board) > 0){
					response.sendRedirect("/semi/blist");
				}else{
					view = request.getRequestDispatcher("views/board/boardError.jsp");
					request.setAttribute("message", "공지글 등록 실패!!!");
					view.forward(request, response);
				}
			} catch (Exception e) {
				view = request.getRequestDispatcher("views/board/boardError.jsp");
				request.setAttribute("message", e.getMessage());
				view.forward(request, response);
			}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}























