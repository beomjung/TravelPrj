#지역 관광정보 조회
import requests
import json

def location_Travel(t_type, t_arr1, t_arr2, b_type, m_type, s_type):
    url = 'http://api.visitkorea.or.kr/openapi/service/rest/KorService/areaBasedList'
    apiKey = "MH5eq4rf03BY2MEb3ITxAZ9zg6KKHZUX1DJH/ud0+wS9I1anpXtyG8TZ6JOL/Rlv+xMZjQWPyh0BB7MwIFZEqA=="
    params = {'serviceKey': apiKey,
              'pageNo': '1', 'numOfRows': '100',
              'MobileApp': 'AppTest', 'MobileOS': 'ETC',
              'arrange': 'P', 'cat1': b_type,
              'contentTypeId': t_type,
              'areaCode': t_arr1, 'sigunguCode': t_arr2,
              'cat2': m_type, 'cat3': s_type, 'listYN': 'Y', 'modifiedtime': '', '_type': 'json'}

    response = requests.get(url, params=params)
    res2 = json.loads(response.text)["response"]["body"]["items"]["item"]
    print(res2)
    return res2