package id.co.chubb.fileprocess.respository;

import id.co.chubb.fileprocess.model.entity.NpsRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface NpsRoleRepository extends JpaRepository<NpsRole, Long>, QuerydslPredicateExecutor<NpsRole> {
}
