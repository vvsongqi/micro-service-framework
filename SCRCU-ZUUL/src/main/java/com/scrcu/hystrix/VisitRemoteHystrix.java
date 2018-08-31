package com.scrcu.hystrix;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;

import com.alibaba.fastjson.JSONObject;
import com.scrcu.remote.VisitRemote;

/**
 * @author qi.song4@pactera.com
 * 2018年5月16日 
 */
public  class VisitRemoteHystrix implements VisitRemote{
	private  Logger logger = LoggerFactory.getLogger(VisitRemoteHystrix.class);
	@Override
	public void saveLog(@RequestBody JSONObject org) {
		logger.error("error : 访问日志存储失败.");
	}
	
}
