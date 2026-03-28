package autoparts.ui;

import autoparts.service.InventoryService;
import autoparts.service.OrderProcessingService;
import autoparts.service.PickingService;
import autoparts.service.CatalogIntegrationService;
import autoparts.model.CustomerOrder;
import autoparts.model.Priority;

import java.util.Scanner;

public class ConsoleMenu {
    private final Scanner scanner;
    private final InventoryService inventoryService;
    private final OrderProcessingService orderService;
    private final PickingService pickingService;
    private final CatalogIntegrationService catalogService;
    
    public ConsoleMenu(InventoryService inventoryService, 
                       OrderProcessingService orderService,
                       PickingService pickingService,
                       CatalogIntegrationService catalogService) {
        this.scanner = new Scanner(System.in);
        this.inventoryService = inventoryService;
        this.orderService = orderService;
        this.pickingService = pickingService;
        this.catalogService = catalogService;
    }
    
    public void start() {
        while (true) {
            printMenu();
            int choice = readIntInput("Выберите пункт: ");
            
            try {
                switch (choice) {
                    case 1:
                        // TODO: занятие 1 - Добавить запчасть в каталог
                        break;
                    case 2:
                        // TODO: занятие 2 - Добавить кросс-номер аналога
                        break;
                    case 3:
                        // TODO: занятие 1 - Создать зону хранения
                        break;
                    case 4:
                        // TODO: занятие 2 - Принять партию на склад
                        break;
                    case 5:
                        // TODO: занятие 2 - Переместить партию между зонами
                        break;
                    case 6:
                        // TODO: занятие 2 - Найти запчасть по OEM
                        break;
                    case 7:
                        // TODO: занятие 2 - Найти аналоги по кросс-номеру
                        break;
                    case 8:
                        // TODO: занятие 2 - Проверить совместимость с VIN
                        break;
                    case 9:
                        String externalOrderId = readStringInput("Введите внешний ID заказа: ");
                        String clientId = readStringInput("Введите ID клиента: ");
                        String vinCode = readStringInput("Введите VIN: ");
                        String priorityInput = readStringInput("Введите приоритет (REGULAR/URGENT): ");

                        Priority priority = Priority.valueOf(priorityInput.toUpperCase());
                        CustomerOrder order = orderService.createOrder(externalOrderId, clientId, vinCode, priority);

                        System.out.println("Заказ создан: " + order);
                        break;
                    case 10:
                        String orderIdForItem = readStringInput("Введите ID заказа: ");
                        String oemNumber = readStringInput("Введите OEM или кросс-номер: ");
                        int quantity = readIntInput("Введите количество: ");

                        orderService.addItemToOrder(orderIdForItem, oemNumber, quantity);
                        System.out.println("Позиция добавлена в заказ");
                        break;
                    case 11:
                        String orderIdToConfirm = readStringInput("Введите ID заказа: ");

                        orderService.confirmOrder(orderIdToConfirm);
                        orderService.reserveForOrder(orderIdToConfirm);

                        System.out.println("Заказ подтвержден и товары зарезервированы");
                        break;
                    case 12:
                        // TODO: занятие 6 - Создать задание на комплектацию
                        break;
                    case 13:
                        // TODO: занятие 6 - Выполнить wave picking (групповая комплектация)
                        break;
                    case 14:
                        // TODO: занятие 6 - Заменить на аналог в заказе
                        break;
                    case 15:
                        // TODO: занятие 6 - Упаковать и отгрузить заказ
                        break;
                    case 16:
                        // TODO: занятие 6 - Отменить заказ
                        break;
                    case 17:
                        int threshold = readIntInput("Введите порог остатка: ");
                        System.out.println(inventoryService.getLowStockReport(threshold));
                        break;
                    case 18:
                        System.out.println(inventoryService.getExpiredBatches());
                        break;
                    case 19:
                        // TODO: занятие 6 - Отчёт: просроченные срочные заказы
                        break;
                    case 20:
                        // TODO: занятие 3 - Синхронизировать с каталогом TecDoc
                        break;
                    case 0:
                        System.out.println("Выход...");
                        return;
                    default:
                        System.out.println("Неверный выбор");
                }
            } catch (Exception e) {
                System.err.println("Ошибка: " + e.getMessage());
            }
        }
    }
    
    private void printMenu() {
        System.out.println("\n=== AutoParts Hub ===");
        System.out.println("1. Добавить запчасть");
        System.out.println("2. Добавить кросс-номер аналога");
        System.out.println("3. Создать зону хранения");
        System.out.println("4. Принять партию на склад");
        System.out.println("5. Переместить партию между зонами");
        System.out.println("6. Найти запчасть по OEM");
        System.out.println("7. Найти аналоги по кросс-номеру");
        System.out.println("8. Проверить совместимость с VIN");
        System.out.println("9. Создать заказ клиента");
        System.out.println("10. Добавить позицию в заказ (с проверкой VIN)");
        System.out.println("11. Подтвердить заказ (резерв)");
        System.out.println("12. Создать задание на комплектацию");
        System.out.println("13. Wave picking (групповая комплектация)");
        System.out.println("14. Заменить на аналог в заказе");
        System.out.println("15. Упаковать и отгрузить заказ");
        System.out.println("16. Отменить заказ");
        System.out.println("17. Отчёт: низкие остатки");
        System.out.println("18. Отчёт: просроченные сертификаты");
        System.out.println("19. Отчёт: просроченные срочные заказы");
        System.out.println("20. Синхронизация с TecDoc");
        System.out.println("0. Выход");
    }
    
    private int readIntInput(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextInt()) {
            System.out.println("Введите число!");
            scanner.next();
        }
        return scanner.nextInt();
    }
    
    private String readStringInput(String prompt) {
        System.out.print(prompt);
        return scanner.next();
    }
}
