import requests
import json

def send_kakaoMessage(content):
    # 리눅스 배포 시 경로 변경해야함
    with open("C:\sin\kakao_code.json","r") as fp:
        tokens = json.load(fp)

    # print(tokens)
    # print(tokens["access_token"])

    friend_url = "https://kapi.kakao.com/v1/api/talk/friends"

    # GET /v1/api/talk/friends HTTP/1.1
    # Host: kapi.kakao.com
    # Authorization: Bearer {ACCESS_TOKEN}

    headers={"Authorization" : "Bearer " + tokens["access_token"]}

    result = json.loads(requests.get(friend_url, headers=headers).text)

    print(type(result))
    print("=============================================")
    print(result)
    print("=============================================")
    friends_list = result.get("elements")
    print(friends_list)
    # print(type(friends_list))
    print("=============================================")
    print(friends_list[0].get("uuid"))
    friend_id = friends_list[0].get("uuid")
    print(friend_id)


    #친구목록 friend를 토큰에 담아 가져와서 elements안에 있는 친구정보를 가져옴
    send_url= "https://kapi.kakao.com/v1/api/talk/friends/message/default/send"
    data={
        'receiver_uuids': '["{}"]'.format(friend_id),
        "template_object": json.dumps({
            "object_type": "text",
            "text":content,
            "link":{
            },
            "button_title": "확인"
        })
    }
    response = requests.post(send_url, headers=headers, data=data)
    print(response.text)
    res_response = json.loads(response.text)
    send_res = "success"
    fail_res = "fail"
    if "successful_receiver_uuids" in res_response.keys():
        return send_res
    else :
        return fail_res
