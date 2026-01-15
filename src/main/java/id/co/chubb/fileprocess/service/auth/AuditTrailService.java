package id.co.chubb.fileprocess.service.auth;

import id.co.chubb.fileprocess.model.dto.UserDetailDto;
import id.co.chubb.fileprocess.model.entity.NpsAuditTrail;
import id.co.chubb.fileprocess.respository.NpsAuditTrailRepository;
import id.co.chubb.fileprocess.util.MessageUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.time.LocalDateTime;

@Service
public class AuditTrailService {
    public static final Logger logger = LoggerFactory.getLogger(AuditTrailService.class);

    @Autowired
    private NpsAuditTrailRepository npsAuditTrailRepository;

    public void logAudit(UserDetailDto userDetailDto) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

        String ip = getClientIp(request);
        String userAgent = request.getHeader("User-Agent");
        String os = getOs(userAgent);
        String browser = getBrowser(userAgent);
        String endpoint = request.getRequestURI();
        String httpMethod = request.getMethod();
        String username = userDetailDto.getSamAccountName();
        String role = userDetailDto.getNewPrepaidRole();
        String branchCode = userDetailDto.getBranchCode();

        this.saveAudit(
                username,
                role,
                LocalDateTime.now(),
                ip,
                os,
                browser,
                branchCode
        );
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

    @Transactional
    public void saveAudit(String user, String role, LocalDateTime loginTime, String ip, String os, String browser,
                          String branchCode) {
        MessageUtils.setLogMessage("Process Save Audit Trail Login", logger);
        NpsAuditTrail audit = new NpsAuditTrail();
        audit.setNpp(user);
        audit.setRole(role);
        audit.setStatus("Sign In");
        audit.setLoginTime(loginTime);
        audit.setIp(ip);
        audit.setOsVersion(os);
        audit.setBrowserAgent(browser);
        audit.setBranchCode(branchCode);

        npsAuditTrailRepository.save(audit);
    }
}
