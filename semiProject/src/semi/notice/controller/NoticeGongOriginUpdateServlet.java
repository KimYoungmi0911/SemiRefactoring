package semi.notice.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import semi.notice.model.service.NoticeGongService;
import semi.notice.model.vo.Notice;


/**
 * Servlet implementation class NoticeGongOriginUpdateServlet
 */
@WebServlet("/ngoriginup")
public class NoticeGongOriginUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeGongOriginUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//파일첨부 기능 게시글 수정 처리용
		
		RequestDispatcher view = null;
		
		if(!ServletFileUpload.isMultipartContent(request)) {
			view = request.getRequestDispatcher("views/notice/noticeGongError.jsp");
			request.setAttribute("message", "enctype 속성에 multipart 사용안 함");
			view.forward(request, response);
		}
		
		// request.setCharacterEncoding("utf-8");
		 
		 //업로드할 파일 용량 제한 : 10Mbyte
		 int maxSize = 1024 * 1024 * 10;
		 
		   // 업로드되는 파일의 저장 폴더 지정하기
	      // String savePath = "c:\\work\\save";
	      // 현재 구동중인 애플리케이션 폴더 안에 저장시에는
		 String savePath = request.getSession()
				 .getServletContext().getRealPath("/semi/ngupfiles");
		 
		 // cos.jar 라이브러리 사용한 경우
	      // request 를 multipart request 객체로 변환함
	      MultipartRequest mrequest = new MultipartRequest(request, savePath, maxSize, "UTF-8",
	            new DefaultFileRenamePolicy());
	      // 객체 생성과 동시에 파일 업로드 처리됨
		
	      Notice notice = new Notice();
	      notice.setN_no(Integer.parseInt(mrequest.getParameter("no")));
	      notice.setN_title(mrequest.getParameter("ngtitle"));
	      notice.setN_content(mrequest.getParameter("ngcontent"));
	      notice.setN_grade(mrequest.getParameter("grade"));
	     
	      
	      // 새로운 첨부파일이 있다면
	      // 저장폴더에 기록된 원래 파일명 조회
	      String originalFileName = mrequest.getFilesystemName("gupfile");
	      String originalFileName2 = mrequest.getFilesystemName("gupfile2");
	      if(originalFileName != null && originalFileName2 != null) {
	    	  notice.setN_file1(originalFileName);
	    	  notice.setN_file2(originalFileName2);
	    	  
	    	  String removeFileName = mrequest.getParameter("rfile");
	          File removeFile = new File(savePath + "\\" + removeFileName);
	          removeFile.delete();
	          
	      }
	      
		try {
			if(new NoticeGongService().updateNotice(notice) > 0) {
				response.sendRedirect("/semi/nglist");
			}else{
				view = request.getRequestDispatcher("views/notice/noticeGongError.jsp");
				request.setAttribute("message", "게시글 수정 실패!!!");
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
