package autoparts.service;

import autoparts.model.AutoPart;
import autoparts.model.CustomerOrder;
import autoparts.model.Priority;

public class PricingService {

    public void setBasePrice(AutoPart autoPart, double price) {
        if (autoPart == null || price < 0) {
            throw new IllegalArgumentException("Некорректная цена");
        }

        autoPart.setBasePrice(price);
    }

    public void applyMarkup(AutoPart autoPart, double percent) {
        if (autoPart == null) {
            throw new IllegalArgumentException("Запчасть не может быть null");
        }

        double newPrice = autoPart.getBasePrice() * (1 + percent / 100);
        autoPart.setBasePrice(newPrice);
    }

    public double getPriceForClient(AutoPart autoPart, String clientId, Priority priority) {
        double price = autoPart.getBasePrice();

        if (priority == Priority.URGENT) {
            price *= 1.10; // +10%
        }

        return price;
    }

    public double calculateOrderProfit(CustomerOrder order, double costPrice) {
        return order.getTotalAmount() - costPrice;
    }
}
