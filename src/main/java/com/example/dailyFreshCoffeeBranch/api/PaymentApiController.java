package com.example.dailyFreshCoffeeBranch.api;

import com.example.dailyFreshCoffeeBranch.annotation.LoginUserInfo;
import com.example.dailyFreshCoffeeBranch.dto.MemberPointUpDto;
import com.example.dailyFreshCoffeeBranch.dto.PaymentDto;
import com.example.dailyFreshCoffeeBranch.security.oauth2.UserInfo;
import com.example.dailyFreshCoffeeBranch.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RequestMapping("/api")
@RequiredArgsConstructor
@RestController
public class PaymentApiController {

    private final PaymentService paymentService;

    /**
     * 포인트 충전
     * @param memberPointUpDto
     * @param userInfo
     * @return
     */
    @PostMapping("/payment/addPoint")
    public ResponseEntity<Map<String, String>> addPoint(
            @Valid @RequestBody MemberPointUpDto memberPointUpDto,
            BindingResult result,
            @LoginUserInfo UserInfo userInfo
    ) {
        Map<String, String> resultMap = new HashMap<>();

        if (result.hasErrors()) {

            for (FieldError error : result.getFieldErrors()) {
                resultMap.put(error.getField(), error.getDefaultMessage());
            }

            return new ResponseEntity<>(resultMap, HttpStatus.BAD_REQUEST);
        }

        paymentService.addPoint(userInfo.getEmail(), memberPointUpDto);

        resultMap.put("msg", "포인트 충전을 완료하였습니다.");

        return ResponseEntity.ok().body(resultMap);
    }

    /**
     * 장바구니 상품 구매확정
     * @param paymentDto
     * @param userInfo
     * @return
     */
    @PostMapping("/payment/confirmCartItemPurchase")
    public ResponseEntity<Map<String, String>> confirmCartItemPurchase(
            @Valid @RequestBody PaymentDto paymentDto,
            BindingResult result,
            @LoginUserInfo UserInfo userInfo
    ) {
        Map<String, String> resultMap = new HashMap<>();

        if (result.hasErrors()) {

            for (FieldError error : result.getFieldErrors()) {
                resultMap.put(error.getField(), error.getDefaultMessage());
            }

            return new ResponseEntity<>(resultMap, HttpStatus.BAD_REQUEST);
        }

        paymentService.confirmCartItemPurchase(userInfo.getEmail(), paymentDto);

        resultMap.put("msg", "구매확정을 완료하였습니다.");

        return ResponseEntity.ok().body(resultMap);
    }

}