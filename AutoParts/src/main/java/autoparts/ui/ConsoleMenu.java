package autoparts.ui;

import autoparts.service.InventoryService;
import autoparts.service.OrderProcessingService;
import autoparts.service.PickingService;
import autoparts.service.CatalogIntegrationService;
import autoparts.model.AutoPart;
import autoparts.model.PartBatch;
import autoparts.model.PartCategory;
import autoparts.model.ZoneType;
import autoparts.model.ABCCategory;
import autoparts.model.StorageZone;

import java.util.List;
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
                        System.out.println("\n--- Добавление запчасти ---");

                        System.out.print("Введите ID запчасти: ");
                        String partId = scanner.nextLine();

                        System.out.print("Введите название: ");
                        String name = scanner.nextLine();

                        System.out.print("Введите OEM номер: ");
                        String oem = scanner.nextLine();

                        System.out.println("Выберите категорию:");
                        PartCategory[] categories = PartCategory.values();
                        for (int i = 0; i < categories.length; i++) {
                            System.out.println((i + 1) + ". " + categories[i].getDescription());
                        }
                        int catChoice = readIntInput("Ваш выбор: ") - 1;
                        PartCategory category = categories[catChoice];

                        System.out.print("Введите производителя: ");
                        String manufacturer = scanner.nextLine();

                        System.out.print("Введите базовую цену: ");
                        double price = scanner.nextDouble();
                        scanner.nextLine();

                        System.out.print("Введите вес (кг): ");
                        double weight = scanner.nextDouble();
                        scanner.nextLine();

                        AutoPart newPart = new AutoPart(partId, name, oem, category, manufacturer, price, weight);
                        inventoryService.addPart(newPart);
                        System.out.println("Запчасть добавлена: " + newPart.getName());
                        break;
                    case 2:
                        System.out.println("\n--- Добавление кросс-номера ---");
                        System.out.print("Введите OEM номер запчасти: ");
                        String oem = scanner.nextLine();

                        AutoPart partToUpdate = inventoryService.findByOem(oem);
                        if (partToUpdate == null) {
                            System.out.println("Запчасть с OEM " + oem + " не найдена");
                            break;
                        }

                        System.out.print("Введите кросс-номер для добавления: ");
                        String crossNumber = scanner.nextLine();

                        partToUpdate.addCrossNumber(crossNumber);
                        System.out.println("Кросс-номер " + crossNumber + " добавлен для " + partToUpdate.getName());
                        break;

                    case 3:
                        System.out.println("\n--- Создание зоны хранения ---");

                        System.out.print("Введите ID зоны: ");
                        String zoneId = scanner.nextLine();

                        System.out.print("Введите название зоны: ");
                        String zoneName = scanner.nextLine();

                        System.out.println("Выберите тип зоны:");
                        ZoneType[] zoneTypes = ZoneType.values();
                        for (int i = 0; i < zoneTypes.length; i++) {
                            System.out.println((i + 1) + ". " + zoneTypes[i].getDescription());
                        }
                        int typeChoice = readIntInput("Ваш выбор: ") - 1;
                        ZoneType zoneType = zoneTypes[typeChoice];

                        System.out.println("Выберите ABC-категорию:");
                        ABCCategory[] abcCategories = ABCCategory.values();
                        for (int i = 0; i < abcCategories.length; i++) {
                            System.out.println((i + 1) + ". " + abcCategories[i].getDescription());
                        }
                        int abcChoice = readIntInput("Ваш выбор: ") - 1;
                        ABCCategory abcCategory = abcCategories[abcChoice];

                        System.out.print("Введите вместимость (количество позиций): ");
                        int capacity = scanner.nextInt();
                        scanner.nextLine();

                        System.out.print("Введите максимальную грузоподъёмность (кг): ");
                        double maxWeight = scanner.nextDouble();
                        scanner.nextLine();

                        StorageZone zone = new StorageZone(zoneId, zoneName, zoneType, abcCategory, capacity, maxWeight);
                        inventoryService.addZone(zone);
                        System.out.println("Зона создана: " + zone.getName());
                        break;
                    case 4:
                        System.out.println("\n--- Приёмка партии ---");
                        System.out.print("Введите OEM номер запчасти: ");
                        String oemForReceive = scanner.nextLine();

                        AutoPart partForReceive = inventoryService.findByOem(oemForReceive);
                        if (partForReceive == null) {
                            System.out.println("Запчасть не найдена");
                            break;
                        }

                        System.out.print("Введите количество: ");
                        int quantity = scanner.nextInt();
                        scanner.nextLine();

                        System.out.print("Введите ID зоны: ");
                        String zoneId = scanner.nextLine();

                        System.out.print("Введите номер сертификата (или 'none'): ");
                        String certificate = scanner.nextLine();
                        if (certificate.equals("none")) certificate = null;

                        try {
                            PartBatch batch = inventoryService.receiveBatch(partForReceive, quantity, zoneId, certificate);
                            System.out.println("Партия принята: " + batch.getBatchId());
                        } catch (Exception e) {
                            System.out.println("Ошибка: " + e.getMessage());
                        }
                        break;
                    case 5:
                        System.out.println("\n--- Перемещение партии ---");
                        System.out.print("Введите ID партии: ");
                        String batchId = scanner.nextLine();

                        System.out.print("Введите ID целевой зоны: ");
                        String targetZoneId = scanner.nextLine();

                        try {
                            inventoryService.relocateBatch(batchId, targetZoneId);
                            System.out.println("Партия перемещена");
                        } catch (Exception e) {
                            System.out.println("Ошибка: " + e.getMessage());
                        }
                        break;
                    case 6:
                        System.out.println("\n--- Поиск по OEM ---");
                        System.out.print("Введите OEM номер: ");
                        String oemSearch = scanner.nextLine();

                        AutoPart foundPart = inventoryService.findByOem(oemSearch);
                        if (foundPart != null) {
                            System.out.println("Найдено: " + foundPart);
                        } else {
                            System.out.println("Запчасть не найдена");
                        }
                        break;

                    case 7:
                        System.out.println("\n--- Поиск аналогов по кросс-номеру ---");
                        System.out.print("Введите кросс-номер: ");
                        String crossSearch = scanner.nextLine();

                        List<AutoPart> analogs = inventoryService.findByCrossNumber(crossSearch);
                        if (analogs.isEmpty()) {
                            System.out.println("Аналоги не найдены");
                        } else {
                            System.out.println("Найдено аналогов: " + analogs.size());
                            for (AutoPart part : analogs) {
                                System.out.println("   - " + part.getName() + " [" + part.getOemNumber() + "]");
                            }
                        }
                        break;

                    case 8:
                        System.out.println("\n--- Проверка совместимости с VIN ---");
                        System.out.print("Введите OEM номер запчасти: ");
                        String oemVin = scanner.nextLine();

                        AutoPart partVin = inventoryService.findByOem(oemVin);
                        if (partVin == null) {
                            System.out.println("Запчасть не найдена");
                            break;
                        }

                        System.out.print("Введите VIN код автомобиля: ");
                        String vin = scanner.nextLine();

                        boolean compatible = partVin.isCompatibleWithVin(vin);
                        if (compatible) {
                            System.out.println("Запчасть совместима с VIN " + vin);
                        } else {
                            System.out.println("Запчасть НЕ совместима с VIN " + vin);
                        }
                        break;
                    case 9:
                        // TODO: занятие 5 - Создать заказ клиента
                        break;
                    case 10:
                        // TODO: занятие 5 - Добавить позицию в заказ (с проверкой VIN)
                        break;
                    case 11:
                        // TODO: занятие 5 - Подтвердить заказ и зарезервировать
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
                        // TODO: занятие 5 - Отчёт: запчасти с низким остатком
                        break;
                    case 18:
                        // TODO: занятие 5 - Отчёт: просроченные сертификаты
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
                // TODO: занятие 4 - обработка исключений с выводом сообщения
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
