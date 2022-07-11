import json
import pickle
import tensorflow as tensorflow
import re
from konlpy.tag import Okt
from tensorflow.keras.preprocessing.sequence import pad_sequences


def nlpData_result(data):
  data = json.loads(data)

  print("tensor 함수 시작! ", data)
  print("tensor 데이터 타입 ", type(data))

  # return 값 변수 만들기
  res_dict = []

  # 학습모델 로딩하기
  loaded_model = tensorflow.keras.models.load_model("model/naver_movie.h5")

  stopwords = ['의', '가', '이', '은', '들', '는', '좀', '잘', '걍', '과', '도', '를', '으로', '자', '에', '와', '한', '하다']

  with open("model/naver_movie_tokenizer.pickle", "rb") as handle:
    tokenizer = pickle.load(handle)

  okt = Okt()

  max_len = 30

  # 불러온 데이터의 크기
  data_size = len(data)
  for i in range(data_size):
    data2 = data[i]
    test = data[i].get("board_post_content")

    new_sentence = re.sub(r'[^ㄱ-ㅎㅏ-ㅣ가-힣 ]', '', test)
    new_sentence = okt.morphs(new_sentence, stem=True)  # 토큰화
    new_sentence = [word for word in new_sentence if not word in stopwords]  # 불용어 제거
    encoded = tokenizer.texts_to_sequences([new_sentence])  # 정수 인코딩
    pad_new = pad_sequences(encoded, maxlen=max_len)  # 패딩
    score = float(loaded_model.predict(pad_new))  # 예측
    if (score > 0.5):
      print("{:.2f}% 확률로 긍정 리뷰입니다.\n".format(score * 100))
      res1 = "1"
      data2["nlp_res"] = res1
    else:
      print("{:.2f}% 확률로 부정 리뷰입니다.\n".format((1 - score) * 100))
      res2 = "0"
      data2["nlp_res"] = res2

    res_dict.append(data2)

  return res_dict





