package id.co.chubb.fileprocess.respository;

import id.co.chubb.fileprocess.model.entity.FileDokMetadata;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FileDokMetadataRepository extends JpaRepository<FileDokMetadata, Long>, QuerydslPredicateExecutor<FileDokMetadata> {
    public List<FileDokMetadata> findByFileNameIn(List<String> filename);
}
