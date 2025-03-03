package com.vikku.core.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PaymentDetails {

    private final String name;
    private final String cardNumber;
    private final int validUntilMonth;
    private final int validUnitYear;
    private final String cvv;

    public PaymentDetails(String name, String cardNumber, int validUntilMonth, int validUnitYear, String cvv) {
        this.name = name;
        this.cardNumber = cardNumber;
        this.validUntilMonth = validUntilMonth;
        this.validUnitYear = validUnitYear;
        this.cvv = cvv;
    }

    public String getName() {
        return name;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public int getValidUntilMonth() {
        return validUntilMonth;
    }

    public int getValidUnitYear() {
        return validUnitYear;
    }

    public String getCvv() {
        return cvv;
    }
}
