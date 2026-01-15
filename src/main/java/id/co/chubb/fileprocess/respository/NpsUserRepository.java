package id.co.chubb.fileprocess.respository;

import id.co.chubb.fileprocess.model.entity.NpsUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NpsUserRepository extends JpaRepository<NpsUser, Long>, QuerydslPredicateExecutor<NpsUser> {
    public Optional<NpsUser> findByLogin(String username);
}
