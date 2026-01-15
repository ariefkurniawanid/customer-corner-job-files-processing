package id.co.chubb.fileprocess.model.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MenuDto {
    private Long id;
    private String name;
    private String path;
    private String icon;
    private String children;
}
