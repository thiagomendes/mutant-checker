package br.com.mutant.checker.repository;

import br.com.mutant.checker.domain.entity.CheckResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CheckResultRepository extends JpaRepository<CheckResult, Long> {
}
