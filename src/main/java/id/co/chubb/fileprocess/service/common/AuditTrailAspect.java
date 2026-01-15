package id.co.chubb.fileprocess.service.common;

import id.co.chubb.fileprocess.service.auth.AuditTrailService;
import id.co.chubb.fileprocess.service.auth.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Component
public class AuditTrailAspect {

    @Autowired
    private AuditTrailService auditTrailService;
    @Autowired
    private AuthService authService;

    //    @Around("within(@org.springframework.web.bind.annotation.RestController *)")
    @Around("@annotation(org.springframework.web.bind.annotation.PostMapping) && args(..)")
    public Object logAudit(ProceedingJoinPoint joinPoint) throws Throwable {

        HttpServletRequest request =
                ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

        if (request.getRequestURI().equals("/auth/login")) {
            String ip = getClientIp(request);
            String userAgent = request.getHeader("User-Agent");
            String os = getOs(userAgent);
            String browser = getBrowser(userAgent);
            String endpoint = request.getRequestURI();
            String httpMethod = request.getMethod();

            // Ambil user login (jika pakai Spring Security)
            String username = SecurityContextHolder.getContext().getAuthentication().getName();

            // Simpan audit
//            auditTrailService.saveAudit(
//                    username,
//                    ip,
//                    os,
//                    browser,
//                    endpoint,
//                    httpMethod,
//                    LocalDateTime.now()
//            );
        }
        return joinPoint.proceed();
    }

    private String getClientIp(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null) ip = request.getRemoteAddr();
        return ip.split(",")[0];
    }

    private String getOs(String ua) {
        if (ua == null) return "Unknown";
        if (ua.contains("Windows")) return "Windows";
        if (ua.contains("Mac")) return "MacOS";
        if (ua.contains("Linux")) return "Linux";
        if (ua.contains("Android")) return "Android";
        if (ua.contains("iPhone")) return "iOS";
        return "Unknown";
    }

    private String getBrowser(String ua) {
        if (ua == null) return "Unknown";
        if (ua.contains("Chrome")) return "Chrome";
        if (ua.contains("Firefox")) return "Firefox";
        if (ua.contains("Safari") && !ua.contains("Chrome")) return "Safari";
        if (ua.contains("Edge")) return "Edge";
        if (ua.contains("OPR")) return "Opera";
        return "Unknown";
    }
}
