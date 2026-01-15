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
@Table(name = "NPS_MENU_ROLES", schema = "NPSAPPIMP")
public class NpsMenuRole {
    @Id
    @NotNull
    @Column(name = "MENU_ID", nullable = false)
    private Long menuId;

    @NotNull
    @Column(name = "ROLE_ID", nullable = false)
    private Long roleId;

}
