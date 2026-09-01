package cdhananjay.spring_boot_crud.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.UUID;

@Profile("dev")
@Component
public class LoggingFilter implements Filter {

    private final ServletRequest servletRequest;
    private final ServletResponse servletResponse;

    public LoggingFilter(HttpServletResponse httpServletResponse, ServletRequest servletRequest, ServletResponse servletResponse) {
        this.servletRequest = servletRequest;
        this.servletResponse = servletResponse;
    }

    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain chain)
            throws IOException, ServletException {

        long startTime = System.currentTimeMillis();

        String uuid = UUID.randomUUID().toString();

        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        httpServletResponse.setHeader("X-Request-ID", uuid);

        System.out.println("Incoming Request: "
                            + httpServletRequest.getMethod() + " "
                            + httpServletRequest.getRequestURI());
        System.out.println("Request Id: " + uuid);

        try {
            chain.doFilter(request, response);
        } finally {
            long duration = System.currentTimeMillis() - startTime;

            System.out.println("Response status: " + httpServletResponse.getStatus());
            System.out.println("API Response time : " + duration + " ms");
        }
    }
}
