package io.vlingo.developers.petclinic.model;

public class PostalAddress {

  public final String streetAddress;
  public final String city;
  public final String stateProvince;
  public final String postalCode;

  public static PostalAddress of(final String streetAddress, final String city, final String stateProvince, final String postalCode) {
    return new PostalAddress(streetAddress, city, stateProvince, postalCode);
  }

  private PostalAddress (final String streetAddress, final String city, final String stateProvince, final String postalCode) {
    this.streetAddress = streetAddress;
    this.city = city;
    this.stateProvince = stateProvince;
    this.postalCode = postalCode;
  }

}