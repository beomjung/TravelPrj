# 테스트용 NLP
from konlpy.tag import Okt

def nlp_tes(spring_sentence, study_type):
    sentence_res2 = [] # 자연어 처리 결과를 담을 변수
    for i in range(len(spring_sentence)):
        # spring에서 받은 학습 데이터 list에서 꺼내서 str로 변환
        voc = spring_sentence[i]
        # 형태소 분석을 위한 okt 라이브러리 객체 생성
        okt = Okt()
        # 형태소 분석 시작
        okt_res = okt.pos(voc)
        print(okt_res)
        # 학습 타입별 문제를 제공하기위한 반복
        for j in range(len(okt_res)):
            # 학습 타입과 같은 품사가 있을경우 처리 시작
            if study_type == okt_res[j][1]:
                # spring에서 받은 문장안에서 태깅된 품사 종류와 같은 단어를 변환
                voc = voc.replace(okt_res[j][0], 'a')
                # 변환된 문장과 정답을 dictionary 타입으로 변환
                dic_content = {'content': voc, 'blink_word': okt_res[j][0]}
                sentence_res2.append(dic_content) # spring으로 전달하기 위해 list 타입으로 변경
                break

    print(sentence_res2)


spring_sentence = []
spring_sentence.append("철수는 슬픕니다")
spring_sentence.append("철수는 행복합니다")
study_type = "Adjective"

nlp_tes(spring_sentence, study_type)
