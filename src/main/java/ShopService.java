import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ShopService {
    private ProductRepo productRepo = new ProductRepo();
    private OrderRepo orderRepo = new OrderMapRepo();

    public Order addOrder(List<String> productIds) throws ProductDoesNotExistException {
        List<Product> products = new ArrayList<>();
        for (String productId : productIds) {
            Product productToOrder = productRepo.getProductById(productId).get();
            if (productToOrder == null) {
                throw new ProductDoesNotExistException("Product mit der Id: " + productId + " konnte nicht bestellt werden!");
            }
            products.add(productToOrder);
        }

        Order newOrder = new Order(UUID.randomUUID().toString(), products, Bestellstatus.PROCESSING);

        return orderRepo.addOrder(newOrder);
    }

    public List<Order> getOrdersWithSpecificOrderStatus(Bestellstatus status) {
        List<Order> orderWithSpecificStaus = orderRepo.getOrders().stream().
                filter(order -> order.bestellstatus().equals(status))
                .toList();
        return orderWithSpecificStaus;
    }

    public void updateOrder(String orderId, Bestellstatus status) {
        Order order = orderRepo.getOrderById(orderId);
        order.withBestellstatus(status);
    }
}
