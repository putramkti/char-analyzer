package com.bootcamp19.analyzer.controller;

//import com.bootcamp19.analyzer.util.CharCounter;
import com.bootcamp19.analyzer.dto.request.CharAnalysisRequest;
import com.bootcamp19.analyzer.dto.response.BaseResponse;
import com.bootcamp19.analyzer.dto.response.CharAnalysisResponse;
import com.bootcamp19.analyzer.repository.CharAnalysisRepository;
import com.bootcamp19.analyzer.service.CharAnalysisService;
//import com.bootcamp19.analyzer.service.CharCounter;
import com.bootcamp19.analyzer.util.CharCounter;
import lombok.RequiredArgsConstructor;
//import org.slf4j.ILoggerFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Slf4j
@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AnalyzerController {

    private final CharAnalysisService charAnalysisService;

//    public AnalyzerController(CharAnalysisService charAnalysisService) {
//        this.charAnalysisService = charAnalysisService;
//    }

    // @GetMapping("/hello")
    // public String hello(){
    //     return "Hello, Putra";
    // }

    // @PostMapping("/analyzer")
    // public Map<Character, Integer> analyze(
    //         @RequestBody Map<String, String> body
    // ){
    //     CharCounter counter =
    //             new CharCounter(body.get("kalimat"));
    //     Map<Character, Integer> hasil =
    //             new TreeMap<Character, Integer>();

    //     for(char c = 'a'; c<= 'z'; c++){
    //         int jumlah = counter.getJumlah(c);

    //         if(jumlah > 0){
    //             hasil.put(c, jumlah);
    //         }
    //     }

    //     return hasil;

    // }

    @GetMapping
    public BaseResponse<List<CharAnalysisResponse>> cari(@RequestParam(required = false) String kalimat) {
        return BaseResponse.ok(charAnalysisService.cari(kalimat));
    }

    @PostMapping
    public BaseResponse<CharAnalysisResponse> create(
            @RequestBody CharAnalysisRequest request,
            @RequestHeader(value = "X-Request-Id", required = false) String requestId){

        if (request == null || request.getKalimat() == null || request.getKalimat().isBlank()) {
            return BaseResponse.error("Kalimat tidak boleh kosong");
        }
        if (request.getKalimat().length() > 255) {
            return BaseResponse.error("Kalimat maksimal 255 karakter");
        }

        if (requestId != null && !requestId.isBlank()) {
            log.info("X-Request-Id: {}, kalimat: {}", requestId, request.getKalimat());
        }
        return BaseResponse.ok(charAnalysisService.create(request));
    }

    @GetMapping("/{id}")
    public BaseResponse<CharAnalysisResponse> getById(@PathVariable Long id){
        CharAnalysisResponse response = charAnalysisService.getById(id);
//        if(response == null){
//            return BaseResponse.error("Data dengan id " + id + " tidak ditemukan");
//
//        }
        return BaseResponse.ok(response);
    }

    @GetMapping("/minimal")
    public BaseResponse<List<CharAnalysisResponse>> getByMinimumTotalLetters(@RequestParam int min){
        return BaseResponse.ok(charAnalysisService.findByMinimumTotalLetters(min));
    }

    @GetMapping("/terbaru")
    public BaseResponse<List<CharAnalysisResponse>> getLatest(){
        return BaseResponse.ok(charAnalysisService.findLatest());
    }

}
