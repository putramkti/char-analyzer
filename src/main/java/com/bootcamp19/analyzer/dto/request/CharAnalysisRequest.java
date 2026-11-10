package com.bootcamp19.analyzer.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CharAnalysisRequest {
    @NotBlank(message = "Kalimat wajib diisi")
    @NotNull(message = "Kalimat tidak boleh null")
    @Size(max = 255, message = "Maksimal kalimat 255 karakter")
    private String kalimat;
}
