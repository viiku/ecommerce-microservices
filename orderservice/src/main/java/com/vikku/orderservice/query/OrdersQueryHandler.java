package com.vikku.orderservice.query;

import com.vikku.orderservice.core.data.OrderEntity;
import com.vikku.orderservice.core.data.OrdersRepository;
import com.vikku.orderservice.query.rest.OrderRestModel;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrdersQueryHandler {

    private final OrdersRepository ordersRepository;

    OrdersQueryHandler(OrdersRepository ordersRepository) {
        this.ordersRepository = ordersRepository;
    }

    @QueryHandler
    public List<OrderRestModel> findOrders(FindOrdersQuery query) {

        List<OrderRestModel> orderRest = new ArrayList<>();

        List<OrderEntity> storedOrders = ordersRepository.findAll();

        for(OrderEntity orderEntity: storedOrders) {
            OrderRestModel orderRestModel = new OrderRestModel();
            BeanUtils.copyProperties(orderEntity, orderRestModel);
            orderRest.add(orderRestModel);
        }

        return orderRest;
    }
}
