package io.chechi.delivery.controller;

import io.chechi.delivery.dto.extra.ExtraRequest;
import io.chechi.delivery.dto.extra.ExtraResponse;
import io.chechi.delivery.service.ExtraService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/extras")
@AllArgsConstructor
public class ExtraController {

    private final ExtraService extraService;

    @GetMapping
    public ResponseEntity<List<ExtraResponse>> findAll () {
        return ResponseEntity.status(HttpStatus.FOUND).body(extraService.findAll());
    }

    @PostMapping
    public ResponseEntity<ExtraResponse> addExtra (@RequestBody @Valid ExtraRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(extraService.addExtra(request));
    }

    @PutMapping("/{extraId}")
    public ResponseEntity<ExtraResponse> updateExtra (@PathVariable Integer extraId, @RequestBody @Valid ExtraRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(extraService.updateExtra(extraId, request));
    }

    @DeleteMapping("/{extraId}")
    public ResponseEntity<Void> deleteExtra (@PathVariable Integer extraId) {
        extraService.deleteExtra(extraId);
        return ResponseEntity.noContent().build();
    }
}
