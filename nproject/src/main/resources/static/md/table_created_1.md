
# table 생성 규칙

소문자를 사용한다.
테이블명 prefix는 tb_ 로 시작하여 시스템 구분 (tb_user_, tb_mart_, tb_hist_)
약어 남발 금지 (usr, plnr 등)
seq, no 사용 금지 → _id 사용
YN 컬럼은 boolean 고려

# 날짜 컬럼 참조

생성일	created_at
수정일	updated_at
삭제일	deleted_at
최종로그인	last_login_at
승인일	approved_at