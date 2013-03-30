package util;

import java.util.Map;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import com.opensymphony.xwork2.ActionContext;

@Aspect
public class Authoriry {
	
	ActionContext actionContext = ActionContext.getContext();
	Map session = actionContext.getSession();
	
	String role =  (String)session.get("role");
	//Ȩ���ж�
	@Around("execution(*com.action.*(..))")
	public void processAuthoriry(ProceedingJoinPoint jp) throws java.lang.Throwable{
		if(role==""||role.equals("nunll")){
			System.out.println("你还未登录，请先登录");
		}else{
			jp.proceed();
		}
		
	}
}
