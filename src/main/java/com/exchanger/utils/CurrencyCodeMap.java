package com.exchanger.utils;

import java.util.HashMap;
import java.util.Map;

public class CurrencyCodeMap {
    /**
     * 통화 코드와 국가명을 매핑한 Map을 생성하여 반환합니다.
     *
     * @return 생성된 통화 코드 맵
     */
    public static Map<String, String> getCodeMap() {
        // HashMap을 사용하여 통화 코드와 국가명을 매핑한 Map을 생성
        Map<String, String> codeMap = new HashMap<>();

        // 각 통화 코드와 국가명을 put 메서드를 사용하여 매핑
        codeMap.put("AED", "아랍에미리트 디르함");
        codeMap.put("AUD", "호주 달러");
        codeMap.put("BHD", "바레인 디나르");
        codeMap.put("BND", "브루나이 달러");
        codeMap.put("CAD", "캐나다 달러");
        codeMap.put("CHF", "스위스 프랑");
        codeMap.put("CNH", "위안화");
        codeMap.put("DKK", "덴마아크 크로네");
        codeMap.put("EUR", "유로");
        codeMap.put("GBP", "영국 파운드");
        codeMap.put("HKD", "홍콩 달러");
        codeMap.put("IDR", "인도네시아 루피아");
        codeMap.put("JPY", "일본 엔");
        codeMap.put("KRW", "한국 원");
        codeMap.put("KWD", "쿠웨이트 디나르");
        codeMap.put("MYR", "말레이지아 링기트");
        codeMap.put("NOK", "노르웨이 크로네");
        codeMap.put("NZD", "뉴질랜드 달러");
        codeMap.put("SAR", "사우디 리얄");
        codeMap.put("SEK", "스웨덴 크로나");
        codeMap.put("SGD", "싱가포르 달러");
        codeMap.put("THB", "태국 바트");
        codeMap.put("USD", "미국 달러");

        return codeMap;
    }
}
