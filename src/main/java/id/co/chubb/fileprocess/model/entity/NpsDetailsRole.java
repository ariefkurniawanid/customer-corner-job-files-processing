package id.co.chubb.fileprocess.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "NPS_DETAILS_ROLES")
public class NpsDetailsRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;

    @Size(max = 100)
    @NotNull
    @Column(name = "DETAILS_ROLES_NAME", nullable = false, length = 100)
    private String detailsRolesName;

    @NotNull
    @Column(name = "ROLES_ID", nullable = false)
    private Long rolesId;

    @NotNull
    @Column(name = "GROUP_ID", nullable = false)
    private Long groupId;

    @NotNull
    @Column(name = "STATUS", nullable = false)
    private Boolean status = false;

    @Size(max = 50)
    @NotNull
    @Column(name = "CREATED_BY", nullable = false, length = 50)
    private String createdBy;

    @NotNull
    @Column(name = "CREATED_DATE", nullable = false)
    private LocalDateTime createdDate;

    @Size(max = 50)
    @NotNull
    @Column(name = "LAST_MODIFIED_BY", nullable = false, length = 50)
    private String lastModifiedBy;

    @NotNull
    @Column(name = "LAST_MODIFIED_DATE", nullable = false)
    private LocalDateTime lastModifiedDate;

}
