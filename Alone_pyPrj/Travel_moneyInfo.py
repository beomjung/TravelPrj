#소개 정보 조회
import requests
import json
def moneyInfo(contentId, contentTypeId):
    url = 'http://api.visitkorea.or.kr/openapi/service/rest/KorService/detailIntro'
    apiKey = "MH5eq4rf03BY2MEb3ITxAZ9zg6KKHZUX1DJH/ud0+wS9I1anpXtyG8TZ6JOL/Rlv+xMZjQWPyh0BB7MwIFZEqA=="
    params ={'serviceKey' : apiKey, 'numOfRows' : '10', 'pageNo' : '1', 'MobileOS' : 'ETC',
             'MobileApp' : 'AppTest',
             'contentId' : contentId, 'contentTypeId' : contentTypeId, '_type': 'json' }
    res_dict={}
    response = requests.get(url, params=params)
    res2 = json.loads(response.text)["response"]["body"]["items"]["item"]
    res2_size = len(res2)
    print(res2)
    if str(type(res2)) == "<class 'list'>":
        for i in range(res2_size):
            res_dict.update(res2[i])
        print(res_dict)
        return res_dict
    else:
        print(res2)
        return res2