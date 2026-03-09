package autoparts.model;

public enum ABCCategory {
    A,  // высокий оборот (>100 шт/мес), зона быстрого доступа
    B,  // средний оборот (20-100 шт/мес)
    C;  // низкий оборот (<20 шт/мес), дальние стеллажи
    
    // TODO: занятие 3 - добавить поля minTurnover, maxTurnover, zoneType
}
