	package com.example.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@Component
    public class ReqAndResHandler extends HandlerInterceptorAdapter {
	private static Logger log = LoggerFactory.getLogger(ReqAndResHandler.class);

        @Override
        public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
                throws Exception {
log.info("==============Staring preHandle Method=================");
            
log.info("==================Ending preHandle Method================");
            return super.preHandle(request, response, handler);
        }

  @Override
public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
		ModelAndView modelAndView) throws Exception {
	// TODO Auto-generated method stub
	super.postHandle(request, response, handler, modelAndView);
}
  
  @Override
public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
		throws Exception {
	// TODO Auto-generated method stub
	super.afterCompletion(request, response, handler, ex);
}
}
