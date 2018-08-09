package couponSystem.phase4.spring.boot.phase4springboot.controllers;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import CouponSystem.CouponSystem;
import CouponSystemExceptions.CouponSystemException;
import Facade.AdminFacade;
import Facade.ClientFacade;
import Facade.ClientType;
import JavaBeans.Company;

@RestController
@RequestMapping("/")
public class UtilityController{
	
	
	
	
	@RequestMapping("LoginServlet")
		public void service(@RequestParam String userName, @RequestParam String password,@RequestParam ClientType type, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession(false);
		// check the session state
		if (session != null)
		{
			session.invalidate();
		}
		
		// check the CouponSystem login
		String name = request.getParameter("userName");
		String password1 = request.getParameter("password");
		ClientType type1 = ClientType.valueOf(request.getParameter("type"));
		

		try {
	    ClientFacade facade;
			facade = CouponSystem.getInstance().login(name, password, type);
		System.out.println(facade);
		session = request.getSession(true);
		session.setMaxInactiveInterval(600);
		session.setAttribute("facade", facade);

		if (facade == null)
		{
			 response.sendRedirect("LoginError.html");
		}else {
			
			
			switch (type) {
			case ADMIN:
				
				response.sendRedirect("admin.html");	
				break;
			case COMPANY:
				response.sendRedirect("company.html");		
				break;
			case CUSTOMER:
				response.sendRedirect("customer.html");		
				break;
				
				
			default:
				break;
			}
			
		
	   
	    }
		}catch (CouponSystemException e) {
			response.sendRedirect("LoginError.html");		
		}
		}
	


		
	@RequestMapping("LogOut")
public void logOut(HttpServletRequest request, HttpServletResponse response) throws ServletException
{
	HttpSession session = request.getSession(false);
	if (session != null) {
		session.invalidate();
		
		try {
			response.sendRedirect("../../../CouponSystem_Phase2/index.html");

		} catch (IOException e) {
			e.printStackTrace();
		}
		
	
	}
}

		
		
	
	
	
}

		




	

