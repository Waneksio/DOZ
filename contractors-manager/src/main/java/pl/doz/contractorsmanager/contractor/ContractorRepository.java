package pl.doz.contractorsmanager.contractor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContractorRepository extends JpaRepository<Contractor, Long> {
    boolean existsByName(String name);

    boolean existsById(Long id);
}
