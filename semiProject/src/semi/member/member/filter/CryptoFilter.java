package semi.member.member.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import semi.member.member.wrapper.CryptoWrapper;

/**
 * Servlet Filter implementation class CryptoFilter
 */
//@WebFilter("*.cp")
public class CryptoFilter implements Filter {

    /**
     * Default constructor. 
     */
    public CryptoFilter() {
    	
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		///회원 비밀번호 비밀번호 암호화 처리
    	HttpServletRequest hRequest = (HttpServletRequest)request;
    	
    	CryptoWrapper cw = new CryptoWrapper(hRequest);
    	
    	chain.doFilter(cw, response);
		
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
