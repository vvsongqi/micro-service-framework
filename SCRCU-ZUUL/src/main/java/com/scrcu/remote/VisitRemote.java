package com.scrcu.remote;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSONObject;
import com.scrcu.hystrix.VisitRemoteHystrix;

/**
 * @author qi.song4@pactera.com
 * 2018年5月11日 
 */
@FeignClient(name="scrcu-log",fallback=VisitRemoteHystrix.class)
public interface VisitRemote {
	
	@RequestMapping(value = "/visit/saveLog",method = RequestMethod.POST)
	public void saveLog(@RequestBody JSONObject org);
}
