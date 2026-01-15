package id.co.chubb.fileprocess.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "NPS_AUDIT_TRAIL")
public class NpsAuditTrail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;

    @Size(max = 50)
    @NotNull
    @Column(name = "NPP", nullable = false, length = 50)
    private String npp;

    @Size(max = 60)
    @Column(name = "ROLE", length = 60)
    private String role;

    @Size(max = 60)
    @Column(name = "STATUS", length = 60)
    private String status;

    @Column(name = "LOGIN_TIME")
    private LocalDateTime loginTime;

    @Size(max = 20)
    @Column(name = "IP", length = 20)
    private String ip;

    @Size(max = 100)
    @Column(name = "OS_VERSION", length = 100)
    private String osVersion;

    @Size(max = 50)
    @Column(name = "BROWSER_AGENT", length = 50)
    private String browserAgent;

    @Column(name = "CREATED_DATE")
    private LocalDateTime createdDate;

    @Column(name = "UPDATED_DATE")
    private LocalDateTime updatedDate;

    @Size(max = 50)
    @Column(name = "LOGIN_TYPE", length = 50)
    private String loginType;

    @Size(max = 10)
    @Column(name = "BRANCH_CODE", length = 10)
    private String branchCode;

}
