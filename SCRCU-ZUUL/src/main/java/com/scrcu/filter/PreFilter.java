package com.scrcu.filter;

import io.jmnarloch.spring.cloud.ribbon.support.RibbonFilterContextHolder;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

/**
 * @author qi.song4@pactera.com
 * 2018年5月7日 
 */
@Component
public  class PreFilter extends ZuulFilter{
	private static final Logger logger = LoggerFactory.getLogger(PreFilter.class);
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
		return FilterConstants.PRE_TYPE;
	}

	@Override
	public Object run() {
		RequestContext ctx = RequestContext.getCurrentContext();
		ctx.set("startTime",System.currentTimeMillis());
		HttpServletRequest request = ctx.getRequest();
	    String grayscale  = request.getParameter("grayscale");
	    logger.info("访问地址 : {} ", request.getRequestURI());
	    if(StringUtils.isEmpty(grayscale)){
	    	 RibbonFilterContextHolder.getCurrentContext().add("gated-launch", "false");
	    }else{
	    	 RibbonFilterContextHolder.getCurrentContext().add("gated-launch", "true");
	    }
		return null;
	}

}
