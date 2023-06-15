package com.auth;

import com.auth.config.jwt.JwtTokenUtil;
import com.auth.entity.User;
import com.auth.respositories.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.MalformedJwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

public class MyInterceptor implements HandlerInterceptor {


    JwtTokenUtil jwtTokenUtil;

    UserRepository userRepository;


    public MyInterceptor(JwtTokenUtil jwtTokenUtil, UserRepository userRepository) {
        this.jwtTokenUtil = jwtTokenUtil;
        this.userRepository = userRepository;
    }


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {


        Map responseObj = new HashMap();

//		/** checking this url exist or not */
//		if(!adminUrlList.contains(request.getRequestURI())){
//			return false;
//		}


        String path = request.getRequestURI();
        String methodType = request.getMethod();
//		if (methodType.equals("POST")) {
//			String contentType = request.getHeader("content-type");
//			if (contentType == null || !contentType.contains("multipart")) {
//				sendErrorResponse(response, 400, "content-type: multipart missing.", null);
//				return false;
//			}
//		}
        List<String> requiredheaders = new ArrayList<>();

        Enumeration<String> headers = request.getHeaderNames();
        if (headers != null) {
            while (headers.hasMoreElements()) {
                String a = headers.nextElement();
                if (!isEmpty(request.getHeader(a))) {
                    requiredheaders.remove(a);
                }
            }
        }

        if (requiredheaders.size() > 0) {
            responseObj.put("message", "Header missing " + requiredheaders.toString());
            sendErrorResponse(response, 412, responseObj);
//            response.sendRedirect("/");
            return false;
        }


//        String authToken = (String) request.getSession().getAttribute("webSecurityToken");
        String authToken = request.getHeader("webSecurityToken");
        System.out.println(authToken);
        if (isEmpty(authToken)) {
            responseObj.put("message", "Authorization header missing");
            sendErrorResponse(response, 412, responseObj);
//            response.sendRedirect("/");
            return false;
        } else if (!authToken.startsWith("Bearer")) {
            responseObj.put("message", "Authorization token invalid");
            sendErrorResponse(response, 401, responseObj);
//            response.sendRedirect("/");
            return false;
        } else {
            try {
                authToken = authToken.substring(12);
                if (!jwtTokenUtil.validateToken(authToken)) {
                    responseObj.put("message", "Authorization token expired");
                    sendErrorResponse(response, 401, responseObj);
//                    response.sendRedirect("/");
                    return false;
                } else {
                    String slug = jwtTokenUtil.getUserSlugFromToken(authToken);
//                        if (jwtTokenUtil.checkFraudCustomerToken(userEmail, authToken)) {
//                            sendErrorResponse(response, 401, "Authorization token expired1", null);
//                            return false;
//                        }

                    User user = userRepository.findBySlug(slug);
                    if (user == null) {
                        responseObj.put("message", "User not found.");
                        sendErrorResponse(response, 401, responseObj);

//                        response.sendRedirect("/");
                        return false;
                    }

                    /** todo : must uncomment this line */

                /*    if (!user.getStatus()) {
                        sendErrorResponse(response, 412, "Account is deactivated.", null);
                        return false;
                    }
                    if (user.getIsDeleted()) {
                        sendErrorResponse(response, 412, "Account is deleted by admin.", null);
                        return false;
                    }
*/
                    request.setAttribute("authUserSlug", slug);
                    request.setAttribute("loggedUser", user);

                }

            } catch (MalformedJwtException exception) {
                responseObj.put("message", "Authorization token  invalid");
                sendErrorResponse(response, 401, responseObj);
//                response.sendRedirect("/");
                return false;

            }

        }

        return true;

    }

    public void sendErrorResponse(HttpServletResponse response, int status, Map responseObj) throws Exception {
        response.setContentType("application/json");
        if (status == 401) {
            response.setStatus(status, "Invalid Request");
        } else if (status == 412) {
            response.setStatus(status, "UnAuthorzied");
        } else {
            response.setStatus(status);
        }

        if (status != 400)
            response.getWriter().write(new ObjectMapper().writeValueAsString(new ResponseEntity<>(responseObj, HttpStatus.UNAUTHORIZED)));
        else
            response.getWriter().write(new ObjectMapper().writeValueAsString(new ResponseEntity<>(responseObj, HttpStatus.BAD_REQUEST)));
    }

    public ResponseEntity<Object> echoRespose(Object data, HttpStatus ok) {
        return ResponseEntity.status(ok).body(data);
    }

    public boolean isEmpty(String data) {
        return data == null || data.length() == 0;
    }
}
