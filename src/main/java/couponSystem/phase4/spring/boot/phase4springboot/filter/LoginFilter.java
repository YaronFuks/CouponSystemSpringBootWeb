package couponSystem.phase4.spring.boot.phase4springboot.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import Facade.ClientFacade;

@Component
@Order(1)
public class LoginFilter implements Filter {

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest httpServletRequest = (HttpServletRequest) request;

		if (httpServletRequest.getSession(false) == null) {
			// redirect
			System.out.println("login-Filter: no Session, user will be redirected.");
			((HttpServletResponse) response)
					.sendRedirect(((HttpServletResponse) response).encodeRedirectURL("index.html"));
//			request.getRequestDispatcher("/index.html");

		} else {
			// Getting the reference to the session
			HttpSession session = httpServletRequest.getSession(false);

			// Receiving the facade by session.getAttribute
			ClientFacade facade = (ClientFacade) session.getAttribute("facade");

			// checking if the facade exists:
			if (facade == null) {

				System.out.println("Login Filter: No Facade");
				request.getRequestDispatcher("/index.html");

			} else {
				System.out.println("Request Forward");
				chain.doFilter(request, response);
			}

		}

	}

	public void init(FilterConfig filterConfig) throws ServletException {

	}

	public void destroy() {

	}

	

}
