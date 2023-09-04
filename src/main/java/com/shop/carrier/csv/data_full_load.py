import pymysql
import requests
import pandas as pd
import re
import sqlalchemy

# 각 csv 데이터 합체
## 각 데이터 컬럼에 맞게 수정
# t["용량"],t["크기"] = t["크기"],t["용량"]
# one = re_col(one)
# t = re_col(t)
# s = re_col(s)
# f = re_col(f)
# fi = re_col(fi)
# se = re_col(se)
# 크기의 값 별로 x를 제거 하고 그 부분에 *처리
# one['크기(cm)'] = one['크기(cm)'].astype(str).apply(lambda x: re.sub(r'[x]', '*', x))
# one['크기(cm)'] = one['크기(cm)'].apply(lambda x: re.sub(r'[mm]', '', x))
# one['크기(cm)'] = one["크기(cm)"].apply(renum)
# f['크기(cm)'] = f['크기(cm)'].astype(str).apply(lambda x: re.sub(r'[x]', '*', x))
# f['크기(cm)'] = f['크기(cm)'].apply(lambda x: re.sub(r'[mm]', '', x))
# f['크기(cm)'] = f['크기(cm)'].apply(lambda x: re.sub(r'[nan]', '', x))
# f['크기(cm)'] = f["크기(cm)"].apply(renum_1)
#
# df = pd.concat([t,f,s,fi,se,one],axis=0)
df = pd.read_csv(r'C:\Users\Playdata\Desktop\냉장고들/데이터종합(최종).csv',encoding='utf-8')

# 데이터 전처리 가정
# 각 컬럼 이름바꾸기
def re_col(df):
    df = df.rename(columns={
        '브랜드': '브랜드',
        '상품명':"제품이름",
        '가격': "가격(원)",
        '용량': '용량(L)',
        '크기': '크기(cm)',
        '전력소비': '전력소비등급(등급)',
        '문': '문 개수(개수)',
        "이미지" : "이미지"
    })
    return df
# 크기별 각 * 값들을 조인하여 합치기
def renum(expression):
    numbers = expression.split('*')
    converted_numbers = [f'{int(num) // 10}' for num in numbers]
    return '*'.join(converted_numbers)

# 철력했을경우 각 실수들의 정수 처리
def renum_1(expression):
    numbers = expression.split('*')
    converted_numbers = []
    for num in numbers:
        if num:  # 빈 문자열이 아닌 경우에만 처리
            converted_numbers.append(f'{int(float(num)//10)}')
    return '*'.join(converted_numbers)
# 각 텍스트의 글자들을 빈값으로 처리
def replace_characters(text):
    return re.sub(r'[가-흫]', '', text)
# 용량의 밸류중 L이 포함된 것을 제거
def add_L(value):
    if value is None or "." in value:
        return value
    elif value.endswith("L"):
        return value
    else:
        return value
# 전력소비등급 중 등급을 제거
def rank(value):
    if value is None or "." in value:
        return value
    elif value.endswith("등급"):
        return value
    else:
        return value
# 문의 개수 중 도어의 단어를 없엠
def doors(value):
    if value is None or "." in value:
        return value
    elif value.endswith("도어"):
        return value
    else:
        return value
# 필요없는 값들 제거
def clean_strings(value):
    if pd.isna(value):
        return value
    cleaned_value = re.sub(r'[()\[\]Lcm₩원]', '', value)
    cleaned_value = re.sub(r'[a-zA-Z]', '', cleaned_value)
    cleaned_value = re.sub(r'[도어nan]', '', cleaned_value)
    cleaned_value = re.sub(r'[x]', '*', cleaned_value)
    cleaned_value = re.sub(r'[nan]', '', cleaned_value)
    return cleaned_value

def clean(df):
    df = df.copy()
    df.fillna("None", inplace=True)
    return df
