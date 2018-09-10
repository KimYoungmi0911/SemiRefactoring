package semi.common.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import semi.common.wrapper.CommonWrapper;

/**
 * Servlet Filter implementation class CommonFilter
 */
//@WebFilter(filterName = "encoding", urlPatterns = { "/*" })
public class CommonFilter implements Filter {
	//필터 클래스는 반드시 Filter 인터페이스를 상속받아서 
	//재구현해야 함
    /**
     * Default constructor. 
     */
    public CommonFilter() {
        // TODO Auto-generated constructor stub
    	System.out.println("CommonFilter 객체 생성됨...");
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
		System.out.println("CommonFilter 객체 소멸됨...");
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, 
			ServletResponse response, 
			FilterChain chain) throws IOException, ServletException {
		// 매개변수가 반드시 3개여야 함
		// request, response, chain

		//서블릿으로 보내기 전에 가공 처리할 내용에 대한
		//코드 작성
		System.out.println("CommonFilter 의 doFilter() run...");
		
		//request 매개변수를 이용하여 요청값에 대한
		//필터링 처리 가능함
		HttpServletRequest hRequest = 
				(HttpServletRequest)request;
		//post 방식으로 전송온 경우에만, 요청값 한글
		//인코딩 처리 필요함
		if(hRequest.getMethod().equalsIgnoreCase("post")){
			System.out.println("post 전송시에만 인코딩 처리함...");
			request.setCharacterEncoding("utf-8");
		}
		
		//필터가 request 또는 response 객체 값에 대한
		//가공처리를 하려고 한다면, 래퍼클래스를 실행해야 함
		CommonWrapper requestWrapper = 
				new CommonWrapper(hRequest);
		
		// 실제 서블릿으로 넘기는 코드임
		//chain.doFilter(request, response);
		//래퍼가 가공처리한 객체 정보를 서블릿으로 넘기려면
		chain.doFilter(requestWrapper, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// 필터 초기화 처리용 메소드
		// 필터 등록시 init parameter 처리하는 역할을 담당함
		System.out.println("CommonFilter 의 init() run...");
	}

}









