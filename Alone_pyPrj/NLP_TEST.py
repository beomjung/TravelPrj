import urllib3
import json
#구어
# openApiURL = "http://aiopen.etri.re.kr:8000/WiseNLU_spoken" # 호출 주소
# 언어 분석 기술(문어)
openApiURL = "http://aiopen.etri.re.kr:8000/WiseNLU" 
accessKey = "d7ddab77-ffee-4e15-8bd0-c14000ceb85e" # API key
analysisCode = "ner" # 형태소 분석 코드
# 구어
text = "자신 있게 말하고 싶어요" # 스프링에서 받은 문장
# 요청 파라미터 JSON 데이터 변환
requestJson = {
    "access_key": accessKey,
    "argument": {
        "text": text,
        "analysis_code": analysisCode
    }
}
#URL 호출 및 파라미터 전달
http = urllib3.PoolManager()
response = http.request(
    "POST",
    openApiURL,
    headers={"Content-Type": "application/json; charset=UTF-8"},
    body=json.dumps(requestJson)
)
print("[responseCode] " + str(response.status))
print("[responBody]")
print(str(response.data, "utf-8"))

accessKey = ""