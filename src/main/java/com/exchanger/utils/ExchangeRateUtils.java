package com.exchanger.utils;

import java.io.*;
import java.math.*;
import java.net.*;
import java.text.*;
import java.util.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

// 출처 : https://velog.io/@minnczi/exchange-rate-open-api-java
public class ExchangeRateUtils {
	// API 연결 객체
	private static HttpURLConnection connection;
	// 기본 환율 설정
	private static BigDecimal defaultExchangeRate = BigDecimal.valueOf(1300);

	/**
	 * 지정된 통화 코드와 단위에 따라 환율을 조회합니다.
	 *
	 * @param targetCurrency 조회할 통화 코드
	 * @param unit           1이면 판매 할 때의 환율, 2이면 구매 할 때의 환율
	 * @return 조회된 환율 (BigDecimal 형태)
	 */
	public static BigDecimal getExchangeRate(String targetCurrency, int unit) {
		// JSON 데이터 파싱을 위한 객체
		JSONParser parser = new JSONParser();

		// 특정 통화 코드에 대한 예외 처리
		if (targetCurrency.equals("IDR")) {
			targetCurrency = "IDR(100)";
		} else if (targetCurrency.equals("JPY")) {
			targetCurrency = "JPY(100)";
		}

		// API 호출을 위한 인증 키, 조회 날짜, 데이터 타입, 그리고 결과로 받아올 환율 값
		String authKey = "Wjt0pUxzkxWKI1rZzXbnWGYhmqgzIO9C";
		String searchDate = new SimpleDateFormat("yyyyMMdd").format(new Date());
		String dataType = "AP01";
		BigDecimal exchangeRate = null;

		try {
			// API 호출을 위한 URL 설정
			URL url = new URL("https://www.koreaexim.go.kr/site/program/financial/exchangeJSON?authkey=" + authKey
					+ "&searchdate=" + searchDate + "&data=" + dataType);

			connection = (HttpURLConnection) url.openConnection();

			// API 호출을 위한 초기 설정
			connection.setRequestMethod("GET");
			connection.setConnectTimeout(5000);
			connection.setReadTimeout(5000);

			// API 응답 상태 코드 확인
			int status = connection.getResponseCode();

			// API 호출 실패 시 예외 처리
			if (status > 299) {
				throw new RuntimeException("API 호출 실패. 응답 코드: " + status);
			} else {
				// API 호출 성공 시 응답 내용을 읽어와서 JSON 파싱
				BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				String line;
				StringBuffer responseContent = new StringBuffer();

				// 서버 응답을 문자열로 변환
				while ((line = reader.readLine()) != null) {
					responseContent.append(line);
				}
				reader.close();

				// JSON 응답 파싱
				JSONArray exchangeRateInfoList = (JSONArray) parser.parse(responseContent.toString());

				// 입력받은 국가 코드에 해당하는 환율 정보 조회
				for (Object o : exchangeRateInfoList) {
					JSONObject exchangeRateInfo = (JSONObject) o;
					// 해당 JSON 객체의 "cur_unit" 값이 targetCurrency와 일치하는지 확인
					if (exchangeRateInfo.get("cur_unit").equals(targetCurrency)) {
						// 송금 받을 때
						if (unit == 1) {
							NumberFormat format = NumberFormat.getInstance(Locale.getDefault());
							// ttb 값은 문자열 형태일 수 있으므로, NumberFormat을 사용하여 쉼표가 포함된 문자열을 double로 파싱
							exchangeRate = new BigDecimal(
									format.parse(exchangeRateInfo.get("ttb").toString()).doubleValue());
							// 송금 할 때
						} else if (unit == 2) {
							NumberFormat format = NumberFormat.getInstance(Locale.getDefault());
							// tts 값은 문자열 형태일 수 있으므로, NumberFormat을 사용하여 쉼표가 포함된 문자열을 double로 파싱
							exchangeRate = new BigDecimal(
									format.parse(exchangeRateInfo.get("tts").toString()).doubleValue());
						}
						break;
					}
				}
			}

		} catch (MalformedURLException e) {
			// URL 형식이 잘못되었을 때 예외 처리
			System.out.println("[ERROR] URL형식이 잘못 되었습니다. 관리자에게 문의하세요.");
			throw new RuntimeException(e);
		} catch (IOException e) {
			// 입출력 예외 처리
			System.out.println("[ERROR] 입출력 오류가 발생했습니다. 다시 시도하세요.");
			throw new RuntimeException(e);
		} catch (ParseException | java.text.ParseException e) {
			// JSON 파싱 예외 처리
			System.out.println("[ERROR] JSON 파싱 중 오류가 발생했습니다. 서버 응답 형식을 확인하세요.");
			throw new RuntimeException(e);
		} finally {
			// 연결 종료
			connection.disconnect();
		}

		// 환율이 null인 경우 기본 환율 설정
		if (exchangeRate == null) {
			exchangeRate = defaultExchangeRate;
		}

		return exchangeRate;
	}
}