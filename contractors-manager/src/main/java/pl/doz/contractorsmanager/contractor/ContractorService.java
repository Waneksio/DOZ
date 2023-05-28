package pl.doz.contractorsmanager.contractor;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.doz.contractorsmanager.exception.ContractorBlankNameException;
import pl.doz.contractorsmanager.exception.ContractorNotFoundException;
import pl.doz.contractorsmanager.exception.NonUniqueContractorNameException;

import java.util.List;

@Service
public class ContractorService {

    ContractorRepository contractorRepository;

    public ContractorService(ContractorRepository contractorRepository) {
        this.contractorRepository = contractorRepository;
    }

    public List<Contractor> findAllContractors() {
        return contractorRepository.findAll();
    }

    public Contractor newContractor(Contractor newContractor) {
        validateContractorName(newContractor.getName());
        return contractorRepository.save(newContractor);
    }

    public Contractor replaceContractor(Long id, Contractor newContractor) {
        validateContractorName(newContractor.getName());
        return contractorRepository.findById(id)
                .map(contractor -> {
                    contractor.setName(newContractor.getName());
                    return contractorRepository.save(contractor);
                })
                .orElseGet(() -> {
                    return contractorRepository.save(newContractor);
                });
    }

    private void validateContractorName(String contractorName) {
        if (contractorName == null) {
            throw new ContractorBlankNameException("Contractor's name cannot be null");
        }
        if (contractorRepository.existsByName(contractorName)) {
            throw new NonUniqueContractorNameException("Contractor with this name already exists: " + contractorName);
        }
    }

    public void deleteContractorByID(Long id) {
        Contractor contractorToDelete = findContractorByID(id);
        contractorRepository.delete(contractorToDelete);
    }

    public Contractor findContractorByID(Long id) {
        return contractorRepository.findById(id).orElseThrow(() -> {
            return new ContractorNotFoundException("Contractor with this id does not exists: " + id);
        });
    }

    public List<Contractor> findAllContractors(Pageable page) {
        return contractorRepository.findAll(page).stream().toList();
    }
}
