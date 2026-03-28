package autoparts.validation;

import autoparts.exception.InvalidVinException;

public class VinValidator {

    public void validateVinFormat(String vinCode) throws InvalidVinException {

        if (vinCode == null || vinCode.length() != 17) {
            throw new InvalidVinException("VIN должен содержать 17 символов", vinCode);
        }

        if (vinCode.matches(".*[IOQ].*")) {
            throw new InvalidVinException("VIN содержит запрещённые символы (I, O, Q)", vinCode);
        }

        if (!vinCode.matches("[A-HJ-NPR-Z0-9]{17}")) {
            throw new InvalidVinException("VIN содержит недопустимые символы", vinCode);
        }
    }

    public void validateVinExists(String vinCode) throws InvalidVinException {
        if (vinCode.startsWith("XXX")) {
            throw new InvalidVinException("VIN не найден в каталоге", vinCode);
        }
    }
}
