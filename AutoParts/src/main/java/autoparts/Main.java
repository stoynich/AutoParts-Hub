package autoparts;

import autoparts.model.*;
import autoparts.catalog.AutodocCatalogClient;
import autoparts.catalog.ExistCatalogClient;
import autoparts.catalog.TecDocCatalogClient;
import autoparts.logger.ConsoleLogger;
import autoparts.logger.Logger;
import autoparts.service.*;
import autoparts.ui.ConsoleMenu;
import autoparts.validation.OrderValidator;
import autoparts.validation.StockValidator;
import autoparts.validation.VinValidator;

public class Main {
    public static void main(String[] args) {
        // TODO: занятие 5 - инициализация логгера
        
        // TODO: занятие 5 - создание сервисов

        OrderValidator orderValidator = new OrderValidator();
        StockValidator stockValidator = new StockValidator();
        VinValidator vinValidator = new VinValidator();
        
        // TODO: занятие 5 - создание сервиса заказов
        
        
        // TODO: занятие 6 - создание сервисов комплектации и каталогов
       
        
        // TODO: занятие 3 - добавление клиентов каталогов

        System.out.println("=== ЗАНЯТИЕ 1: Тестовые данные ===\n");

        // 1. Создаем первую запчасть с кросс-номерами
        AutoPart part1 = new AutoPart(
                "P001",
                "Тормозные колодки передние",
                "OEM-BOSCH-123",
                PartCategory.BRAKES,
                "Bosch",
                2500.0,
                1.5
        );
        part1.addCrossNumber("CROSS-BOSCH-456");
        part1.addCrossNumber("CROSS-TRW-789");

        // 2. Создаем вторую запчасть с кросс-номерами
        AutoPart part2 = new AutoPart(
                "P002",
                "Масляный фильтр",
                "OEM-MANN-789",
                PartCategory.CONSUMABLES,
                "Mann",
                500.0,
                0.2
        );
        part2.addCrossNumber("CROSS-MANN-123");
        part2.addCrossNumber("CROSS-FRAM-456");

        // 3. Создаем зону A
        StorageZone zoneA = new StorageZone(
                "ZONE-A",
                "Зона быстрой комплектации A",
                ZoneType.FAST_PICK,
                ABCCategory.A,
                1000,
                5000.0
        );

        // 4. Создаем зону C
        StorageZone zoneC = new StorageZone(
                "ZONE-C",
                "Зона массового хранения C",
                ZoneType.BULK_STORAGE,
                ABCCategory.C,
                5000,
                20000.0
        );

        // 5. Создаем поставщика
        PartCategory[] categories = {PartCategory.BRAKES, PartCategory.CONSUMABLES};
        Supplier supplier = new Supplier(
                "SUP-001",
                "ООО АвтоПоставка",
                "7712345678",
                "+7 (495) 123-45-67",
                "info@autopostavka.ru",
                categories
        );

        // ВЫВОДИМ ВСЕ СОЗДАННЫЕ ОБЪЕКТЫ
        System.out.println("ЗАПЧАСТИ:");
        System.out.println("  " + part1);
        System.out.println("  Кросс-номера: " + part1.getCrossNumbers());
        System.out.println("  " + part2);
        System.out.println("  Кросс-номера: " + part2.getCrossNumbers());

        System.out.println("\nЗОНЫ ХРАНЕНИЯ:");
        System.out.println("  " + zoneA);
        System.out.println("  " + zoneC);

        System.out.println("\nПОСТАВЩИК:");
        System.out.println("  " + supplier);


        // TODO: 2 запчасти с кросс-номерами, 2 зоны (A и C категории), 1 поставщик
        // TODO: вывести в консоль созданные объекты
        
        // TODO: занятие 3 - запуск меню
       
    }
}
