package org.svenehrke.htmxexamplecontactsspringbootgradlejte;

import io.soabase.recordbuilder.core.RecordBuilder;

import java.math.BigInteger;

@RecordBuilder
public record Contact(BigInteger id, String firstName, String lastName, String phone, String email) {
}
