package com.example.smallpeopleblog.dto;

import com.example.smallpeopleblog.constant.ItemCategory;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class ItemSearchDto {

    private ItemCategory searchCategory1 = ItemCategory.ALL;
    private String searchCategory2 = "";
    private String searchCategory3 = "";
    private String searchType = "";
    private String searchKeyword = "";

}
