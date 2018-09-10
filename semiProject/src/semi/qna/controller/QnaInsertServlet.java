package semi.qna.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import semi.notice.model.service.NoticeGongService;
import semi.notice.model.vo.Notice;
import semi.products.model.vo.Product;
import semi.qna.model.service.QnaService;
import semi.qna.model.vo.QnaBoard;

/**
 * Servlet implementation class QnaInsertServlet
 */
@WebServlet("/qinsert")
public class QnaInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QnaInsertServlet() {
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
					 view = request.getRequestDispatcher("views/qna/qnaError.jsp");
					 request.setAttribute("message", "enctype 속성 값 에러!");
					 view.forward(request, response);
				 }
				 
				// 파일이 업로드되어 저장될 폴더 지정
					String savePath = request.getSession().getServletContext().getRealPath("/semi/qupfiles");
					
					// request 를 MultipartRequest 로 변환함
					MultipartRequest mrequest = 
							new MultipartRequest(request, savePath, maxSize, "UTF-8", 
									new DefaultFileRenamePolicy());
					
					QnaBoard qna = new QnaBoard();
					
					String qnoString = (String)mrequest.getParameter("pro");
					String[] array = qnoString.split(" . ");
					/*System.out.println("출력 : " +mrequest.getParameter("pro"));
					System.out.println("출력2 : " + (String)mrequest.getParameter("pro"));
					System.out.println("출력3 : " + qnoString);*/
					int qnoInt = Integer.parseInt(array[0]);
					for(int i=0;i<array.length;i++) {
						//System.out.println("array 출력 : " +array[0]);
						}
					//System.out.println("qnoInt : " + qnoInt);
					
					qna.setQ_title(mrequest.getParameter("qtitle"));
					qna.setM_id(mrequest.getParameter("qwriter"));
					qna.setQ_content(mrequest.getParameter("qcontent"));
					qna.setP_no(qnoInt);
					
					
					String originalFileName = mrequest.getFilesystemName("qupfile"); //writeform 에 있는 첨부파일 이름
					String originalFileName2 = mrequest.getFilesystemName("qupfile2");
				
					qna.setQ_file1(originalFileName);
					qna.setQ_file2(originalFileName2);

				try {
					

					if(new QnaService().insertNotice(qna) > 0){
						response.sendRedirect("/semi/qlist");
					}else{
						view = request.getRequestDispatcher("views/qna/qnaError.jsp");
						request.setAttribute("message", "공지글 등록 실패!!!");
						view.forward(request, response);
					}
				} catch (Exception e) {
					view = request.getRequestDispatcher("views/qna/qnaError.jsp");
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
