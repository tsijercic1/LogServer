package ba.unsa.etf.si.logserver.filters;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component
@Order(1)
public class HeaderFilter implements Filter {

    @Value("${app.pass}")
    private String pass;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        if (req.getHeader("pass") != null && req.getHeader("pass").equals(pass)) {
            System.out.println("we have a pass");
        } else {
            System.out.println("We don't");
        }
        chain.doFilter(request, response);
    }

}
