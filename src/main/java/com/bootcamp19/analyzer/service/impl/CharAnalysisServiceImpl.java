package com.bootcamp19.analyzer.service.impl;

import com.bootcamp19.analyzer.dto.response.CharAnalysisResponse;
import com.bootcamp19.analyzer.entity.CharAnalysis;
import com.bootcamp19.analyzer.repository.CharAnalysisRepository;
import com.bootcamp19.analyzer.service.CharAnalysisService;
import com.bootcamp19.analyzer.util.CharCounter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CharAnalysisServiceImpl implements CharAnalysisService {

    private final CharAnalysisRepository charAnalysisRepository;

    @Override
    public List<CharAnalysisResponse> cari(String kata) {
        List<CharAnalysis> semua;
        if(kata == null || kata.isBlank()){
            semua = charAnalysisRepository.findAll();
        }else{
            semua = charAnalysisRepository.findByKalimatContainingIgnoreCase(kata);
        }

        List<CharAnalysisResponse> hasil = new ArrayList<>();

        for(CharAnalysis data:semua){
            hasil.add(toResponse(data));
        }
        return hasil;
    }

    private CharAnalysisResponse toResponse(CharAnalysis data){
        CharCounter counter = new CharCounter(data.getKalimat());

        return CharAnalysisResponse.from(data, counter.toMap());
    }
}
