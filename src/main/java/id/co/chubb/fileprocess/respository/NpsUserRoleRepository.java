package id.co.chubb.fileprocess.respository;

import id.co.chubb.fileprocess.model.entity.NpsUserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NpsUserRoleRepository extends JpaRepository<NpsUserRole, Long>, QuerydslPredicateExecutor<NpsUserRole> {
    public Optional<NpsUserRole> findByUserId(Long userId);
}
