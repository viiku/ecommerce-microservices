package com.vikku.OrderService.query;

import com.vikku.OrderService.core.data.OrderEntity;
import com.vikku.OrderService.core.data.OrdersRepository;
import com.vikku.OrderService.core.events.OrderCreatedEvent;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.messaging.interceptors.ExceptionHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;


@Component
@ProcessingGroup("order-group")  // Grouping two event-handler class in to single group
public class OrderEventsHandler {

    private final OrdersRepository ordersRepository;

    OrderEventsHandler(OrdersRepository ordersRepository) {
        this.ordersRepository = ordersRepository;
    }

    @ExceptionHandler(resultType=Exception.class)
    public void handle(Exception exception) throws Exception {
//        Log error message
//        Log error message about rolling back transaction
        throw exception;
    }

//    Handling IllegalArgumentException
    @ExceptionHandler(resultType=IllegalArgumentException.class)
    public void handle(IllegalArgumentException exception) {
//        Log error message
//        Log error message about rolling back transaction
    }

    @EventHandler
    public void on(OrderCreatedEvent orderCreatedEvent) throws Exception {

        OrderEntity orderEntity = new OrderEntity();
        BeanUtils.copyProperties(orderCreatedEvent, orderEntity);

//        this save method can cause some exception
//        we can use try and catch and handle exception
//        if there are multiple methods then we can apply more try and catch method
        try {
            ordersRepository.save(orderEntity);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }

        if(true) throw new Exception("Forcing exception in Event Handler Class");
    }
}
