package com.vikku.OrderService;

import com.vikku.OrderService.command.CreateOrderCommandInterceptor;
import org.axonframework.commandhandling.CommandBus;
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
}
