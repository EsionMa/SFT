package com.wangzhixuan.commons.shiro;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.UserFilter;
import org.apache.shiro.web.util.WebUtils;

import com.wangzhixuan.commons.result.RespResult;
import com.wangzhixuan.commons.utils.StringUtils;

import net.sf.json.JSONObject;

/**
 * ajax shiro session超时统一处理
 * <p>
 * 参考：http://looooj.github.io/blog/2014/06/17/shiro-user-filter.html
 *
 * @author L.cm
 */
public class ShiroAjaxSessionFilter extends UserFilter {
    @Override
    public void doFilterInternal(ServletRequest request, ServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        HttpServletRequest req = WebUtils.toHttp(request);
        HttpServletResponse res = WebUtils.toHttp(response);
        String method = req.getMethod();
        res.setHeader("Access-Control-Allow-Credentials", "true");
        res.setHeader("Access-Control-Allow-Origin", "*");
        res.setHeader("Access-Control-Allow-Methods", "*");
        res.setHeader("Access-Control-Max-Age", "3600");
        res.setHeader("Allow", "GET, HEAD, POST, PUT, DELETE, TRACE, OPTIONS, PATCH");
        res.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept, __token");
        if ("OPTIONS".equals(method)) {
            return;
        }
        super.doFilterInternal(request, response, chain);
    }

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        return super.isAccessAllowed(request, response, mappedValue);
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest req = WebUtils.toHttp(request);
        String token = req.getHeader("__token");
        RespResult<String> ss = new RespResult<>();
        ss.getFail("SF9999", "please login");
        PrintWriter writer = WebUtils.toHttp(response).getWriter();
        response.setCharacterEncoding("utf-8");
        writer.write(JSONObject.fromObject(ss).toString());
        return false;
    }

}