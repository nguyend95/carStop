package eu.epptec.carStop.enums;

import java.util.Arrays;

public enum ROLE {
    ROLE_USER("USER"),
    ROLE_ADMIN("ADMIN");

    ROLE(String name) {
    }

    public static boolean checkByName(String value) {
        return Arrays.stream(ROLE.values()).anyMatch(
                role -> role.name().equals(value)
        );
    }

    public static ROLE findByName(String value) {
        return Arrays.stream(ROLE.values()).filter(
                role -> role.name().equals(value)
        ).findAny().orElseThrow(IllegalArgumentException::new);
    }
}
