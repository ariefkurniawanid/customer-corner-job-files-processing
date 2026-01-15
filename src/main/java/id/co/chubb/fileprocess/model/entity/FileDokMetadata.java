package id.co.chubb.fileprocess.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "FILE_DOK_METADATA")
public class FileDokMetadata {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;

    @Size(max = 200)
    @Column(name = "FILE_NAME", length = 200)
    private String fileName;

    @Size(max = 50)
    @Column(name = "FILE_CATEGORY", length = 50)
    private String fileCategory;

    @Size(max = 50)
    @Column(name = "DOCUMENT_CODE", length = 50)
    private String documentCode;

    @Lob
    @Column(name = "CONTENT_BASE64")
    private String contentBase64;

    @Column(name = "FILE_SIZE")
    private Long fileSize;

    @Column(name = "FILE_DATE")
    private LocalDateTime fileDate;

    @Size(max = 500)
    @Column(name = "PATH_DIRECTORY", length = 500)
    private String pathDirectory;

    @Size(max = 20)
    @Column(name = "STORAGE_TYPE", length = 20)
    private String storageType;

    @Size(max = 50)
    @Column(name = "DOCUMENT_TYPE", length = 50)
    private String documentType;

    @Size(max = 50)
    @Column(name = "NASABAH_ID", length = 50)
    private String nasabahId;

    @Size(max = 50)
    @Column(name = "POLICY_ID", length = 50)
    private String policyId;

    @Column(name = "ENCRYPTED_FLAG")
    private Boolean encryptedFlag;

    @Size(max = 20)
    @Column(name = "ACCESS_LEVEL", length = 20)
    private String accessLevel;

    @Size(max = 50)
    @Column(name = "CREATED_BY", length = 50)
    private String createdBy;

    @Column(name = "REGISTER_DATE")
    private LocalDateTime registerDate;

}