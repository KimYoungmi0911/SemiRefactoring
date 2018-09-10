package semi.notice.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import semi.notice.model.service.NoticeGongService;
import semi.notice.model.vo.Notice;
import semi.notice.model.vo.NoticeComment;





/**
 * Servlet implementation class NoticeGongCommentServlet
 */
@WebServlet("/ngcomment")
public class NoticeGongCommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeGongCommentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//댓글 달기 처리용
	/*	
		response.setContentType("text/html; charset=utf-8");
		
		
		
		RequestDispatcher view = null;
				
		NoticeComment notice = new NoticeComment();
		
		//int ccc = Integer.parseInt(request.getParameter("no"));
		//String comm = request.getParameter("comment");
		notice.setCommentContent(request.getParameter("comment"));
	    notice.setCommentNotice(Integer.parseInt(request.getParameter("no")));
	
	  System.out.println("서블릿출력 : " + notice);
		
		try {
			if(new NoticeGongService().insertComment(notice) > 0){
				response.sendRedirect("/second/nglist");
				request.setAttribute("notice", notice);
				
		
			}else{
				view = request.getRequestDispatcher("gonggu/noticego/noticeGongError.jsp");
				request.setAttribute("message", "댓글등록실패!!");
				view.forward(request, response);				
			}
			
		} catch (NoticeGongException e) {
			view = request.getRequestDispatcher("gonggu/noticego/noticeGongError.jsp");
			request.setAttribute("message", e.getMessage());
			view.forward(request, response);	
		}*/
		NoticeComment nComment = new NoticeComment();
		Notice notice = new Notice();
		
		
		int ccc = Integer.parseInt(request.getParameter("no"));
		String comm = request.getParameter("com");
		
		nComment.setCommentNotice(ccc);
		nComment.setCommentContent(comm);

		try {
			if(new NoticeGongService().insertComment(nComment) > 0){
				
				request.setAttribute("notice", notice);
				
				JSONObject notice1 = new JSONObject();
				notice1.put("no", notice.getN_no());
				notice1.put("com", notice.getN_content());
				
				response.setContentType("application/json; charset=utf-8");
				PrintWriter out = response.getWriter();
				out.println(notice1);
				out.close();
				
				
			}
			
		} catch (Exception e) {
			
			request.setAttribute("message", e.getMessage());
		
		}finally{
			
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
