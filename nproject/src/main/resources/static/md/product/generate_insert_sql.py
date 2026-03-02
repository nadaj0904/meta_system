import pandas as pd
import uuid

file_path = r"c:\Dev_antigravity_git\meta_system\nproject\src\main\resources\static\md\product\생명보험_종신보험_보험사_상품_판매채널_판매일자2.xlsx"
df = pd.read_excel(file_path)

# Ensure NaN is replaced with None or handled
df = df.where(pd.notnull(df), None)

sql_statements = []
sql_statements.append("INSERT INTO tb_product_master (company_id, product_name, product_code, product_status_code, sales_channel, sale_start_date) VALUES")
values = []

for index, row in df.iterrows():
    company_name = str(row['보험사']).strip() if row['보험사'] else ''
    product_name = str(row['상품명']).strip() if row['상품명'] else ''
    sales_channel = str(row['판매채널']).strip() if row['판매채널'] else ''
    sale_date = row['판매일자']
    
    # Format date if possible
    if pd.notnull(sale_date):
        sale_date_str = sale_date.strftime('%Y-%m-%d')
    else:
        sale_date_str = 'NULL'
        
    product_code = f"PRD_{uuid.uuid4().hex[:8].upper()}"
    status_code = 'SALE_ING'
    
    # company_name matching involves finding the company_id, 
    # we can use a subquery for company_id based on company_name
    # But some company names in Excel might not exactly match tb_product_company.
    # We will use a subquery and hope it matches, or insert missing ones.
    # Wait, the user said "생명보험 데이터로 넣어주세요". So these are Life Insurance companies.
    
    company_subquery = f"(SELECT company_id FROM tb_product_company WHERE company_name LIKE '%{company_name}%' LIMIT 1)"
    
    safe_product_name = product_name.replace("'", "''")
    val_str = f"({company_subquery}, '{safe_product_name}', '{product_code}', '{status_code}', '{sales_channel}', "
    if sale_date_str == 'NULL':
        val_str += "NULL)"
    else:
        val_str += f"'{sale_date_str}')"
        
    values.append(val_str)

final_sql = sql_statements[0] + "\n" + ",\n".join(values) + ";"

with open(r"c:\Dev_antigravity_git\meta_system\nproject\src\main\resources\static\md\product\insert_products.sql", "w", encoding="utf-8") as f:
    f.write(final_sql)

print("Generated SQL successfully.")
