package semi.member.member.wrapper;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.util.Base64;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class CryptoWrapper extends HttpServletRequestWrapper{

	public CryptoWrapper(HttpServletRequest request) {
		super(request);
	}
	
	@Override
	public String getParameter(String name){
		String value = null;
		System.out.println("name : " + name);
		if(name != null && name.equals("m_password1")){
			System.out.println("super : " + super.getParameter(name));
			value = getSha512(super.getParameter(name));
		}else{
			value = super.getParameter(name);
		}
		
		return value;
	}
	
	private String getSha512(String m_password1) {
		String cryptoPwd = null;
		System.out.println("pass : " + m_password1);
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-512");
			byte[] pwdValues = m_password1.getBytes(Charset.forName("UTF-8"));
			md.update(pwdValues);
			cryptoPwd = Base64.getEncoder().encodeToString(pwdValues);
			
		} catch (Exception e) {
			System.out.println("Sha512 error...");
			e.printStackTrace();
		}
		
		return cryptoPwd;
	}

}
