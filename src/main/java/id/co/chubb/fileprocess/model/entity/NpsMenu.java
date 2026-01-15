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
@Table(name = "NPS_MENU")
public class NpsMenu {
    @Id
    @Column(name = "MENU_ID", nullable = false)
    private Long id;

    @Size(max = 100)
    @Column(name = "NAME", length = 100)
    private String name;

    @Size(max = 200)
    @Column(name = "PATH", length = 200)
    private String path;

    @Size(max = 100)
    @Column(name = "ICON", length = 100)
    private String icon;

    @Column(name = "PARENT_ID")
    private Long parentId;

    @Column(name = "ORDER_NO")
    private Long orderNo;

    @Column(name = "IS_ACTIVE")
    private Boolean isActive;

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
