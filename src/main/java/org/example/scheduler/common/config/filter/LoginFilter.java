package org.example.scheduler.common.config.filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import org.example.scheduler.common.exception.LoginException;
import org.springframework.util.PatternMatchUtils;


public class LoginFilter implements Filter {
    private static final String[] WHITE_LIST = {"/", "/user/login","/user/signup","/user/logout"};


    @Override
    public void doFilter(
            ServletRequest request,
            ServletResponse response,
            FilterChain chain
    ) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String requestURI = httpRequest.getRequestURI();

        if (!isWhiteList(requestURI)) {
            HttpSession session = httpRequest.getSession(false);
            if (session == null || session.getAttribute("userId") == null) {
                exceptionHandler(response);
                return;
            }
        }
        chain.doFilter(request,response);
    }

    private boolean isWhiteList(String requestURI) {
        return PatternMatchUtils.simpleMatch(WHITE_LIST,requestURI);
    }

    /*
    왜 filter 부분에서 작성한 exception이 global exception에 걸리지 않을까요?
    filter의 순서 다시한번 상기하기
    Filter -> DispatcherServlet -> Controller -> Response
    @ControllerAdvice가 동작하기 전에 filter 부분에서 처리되고 끝나기 때문입니다.
     * */
    private void exceptionHandler(ServletResponse response) throws IOException {
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        httpServletResponse.setContentType("application/json");
        httpServletResponse.setCharacterEncoding("UTF-8");

        String jsonResponse = String.format(
            "{\"error\": \"%s\", \"message\": \"%s\"}",
            "NOT_AUTHORIZED",
            "로그인이 필요한 기능입니다."
        );
        httpServletResponse.getWriter().write(jsonResponse);
    }
}
