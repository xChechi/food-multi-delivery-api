package io.chechi.delivery.service.impl;

import io.chechi.delivery.converter.ExtraConverter;
import io.chechi.delivery.dto.extra.ExtraRequest;
import io.chechi.delivery.dto.extra.ExtraResponse;
import io.chechi.delivery.entity.Extra;
import io.chechi.delivery.exception.ExtraNotFoundException;
import io.chechi.delivery.repository.ExtraRepository;
import io.chechi.delivery.service.ExtraService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ExtraServiceImpl implements ExtraService {

    private final ExtraRepository extraRepository;
    private final ExtraConverter extraConverter;

    @Override
    public List<ExtraResponse> findAll() {
        List<Extra> extraList = extraRepository.findAll();
        return extraList.stream()
                .map(extraConverter::toResponse)
                .toList();
    }

    @Override
    public ExtraResponse addExtra(ExtraRequest request) {
        Extra extra = extraConverter.addExtra(request);
        Extra savedExtra = extraRepository.save(extra);
        return extraConverter.toResponse(savedExtra);
    }

    @Override
    public ExtraResponse updateExtra(Integer extraId, ExtraRequest request) {
        Extra extra = extraRepository.findById(extraId).orElseThrow(() -> new ExtraNotFoundException("Extra not found"));
        extra.setName(request.getName());
        extra.setPrice(request.getPrice());
        Extra savedExtra = extraRepository.save(extra);
        return extraConverter.toResponse(savedExtra);
    }

    @Override
    public void deleteExtra(Integer extraId) {
        extraRepository.deleteById(extraId);
    }
}
