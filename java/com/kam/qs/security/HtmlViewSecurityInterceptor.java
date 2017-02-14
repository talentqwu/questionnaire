package com.kam.qs.security;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.bstek.dorado.console.authentication.AuthenticationManager;
import com.bstek.dorado.util.PathUtils;
import com.bstek.dorado.view.resolver.HtmlViewResolver;
import com.bstek.dorado.web.DoradoContext;
import com.kam.qs.dao.common.TreeNodeDao;
import com.kam.qs.emnu.Role;
import com.kam.qs.entity.common.Permission;
import com.kam.qs.entity.common.TreeNode;
import com.kam.qs.entity.common.User;
import com.kam.qs.util.Constants;

public class HtmlViewSecurityInterceptor extends HandlerInterceptorAdapter {

	private AuthenticationManager authenticationManager;
	
	private String interceptedNamePattern;

	public String getInterceptedNamePattern() {
		return interceptedNamePattern;
	}

	public void setInterceptedNamePattern(String interceptedNamePattern) {
		this.interceptedNamePattern = interceptedNamePattern;
	}

	public AuthenticationManager getAuthenticationManager() {
		return authenticationManager;
	}

	public void setAuthenticationManager(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		String path = request.getRequestURI();
		Boolean loginStatus = authenticationManager.isAuthenticated(request);
		if (handler instanceof HtmlViewResolver
				&& PathUtils.match(interceptedNamePattern, path.replace('/', '.'))) {
			if (!loginStatus &&
					path.indexOf(Constants.LOGIN_VIEW_PATH) < 0) {
				response.sendRedirect(Constants.LOGIN_VIEW_PATH);
				return false;
			} else if (loginStatus) {
				boolean pass = true;
				// 检查用户角色，防止用户直接从URL中进入系统设置与管理功能
				User currentUser = (User)request.getSession().getAttribute(Constants.CURRENT_USER);
				String url = path.substring(path.lastIndexOf('/') + 1);
				if (url.startsWith("view.setting") || url.startsWith("view.manage") || 
						url.startsWith("view.task") || url.startsWith("view.analysis")) {
					TreeNodeDao treeNodeDao = (TreeNodeDao)DoradoContext.getCurrent().getServiceBean("&treeNodeDao");
					List<TreeNode> treeNodes = treeNodeDao.getByUrl(url);
					if (treeNodes != null && treeNodes.size() > 0) {
						pass = false;
						TreeNode treeNode = treeNodes.get(0);
						for (Role role : treeNode.getRoles()) {
							for (Permission permission : currentUser.getPermissions())
								if (permission.getRole().equals(role)) {
									pass = true;
									break;
								}
						}
						// 没有授权使用URL时的处理
						if (!pass) {
							response.sendRedirect(Constants.LOGIN_VIEW_PATH);
							return false;
						}
					}
				}
			}
		}
		
		return super.preHandle(request, response, handler);
	}
}
