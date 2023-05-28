package pl.doz.contractorsmanager.contractor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ContractorController {

    private ContractorService contractorService;

    @Autowired
    public ContractorController(ContractorService contractorService) {
        this.contractorService = contractorService;
    }

    @GetMapping("/contractors")
    List<Contractor> getContractors(@RequestParam(defaultValue = "0") int pageNumber, @RequestParam(defaultValue = "10") int pageSize) {
        Pageable page = PageRequest.of(pageNumber, pageSize);
        return contractorService.findAllContractors(page);
    }

    @PostMapping("/contractors")
    Contractor newContractor(@RequestBody Contractor newContractor) {
        return contractorService.newContractor(newContractor);
    }

    @PutMapping("/contractors/{id}")
    Contractor replaceContractor(@RequestBody Contractor newContractor, @PathVariable Long id) {
        return contractorService.replaceContractor(id, newContractor);
    }

    @DeleteMapping("/contractors/{id}")
    void deleteContractor(@PathVariable Long id) {
        contractorService.deleteContractorByID(id);
    }

}
