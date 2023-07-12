package com.example.smallpeopleblog.dto;

import lombok.*;

import javax.validation.constraints.Min;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter @Setter
public class CartItemUpdateDto {

    @Min(value = 1, message = "상품을 최소 1개 이상 담아주세요.")
    private int count;

}