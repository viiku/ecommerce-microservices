package com.vikku.orderservice;

import com.vikku.orderservice.command.CreateOrderCommandInterceptor;
import com.vikku.orderservice.core.errorhandling.OrdersServiceEventsHandler;
import org.axonframework.commandhandling.CommandBus;
import org.axonframework.config.EventProcessingConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class OrderServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderServiceApplication.class, args);
	}

	@Autowired
	public void registerCreateOrderCommandInterceptor(ApplicationContext context, CommandBus commandBus) {
		commandBus.registerDispatchInterceptor(context.getBean(CreateOrderCommandInterceptor.class));
	}

	@Autowired
	public void configure(EventProcessingConfigurer configurer) {
		configurer.registerListenerInvocationErrorHandler("order-group",
				conf -> new OrdersServiceEventsHandler());

//		Axon framework provides PropagatingErrorHandler class for
//		propagating

//		configurer.registerListenerInvocationErrorHandler("order-group",
//				conf -> PropagatingErrorHandler.instance());
	}
}
