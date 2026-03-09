package autoparts.model;

public enum ZoneType {
    FAST_PICK,      // быстрая комплектация (ABC-A)
    BULK_STORAGE,   // массовое хранение (ABC-B/C)
    RETURNS,        // возвраты и брак
    QUARANTINE,     // карантинная зона (без сертификатов)
    CROSS_DOCKING;  // сквозная погрузка
    
    // TODO: занятие 3 - добавить поля isFastAccess, requiresCertificate
}
