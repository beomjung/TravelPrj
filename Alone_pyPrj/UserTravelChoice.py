import requests
import json

def User_Choice(contenttypeId, keyWord, areaCode):
    url = 'http://api.visitkorea.or.kr/openapi/service/rest/KorService/searchKeyword'
    apiKey = "MH5eq4rf03BY2MEb3ITxAZ9zg6KKHZUX1DJH/ud0+wS9I1anpXtyG8TZ6JOL/Rlv+xMZjQWPyh0BB7MwIFZEqA=="

    params ={'serviceKey' : apiKey, 'MobileApp' : 'AppTest', 'MobileOS' : 'ETC',
             'pageNo' : '1', 'numOfRows' : '20', 'listYN' : 'Y', 'arrange' : 'B',
             'contentTypeId' : contenttypeId, 'areaCode' : areaCode, 'sigunguCode' : '',
             'cat1' : '', 'cat2' : '', 'cat3' : '', 'keyword' : keyWord, "_type" : "json"}

    response = requests.get(url, params=params)
    res2 = json.loads(response.text)["response"]["body"]["items"]["item"]
    print(type(res2))
    print(res2)
    print("----------------------------------------------------------------------------------------")
    if str(type(res2)) == "<class 'list'>":
        res3 = res2[0]
        if "firstimage" in res3.keys():
            return res3
        else :
            res3 = res2[1]
            return res3

    return res2

def User_Choice2(areaCode, areaCode2, keyWord):
    url = 'http://api.visitkorea.or.kr/openapi/service/rest/KorService/searchKeyword'
    apiKey = "MH5eq4rf03BY2MEb3ITxAZ9zg6KKHZUX1DJH/ud0+wS9I1anpXtyG8TZ6JOL/Rlv+xMZjQWPyh0BB7MwIFZEqA=="

    params ={'serviceKey' : apiKey, 'MobileApp' : 'AppTest', 'MobileOS' : 'ETC',
             'pageNo' : '1', 'numOfRows' : '20', 'listYN' : 'Y', 'arrange' : 'B',
             'contentTypeId' : '12', 'areaCode' : areaCode, 'sigunguCode' : areaCode2,
             'cat1' : '', 'cat2' : '', 'cat3' : '', 'keyword' : keyWord, "_type" : "json"}

    response = requests.get(url, params=params)
    res2 = json.loads(response.text)["response"]["body"]["items"]["item"]
    print(type(res2))
    print(res2)
    print("----------------------------------------------------------------------------------------")
    if str(type(res2)) == "<class 'list'>":
        res3 = res2[0]
        if "firstimage" in res3.keys():
            return res3
        else :
            res3 = res2[1]
            return res3

    return res2