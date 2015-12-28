package com.thoughtworks.qa.vcdstore.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class WelcomeController implements Controller {

    public  ModelAndView handleRequest(HttpServletRequest req,
                                            HttpServletResponse res) throws Exception {
        
    	Map dataMap = new HashMap();
    	ModelAndView mv= null;
        String username= req.getParameter("userName");
        if(username== null){
            dataMap.put("userName","");
        	mv= new ModelAndView("login_page", dataMap);
        }else{
            dataMap.put("userName",username);
            mv= new ModelAndView("welcome_page", dataMap);

        }
        return mv;
    }
}
