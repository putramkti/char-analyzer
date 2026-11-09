package com.bootcamp19.analyzer.service;

import com.bootcamp19.analyzer.dto.response.CharAnalysisResponse;
import com.bootcamp19.analyzer.entity.CharAnalysis;

import java.util.List;

public interface CharAnalysisService {
    List<CharAnalysisResponse> cari(String kata);
}
