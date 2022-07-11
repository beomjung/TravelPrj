# 국내 관광지 정보

import requests
import json


def localTravel_Info():

    # API 주소
    url = 'http://api.odcloud.kr/api/15003416/v1/uddi:a635e6c7-82cf-4714-b002-c7cf4cb20121_201609071527'

    # 내꺼 서비스 키
    StringKey = "MH5eq4rf03BY2MEb3ITxAZ9zg6KKHZUX1DJH/ud0+wS9I1anpXtyG8TZ6JOL/Rlv+xMZjQWPyh0BB7MwIFZEqA=="

    # url 전달 파라미터
    params = {'serviceKey': StringKey,
              'page': '1', 'perPage': '100',
              'returnType': 'json'
              }
    # 결과값 받고
    response = requests.get(url, params)
    # json.loads == type(dict[]) 로 변환
    jsonTest = json.loads(response.text)

    return jsonTest
