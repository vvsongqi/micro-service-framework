package com.scrcu.filter;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.scrcu.common.HttpUtil;
import com.scrcu.remote.VisitRemote;

/**
 * @author qi.song4@pactera.com
 * 2018年5月8日 
 */
@Component
public class PostFilter extends ZuulFilter{

	@Autowired
	private VisitRemote remote;

	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public int filterOrder() {
		return 0;
	}

	@Override
	public String filterType() {
		return FilterConstants.POST_TYPE;
	}

	@Override
	public Object run() {
		RequestContext context = RequestContext.getCurrentContext();
		saveLog(context);
		return null;
	}

	/**
	 * log save
	 * @param context
	 */
	public void saveLog(final RequestContext context){
		new Thread(){
			public void run() {
				HttpServletRequest reqeust = context.getRequest();
				JSONObject org = new JSONObject();
				org.put("resStatusCode", context.getResponseStatusCode());
				org.put("resTimeLength", (int) (System.currentTimeMillis() - ((Long) context.get("startTime"))));
				org.put("httpUrl", reqeust.getServletPath());
				org.put("httpType", reqeust.getMethod());
				org.put("appKey", reqeust.getParameter("appKey"));
				org.put("clientIp", HttpUtil.getIpAddr(reqeust));
				remote.saveLog(org);
			};

		}.start();
	}
}
