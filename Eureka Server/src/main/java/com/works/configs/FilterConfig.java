package com.works.configs;

import org.springframework.cloud.sleuth.Span;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.context.annotation.Configuration;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
public class FilterConfig implements Filter {

    final Tracer tracer;
    public FilterConfig(Tracer tracer) {
        this.tracer = tracer;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        System.out.println("doFilter Call");
        Span span = tracer.currentSpan();
        String traceId = "";
        if (span != null) {
            traceId = span.context().traceId();
        }
        res.setHeader("traceId", traceId);
        chain.doFilter(req, res);
    }

}
