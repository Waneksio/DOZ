package pl.doz.contractorsmanager.exception;

public class ContractorNotFoundException extends RuntimeException {
    public ContractorNotFoundException(String message) {
        super(message);
    }
}
