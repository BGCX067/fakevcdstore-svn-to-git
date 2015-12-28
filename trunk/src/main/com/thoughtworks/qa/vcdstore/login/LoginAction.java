package com.thoughtworks.qa.vcdstore.login;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class LoginAction implements Controller {
	private LoginService loginService;
	private String gotoUrl;

	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String userName = request.getParameter("userName");
		this.getUserInfo(request, userName);
		return new ModelAndView(this.getGotoUrl());
	}

	private void getUserInfo(HttpServletRequest request, String userName) {
		String userInfo = loginService.getUserInfo(userName);
		request.setAttribute("userInfo", userInfo);
		System.out.println(userInfo);
	}

	public String getGotoUrl() {
		return gotoUrl;
	}

	public void setGotoUrl(String gotoUrl) {
		this.gotoUrl = gotoUrl;
	}

	public LoginService getLoginService() {
		return loginService;
	}

	public void setLoginService(LoginService loginService) {
		this.loginService = loginService;
	}

}
