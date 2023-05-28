package pl.doz.contractorsmanager.exception;

public class NonUniqueContractorNameException extends RuntimeException {
    public NonUniqueContractorNameException(String message) {
        super(message);
    }
}
