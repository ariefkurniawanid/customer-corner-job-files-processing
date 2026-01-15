package id.co.chubb.fileprocess.respository;

import id.co.chubb.fileprocess.model.entity.NpsAuditTrail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NpsAuditTrailRepository extends JpaRepository<NpsAuditTrail, Long>, QuerydslPredicateExecutor<NpsAuditTrail> {
    public Optional<NpsAuditTrail> findById(Long id);
}
