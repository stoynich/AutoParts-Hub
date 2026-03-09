package autoparts.service;

import autoparts.model.AutoPart;
import autoparts.model.CustomerOrder;
import autoparts.model.Priority;

public class PricingService {
    
    public void setBasePrice(AutoPart autoPart, double price) {
        // TODO: занятие 5 - установить basePrice
    }
    
    public void applyMarkup(AutoPart autoPart, double percent) {
        // TODO: занятие 5 - увеличить basePrice на percent%
    }
    
    public double getPriceForClient(AutoPart autoPart, String clientId, Priority priority) {
        // TODO: занятие 5 - для URGENT +10% к цене
        return autoPart.getBasePrice();
    }
    
    public double calculateOrderProfit(CustomerOrder order, double costPrice) {
        // TODO: занятие 5 - рассчитать (getTotalAmount() - costPrice * количество)
        return 0.0;
    }
}
