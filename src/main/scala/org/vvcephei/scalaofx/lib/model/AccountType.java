package org.vvcephei.scalaofx.lib.model;

public enum AccountType {
    CHECKING, SAVINGS, MONEYMRKT,
    CREDITCARD /*non-standard*/;

    public static AccountType from(String name) {
        return valueOf(name.toUpperCase());
    }
}
