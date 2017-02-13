package com.kam.qs.security;

import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jackson.node.ObjectNode;
import org.hibernate.annotations.common.util.StringHelper;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

import com.bstek.dorado.common.proxy.PatternMethodInterceptor;
import com.bstek.dorado.console.authentication.AuthenticationManager;
import com.bstek.dorado.console.security.AccessDeniedException;
import com.bstek.dorado.data.JsonUtils;
import com.bstek.dorado.web.DoradoContext;
import com.kam.qs.dao.common.DoradoServiceDao;
import com.kam.qs.entity.common.DoradoService;
import com.kam.qs.entity.common.Permission;
import com.kam.qs.entity.common.Permission.Role;
import com.kam.qs.entity.common.User;
import com.kam.qs.security.interceptor.InnerInterceptor;
import com.kam.qs.util.Constants;

public class ViewServiceSecurityInterceptor extends PatternMethodInterceptor {

	private static final Log logger = LogFactory.getLog(ViewServiceSecurityInterceptor.class);
	
	private String serviceNamePattern;
	private static PathMatcher matcher = new AntPathMatcher();
	private AuthenticationManager authenticationManager;
	
	public String getServiceNamePattern() {
		return serviceNamePattern;
	}

	public void setServiceNamePattern(String serviceNamePattern) {
		this.serviceNamePattern = serviceNamePattern;
	}
	
	public AuthenticationManager getAuthenticationManager() {
		return authenticationManager;
	}

	public void setAuthenticationManager(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}

	public Object invoke(MethodInvocation invocation) throws Throwable {
		String action, serviceName = null, currentPage = null;

		for (Object arg : invocation.getArguments()) {
			if (arg instanceof ObjectNode) {
				ObjectNode objectNode = (ObjectNode) arg;
				action = JsonUtils.getString(objectNode, "action");
				if (objectNode.get("context") != null && objectNode.get("context").get("PAGE_NAME") != null)
					currentPage = objectNode.get("context").get("PAGE_NAME").getTextValue();
				if (action.equals("remote-service")) 
					serviceName = JsonUtils.getString(objectNode, "service");
				if (action.equals("resolve-data")) 
					serviceName = JsonUtils.getString(objectNode,"dataResolver");
				if (action.equals("load-data")) 
					serviceName = JsonUtils.getString(objectNode, "dataProvider");
				logger.info("====> " + action + ": " + serviceName);
				if (serviceName != null && match(serviceNamePattern, serviceName)) {
					Boolean loginStatus = authenticationManager.isAuthenticated(
							DoradoContext.getAttachedRequest());
					if (!loginStatus) 
						throw new AccessDeniedException("您必须登录才能使用服务！");
					else {
						DoradoContext.getCurrent().setAttribute(
								DoradoContext.REQUEST, Constants.CURRENT_PAGE, currentPage);
						DoradoServiceDao serviceDao = (DoradoServiceDao)
								DoradoContext.getCurrent().getServiceBean("&doradoServiceDao");
						DoradoService doradoService = serviceDao.getByDoradoServiceName(serviceName);
						if (doradoService != null) {
							boolean pass = validate(doradoService, invocation, objectNode);
							if (!pass)
								throw new AccessDeniedException("您没有授权使用该服务！");
						}
					}
				}
				break;
			}
		}

		return invocation.proceed();
	}

	private boolean validate(DoradoService doradoService, MethodInvocation invocation, 
			ObjectNode objectNode) throws Exception {
		boolean pass = false;
		
		if (StringHelper.isNotEmpty(doradoService.getInterceptorServiceName())) {
			// 调用服务拦截器对服务的执行权限进行判断处理
			InnerInterceptor interceptor = (InnerInterceptor)
					DoradoContext.getCurrent().getServiceBean(Constants.PREFIX_SECURITY_INTERCEPTOR_SERVICE
							+ doradoService.getInterceptorServiceName());
			pass = interceptor.intercept(doradoService, invocation, objectNode);
		} else {
			// 使用共用方法对服务的执行权限进行判断处理
			User currentUser= (User) DoradoContext.getCurrent().getAttribute(
					DoradoContext.SESSION, Constants.CURRENT_USER);
			for (Role role : doradoService.getRoles()) {
				for (Permission permission : currentUser.getPermissions())
					if (permission.getRole().equals(role)) {
						pass = true;
						break;
					}
			}
		}
		
		return pass;
	}
	
	public static boolean match(String pattern, String text) {
		return (StringUtils.isNotEmpty(text)) ? matcher.match(pattern, text)
				: false;
	}

	public boolean check(String action, String serviceName) {
		return true;
	}

}
