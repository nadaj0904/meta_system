import pandas as pd
import sys

try:
    file_path = r"c:\Dev_antigravity_git\meta_system\nproject\src\main\resources\static\md\product\생명보험_종신보험_보험사_상품_판매채널_판매일자2.xlsx"
    df = pd.read_excel(file_path)
    print("Columns:", df.columns.tolist())
    for index, row in df.head().iterrows():
        print(row.to_dict())
    print("Total rows:", len(df))
except Exception as e:
    print(f"Error: {e}")
