package id.co.chubb.fileprocess.model.dto.fileprocess;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FileDetailDto {
    private String fileName;
    private String filePath;
    private String fileType;
    private Long fileSize;
    private String fileDate;
    private String contentBase64;
    private String contentType;
    private String pathDirectory;
    private String storageType;
    private String documentType;
    private String nasabahId;
    private String policyId;
    private Boolean endcyptedFlag;
    private String createdBy;
    private LocalDateTime registerDate;
    private LocalDateTime lastUpdateDate;
    private LocalDateTime retentionUntilDate;

}
