package semi.common.wrapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

//Filter 클래스에서 request 값 가공을 목적으로 하는 클래스
//request 에 대한 가공처리용 래퍼와
//response 에 대한 가공처리용 래퍼로 만들 수 있음.
//각각 HttpServletRequestWrapper 또는
//HttpServletResponseWrapper 중 하나를 상속 받아서 만듦

//CommonFilter 의 request 객체 값 가공처리용 래퍼클래스
public class CommonWrapper 
	extends HttpServletRequestWrapper{
	//부모클래스에 기본 생성자 없음
	//후손이 매개변수가 있는 생성자만 만들 수 있음
	public CommonWrapper(HttpServletRequest request) {
		super(request);		
	}
	
	//request 객체의 값 가공처리에 getParameter() 
	//메소드를 오버라이딩함
	@Override
	public String getParameter(String name){
		System.out.println("CommonWrapper 의 getParameter() run...");
		return super.getParameter(name);
	}
}










