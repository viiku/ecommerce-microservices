package com.vikku.orderservice.query.rest;

import com.vikku.orderservice.query.FindOrdersQuery;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrdersQueryController {

    @Autowired
    QueryGateway queryGateway;

    @GetMapping
    public List<OrderRestModel> getOrders() {

        FindOrdersQuery findOrdersQuery = new FindOrdersQuery();

        List<OrderRestModel> orders = queryGateway.query(findOrdersQuery,
                ResponseTypes.multipleInstancesOf(OrderRestModel.class)).join();

        return orders;

//        return queryGateway.query(findOrdersQuery,
//                ResponseTypes.multipleInstancesOf(OrderRestModel.class)).join();
    }
}
