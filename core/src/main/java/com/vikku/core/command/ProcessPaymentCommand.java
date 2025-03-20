package com.vikku.core.command;

import com.vikku.core.model.PaymentDetails;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
public class ProcessPaymentCommand {

    @TargetAggregateIdentifier
    private final String paymentId;
    private final String orderId;
    private final PaymentDetails paymentDetails;

    public ProcessPaymentCommand(String paymentId, String orderId, PaymentDetails paymentDetails) {
        this.paymentId = paymentId;
        this.orderId = orderId;
        this.paymentDetails = paymentDetails;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public String getOrderId() {
        return orderId;
    }

    public PaymentDetails getPaymentDetails() {
        return paymentDetails;
    }
}
