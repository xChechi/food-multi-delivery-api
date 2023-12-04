package io.chechi.delivery.converter;

import io.chechi.delivery.dto.extra.ExtraRequest;
import io.chechi.delivery.dto.extra.ExtraResponse;
import io.chechi.delivery.entity.Extra;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ExtraConverter {

    public Extra addExtra (ExtraRequest request) {

        return Extra.builder()
                .name(request.getName())
                .price(request.getPrice())
                .build();
    }

    public ExtraResponse toResponse (Extra extra) {

        return ExtraResponse.builder()
                .id(extra.getId())
                .name(extra.getName())
                .price(extra.getPrice())
                .build();
    }
}
