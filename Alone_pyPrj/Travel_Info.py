import requests
import json
#공통정보 조회
def Travel_Detail_info(contentId, contentTypeId):
    url = 'http://api.visitkorea.or.kr/openapi/service/rest/KorService/detailCommon'
    apiKey = "MH5eq4rf03BY2MEb3ITxAZ9zg6KKHZUX1DJH/ud0+wS9I1anpXtyG8TZ6JOL/Rlv+xMZjQWPyh0BB7MwIFZEqA=="
    #contentID, contentTypeID 두개만 매개변수로 던지기
    params ={'serviceKey' : apiKey, 'numOfRows' : '10', 'pageNo' : '1', 'MobileOS' : 'ETC',
             'MobileApp' : 'AppTest', 'contentId' : contentId, 'contentTypeId' : contentTypeId,
             'defaultYN' : 'Y', 'firstImageYN' : 'Y', 'areacodeYN' : 'Y', 'catcodeYN' : 'Y',
             'addrinfoYN' : 'Y', 'mapinfoYN' : 'Y', 'overviewYN' : 'Y' ,'_type' : 'json'}

    response = requests.get(url, params=params)
    res1 = json.loads(response.text)["response"]["body"]["items"]["item"]
    print(res1)
    return res1