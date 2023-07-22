package com.example.dailyFreshCoffeeBranch.service;

import com.example.dailyFreshCoffeeBranch.dto.DeliveryDto;
import com.example.dailyFreshCoffeeBranch.dto.DeliveryItemDto;
import com.example.dailyFreshCoffeeBranch.entity.Delivery;
import com.example.dailyFreshCoffeeBranch.exception.DeliveryNotFoundException;
import com.example.dailyFreshCoffeeBranch.repository.DeliveryItemRepository;
import com.example.dailyFreshCoffeeBranch.repository.DeliveryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class DeliveryService {

    private final DeliveryRepository deliveryRepository;
    private final DeliveryItemRepository deliveryItemRepository;

    /**
     * 회원 배송 목록
     *
     * @param email
     * @return
     */
    public Page<DeliveryDto> getMemberDeliveryList(String email, Pageable pageable) {
        Page<Delivery> deliveryList = deliveryRepository.findByMemberEmail(email, pageable);

        return deliveryList
                .map(d -> DeliveryDto.of(d));
    }

    /**
     * 회원 배송 상세
     *
     * @param email
     * @param id
     * @return
     */
    public DeliveryDto getMemberDeliveryDetail(String email, Long id, Pageable pageable) {
        Delivery delivery = deliveryRepository.findByMemberEmailAndId(email, id)
                .orElseThrow(() -> new DeliveryNotFoundException("배송을 조회할 수 없습니다."));

        Page<DeliveryItemDto> deliveryItemDtoPage = deliveryItemRepository.findByDeliveryId(delivery.getId(), pageable)
                .map(DeliveryItemDto::of);

        return DeliveryDto.create(delivery, deliveryItemDtoPage);
    }

}