# 값 변형
def convert(value):
    cleaned_value = ''.join(c for c in str(value) if c.isdigit() or c == '.')
    try:
        return float(cleaned_value)
    except ValueError:
        raise TypeError(f"Could not convert {value} to numeric")
# 각 가구별 값들을 정의
# 0~200 1가구
# 200 ~ 500 2가구
# 500이상 4가구
def peple(volume):
    if 0 < volume < 200:
        return "1가구"
    elif 200 <= volume <= 500:
        return "2가구"
    elif volume == 0:
        return "0"
    else:
        return "4가구"
url = "https://www.enuri.com/wide/api/listGoods.jsp"
headers = {
    'User-Agent': 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/116.0.0.0 Safari/537.36',
    'Referer':"https://www.enuri.com/Index.jsp"}

payload = {
        "from": "list",
        "device": "pc",
        "category": "0602",
        "tab": "0",
        "isDelivery": "N",
        "isRental": "N",
        "pageNum": 1,
        "pageGap": "30",
        "sort": "1",
        "factory": "",
        "factory_code": "",
        "brand": "%EC%82%BC%EC%84%B1%EC%A0%84%EC%9E%90",
        "brand_code": "6774539740966188070",
        "bf": "",
        "shopcode": "",
        "keyword": "",
        "in_keyword": "",
        "s_price": "0",
        "e_price": "0",
        "spec": "",
        "spec_name": "",
        "color": "",
        "isReSearch": "N",
        "isTest": "N",
        "prtmodelno": "",
        "isMakeshop": "Y",
        "discount": "",
        "bbsscore": "",
        "unit": "",
    }

all_df = pd.DataFrame(d)
group_model_list = all_df["groupModel"]
nested_list = [item["list"] for item in group_model_list] # group_model_list안에 list들을 불러 들임
flat_list = [item for sublist in nested_list for item in sublist] #리스트 안에 리스트를 다시 불러들임
df = pd.DataFrame(flat_list)
text_list = all_df["strSpec1"] # strSpec1 값들을 전처리하기 위한 값 설정
no_space_list = [text.replace("$TAG$_188285_18_", "") for text in text_list]
# 리스트안에 필요 없는 값들을 순차 적으로 세거
all_df["strSpec1_no_space"] = no_space_list
all_df['strSpec1_no_space'] = all_df['strSpec1_no_space'].str.replace('냉장고$END$/$TAG$_213018_2_','', regex=False)
all_df['strSpec1_no_space'] = all_df['strSpec1_no_space'].str.replace('냉장고','', regex=False)
all_df['strSpec1_no_space'] = all_df['strSpec1_no_space'].str.replace('업소용냉동고/냉동전용/스탠드형/$TAG$_213018_4_/','', regex=False)
all_df['strSpec1_no_space'] = all_df['strSpec1_no_space'].str.replace('업소용/냉장전용/스탠드형/$TAG$_213018_2/','', regex=False)
all_df['strSpec1_no_space'] = all_df['strSpec1_no_space'].str.replace('$TAG$_188285_12_소형냉장전용/','', regex=False)
all_df['strSpec1_no_space'] = all_df['strSpec1_no_space'].str.replace('일반형/$TAG$_188442_0_용량$END$:','', regex=False)
all_df['strSpec1_no_space'] = all_df['strSpec1_no_space'].str.replace('$TAG$','', regex=False)
all_df['strSpec1_no_space'] = all_df['strSpec1_no_space'].str.replace('$END$','', regex=False)
all_df['strSpec1_no_space'] = all_df['strSpec1_no_space'].str.replace('양문형/얼음형태:큐브/$TAG$_188442_0_용량$END$:','', regex=False)
all_df['strSpec1_no_space'] = all_df['strSpec1_no_space'].str.replace('양문형/','', regex=False)
all_df['strSpec1_no_space'] = all_df['strSpec1_no_space'].str.replace('얼음형태:큐브/','', regex=False)
all_df['strSpec1_no_space'] = all_df['strSpec1_no_space'].str.replace('_188417_0_에너지효율:','', regex=False)
# 필요없는 값들이 제거 된 상태에서 정규식을 사용하여 필요한 부분을 추출 함.
def extract_info(s):
    door = re.search('(\d+도어)', s)
    volume = re.search('(\d+L)', s)
    grade = re.search('(\d+등급)', s)

    door = door.group(1) if door else None
    volume = volume.group(1) if volume else None
    grade = grade.group(1) if grade else None

    return pd.Series([door, volume, grade])
