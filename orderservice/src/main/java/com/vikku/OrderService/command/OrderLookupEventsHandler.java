package com.vikku.OrderService.command;

import com.vikku.OrderService.core.data.OrderLookupEntity;
import com.vikku.OrderService.core.data.OrderLookupRepository;
import com.vikku.OrderService.core.events.OrderCreatedEvent;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
@ProcessingGroup("order-group")  // Grouping two event handler class in to single group
public class OrderLookupEventsHandler {

    private final OrderLookupRepository orderLookupRepository;

    OrderLookupEventsHandler(OrderLookupRepository orderLookupRepository) {
        this.orderLookupRepository = orderLookupRepository;
    }

    @EventHandler
    public void on(OrderCreatedEvent event) {

        OrderLookupEntity orderLookupEntity = new OrderLookupEntity();
        BeanUtils.copyProperties(event, orderLookupEntity);

//        we can also check here if the data exists in database or not

        orderLookupRepository.save(orderLookupEntity);
    }
}
