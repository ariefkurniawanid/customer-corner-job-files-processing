package id.co.chubb.fileprocess.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "NPS_USER")
public class NpsUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;

    @Size(max = 50)
    @NotNull
    @Column(name = "LOGIN", nullable = false, length = 50)
    private String login;

    @Size(max = 60)
    @Column(name = "PASSWORD_HASH", length = 60)
    private String passwordHash;

    @Size(max = 50)
    @Column(name = "FIRST_NAME", length = 50)
    private String firstName;

    @Size(max = 50)
    @Column(name = "LAST_NAME", length = 50)
    private String lastName;

    @Column(name = "ACTIVATED")
    private Boolean activated;

    @Size(max = 100)
    @Column(name = "EMAIL", length = 100)
    private String email;

    @Column(name = "RESET_DATE")
    private LocalDateTime resetDate;

    @Size(max = 50)
    @Column(name = "CREATED_BY", length = 50)
    private String createdBy;

    @Column(name = "CREATED_DATE")
    private LocalDateTime createdDate;

    @Size(max = 50)
    @Column(name = "LAST_MODIFIED_BY", length = 50)
    private String lastModifiedBy;

    @Column(name = "LAST_MODIFIED_DATE")
    private LocalDateTime lastModifiedDate;

    @Column(name = "BRANCH_ID")
    private Integer branchId;

    @Column(name = "PASSWORD_EXPIRY_DATE")
    private LocalDateTime passwordExpiryDate;

    @Column(name = "STATUS")
    private Long status;

    @Column(name = "ACTIVE")
    private Boolean active;

    @Column(name = "LOCKED")
    private Boolean locked;

    @Column(name = "LAST_LOGIN")
    private LocalDateTime lastLogin;

}
