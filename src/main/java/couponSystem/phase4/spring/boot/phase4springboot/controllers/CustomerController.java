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
import Facade.CustomerFacade;
import JavaBeans.Coupon;

@RestController
@RequestMapping("/CustomerController")
public class CustomerController {

	
	
	
	@RequestMapping(value="PurchaseCoupon", method = RequestMethod.POST)
	public void purchaseCoupon(@RequestParam("id")long id, HttpServletRequest request, HttpServletResponse response) 
	{
		HttpSession session = request.getSession(false);
		CustomerFacade cuf= (CustomerFacade) session.getAttribute("facade");
		try {
			Coupon coupon= new Coupon();
			coupon.setId(id);
			cuf.purchaseCoupon(coupon);
		} catch (CouponSystemException e) {
			e.printStackTrace();
		}finally {
			try {
				response.sendRedirect(response.encodeRedirectURL("../customer.html"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	@RequestMapping(value="ReadAllPurchasedCoupons", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Collection<Coupon> readAllCoupons(HttpServletRequest request, HttpServletResponse response) 
	{
		HttpSession session = request.getSession(false);
		CustomerFacade cuf= (CustomerFacade) session.getAttribute("facade");
		Collection<Coupon> couponsList = new ArrayList<>();
		try {
			couponsList = cuf.getAllPurchasedCoupon();
		} catch (CouponSystemException e) {
			e.printStackTrace();
		}
		return couponsList;
	}
	
	
	@RequestMapping(value="ReadAllPurchasedCouponsByType", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Collection<Coupon> readAllCouponsByType(@RequestParam("type") JavaBeans.Coupon.CouponType type, HttpServletRequest request, HttpServletResponse response) 
	{
		HttpSession session = request.getSession(false);
		CustomerFacade cuf= (CustomerFacade) session.getAttribute("facade");
		Collection<Coupon> couponsList = new ArrayList<>();
		try {
			couponsList = cuf.getAllPurchasedCouponByType(type);
		} catch (CouponSystemException e) {
			e.printStackTrace();
		}
		return couponsList;
	}
	
	@RequestMapping(value="ReadAllPurchasedCouponsByUntilPrice", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Collection<Coupon> readAllCouponsByUntilPrice(@RequestParam("price") double price , HttpServletRequest request, HttpServletResponse response) 
	{
		HttpSession session = request.getSession(false);
		CustomerFacade cuf= (CustomerFacade) session.getAttribute("facade");
		Collection<Coupon> couponsList = new ArrayList<>();
		try {
			couponsList = cuf.getAllPurchasedCouponByPrice(price);
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
