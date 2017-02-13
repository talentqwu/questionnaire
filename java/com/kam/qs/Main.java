package com.kam.qs;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.bstek.dorado.annotation.Expose;
import com.bstek.dorado.core.Context;
import com.bstek.dorado.web.DoradoContext;
import com.kam.qs.security.AuthenticationManager;
import com.kam.qs.util.Constants;

@Component(value = "qs.main")
public class Main {

	private AuthenticationManager authenticationManager;
	
	@Expose
	public void logout() {
		DoradoContext.getCurrent().removeAttribute(
				DoradoContext.SESSION, Constants.CURRENT_USER);
	}
	
	@Expose
	public boolean login(String userName, String password) {
		try {
			authenticationManager = (AuthenticationManager)
					Context.getCurrent().getServiceBean("&authenticationManager");
		} catch(Exception e) {
			authenticationManager = new AuthenticationManager();
		}
		
		return authenticationManager.authenticate(userName, password);
	}
	
	@Expose
	public Map<String, Object> getContextAttributes(List<String> keys) throws Exception {
		Map<String, Object> results = new HashMap<String, Object>();
		DoradoContext context = DoradoContext.getCurrent();
		for (String key : keys) 
			results.put(key, context.getAttribute(DoradoContext.SESSION, key));
		
		return results;
	}
	
	@Expose
	public void setContextAttributes(Map<String, Object> parameter) {
		DoradoContext context = DoradoContext.getCurrent();
		for (String key : parameter.keySet()) 
			context.setAttribute(DoradoContext.SESSION, key, parameter.get(key));
	}
}
