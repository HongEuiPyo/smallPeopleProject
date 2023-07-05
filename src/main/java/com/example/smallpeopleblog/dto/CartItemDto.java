package com.example.smallpeopleblog.dto;

import com.example.smallpeopleblog.entity.Cart;
import com.example.smallpeopleblog.entity.CartItem;
import com.example.smallpeopleblog.entity.Item;
import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter @Setter
public class CartItemDto {

    private Long id;

    @NotNull(message = "상품 아이디는 필수 입력 값입니다.")
    private Long itemId;

    @Min(value = 1, message = "최소 1개 이상 담아주세요.")
    private int count;

    private String itemName;

    private int price;

    private String imageUrl;

    public CartItem toEntity(Cart cart, Item item) {
        return CartItem.builder()
                .cart(cart)
                .item(item)
                .count(count)
                .build();
    }
}
