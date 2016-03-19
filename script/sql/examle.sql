-- ユーザ情報を取得する
select  * from USERS WHERE NAME = ${NAME@CHAR};

-- 現在時刻取得
select date_format(now(), "%Y年%m月%d日 %h時%i分%s秒") from DUAL;