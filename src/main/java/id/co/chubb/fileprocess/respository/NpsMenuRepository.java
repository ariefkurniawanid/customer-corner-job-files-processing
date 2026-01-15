package id.co.chubb.fileprocess.respository;

import id.co.chubb.fileprocess.model.entity.NpsMenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NpsMenuRepository extends JpaRepository<NpsMenu, Long>, QuerydslPredicateExecutor<NpsMenu> {
    public Optional<NpsMenu> findById(Long id);
}
