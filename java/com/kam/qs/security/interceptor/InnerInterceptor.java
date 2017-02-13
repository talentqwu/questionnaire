package com.kam.qs.security.interceptor;

import org.aopalliance.intercept.MethodInvocation;
import org.codehaus.jackson.node.ObjectNode;

import com.kam.qs.entity.common.DoradoService;

public interface InnerInterceptor {

	/**
	 * 拦截服务方法（请参考ViewServiceSecurityInterceptor）。
	 * @param doradoService 服务定义
	 * @param invocation 调用的方法
	 * @param objectNode 对象节点
	 * @return
	 */
	public boolean intercept(DoradoService doradoService, MethodInvocation invocation, ObjectNode objectNode) throws Exception;
}
