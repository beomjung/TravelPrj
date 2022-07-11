import requests
import json

url = "https://kauth.kakao.com/oauth/token" #url 호출을 통한 토큰 얻기
rest_api_key = 'cbfb7720218e771c67e74780f1e85d81' #카카오톡 rest_api_key
redirect_uri = 'http://localhost:10000' # APP에서 등록한 redirect_url
authorize_code = 'RzR7Lyroc2oLjvOabKZAMXWgTp4kfVfHVXz67AbDJ7L2Oz9H-YNZe8Z2pqBMHLlAKHMpAwo9c5sAAAGBix0cJw' #web에서 가져온 tokens값을 가져와서 넣어줌

#요청시 필수 데이터
data = {
        'grant_type':'authorization_code',
        'client_id':rest_api_key,
        'redirect_uri':redirect_uri,
        'code': authorize_code,
    }
response = requests.post(url, data=data)
tokens = response.json()
print(tokens)
#json형식의 필수 데이터들을 저장함
with open(r"C:\sin\kakao_code.json","w") as fp:
    json.dump(tokens, fp)