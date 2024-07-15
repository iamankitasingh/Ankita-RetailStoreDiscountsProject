package com.retail.retailStoreDiscounts.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.retail.retailStoreDiscounts.model.Bill;
import com.retail.retailStoreDiscounts.model.Product;
import com.retail.retailStoreDiscounts.model.User;

@Service
public class DiscountService {

	public double calculateDiscount(Bill bill) {
        User user = bill.getUser();
        List<Product> products = bill.getProducts();

        double total = calculateTotal(products);
        double nonGroceryTotal = calculateNonGroceryTotal(products);
        double percentageDiscount = getPercentageDiscount(user);
        double discountAmount = nonGroceryTotal * percentageDiscount;
        double additionalDiscount = calculateAdditionalDiscount(total);

        return discountAmount + additionalDiscount;
    }

    private double calculateTotal(List<Product> products) {
        double total = 0;
        for (Product product : products) {
            total += product.getPrice();
        }
        return total;
    }

    private double calculateNonGroceryTotal(List<Product> products) {
        double nonGroceryTotal = 0;
        for (Product product : products) {
            if (!"GROCERY".equalsIgnoreCase(product.getProductType())) {
                nonGroceryTotal += product.getPrice();
            }
        }
        return nonGroceryTotal;
    }

    private double getPercentageDiscount(User user) {
        switch (user.getUserType().toUpperCase()) {
            case "EMPLOYEE":
                return 0.30;
            case "AFFILIATE":
                return 0.10;
            case "CUSTOMER":
                if (user.getCustomerDuration() > 2) {
                    return 0.05;
                }
                break;
        }
        return 0.0;
    }

    private double calculateAdditionalDiscount(double total) {
        return ((int) (total / 100)) * 5;
    }
}
