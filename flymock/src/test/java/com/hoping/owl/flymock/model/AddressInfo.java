package com.hoping.owl.flymock.model;

import java.util.List;

/**
 * Created by houping wang on 2019/4/9
 *
 * @author houping wang
 */
public class AddressInfo {

    private Address address;

    private Boolean nonProfit;

    private String name;

    private List<Link> links;

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Boolean getNonProfit() {
        return nonProfit;
    }

    public void setNonProfit(Boolean nonProfit) {
        this.nonProfit = nonProfit;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Link> getLinks() {
        return links;
    }

    public void setLinks(List<Link> links) {
        this.links = links;
    }

    @Override
    public String toString() {
        return "AddressInfo{" +
                "address=" + address +
                ", nonProfit=" + nonProfit +
                ", name='" + name + '\'' +
                ", links=" + links +
                '}';
    }

    static class Link{
        private String name;

        private String url;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }

    static class Address {
        private String street;

        private String city;

        private String country;

        public String getStreet() {
            return street;
        }

        public void setStreet(String street) {
            this.street = street;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }
    }
}
