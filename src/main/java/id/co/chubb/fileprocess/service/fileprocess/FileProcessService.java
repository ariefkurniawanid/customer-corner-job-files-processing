package id.co.chubb.fileprocess.service.fileprocess;

import id.co.chubb.fileprocess.model.dto.fileprocess.FileDetailDto;
import id.co.chubb.fileprocess.model.entity.FileDokMetadata;
import id.co.chubb.fileprocess.respository.FileDokMetadataRepository;
import id.co.chubb.fileprocess.util.Base64Utils;
import id.co.chubb.fileprocess.util.ConstantUtils;
import id.co.chubb.fileprocess.util.MessageUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@Service
public class FileProcessService {

    @Autowired
    private FileDokMetadataRepository fileDokMetadataRepository;

    @Value("${app.files.batch.limit}")
    private Long limit;

    @Value("${app.files.parent.dir}")
    private Path parentDir;

    @Value("${app.files.pattern}")
    private String pattern;

    public void processFileFund() throws IOException {
        MessageUtils.setLogMessage("Processing File Fund", log);
        try (Stream<Path> stream = Files.walk(parentDir)) {
            List<FileDokMetadata> fileDetailDtos = new ArrayList<>();
            List<Path> files = stream
                    .filter(Files::isRegularFile)
                    .filter(path -> path.getFileName().toString().toLowerCase().endsWith(pattern))
                    .limit(limit)
                    .toList();
            if (files.size() > 0) {
                Set<String> fileNamesFromDir = files.stream()
                        .map(path -> path.getFileName().toString())
                        .collect(Collectors.toSet());
                List<FileDokMetadata> fileNamesFromDb = gettingFileFromDb(new ArrayList<>(fileNamesFromDir));
                Map<String, FileDokMetadata> mappingEntity = new HashMap<>();
                if (fileNamesFromDb != null) {
                    mappingEntity = fileNamesFromDb
                            .stream()
                            .collect(Collectors.toMap(FileDokMetadata::getFileName, fileDokMetadata -> fileDokMetadata));
                }
                for (Path path : files) {
                    try {
                        String fileName = path.getFileName().toString();
                        if (mappingEntity.containsKey(fileName)) {
                            FileDokMetadata existingFile = mappingEntity.get(fileName);
                            String contentBase64 = Base64Utils.convertPdf(path);
                            existingFile.setContentBase64(contentBase64);
                            existingFile.setRegisterDate(LocalDateTime.now());
                            existingFile.setFileSize(path.toFile().length());
                            existingFile.setFileDate(Files.getLastModifiedTime(path).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
                            existingFile.setPathDirectory(path.getParent().toString());
                            fileDetailDtos.add(existingFile);
                        } else {
                            FileDokMetadata newFile = new FileDokMetadata();
                            String contentBase64 = Base64Utils.convertPdf(path);
                            newFile.setContentBase64(contentBase64);
                            newFile.setFileName(path.getFileName().toString());
                            newFile.setFileCategory(ConstantUtils.FUND);
                            newFile.setFileSize(path.toFile().length());
                            newFile.setFileDate(Files.getLastModifiedTime(path).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
                            newFile.setPathDirectory(path.getParent().toString());
                            newFile.setStorageType("FileSystem");
                            newFile.setDocumentType(StringUtils.getFilenameExtension(path.getFileName().toString()));
                            newFile.setEncryptedFlag(Boolean.TRUE);
                            newFile.setCreatedBy("System");
                            newFile.setRegisterDate(LocalDateTime.now());
                            fileDetailDtos.add(newFile);
                        }
                    } catch (Exception e) {
                        MessageUtils.setErrorLogMessage("Skip Files " + path.getFileName() + "With Error " + e.getMessage(), log);
                    }
                }
                fileDokMetadataRepository.saveAll(fileDetailDtos);
            } else {
                MessageUtils.setLogMessage("No files found", log);
            }
        }
    }

    @Transactional
    protected List<FileDokMetadata> gettingFileFromDb(List<String> listFilename) {
        List<FileDokMetadata> fileNamesFromDb = fileDokMetadataRepository.findByFileNameIn(listFilename);
        if (fileNamesFromDb.size() > 0) {
            return fileNamesFromDb;
        } else {
            return null;
        }
    }
}