package id.co.chubb.fileprocess.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "NPS_GROUP")
public class NpsGroup {
    @Id
    @Column(name = "GROUP_ID", nullable = false)
    private Long id;

    @Size(max = 100)
    @NotNull
    @Column(name = "GROUP_NAME", nullable = false, length = 100)
    private String groupName;

    @Size(max = 255)
    @Column(name = "GROUP_DESCRIPTION")
    private String groupDescription;

    @Size(max = 50)
    @NotNull
    @Column(name = "GROUP_SHORTNAME", nullable = false, length = 50)
    private String groupShortname;

    @NotNull
    @Column(name = "GROUP_STATUS", nullable = false)
    private Boolean groupStatus = false;

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
