package com.bootcamp19.analyzer.service.impl;

import com.bootcamp19.analyzer.dto.request.CharAnalysisRequest;
import com.bootcamp19.analyzer.dto.response.CharAnalysisResponse;
import com.bootcamp19.analyzer.entity.CharAnalysis;
import com.bootcamp19.analyzer.exception.AlreadyExistExeption;
import com.bootcamp19.analyzer.exception.AnalysisNotFoundException;
import com.bootcamp19.analyzer.repository.CharAnalysisRepository;
import com.bootcamp19.analyzer.service.CharAnalysisService;
import com.bootcamp19.analyzer.util.CharCounter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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

    @Override
    public CharAnalysisResponse create(CharAnalysisRequest request) {
        String kalimat = request.getKalimat().trim();
//        CharAnalysis data = charAnalysisRepository.findByKalimat(request.getKalimat());
        if(charAnalysisRepository.existsByKalimatIgnoreCase(kalimat)){
            throw new AlreadyExistExeption(request.getKalimat());
        }
        CharCounter counter = new CharCounter(request.getKalimat());

        CharAnalysis entity = new CharAnalysis();
        entity.setKalimat(counter.getKalimat());
        entity.setTotalHuruf(counter.getTotalHuruf());
        entity.setCreatedAt(LocalDateTime.now());

        CharAnalysis saved = charAnalysisRepository.save(entity);
        return CharAnalysisResponse.from(saved, counter.toMap());
    }

    @Override
    public CharAnalysisResponse getById(Long id) {
        CharAnalysis data =  charAnalysisRepository.findById(id)
                .orElseThrow( () -> new AnalysisNotFoundException(id));
        return toResponse(data);
    }

    @Override
    public List<CharAnalysisResponse> findByMinimumTotalLetters(int min) {
        return toResponses(charAnalysisRepository.findByMinimumTotalLetters(min));
    }

    @Override
    public List<CharAnalysisResponse> findLatest() {
        return toResponses(charAnalysisRepository.findLatest());
    }

    private CharAnalysisResponse toResponse(CharAnalysis data){
        CharCounter counter = new CharCounter(data.getKalimat());

        return CharAnalysisResponse.from(data, counter.toMap());
    }

    private List<CharAnalysisResponse> toResponses(List<CharAnalysis> entities){
        List<CharAnalysisResponse> responses = new ArrayList<>();
        for(CharAnalysis entity: entities){
            responses.add(toResponse(entity));
        }
        return responses;
    }
}
