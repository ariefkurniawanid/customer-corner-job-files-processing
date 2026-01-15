package id.co.chubb.fileprocess.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "NPS_USER_ROLES")
public class NpsUserRole {
    @Id
    @NotNull
    @Column(name = "USER_ID", nullable = false)
    private Long userId;

    @NotNull
    @Column(name = "DETAILS_ROLES_ID", nullable = false)
    private Long detailsRolesId;

}
