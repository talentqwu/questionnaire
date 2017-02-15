package com.kam.qs;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.bstek.dorado.annotation.DataProvider;
import com.bstek.dorado.annotation.DataResolver;
import com.bstek.dorado.annotation.Expose;
import com.bstek.dorado.web.DoradoContext;
import com.kam.qs.dao.common.CalendarEventDao;
import com.kam.qs.entity.common.CalendarEvent;
import com.kam.qs.entity.common.User;
import com.kam.qs.security.AuthenticationManager;
import com.kam.qs.util.Constants;

@Component(value = "qs.main")
public class Main {

	@Resource
	private CalendarEventDao calendarEventDao;
	
	@Resource
	private AuthenticationManager authenticationManager;
	
	@Expose
	public void logout() {
		DoradoContext.getCurrent().removeAttribute(
				DoradoContext.SESSION, Constants.CURRENT_USER);
	}
	
	@Expose
	public boolean login(String userName, String password) {
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
	
	@DataProvider
	public List<CalendarEvent> getEvents(Map<String, Object> parameter) {
		User user= (User) DoradoContext.getCurrent().getAttribute(
				DoradoContext.SESSION, Constants.CURRENT_USER);
		
		Date startTime = (Date)parameter.get("startTime");
		Date endTime = (Date)parameter.get("endTime");
		return calendarEventDao.getByUserId(user.getId(), startTime, endTime);
	}
 
    @DataResolver
    @Transactional
    public void saveEvent(List<CalendarEvent> events) {
		User user= (User) DoradoContext.getCurrent().getAttribute(
				DoradoContext.SESSION, Constants.CURRENT_USER);
		for (CalendarEvent event : events)
			event.setUser(user);
    	calendarEventDao.persistEntities(events);
    }
}
