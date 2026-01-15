package id.co.chubb.fileprocess.respository;

import id.co.chubb.fileprocess.model.entity.NpsDetailsRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NpsDetailsRoleRepository extends JpaRepository<NpsDetailsRole, Long>, QuerydslPredicateExecutor<NpsDetailsRole> {
    public Optional<NpsDetailsRole> findByDetailsRolesName(String roleName);
}
