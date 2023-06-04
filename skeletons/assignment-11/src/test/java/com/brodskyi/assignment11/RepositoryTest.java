package com.brodskyi.assignment11;

import com.brodskyi.assignment11.api.entity.Customer;
import com.brodskyi.assignment11.api.entity.Order;
import com.brodskyi.assignment11.api.entity.OrderItem;
import com.brodskyi.assignment11.api.entity.Product;
import com.brodskyi.assignment11.implementation.repository.CustomerRepository;
import com.brodskyi.assignment11.implementation.repository.OrderItemRepository;
import com.brodskyi.assignment11.implementation.repository.OrderRepository;
import com.brodskyi.assignment11.implementation.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.math.BigDecimal;

@SpringBootTest
public class RepositoryTest {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private ProductRepository productRepository;

    @Test
    @Transactional
    @Rollback
    public void testMakeOrderForNewCustomer() {
        Customer customer = new Customer();
        customer.setName("John Doe");

        Product product = new Product();
        product.setName("Product 1");
        product.setPrice(BigDecimal.valueOf(10));

        customerRepository.addOrUpdate(customer);
        productRepository.addOrUpdate(product);

        OrderItem orderItem = new OrderItem();
        orderItem.setProduct(product);
        orderItem.setCount(2);

        Order order = new Order();
        order.setCustomer(customer);
        order.addItem(orderItem);

        orderRepository.addOrUpdate(order);

        Order savedOrder = orderRepository.findById(order.getId());
        Assertions.assertNotNull(savedOrder);
        Assertions.assertEquals(customer, savedOrder.getCustomer());
        Assertions.assertEquals(1, savedOrder.getItems().size());
        Assertions.assertEquals(orderItem, savedOrder.getItems().get(0));

        Customer savedCustomer = customerRepository.findById(customer.getId());
        Assertions.assertNotNull(savedCustomer);
    }

    @Test
    @Transactional
    @Rollback
    public void testIsVIPCustomer() {
        Customer customer = new Customer();
        customer.setName("John Doe");

        Product product = new Product();
        product.setName("Product 1");
        product.setPrice(BigDecimal.valueOf(50));

        customerRepository.addOrUpdate(customer);
        productRepository.addOrUpdate(product);

        OrderItem orderItem = new OrderItem();
        orderItem.setProduct(product);
        orderItem.setCount(2);

        Order order = new Order();
        order.setCustomer(customer);
        order.addItem(orderItem);

        orderRepository.addOrUpdate(order);
        customerRepository.addOrUpdate(customer);

        Order savedOrder = orderRepository.findById(order.getId());
        Assertions.assertNotNull(savedOrder);
        Assertions.assertEquals(customer, savedOrder.getCustomer());
        Assertions.assertEquals(1, savedOrder.getItems().size());
        Assertions.assertEquals(orderItem, savedOrder.getItems().get(0));

        Customer savedCustomer = customerRepository.findById(customer.getId());
        Assertions.assertNotNull(savedCustomer);
        Assertions.assertFalse(customer.isVIPCustomer());
        Assertions.assertEquals(BigDecimal.ZERO, savedCustomer.getDiscountRate());
    }
}