# 새로운 컬럼 생성
# 문, 전력소비, 크기를 추출하여 각 데이터 프래임에 넣음
new_one = pd.DataFrame()
new_one[['Grade','Volume','Door']] = all_df['strSpec1_no_space'].apply(extract_info)
# strSpec2의 각 데이터 전처리 과정
all_df["strSpec2"] = all_df["strSpec2"].str[-22:]
all_df['strSpec2'] = all_df['strSpec2'].str.replace(':','', regex=False)
all_df['strSpec2'] = all_df['strSpec2'].str.replace('END$','', regex=False)
all_df['strSpec2'] = all_df['strSpec2'].str.replace('ND$','', regex=False)
all_df['strSpec2'] = all_df['strSpec2'].str.replace('D$','', regex=False)
all_df['strSpec2'] = all_df['strSpec2'].str.replace('$','', regex=False)
new_two = pd.DataFrame({"Size": all_df["strSpec2"]})
# 추출한 값들을 하나의 데이터 프레임으로 함침
want = pd.concat([all_df[["strModelName", "strBrand"]], df[["strPrice"]],
                  new_one,new_two,df["strSmallImg"]], axis=1)
# 각 이미지의 필요한 부분을 띄위기 위해 부가적인 싸이트를 이어 붙임
df.loc[df['brand'] == 'SMEG', 'image'] = "https://smegkorea.com/" + df['image']
print(df.info())
# 각 어플라이를 위한 각 필요한 컬럼을 담음
columns_to_clean = ["크기(cm)", "전력소비등급(등급)", "용량(L)", "문 개수(개수)", "가격(원)"]
# 각 어플라이를 사용하여 문자열로 전환
for column in columns_to_clean:
    df[column] = df[column].astype(str).apply(clean_strings)

def extract_info(s):
    door = re.search('(\d+도어)', s)
    volume = re.search('(\d+L)', s)
    grade = re.search('(\d+등급)', s)

    door = door.group(1) if door else None
    volume = volume.group(1) if volume else None
    grade = grade.group(1) if grade else None

    return pd.Series([door, volume, grade])

test_df = clean(df)
# 용량 사용인원 등을 실수를 정수로 전환
df["용량(L)"] = df["용량(L)"].apply(lambda x: 0 if x == '' else int(round(float(x))))
df['사용인원'] = df['용량(L)'].apply(peple)
df['사용인원'] = df['사용인원'].replace('0', '')
df = df.reset_index(drop=True)

# df.to_csv(r"C:\Users\Playdata\Desktop\냉장고들/냉장고/데이터종합(최종)_1.csv",index=False, encoding='utf-8')
# 전처리된 데이터를 데이터페이스 툴에 넣음
# myspl
db = pymysql.connect(host="localhost",
                      port=3308,
                      user="root",
                      passwd="encore!#",
                      db= "no")
cur = db.cursor()

user = 'root'
password = 'encore!#'
host='3.38.84.65' # loopback # localhost
port = '3308'
database = 'Carrier'
engine = sqlalchemy.create_engine(f"mysql://{user}:{password}@{host}:{port}/{database}") # MYSQL오류 발생 => # sqlalchemy 의존성 패키지 설치

# 읽어올 CSV 파일 경로 지정
csv_file_path = f"main_.csv"

df = pd.read_csv(csv_file_path)

df.to_sql(name='carrier', con=engine, if_exists='append', index=False)

print("CSV 파일을 데이터베이스에 저장 완료!")