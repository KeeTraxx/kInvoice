package ch.compile.kinvoice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Address {

    public final static Address DUMMY = new Address("Peter Muster", "Musterstrasse 123", "3072", "Ostermundigen", "Switzerland");

    private String name;
    private String street;
    private String zip;
    private String city;
    private String country;

    @Override
    public String toString() {
        return name + "\n" + street + "\n" + zip + " " + city + (country != null ? (country.equals("Switzerland") ? "" : "\n" + country) : "");
    }
}
