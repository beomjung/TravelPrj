import time

from selenium import webdriver
from selenium.webdriver.chrome.options import Options

Options = Options()
Options.add_argument("user-agent=Mozilla/5.0 (Windows NT 6.1; WOW64; Trident/7.0; rv:11.0) like Gecko")

driver = webdriver.Chrome("chromedriver.exe")
arr=["강서구","강동구","화곡동"]
pageLength = 11
totalReview = []
for search in arr:
    for pageNum in range(1, pageLength):
        url = "https://www.mangoplate.com/search/?keyword=" + str(search) + "&page=" + str(pageNum)
        print(url)
        #url 열고 들어가기
        driver.get(url)
        # 이상한 배너 떠너 body 한번 누르고
        driver.find_element_by_tag_name('body').click()
        #다시 url 들어가면 배너 사라짐
        driver.get(url)
        driver.implicitly_wait(5)
        # 맛집 선택해서 들어가기 (20개 고정)
        for titleLi in range(1, 11):
            for titlediv in range(1,3):
                clickNum = 0;
                reviewTextIdx = 1
                # 페이지 들어가서 li 있는거 클릭
                driver.find_element_by_xpath("/html/body/main/article/div[2]/div/div/section/div[3]/ul/li["+str(titleLi)+ "]/div[" + str(titlediv)+"]").click()
                while(True):
                    try:
                        #들어가서 더보기 버튼 클릭
                        driver.implicitly_wait(5)
                        driver.find_element_by_xpath("/html/body/main/article/div[1]/div[1]/div/section[3]/div[2]").click()
                        clickNum += 1
                        time.sleep(3)
                        if clickNum == 15 :
                            break
                    except:
                        break
                while True:
                    try:
                        # 사용자 text 긁어 오기
                        reviewText = driver.find_element_by_xpath("/html/body/main/article/div[1]/div[1]/div/section[3]/ul/li["+str(reviewTextIdx)+"]/a/div[2]/div/p").text
                        totalReview.append(reviewText)
                        print(reviewText)
                        reviewTextIdx += 1
                        print("---------------" + str(reviewTextIdx) + "---------------")

                    except:
                        driver.back()
                        break;





