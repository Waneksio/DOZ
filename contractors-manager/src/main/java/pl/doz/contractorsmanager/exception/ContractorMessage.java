package pl.doz.contractorsmanager.exception;

import lombok.Data;

@Data
public class ContractorMessage {
    String message;

    public ContractorMessage(String message) {
        this.message = message;
    }
}
