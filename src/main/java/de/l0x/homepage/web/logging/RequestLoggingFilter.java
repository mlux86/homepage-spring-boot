package de.l0x.homepage.web.logging;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class RequestLoggingFilter implements Filter
{

    private final FileBufferedRequestLogger logger;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException
    {
        logger.log((HttpServletRequest) request);
        chain.doFilter(request,response);
    }

}
