package io.chechi.delivery.service;

import io.chechi.delivery.dto.extra.ExtraRequest;
import io.chechi.delivery.dto.extra.ExtraResponse;

import java.util.List;

public interface ExtraService {

    List<ExtraResponse> findAll ();

    ExtraResponse addExtra (ExtraRequest request);

    ExtraResponse updateExtra (Integer extraId, ExtraRequest request);

    void deleteExtra (Integer extraId);
}
