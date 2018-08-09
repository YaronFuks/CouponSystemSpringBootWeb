package couponSystem.phase4.spring.boot.phase4springboot.controllers;

import java.io.IOException;
import java.sql.Date;
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
import Facade.CompanyFacade;
import JavaBeans.Coupon;

@RestController
@RequestMapping("/CompanyController")
public class CompanyController {
	
	@RequestMapping(value="createCoupon", method = RequestMethod.POST)
	public void createCoupon(@RequestParam("title")String title,
			@RequestParam("start_date") Date start_date,
			@RequestParam("end_date") Date end_date,
			@RequestParam("amount") int amount,
			@RequestParam("type") JavaBeans.Coupon.CouponType type,
			@RequestParam("message") String message,
			@RequestParam("price") double price,
			@RequestParam("image") String image,
			HttpServletRequest request, HttpServletResponse response)
	{
		HttpSession session = request.getSession(false);
		CompanyFacade cf=  (CompanyFacade) session.getAttribute("facade");
		Coupon coupon;
		try {
			coupon = new Coupon(title, start_date, end_date, amount, type, message, price, image);
			cf.createCoupon(coupon);
		} catch (CouponSystemException e) {
			e.printStackTrace();
		}finally {
			try {
				response.sendRedirect(response.encodeRedirectURL("../company.html"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	
	
	
	@RequestMapping(value="deleteCoupon", method = RequestMethod.GET)
	public void deleteCoupon(@RequestParam("id") long id, HttpServletRequest request, HttpServletResponse response)
	{
		HttpSession session = request.getSession(false);
		CompanyFacade cf=  (CompanyFacade) session.getAttribute("facade");
		try {
			Coupon coupon = cf.readCoupon(id);
			cf.deleteCoupon(coupon);
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
	public void updateCoupon(@RequestParam("id") long id, @RequestParam("end_date") Date end_date, @RequestParam("price") double price , HttpServletRequest request, HttpServletResponse response)
	{
		HttpSession session = request.getSession(false);
		CompanyFacade cf=  (CompanyFacade) session.getAttribute("facade");
		try {
			Coupon coupon = cf.readCoupon(id);
			coupon.setEndDate(end_date);
			coupon.setPrice(price);
			cf.updateCoupon(coupon);
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
	public Coupon readCoupon(@RequestParam("id") long id , HttpServletRequest request, HttpServletResponse response) 
	{
		HttpSession session = request.getSession(false);
		CompanyFacade cf=  (CompanyFacade) session.getAttribute("facade");
		Coupon coupon=new Coupon();
		
			try {
				coupon = cf.readCoupon(id);
			} catch (CouponSystemException e) {
				e.printStackTrace();
			}
		
		return coupon;
	}
	
	
	
	@RequestMapping(value="ReadAllCoupons", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Collection<Coupon> readAllCoupons(HttpServletRequest request, HttpServletResponse response) 
	{
		HttpSession session = request.getSession(false);
		CompanyFacade cf=  (CompanyFacade) session.getAttribute("facade");
		Collection<Coupon> couponsList = new ArrayList<>();
		try {
			couponsList = cf.readAllCoupon();
		} catch (CouponSystemException e) {
			e.printStackTrace();
		}
		return couponsList;
	}
	
	
	@RequestMapping(value="ReadAllCouponsByType", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Collection<Coupon> readAllCouponsByType(@RequestParam("type") JavaBeans.Coupon.CouponType type , HttpServletRequest request, HttpServletResponse response) 
	{
		HttpSession session = request.getSession(false);
		CompanyFacade cf=  (CompanyFacade) session.getAttribute("facade");
		Collection<Coupon> couponsList = new ArrayList<>();
		try {
			couponsList = cf.readAllCouponByType(type);
		} catch (CouponSystemException e) {
			e.printStackTrace();
		}
		return couponsList;
	}
	
	
	@RequestMapping(value="ReadAllCouponsByUntilPrice", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Collection<Coupon> readAllCouponsByUntilPrice(@RequestParam("price") double price , HttpServletRequest request, HttpServletResponse response) 
	{
		HttpSession session = request.getSession(false);
		CompanyFacade cf=  (CompanyFacade) session.getAttribute("facade");
		Collection<Coupon> couponsList = new ArrayList<>();
		try {
			couponsList = cf.readAllCouponByUntilPrice(price);
		} catch (CouponSystemException e) {
			e.printStackTrace();
		}
		return couponsList;
	}
	
	
	@RequestMapping(value="ReadAllCouponsByUntilDate", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Collection<Coupon> readAllCouponsByUntilDate(@RequestParam("end_date") String end_date, HttpServletRequest request, HttpServletResponse response) 
	{
		HttpSession session = request.getSession(false);
		CompanyFacade cf=  (CompanyFacade) session.getAttribute("facade");
		Collection<Coupon> couponsList = new ArrayList<>();
		try {
			couponsList = cf.readAllCouponByUntilDate(end_date);
		} catch (CouponSystemException e) {
			e.printStackTrace();
		}
		return couponsList;
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
