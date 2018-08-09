package couponSystem.phase4.spring.boot.phase4springboot.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import CouponSystemExceptions.CouponSystemException;
import Facade.AdminFacade;
import JavaBeans.Company;
import JavaBeans.Customer;



@RestController
@RequestMapping("/AdminController")
public class AdminController {
	
	

		@RequestMapping(value="CreateCompany", method = RequestMethod.POST)
		public void createCompany(@RequestParam String compName,@RequestParam String password,@RequestParam String email, HttpServletRequest request, HttpServletResponse response) 
		{
			Company company;
			HttpSession session = request.getSession(false);
			AdminFacade af= (AdminFacade) session.getAttribute("facade");
			try {
				company = new Company(compName, password, email);
				af.createCompany(company);
				
			} catch (CouponSystemException e) {
				e.printStackTrace();
			}finally {
				try {
					response.sendRedirect(response.encodeRedirectURL("../admin.html"));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		@RequestMapping(value="CreateCustomer", method = RequestMethod.POST)
		public void createCustomer(@RequestParam String custName, @RequestParam String password, HttpServletRequest request, HttpServletResponse response)
		{
			HttpSession session = request.getSession(false);
			AdminFacade af= (AdminFacade) session.getAttribute("facade");
			Customer customer;
			customer = new Customer(custName, password);
			try {
				af.createCustomer(customer);
			} catch (CouponSystemException e) {
				e.printStackTrace();
			}finally {
				try {
					response.sendRedirect(response.encodeRedirectURL("../admin.html"));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		
		@RequestMapping(value="DeleteCompany", method = RequestMethod.GET)
		public void deleteCompany(@RequestParam long id, HttpServletRequest request, HttpServletResponse response)
		{
			HttpSession session = request.getSession(false);
			AdminFacade af= (AdminFacade) session.getAttribute("facade");
			try {
				Company company = af.readCompany(id);
				af.deleteCompany(company);
			} catch (CouponSystemException e) {
				e.printStackTrace();
			}finally {
				try {
					response.sendRedirect(response.encodeRedirectURL("../admin.html"));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		@RequestMapping(value="DeleteCustomer", method = RequestMethod.GET)
		public void deleteCustomer(@RequestParam long id, HttpServletRequest request, HttpServletResponse response)
		{
			HttpSession session = request.getSession(false);
			AdminFacade af= (AdminFacade) session.getAttribute("facade");
			try {
				Customer customer = af.readCustomer(id);
				af.deleteCustomer(customer);
			} catch (CouponSystemException e) {
				e.printStackTrace();
			}finally {
				try {
					response.sendRedirect(response.encodeRedirectURL("../admin.html"));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		
		@RequestMapping(value="UpdateCompany", method = RequestMethod.GET)
		public void updateCompany(@RequestParam long id, @RequestParam String password, @RequestParam String email, HttpServletRequest request, HttpServletResponse response)
		{
			HttpSession session = request.getSession(false);
			AdminFacade af= (AdminFacade) session.getAttribute("facade");
			try {
				Company company = af.readCompany(id);
				company.setPassword(password);
				company.setEmail(email);
				af.updateCompany(company);
			} catch (CouponSystemException e) {
				e.printStackTrace();
			}finally {
				try {
					response.sendRedirect(response.encodeRedirectURL("../admin.html"));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		
		
		@RequestMapping(value="UpdateCustomer", method = RequestMethod.GET)
		public void updateCustomer(@RequestParam long id, @RequestParam String password, HttpServletRequest request, HttpServletResponse response)
		{
			HttpSession session = request.getSession(false);
			AdminFacade af= (AdminFacade) session.getAttribute("facade");
			try {
				Customer customer = af.readCustomer(id);
				customer.setPassword(password);
				af.updateCustomer(customer);
			} catch (CouponSystemException e) {
				e.printStackTrace();
			}finally {
				try {
					response.sendRedirect(response.encodeRedirectURL("../admin.html"));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		
		@RequestMapping(value="ReadCompany", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
		public Company readCompany(@RequestParam long id, HttpServletRequest request, HttpServletResponse response) 
		{
			HttpSession session = request.getSession(false);
			AdminFacade af= (AdminFacade) session.getAttribute("facade");
			Company company=new Company();
			
				try {
					company = af.readCompany(id);
				} catch (CouponSystemException e) {
					e.printStackTrace();
				}
			
			return company;
		}
		
		

		@RequestMapping(value="ReadCustomer", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
		public Customer readCustomer(@RequestParam long id, HttpServletRequest request, HttpServletResponse response)
		{
			HttpSession session = request.getSession(false);
			AdminFacade af= (AdminFacade) session.getAttribute("facade");
			Customer customer=new Customer();
			try {
				customer = af.readCustomer(id);
			} catch (CouponSystemException e) {
				e.printStackTrace();
			}
			return customer;
		}
		
		
		@RequestMapping(value="ReadAllCompanies", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
		public Collection<Company> readAllCompany(HttpServletRequest request, HttpServletResponse response) 
		{
			HttpSession session = request.getSession(false);
			AdminFacade af= (AdminFacade) session.getAttribute("facade");
			Collection<Company> companiesList = new ArrayList<>();
			try {
				companiesList = af.readAllCompany();
			} catch (CouponSystemException e) {
				e.printStackTrace();
			}
			return companiesList;
		}



		@RequestMapping(value="ReadAllCustomers", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Collection<Customer> readAllCustomer(HttpServletRequest request, HttpServletResponse response)
	{
		HttpSession session = request.getSession(false);
		AdminFacade af= (AdminFacade) session.getAttribute("facade");
		Collection<Customer> customersList = new ArrayList<>();
		try {
		
		customersList = af.readAllCustomer();
		} catch (CouponSystemException e) {
	e.printStackTrace();
		}
		return customersList;
	}

		
		@RequestMapping(value="LogOut", method = RequestMethod.GET)
	public void logOut(HttpServletRequest request, HttpServletResponse response) throws ServletException
	{
		HttpSession session = request.getSession(false);
		if (session != null) {
			session.invalidate();
			
			try {
				response.sendRedirect("/index.html");

			} catch (IOException e) {
				e.printStackTrace();
			}
			
		
		}
	}







	}



