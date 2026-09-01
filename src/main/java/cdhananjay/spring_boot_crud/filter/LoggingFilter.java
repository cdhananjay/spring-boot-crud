package cdhananjay.spring_boot_crud.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.UUID;

@Profile("dev")
@Component
public class LoggingFilter extends OncePerRequestFilter {

    private final ServletRequest servletRequest;
    private final ServletResponse servletResponse;

    public LoggingFilter(HttpServletResponse httpServletResponse, ServletRequest servletRequest, ServletResponse servletResponse) {
        this.servletRequest = servletRequest;
        this.servletResponse = servletResponse;
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        String uri = request.getRequestURI();

        return !uri.startsWith("/api/");
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {
        long startTime = System.currentTimeMillis();

        String uuid = UUID.randomUUID().toString();

        response.setHeader("X-Request-ID", uuid);

        System.out.println("Incoming Request: "
                + request.getMethod() + " "
                + request.getRequestURI());
        System.out.println("Request Id: " + uuid);

        try {
            filterChain.doFilter(request, response);
        } finally {
            long duration = System.currentTimeMillis() - startTime;

            System.out.println("Response status: " + response.getStatus());
            System.out.println("API Response time : " + duration + " ms");
        }
    }
}
