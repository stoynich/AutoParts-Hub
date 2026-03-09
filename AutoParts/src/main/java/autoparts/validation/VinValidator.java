package autoparts.validation;

import autoparts.exception.InvalidVinException;

public class VinValidator {
    
    public void validateVinFormat(String vinCode) throws InvalidVinException {
        // TODO: занятие 4 - проверить длину 17 символов
        // TODO: проверить допустимые символы (без I, O, Q)
        // TODO: бросить InvalidVinException если неверный формат
    }
    
    public void validateVinExists(String vinCode) throws InvalidVinException {
        // TODO: занятие 4 - имитация проверки в базе каталога
    }
}
