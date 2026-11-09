package com.bootcamp19.analyzer.controller;

//import com.bootcamp19.analyzer.util.CharCounter;
import com.bootcamp19.analyzer.dto.response.BaseResponse;
import com.bootcamp19.analyzer.dto.response.CharAnalysisResponse;
import com.bootcamp19.analyzer.repository.CharAnalysisRepository;
import com.bootcamp19.analyzer.service.CharAnalysisService;
//import com.bootcamp19.analyzer.service.CharCounter;
import com.bootcamp19.analyzer.util.CharCounter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

//kalau pakai @controller responsenya html di methodnya harus ada response body biair jadi json
//maka dari itu
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AnalyzerController {

    private final CharAnalysisService charAnalysisService;

//    public AnalyzerController(CharAnalysisService charAnalysisService) {
//        this.charAnalysisService = charAnalysisService;
//    }

    @GetMapping("/hello")
    public String hello(){
        return "Hello, Putra";
    }

    @PostMapping("/analyzer")
    public Map<Character, Integer> analyze(
            @RequestBody Map<String, String> body
    ){
        CharCounter counter =
                new CharCounter(body.get("kalimat"));
        Map<Character, Integer> hasil =
                new TreeMap<Character, Integer>();

        for(char c = 'a'; c<= 'z'; c++){
            int jumlah = counter.getJumlah(c);

            if(jumlah > 0){
                hasil.put(c, jumlah);
            }
        }

        return hasil;

    }

    @GetMapping("/find")
    public BaseResponse<List<CharAnalysisResponse>> cari(@RequestParam(required = false) String kalimat) {
        return BaseResponse.ok(charAnalysisService.cari(kalimat));
    }
//    public BaseResponse<ListCharAnalysisResponse> cari (@RequestParam(required = false) String kalimat){
//        return BaseResponse.ok()
//    }


}
