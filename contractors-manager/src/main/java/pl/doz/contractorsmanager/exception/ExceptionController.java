package pl.doz.contractorsmanager.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {
    @ExceptionHandler(NonUniqueContractorNameException.class)
    ResponseEntity<ContractorMessage> handleException(NonUniqueContractorNameException ex) {
        return ResponseEntity.status(403).body(new ContractorMessage(ex.getMessage()));
    }

    @ExceptionHandler(ContractorNotFoundException.class)
    ResponseEntity<ContractorMessage> handleException(ContractorNotFoundException ex) {
        return ResponseEntity.status(404).body(new ContractorMessage(ex.getMessage()));
    }

    @ExceptionHandler(ContractorBlankNameException.class)
    ResponseEntity<ContractorMessage> handleException(ContractorBlankNameException ex) {
        return ResponseEntity.status(403).body(new ContractorMessage(ex.getMessage()));
    }
}
