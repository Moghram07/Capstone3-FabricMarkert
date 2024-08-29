package com.example.capstone_3.Service;

import com.example.capstone_3.Api.ApiException;
import com.example.capstone_3.DTO.OrderHistoryDTO;
import com.example.capstone_3.DTO.ShippingDTO;
import com.example.capstone_3.Model.*;
import com.example.capstone_3.Repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final AddressRepository addressRepository;
    private final OrderRepository orderRepository;
    private final FabricRepository fabricRepository;
    private final MerchantRepository merchantRepository;
    private final TailorRepository tailorRepository;
    private final DesignerRepository designerRepository;
    private final StockRepository stockRepository;
    private final ShippingRepository shippingRepository;
    private final ShippingService shippingService;
    private final OrderService orderService;

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public void addCustomer(Customer customer) {
        customerRepository.save(customer);
    }

    public void updateCustomer(Integer id,Customer customer) {
        Customer c = customerRepository.findCustomerById(id);
        if (c == null) {
            throw new ApiException("Customer not found");
        }
        c.setName(customer.getName());
        c.setAge(customer.getAge());
        c.setEmail(customer.getEmail());
        c.setHeight(customer.getHeight());
        c.setWeight(customer.getWeight());
        c.setPhone(customer.getPhone());
        customerRepository.save(c);
    }

    public void deleteCustomer(Integer id) {
        Customer c = customerRepository.findCustomerById(id);
        if (c == null) {
            throw new ApiException("Customer not found");
        }
        customerRepository.delete(c);
    }

    public List<Order> getOrders(Integer customerId){
        Customer customer = customerRepository.findCustomerById(customerId);
        return orderRepository.findOrderByCustom(customer);
    }

    public void makeOrder(Integer customerId, Integer fabricId , Integer MerchantId , Integer tailorId , Integer designerId , double meter) {
        Customer customer = customerRepository.findCustomerById(customerId);
        if (customer == null) {
            throw new ApiException("Customer not found");
        }
        Fabric fabric = fabricRepository.findFabricById(fabricId);
        if (fabric == null) {
            throw new ApiException("Fabric not found");
        }
        Merchant merchant = merchantRepository.findMerchantById(MerchantId);
        if (merchant == null) {
            throw new ApiException("Merchant not found");
        }
        Tailor tailor = tailorRepository.findTailorById(tailorId);
        if (tailor == null) {
            throw new ApiException("Tailor not found");
        }
        Designer designer = designerRepository.findDesignerById(designerId);
        if (designer == null) {
            throw new ApiException("Designer not found");
        }
        Stock stock = stockRepository.findStockByFabricIdAndMerchantId(fabricId, MerchantId);
        if (stock == null) {
            throw new ApiException("Stock not found");
        }
        if (stock.getQuantity() == 0){
            throw new ApiException("Fabric stock is zero");
        }
        double total_meters = stock.getQuantity() * fabric.getSize();
        if (total_meters < meter){
            throw new ApiException("Fabric length is less than meters");
        }
        double total_price = fabric.getPrice() + (tailor.getPriceByMeter()*meter) + designer.getPrice()+10;
        Order order = new Order(null,"Pending",total_price, LocalDateTime.now(),meter,customer,null,designer,fabric,merchant,tailor);
        orderService.addOrder(order);
        ShippingDTO shipping = new ShippingDTO(order.getId() , null ,10 ,"initialled" );
        shippingService.addShipping(shipping);
        if (fabric.getLength()-meter == 0){
            fabric.setLength(10);
            fabricRepository.save(fabric);
            stock.setQuantity(stock.getQuantity()-1);
            stockRepository.save(stock);
        }
        fabric.setLength(fabric.getLength()-meter);
        fabricRepository.save(fabric);
    }


    public double totalSpend(Integer customerId){
        Customer customer = customerRepository.findCustomerById(customerId);
        if (customer == null) {
            throw new ApiException("Customer not found");
        }
        List<Order> orders = getOrders(customerId);
        double total = 0;
        for (Order order : orders) {
            total = total + order.getTotalPrice();
        }
        return total;
    }

    public List<OrderHistoryDTO> getOrderHistory(Integer customerId) {
        List<Order> orders = orderRepository.findOrderByCustomId(customerId);

        List<OrderHistoryDTO> orderHistory = new ArrayList<>();

        for (Order order : orders) {
            OrderHistoryDTO history = new OrderHistoryDTO();
            history.setOrderId(order.getId());
            history.setOrderStatus(order.getOrderStatus());
            history.setTotalPrice(order.getTotalPrice());
            history.setOrderDate(order.getOrderDate());
//            history.setDeliveryDate(order.getShipping() != null ? order.getShipping().getDeliveryDate() : null);

            history.setFabricName(order.getFabric() != null ? order.getFabric().getName() : "N/A");
            history.setDesignerName(order.getDesigner() != null ? order.getDesigner().getName() : "N/A");
            history.setTailorName(order.getTailor() != null ? order.getTailor().getName() : "N/A");
            history.setMerchantName(order.getMerchant() != null ? order.getMerchant().getOwnerName() : "N/A");

            orderHistory.add(history);
        }

        return orderHistory;
    }



}
