package kr.jadekim.oj.mainserver.config;

import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ohyongtaek on 2016. 8. 24..
 */
public class CsrfAddingInterceptor extends HandlerInterceptorAdapter {

    public void postHandle(HttpServletRequest req, HttpServletResponse res, Object handler, ModelAndView mav) {
        CsrfToken token = (CsrfToken) req.getAttribute(CsrfToken.class.getName());
        if (token != null) {
            Map<String,String> map = new HashMap<>();
            map.put("parameterName",token.getParameterName());
            map.put("token",token.getToken());
            mav.addObject("_csrf",map);
        }
    }
}
