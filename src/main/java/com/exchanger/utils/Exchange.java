package com.exchanger.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Exchange {
    /**
     * 지정된 환율을 사용하여 해당 통화로 판매할 때 얻을 수 있는 금액을 계산합니다.
     *
     * @param code 판매할 통화 코드
     * @param i    판매하려는 금액 (KRW 기준)
     * @return 해당 통화로 얻을 수 있는 금액 (판매 환율을 적용한 결과)
     */
    public String sellCurrency(String code, int i) {
        BigDecimal amountInCurrency = null;
        // 소문자를 입력받아도 자동으로 대문자로 변환
        String currencyCode = code.toUpperCase();

        // 판매 환율 조회
        BigDecimal exchangeRate = ExchangeRateUtils.getExchangeRate(currencyCode, 1);

        // BigDecimal 클래스 자체에서 제공하는 소수점 이하 자리수 제어 방법을 사용
        if (exchangeRate != null) {
            exchangeRate = exchangeRate.setScale(2, RoundingMode.HALF_UP);
        }

        if (exchangeRate != null) {
            BigDecimal amountIn = new BigDecimal(i);
            amountInCurrency = amountIn.multiply(exchangeRate);
        } else {
            return null;
        }
        return amountInCurrency.toString();
    }

    /**
     * 지정된 환율을 사용하여 해당 통화를 구매할 때 필요한 금액을 계산합니다.
     *
     * @param code 구매할 통화 코드
     * @param i    구매하려는 금액 (해당 통화 기준)
     * @return 해당 통화를 구매하기 위해 필요한 금액 (구매 환율을 적용한 결과)
     */
    public String buyCurrency(String code, int i) {
        BigDecimal amountInCurrency = null;
        // 소문자를 입력받아도 자동으로 대문자로 변환
        String currencyCode = code.toUpperCase();

        // 구매 환율 조회
        BigDecimal exchangeRate = ExchangeRateUtils.getExchangeRate(currencyCode, 2);

        // BigDecimal 클래스 자체에서 제공하는 소수점 이하 자리수 제어 방법을 사용
        if (exchangeRate != null) {
            exchangeRate = exchangeRate.setScale(2, RoundingMode.HALF_UP);
        }

        if (exchangeRate != null) {
            BigDecimal amountInKRW = new BigDecimal(i);
            amountInCurrency = amountInKRW.divide(exchangeRate, 2, RoundingMode.HALF_UP);
        } else {
            return null;
        }
        return amountInCurrency.toString();
    }

    /**
     * CheckCurrencyCode 클래스를 사용하여 통화 코드 정보를 조회합니다.
     */
    public void check() {
        CheckCurrencyCode checker = new CheckCurrencyCode();
        checker.checkCurrencyCode();
    }
}
