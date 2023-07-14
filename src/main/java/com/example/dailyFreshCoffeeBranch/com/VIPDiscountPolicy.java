package com.example.dailyFreshCoffeeBranch.com;

import com.example.dailyFreshCoffeeBranch.constant.Role;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class VIPDiscountPolicy {

    @Value("${vipDiscountPolicy.applyRole}")
    private String applyRole;

    @Value("${vipDiscountPolicy.discountRate}")
    private double discountRate;

    public double getDiscountPrice(int totalPrice, Role memberRole) {
        if (memberRole.toString().equals(applyRole)) {
            double applyPrice = totalPrice * discountRate;
            totalPrice -= applyPrice;
        }
        return totalPrice;
    }

}