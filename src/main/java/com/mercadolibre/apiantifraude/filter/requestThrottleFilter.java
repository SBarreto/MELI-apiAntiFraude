package com.mercadolibre.apiantifraude.filter;
import com.github.benmanes.caffeine.cache.CacheLoader;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.TimeUnit;


/**
 * Permite establecer un rate limiter basico para requests recurrentes desde una ip
 * Configurado para haceptar 5 requests por minuto
 * Codigo fuente tomado y adaptado de https://stackoverflow.com/questions/44042412/how-to-set-rate-limit-for-each-user-in-spring-boot#:~:text=Spring%20does%20not%20have%20rate,on%20IP%20address%20or%20username.
 */
@Component
public class requestThrottleFilter implements Filter {

    private static final int MAX_REQUESTS_PER_MINUTE = 5;

    private LoadingCache<String, Integer> requestCountsPerIpAddress;

    public requestThrottleFilter(){
        super();
        requestCountsPerIpAddress = Caffeine.newBuilder().
                expireAfterWrite(1, TimeUnit.MINUTES).build(new CacheLoader<String, Integer>() {
                    public Integer load(String key) {
                        return 0;
                    }
                });
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        String clientIpAddress = getClientIP((HttpServletRequest) servletRequest);
        if(isMaximumRequestsPerSecondExceeded(clientIpAddress)){
            httpServletResponse.setStatus(HttpStatus.TOO_MANY_REQUESTS.value());
            httpServletResponse.getWriter().write("Too many requests");
            return;
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    private boolean isMaximumRequestsPerSecondExceeded(String clientIpAddress){
        Integer requests = 0;
        requests = requestCountsPerIpAddress.get(clientIpAddress);
        if(requests != null){
            if(requests > MAX_REQUESTS_PER_MINUTE) {
                requestCountsPerIpAddress.asMap().remove(clientIpAddress);
                requestCountsPerIpAddress.put(clientIpAddress, requests);
                return true;
            }

        } else {
            requests = 0;
        }
        requests++;
        requestCountsPerIpAddress.put(clientIpAddress, requests);
        return false;
    }

    public String getClientIP(HttpServletRequest request) {
        String xfHeader = request.getHeader("X-Forwarded-For");
        if (xfHeader == null){
            return request.getRemoteAddr();
        }
        return xfHeader.split(",")[0];
    }

    @Override
    public void destroy() {

    }
}
