package Architect;

import java.util.HashMap;
import java.util.Map;

public class MyAdapter {

    public static Map<String,String> countries = new HashMap<>();

    static {
        countries.put("AU", "Australia");
        countries.put("RU", "Russia");
        countries.put("CA", "Canada");
    }

    public static void main(String[] args) {

    }

    public static class DataAdapter implements RowItem {

        private Customer customer;
        private Contact contact;

        public DataAdapter(Customer customer, Contact contact) {
            this.customer = customer;
            this.contact = contact;
        }

        @Override
        public String getCountryCode() {
            String str = "";
            for (Map.Entry<String, String> pair : countries.entrySet()) {
                if (pair.getValue().equals(customer.getCountryName()))
                    str = pair.getKey();
            }
            return str;
        }

        @Override
        public String getCompany() {
            return customer.getCompanyName();
        }

        @Override
        public String getContactFirstName() {
            String s = contact.getName();
            return s.substring(s.indexOf(" ") + 1);
        }

        @Override
        public String getContactLastName() {
            String s = contact.getName();
            return s.substring(0, s.indexOf(","));
        }

        @Override
        public String getDialString() {
            String s = contact.getPhoneNumber().replace("(", "");
            String s1 = s.replace(")", "");
            return "callto://" + s1.replaceAll("-", "");
        }
    }

    public interface RowItem {
        String getCountryCode();
        String getCompany();
        String getContactFirstName();
        String getContactLastName();
        String getDialString();
    }

    public interface Customer {
        String getCompanyName();
        String getCountryName();
    }

    public interface Contact {
        String getName();
        String getPhoneNumber();
    }
}