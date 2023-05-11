package kr.co.hhjpetclinicstudy.service.model.dtos.request;

import lombok.*;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class IdsReqDTO {

    private List<Long> conditionIds;
}
