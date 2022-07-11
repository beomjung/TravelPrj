import requests
import json

def keyword_travel(contenttypeId, keyWord):
    url = 'http://api.visitkorea.or.kr/openapi/service/rest/KorService/searchKeyword'
    apiKey = "MH5eq4rf03BY2MEb3ITxAZ9zg6KKHZUX1DJH/ud0+wS9I1anpXtyG8TZ6JOL/Rlv+xMZjQWPyh0BB7MwIFZEqA=="

    params ={'serviceKey' : apiKey, 'MobileApp' : 'AppTest', 'MobileOS' : 'ETC',
             'pageNo' : '1', 'numOfRows' : '20', 'listYN' : 'Y', 'arrange' : 'B',
             'contentTypeId' : contenttypeId, 'areaCode' : '', 'sigunguCode' : '',
             'cat1' : '', 'cat2' : '', 'cat3' : '', 'keyword' : keyWord, "_type" : "json"}

    response = requests.get(url, params=params)
    res2 = json.loads(response.text)["response"]["body"]["items"]["item"]
    print(res2)
    return res2
