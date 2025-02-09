package com.vikku.OrderService.query;

import com.vikku.OrderService.core.data.OrderEntity;
import com.vikku.OrderService.core.data.OrdersRepository;
import com.vikku.OrderService.core.events.OrderCreatedEvent;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
@ProcessingGroup("order-group")  // Grouping two event-handler class in to single group
public class OrderEventsHandler {

    private final OrdersRepository ordersRepository;

    OrderEventsHandler(OrdersRepository ordersRepository) {
        this.ordersRepository = ordersRepository;
    }

    @EventHandler
    public void on(OrderCreatedEvent event) {

        OrderEntity orderEntity = new OrderEntity();
        BeanUtils.copyProperties(event, orderEntity);

        ordersRepository.save(orderEntity);
    }
}
