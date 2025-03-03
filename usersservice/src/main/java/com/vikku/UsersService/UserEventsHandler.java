package com.vikku.UsersService;

import com.vikku.core.model.PaymentDetails;
import com.vikku.core.model.User;
import com.vikku.core.query.FetchUserPaymentDetailsQuery;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

@Component
public class UserEventsHandler {

    @QueryHandler
    public void handle(FetchUserPaymentDetailsQuery fetchUserPaymentDetailsQuery) {
        PaymentDetails paymentDetails = new PaymentDetails(
                "123Card",
                "123",
                12,
                2030,
                "123");

        User user = new User(
                "VIVEK",
                "SINGH",
                fetchUserPaymentDetailsQuery.getUserId(),
                paymentDetails);
    }
}
