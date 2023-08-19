package org.svenehrke.htmxexamplecontactsspringbootgradlejte;

import java.math.BigInteger;

public record Contact(BigInteger id, String firstName, String lastName, String phone, String email) {
}
