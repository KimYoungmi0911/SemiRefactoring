package semi.notice.controller;

import java.io.IOException;
import java.util.Iterator;

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



/**
 * Servlet implementation class NoticeGongInsertServlet
 */
@WebServlet("/nginsert")
public class NoticeGongInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeGongInsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
		
		

		//파일 첨부 기능 공지글 등록 처리용 컨트롤러
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		//업로드할 파일의 용량 제한 : 예, 10Mbyte 로 정한다면
		 int maxSize = 1024 * 1024 * 10;
		
		 RequestDispatcher view =null;
		// enctype 속성이 "multipart/form-data"로 전송 체크
		 if(!ServletFileUpload.isMultipartContent(request)){
			 view = request.getRequestDispatcher("views/notice/noticeGongError.jsp");
			 request.setAttribute("message", "enctype 속성 값 에러!");
			 view.forward(request, response);
		 }
		
		// 파일이 업로드되어 저장될 폴더 지정
		String savePath = request.getSession().getServletContext().getRealPath("/semi/ngupfiles");
		
		// request 를 MultipartRequest 로 변환함
		MultipartRequest mrequest = 
				new MultipartRequest(request, savePath, maxSize, "UTF-8", 
						new DefaultFileRenamePolicy());
		
		// 전송온 값 꺼내서 변수/객체에 저장하기
		Notice notice = new Notice();
		/*//Notice n = null;
		String title = mrequest.getParameter("ngtitle");
		String writer = mrequest.getParameter("ngwriter");
		String con = mrequest.getParameter("ngcontent");
		String file1 = mrequest.getParameter("file1");
		String file2 = mrequest.getParameter("file2");
		String grade = mrequest.getParameter("grade");
		
		notice.setN_title(title);
		notice.setA_id(writer);
		notice.setN_content(con);
		notice.setN_file1(file1);
		notice.setN_file2(file2);
		notice.setN_grade(grade);*/
		
		
		notice.setN_title(mrequest.getParameter("ngtitle"));
		notice.setA_id(mrequest.getParameter("ngwriter"));
		notice.setN_content(mrequest.getParameter("ngcontent"));
		notice.setN_grade(mrequest.getParameter("grade"));
		
	
		
		//저장폴더에 기록된 원래 파일명 조회
		String originalFileName = mrequest.getFilesystemName("gupfile"); //writeform 에 있는 첨부파일 이름
		String originalFileName2 = mrequest.getFilesystemName("gupfile2");
		
		notice.setN_file1(originalFileName);
		notice.setN_file2(originalFileName2);
		
		
		////ajax 시작
		
		/*String json1 = request.getParameter("jsonStr");
		System.out.println("JSON : " + json1);*/
		
		try {
			/*JSONParser jsonParser = new JSONParser();
			JSONObject job = (JSONObject)jsonParser.parse(json1);
			String  Title = (String)job.get("ngtitle");
			String  Writer = (String)job.get("ngwriter");
			String  File1 = (String)job.get("gupfile");
			String  File2 = (String)job.get("gupfile2");
			String  Content = (String)job.get("ngcontent");
			String  Grade = (String)job.get("grade");
			
			n = new Notice();
			n.setN_title(Title);
			n.setA_id(Writer);
			n.setN_file1(File1);
			n.setN_file2(File2);
			n.setN_content(Content);
			n.setN_grade(Grade);
			
			System.out.println("JSON/N변수에 담은 : " + n.toString());*/
			
			
			if(new NoticeGongService().insertNotice(notice) > 0){
				response.sendRedirect("/semi/nglist");
			}else{
				view = request.getRequestDispatcher("views/notice/noticeGongError.jsp");
				request.setAttribute("message", "공지글 등록 실패!!!");
				view.forward(request, response);
			}
		} catch (Exception e) {
			view = request.getRequestDispatcher("views/notice/noticeGongError.jsp");
			request.setAttribute("message", e.getMessage());
			view.forward(request, response);
		}
		 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
