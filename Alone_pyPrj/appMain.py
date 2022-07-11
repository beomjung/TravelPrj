# --coding: utf-8 --

from flask import Flask, request, Response  # 추가
from functools import wraps  # 추가
from Travel_main import location_Travel  # 지역기반 조회
from Travel_Info import Travel_Detail_info  # 공통정보 조회
from Travel_ReInfo import Travel_reInfo, travel_listReInfo  # 반복정보 조회
from Travel_moneyInfo import moneyInfo  # 소개정보 조회
from Result import nlpData_result
from Travel_keyWord import keyword_travel
from UserTravelChoice import User_Choice, User_Choice2
from kakao_friend_send import send_kakaoMessage
import json

application = Flask(__name__)


def as_json(f):  # 추가
    @wraps(f)
    def decorated_function(*args, **kwargs):
        res = f(*args, **kwargs)
        res = json.dumps(res, ensure_ascii=False).encode("UTF-8")
        return Response(res, content_type="application/json; charset-utf-8")

    return decorated_function


@application.route("/")
def hello():
    return "<h1> hello Pytrhon </h1>"


@application.route("/Travel_main", methods=['POST', 'GET'])
@as_json  # 추가
def location_travel():
    t_type = request.args.get("contentTypeId")
    t_arr1 = request.args.get("areaCode")
    t_arr2 = request.args.get("sigunguCode")
    b_type = request.args.get("cat1")
    m_type = request.args.get("cat2")
    s_type = request.args.get("cat3")
    print(t_type)
    print(t_arr1)
    print(t_arr2)
    print(b_type)
    print(m_type)
    print(s_type)
    location_travel_res = location_Travel(t_type, t_arr1, t_arr2, b_type, m_type, s_type)
    return location_travel_res


@application.route("/Travel_info", methods=['POST', 'GET'])  # 공통정보 조회
@as_json
def travel_info():
    print("공통정보 조회 시작!!")
    contentId = request.args.get("contentId")
    contentTypeId = request.args.get("contentTypeId")
    print(contentId)
    print(contentTypeId)
    travel_info_res = Travel_Detail_info(contentId, contentTypeId)
    print("공통정보 조회 종료!!")
    return travel_info_res


@application.route("/Travel_Reinfo", methods=['POST', 'GET'])  # 반복정보 조회
@as_json
def travel_ReInfo():
    print("반복정보 조회 시작!!")
    contentId = request.args.get("contentId")
    contentTypeId = request.args.get("contentTypeId")
    print(contentId)
    print(contentTypeId)
    travel_ReInfo_res = Travel_reInfo(contentId, contentTypeId)
    print("반복정보 조회 종료!!")
    return travel_ReInfo_res


@application.route("/Travel_listReinfo", methods=['POST', 'GET'])  # 반복정보 조회
@as_json
def travel_ReInfolist():
    print("반복정보 조회 시작!!")
    contentId = request.args.get("contentId")
    contentTypeId = request.args.get("contentTypeId")
    print(contentId)
    print(contentTypeId)
    travel_ReInfo_reslist = travel_listReInfo(contentId, contentTypeId)
    print("반복정보 조회 종료!!")
    return travel_ReInfo_reslist


@application.route("/Travel_moneyinfo", methods=['POST', 'GET'])  # 소개정보 조회
@as_json
def travel_MoneyInfo():
    print("소개정보 조회 시작!!")
    contentId = request.args.get("contentId")
    contentTypeId = request.args.get("contentTypeId")
    print(contentId)
    print(contentTypeId)
    travel_moneyInfo_res = moneyInfo(contentId, contentTypeId)
    print("소개정보 조회 종료!!")
    return travel_moneyInfo_res


@application.route("/nlp_jsonDataInfo", methods=['POST', 'GET'])  # 자연어 처리 데이터 결과 확인
@as_json
def nlp_jsonDataInfo():
    print("json 데이터 가져오기 시작!")
    data = request.args.get("data")
    print(type(data))
    nlp_result = nlpData_result(data)
    print(nlp_result)
    return nlp_result


@application.route("/keyword_travelInfo", methods=['POST', 'GET'])  # 키워드 검색 결과 조회
@as_json
def keyWord_travelInfo():
    print("키워드 검색 시작!")
    contenttypeId = request.args.get("contenttypeId")
    keyWord = request.args.get("keyword")
    print(contenttypeId)
    print(keyWord)
    keyWord_travelInfo_res = keyword_travel(contenttypeId, keyWord)
    print("키워드 검색 종료!")
    return keyWord_travelInfo_res


@application.route("/User_Choice2", methods=['POST', 'GET'])  # 키워드 검색 결과 조회
@as_json
def User_Choice22():
    print("키워드 검색 시작!")
    keyWord = request.args.get("keyword")
    areaCode = request.args.get("areaCode")
    areaCode2 = request.args.get("areaCode2")
    print(keyWord)
    keyWord_UserInfo_res = User_Choice2(areaCode, areaCode2, keyWord)
    print("키워드 검색 종료!")
    return keyWord_UserInfo_res


@application.route("/UserKeyWord_travelInfo", methods=['POST', 'GET'])  # 키워드 검색 결과 조회
@as_json
def user_keyWord():
    print("유저 키워드 검색 시작!")
    areaCode = request.args.get("areaCode")
    contenttypeId = request.args.get("contenttypeId")
    keyWord = request.args.get("keyword")
    print(contenttypeId)
    print(keyWord)
    keyWord_travelInfo_res = User_Choice(contenttypeId, keyWord, areaCode)
    print("유저 키워드 검색 종료!")
    return keyWord_travelInfo_res

@application.route('/kakaoFriend', methods=['POST', 'GET'])
def parsing1():
    print("start kakaoFriend")
    content  = request.args.get("content");
    print("자바에서 넘어온 그룹별 카카오 메시지 컨텐츠는 >>>>>>>>>>>>>>",content)
    res = send_kakaoMessage(content)
    print("카카오 메시지 발송 결과는>>>>>>>>>>>>>>>>", res);
    return res


if __name__ == "__main__":
    application.run(host="0.0.0.0", port=8000)

# @application.route("/localTravel_Info", methods=['POST', 'GET'])
# @as_json
# def local_Travel():
#     local_jsonResult = localTravel_Info()
#     return local_jsonResult
#
# @application.route("/koreaTravel_Standard", methods=['POST','GET'])
# @as_json
# def koreaTravel_basic():
#     koreaTravel_jsonResult = koreaTravel_Standard()
#     return koreaTravel_jsonResult
#
# @application.route("/big_local", methods=['POST','GET'])
# @as_json
# def koreaBig_local():
#     big_jsonResult = big_local()
#     return big_jsonResult
#
# @application.route("/small_local", methods=['POST','GET'])
# @as_json
# def koreaSmall_local():
#     small_josnResult = small_local()
#     return small_josnResult
#
# @application.route("/test", methods=['POST','GET'])
# @as_json
# def test():
#     small_josnResult = addr()
#     return small_josnResult
