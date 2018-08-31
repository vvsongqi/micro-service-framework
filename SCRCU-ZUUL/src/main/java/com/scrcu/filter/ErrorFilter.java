package com.scrcu.filter;

import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;

/**
 * @author qi.song4@pactera.com
 * 2018年5月16日 
 */
@Component
public class ErrorFilter extends ZuulFilter {

	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public int filterOrder() {
		// TODO Auto-generated method stub
		return 10;
	}

	@Override
	public String filterType() {
		// TODO Auto-generated method stub
		return FilterConstants.ERROR_TYPE;
	}
	@Override
	public Object run() {
//		RequestContext ctx = RequestContext.getCurrentContext();
		return null;
	}


}
