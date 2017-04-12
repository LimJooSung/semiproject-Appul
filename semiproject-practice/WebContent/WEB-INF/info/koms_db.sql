-- drop table
drop table proposal_comment;
drop table qna_comment;
drop table inst_comment;
drop table proposal_board;
drop table qna_board;
drop table inst_board;
drop table selecting_presenter;
drop table selecting_group;
drop table member;

select * from member;

-- drop sequence
drop sequence id_seq;
drop sequence proposal_board_seq;
drop sequence qna_board_seq;
drop sequence inst_board_seq;
drop sequence proposal_comment_seq;
drop sequence qna_comment_seq;
drop sequence inst_comment_seq;
drop sequence mem_number_seq;

-- create sequence
create sequence proposal_board_seq;
create sequence qna_board_seq;
create sequence inst_board_seq;
create sequence proposal_comment_seq;
create sequence qna_comment_seq;
create sequence inst_comment_seq;
create sequence mem_number_seq;

-- create table
-- member 테이블
create table member(
   id               varchar2(100) primary key,
   password      varchar2(100) not null,
   mem_name      varchar2(100) not null,
   gender         varchar2(100) not null,
   birth_date    varchar2(100) not null,
   mem_type      varchar2(100) not null,
   getout		 char(1) default('N'),
   mem_number number not null
)
select * from member
select id,password,mem_name,gender,birth_date,mem_type,getout from member where id='java' and password='1234'and getout='N';
insert into member (id, password, mem_name, gender, birth_date, mem_type, mem_number) VALUES ('java', '1234', '강정호', '남', '1970-01-01', '일반학생', id_Seq.nextval);
insert into member (id, password, mem_name, gender, birth_date, mem_type, mem_number) 
values ('java', '1234', '강정호', '남', '1970-01-01', '일반학생', 1);
insert into member (id, password, mem_name, gender, birth_date, mem_type, mem_number) 
values ('spring', '1234', '김가린', '여', '1970-01-01', '일반학생', 1);
insert into member (id, password, mem_name, gender, birth_date, mem_type, mem_number) 
values ('tico', '1234', '서정우', '남', '1970-01-01', '강사', 1);

-- 발표자 선정 테이블
create table selecting_presenter(
	id		varchar2(100) primary key,
	cnt_presentation number default(0),
	constraint fk_id_presenter foreign key(id) references member(id)
)

-- 조 선정 테이블
create table selecting_group(
	id						varchar2(100) primary key,
	group_no 			number not null,
	selecting_date	date,	
	constraint fk_id_group foreign key(id) references member(id)
)

-- 건의사항 게시판 테이블
create table proposal_board(
   proposal_board_no      number primary key,
   id                           varchar2(100) not null,
   title                        varchar2(100) not null,
   content                     clob,
   time_posted               date,
   file_name                  varchar2(100),
   hit                        number default(0),
    secret               char(1) default('n'),    -- 'Y' or 'N'으로 쓸 예정(?)
   constraint fk_id_proposal_board foreign key(id) references member(id) ON DELETE CASCADE
)

-- 건의사항 게시판 댓글 테이블
create table proposal_comment(
   proposal_comment_no      number primary key,
   proposal_board_no         number not null,
   id 						varchar2(100) not null,
   name				   		varchar2(100) not null,
   time_posted                  date,
   content                        clob,
   parent              number,      -- number(?)
   constraint fk_proposal_board_no foreign key(proposal_board_no) references proposal_board(proposal_board_no) ON DELETE CASCADE
   ,constraint fk_id_proposal_comment foreign key(id) references member(id)
   )

-- QnA 게시판 테이블
create table qna_board(
   qna_board_no      number primary key,
   id                     varchar2(100) not null,
   title                  varchar2(100) not null,
   content               clob,
   time_posted         date,
   file_name            varchar2(100),
   hit                  number default(0),
    secret               char(1) default('n'),      -- 'Y' or 'N'으로 쓸 예정(?)
   constraint fk_id_qna_board foreign key(id) references member(id)
)

-- QnA 게시판 댓글 테이블
create table qna_comment(
   qna_comment_no      number primary key,
   qna_board_no         number not null,
   id                  varchar2(100) not null,
   name				   varchar2(100) not null,
   time_posted            date,
   content                  clob,
   parent              number default 0,      -- number(?)
   constraint fk_id_qna_comment foreign key(id) references member(id),
   constraint fk_qna_board_no foreign key(qna_board_no) references qna_board(qna_board_no) ON DELETE CASCADE
)

-- 강사 게시판 테이블
create table inst_board(
   inst_board_no      number primary key,
   id                     varchar2(100) not null,
   title                  varchar2(100) not null,
   content               clob,
   time_posted         date,
   file_name            varchar2(100),
   hit                  number default(0),
   avg_rating            binary_double default(5.0),
   constraint fk_id_inst_board foreign key(id) references member(id)
)

-- 강사 게시판 댓글 테이블
create table inst_comment(
   inst_comment_no      number primary key,
   inst_board_no         number not null,
   id                  varchar2(100) not null,
   name				   varchar2(100) not null,
   time_posted            date,
   content                  clob,
   parent              number default 0,      -- number(?)
   constraint fk_id_inst_comment foreign key(id) references member(id),
   constraint fk_inst_board_no foreign key(inst_board_no) references inst_board(inst_board_no) ON DELETE CASCADE
)



				
-- member 테이블 값 삽입
insert into member (id, password, mem_name, birth_date, gender, mem_type, mem_number) values
('java01', 'java01', '강정호', '1970-01-01', '남성', '일반학생', mem_number_seq.nextval);
insert into member (id, password, mem_name, birth_date, gender, mem_type, mem_number) values
('java02', 'java02', '김가린', '1970-01-01', '여성', '우수학생', mem_number_seq.nextval);
insert into member (id, password, mem_name, birth_date, gender, mem_type, mem_number) values
('java03', 'java03', '김규익', '1970-01-01', '남성', '일반학생', mem_number_seq.nextval);
insert into member (id, password, mem_name, birth_date, gender, mem_type, mem_number) values
('java04', 'java04', '김래현', '1970-01-01', '남성', '우수학생', mem_number_seq.nextval);
insert into member (id, password, mem_name, birth_date, gender, mem_type, mem_number) values
('java05', 'java05', '김호겸', '1970-01-01', '남성', '일반학생', mem_number_seq.nextval);
insert into member (id, password, mem_name, birth_date, gender, mem_type, mem_number) values
('java06', 'java06', '김문일', '1970-01-01', '남성', '우수학생', mem_number_seq.nextval);
insert into member (id, password, mem_name, birth_date, gender, mem_type, mem_number) values
('java07', 'java07', '김성환', '1970-01-01', '남성', '일반학생', mem_number_seq.nextval);
insert into member (id, password, mem_name, birth_date, gender, mem_type, mem_number) values
('java08', 'java08', '김승신', '1970-01-01', '남성', '우수학생', mem_number_seq.nextval);
insert into member (id, password, mem_name, birth_date, gender, mem_type, mem_number) values
('java09', 'java09', '김종봉', '1970-01-01', '남성', '일반학생', mem_number_seq.nextval);
insert into member (id, password, mem_name, birth_date, gender, mem_type, mem_number) values
('java10', 'java10', '김지원', '1970-01-01', '여성', '우수학생', mem_number_seq.nextval);
insert into member (id, password, mem_name, birth_date, gender, mem_type, mem_number) values
('java11', 'java11', '김택민', '1970-01-01', '남성', '우수학생', mem_number_seq.nextval);
insert into member (id, password, mem_name, birth_date, gender, mem_type, mem_number) values
('java12', 'java12', '노주희', '1970-01-01', '여성', '일반학생', mem_number_seq.nextval);
insert into member (id, password, mem_name, birth_date, gender, mem_type, mem_number) values
('java13', 'java13', '문성준', '1970-01-01', '남성', '일반학생', mem_number_seq.nextval);
insert into member (id, password, mem_name, birth_date, gender, mem_type, mem_number) values
('java14', 'java14', '박다혜', '1970-01-01', '여성', '일반학생', mem_number_seq.nextval);
insert into member (id, password, mem_name, birth_date, gender, mem_type, mem_number) values
('java15', 'java15', '박영덕', '1970-01-01', '남성', '일반학생', mem_number_seq.nextval);
insert into member (id, password, mem_name, birth_date, gender, mem_type, mem_number) values
('java16', 'java16', '박영우', '1970-01-01', '남성', '일반학생', mem_number_seq.nextval);
insert into member (id, password, mem_name, birth_date, gender, mem_type, mem_number) values
('java17', 'java17', '배서경', '1970-01-01', '여성', '일반학생', mem_number_seq.nextval);
insert into member (id, password, mem_name, birth_date, gender, mem_type, mem_number) values
('java18', 'java18', '송준영', '1970-01-01', '남성', '우수학생', mem_number_seq.nextval);
insert into member (id, password, mem_name, birth_date, gender, mem_type, mem_number) values
('java19', 'java19', '신용혁', '1970-01-01', '남성', '우수학생', mem_number_seq.nextval);
insert into member (id, password, mem_name, birth_date, gender, mem_type, mem_number) values
('java20', 'java20', '오남준', '1970-01-01', '남성', '일반학생', mem_number_seq.nextval);
insert into member (id, password, mem_name, birth_date, gender, mem_type, mem_number) values
('java21', 'java21', '윤다혜', '1970-01-01', '여성', '일반학생', mem_number_seq.nextval);
insert into member (id, password, mem_name, birth_date, gender, mem_type, mem_number) values
('java22', 'java22', '이상섭', '1970-01-01', '남성', '우수학생', mem_number_seq.nextval);
insert into member (id, password, mem_name, birth_date, gender, mem_type, mem_number) values
('java23', 'java23', '이승우', '1970-01-01', '남성', '일반학생', mem_number_seq.nextval);
insert into member (id, password, mem_name, birth_date, gender, mem_type, mem_number) values
('java24', 'java24', '이현근', '1970-01-01', '남성', '일반학생', mem_number_seq.nextval);
insert into member (id, password, mem_name, birth_date, gender, mem_type, mem_number) values
('java25', 'java25', '임경수', '1970-01-01', '남성', '우수학생', mem_number_seq.nextval);
insert into member (id, password, mem_name, birth_date, gender, mem_type, mem_number) values
('java26', 'java26', '임소영', '1970-01-01', '여성', '우수학생', mem_number_seq.nextval);
insert into member (id, password, mem_name, birth_date, gender, mem_type, mem_number) values
('java27', 'java27', '임주성', '1970-01-01', '남성', '일반학생', mem_number_seq.nextval);
insert into member (id, password, mem_name, birth_date, gender, mem_type, mem_number) values
('java28', 'java28', '임진우', '1970-01-01', '남성', '일반학생', mem_number_seq.nextval);
insert into member (id, password, mem_name, birth_date, gender, mem_type, mem_number) values
('java29', 'java29', '장기혁', '1970-01-01', '남성', '일반학생', mem_number_seq.nextval);
insert into member (id, password, mem_name, birth_date, gender, mem_type, mem_number) values
('java30', 'java30', '전응구', '1970-01-01', '남성', '우수학생', mem_number_seq.nextval);
insert into member (id, password, mem_name, birth_date, gender, mem_type, mem_number) values
('java31', 'java31', '정석희', '1970-01-01', '남성', '일반학생', mem_number_seq.nextval);
insert into member (id, password, mem_name, birth_date, gender, mem_type, mem_number) values
('java32', 'java32', '정태형', '1970-01-01', '남성', '일반학생', mem_number_seq.nextval);
insert into member (id, password, mem_name, birth_date, gender, mem_type, mem_number) values
('java33', 'java33', '정현지', '1970-01-01', '여성', '일반학생', mem_number_seq.nextval);
insert into member (id, password, mem_name, birth_date, gender, mem_type, mem_number) values
('java34', 'java34', '조송희', '1970-01-01', '여성', '일반학생', mem_number_seq.nextval);
insert into member (id, password, mem_name, birth_date, gender, mem_type, mem_number) values
('java35', 'java35', '한지선', '1970-01-01', '여성', '일반학생', mem_number_seq.nextval);
insert into member (id, password, mem_name, birth_date, gender, mem_type, mem_number) values
('java36', 'java36', '황윤상', '1970-01-01', '남성', '일반학생', mem_number_seq.nextval);
insert into member (id, password, mem_name, birth_date, gender, mem_type, mem_number) values
('java00', 'java00', '서정우', '1970-01-01', '남성', '강사', 0);

-- selecting_presenter 값 삽입
insert into selecting_presenter(id, cnt_presentation) values('java01', 0);
insert into selecting_presenter(id, cnt_presentation) values('java02', 0);
insert into selecting_presenter(id, cnt_presentation) values('java03', 0);
insert into selecting_presenter(id, cnt_presentation) values('java04', 1);
insert into selecting_presenter(id, cnt_presentation) values('java05', 2);
insert into selecting_presenter(id, cnt_presentation) values('java06', 3);
insert into selecting_presenter(id, cnt_presentation) values('java07', 4);
insert into selecting_presenter(id, cnt_presentation) values('java08', 0);
insert into selecting_presenter(id, cnt_presentation) values('java09', 0);
insert into selecting_presenter(id, cnt_presentation) values('java10', 0);
insert into selecting_presenter(id, cnt_presentation) values('java11', 1);
insert into selecting_presenter(id, cnt_presentation) values('java12', 2);
insert into selecting_presenter(id, cnt_presentation) values('java13', 3);
insert into selecting_presenter(id, cnt_presentation) values('java14', 4);
insert into selecting_presenter(id, cnt_presentation) values('java15', 0);
insert into selecting_presenter(id, cnt_presentation) values('java16', 0);
insert into selecting_presenter(id, cnt_presentation) values('java17', 0);
insert into selecting_presenter(id, cnt_presentation) values('java18', 1);
insert into selecting_presenter(id, cnt_presentation) values('java19', 2);
insert into selecting_presenter(id, cnt_presentation) values('java20', 3);
insert into selecting_presenter(id, cnt_presentation) values('java21', 4);
insert into selecting_presenter(id, cnt_presentation) values('java22', 0);
insert into selecting_presenter(id, cnt_presentation) values('java23', 0);
insert into selecting_presenter(id, cnt_presentation) values('java24', 0);
insert into selecting_presenter(id, cnt_presentation) values('java25', 1);
insert into selecting_presenter(id, cnt_presentation) values('java26', 2);
insert into selecting_presenter(id, cnt_presentation) values('java27', 3);
insert into selecting_presenter(id, cnt_presentation) values('java28', 4);
insert into selecting_presenter(id, cnt_presentation) values('java29', 0);
insert into selecting_presenter(id, cnt_presentation) values('java30', 0);
insert into selecting_presenter(id, cnt_presentation) values('java31', 0);
insert into selecting_presenter(id, cnt_presentation) values('java32', 1);
insert into selecting_presenter(id, cnt_presentation) values('java33', 2);
insert into selecting_presenter(id, cnt_presentation) values('java34', 3);
insert into selecting_presenter(id, cnt_presentation) values('java35', 4);
insert into selecting_presenter(id, cnt_presentation) values('java36', 4);

select * from inst_board

-- 게시판 게시글 삽입
insert into inst_board(inst_board_no, id, title, content, time_posted)
values(inst_board_seq.nextval, 'java01', '연습1', '연습이다1', sysdate);
insert into inst_board(inst_board_no, id, title, content, time_posted)
values(inst_board_seq.nextval, 'java01', '연습2', '연습이다2', sysdate);
insert into inst_board(inst_board_no, id, title, content, time_posted)
values(inst_board_seq.nextval, 'java01', '연습3', '연습이다3', sysdate);
insert into inst_board(inst_board_no, id, title, content, time_posted)
values(inst_board_seq.nextval, 'java02', '연습4', '연습이다4', sysdate);
insert into inst_board(inst_board_no, id, title, content, time_posted)
values(inst_board_seq.nextval, 'java02', '연습5', '연습이다5', sysdate);
insert into inst_board(inst_board_no, id, title, content, time_posted)
values(inst_board_seq.nextval, 'java02', '연습6', '연습이다6', sysdate);
insert into inst_board(inst_board_no, id, title, content, time_posted)
values(inst_board_seq.nextval, 'java03', '연습7', '연습이다7', sysdate);
insert into inst_board(inst_board_no, id, title, content, time_posted)
values(inst_board_seq.nextval, 'java03', '연습8', '연습이다8', sysdate);
insert into inst_board(inst_board_no, id, title, content, time_posted)
values(inst_board_seq.nextval, 'java03', '연습9', '연습이다9', sysdate);
insert into inst_board(inst_board_no, id, title, content, time_posted)
values(inst_board_seq.nextval, 'java04', '연습10', '연습이다10', sysdate);
insert into inst_board(inst_board_no, id, title, content, time_posted)
values(inst_board_seq.nextval, 'java04', '연습11', '연습이다11', sysdate);

insert into proposal_board(proposal_board_no, id, title, content, time_posted)
values(proposal_board_seq.nextval, 'java05', '연습1', '연습이다1', sysdate);
insert into proposal_board(proposal_board_no, id, title, content, time_posted)
values(proposal_board_seq.nextval, 'java05', '연습2', '연습이다2', sysdate);
insert into proposal_board(proposal_board_no, id, title, content, time_posted)
values(proposal_board_seq.nextval, 'java05', '연습3', '연습이다3', sysdate);
insert into proposal_board(proposal_board_no, id, title, content, time_posted)
values(proposal_board_seq.nextval, 'java06', '연습3', '연습이다3', sysdate);

insert into qna_board(qna_board_no, id, title, content, time_posted)
values(qna_board_seq.nextval, 'java07', '연습1', '연습이다1', sysdate);
insert into qna_board(qna_board_no, id, title, content, time_posted)
values(qna_board_seq.nextval, 'java07', '연습2', '연습이다2', sysdate);
insert into qna_board(qna_board_no, id, title, content, time_posted)
values(qna_board_seq.nextval, 'java07', '연습3', '연습이다3', sysdate);
insert into qna_board(qna_board_no, id, title, content, time_posted)
values(qna_board_seq.nextval, 'java08', '연습3', '연습이다3', sysdate);





-------------------- 연습용 sql -----------------------

select inst.* from(
			select row_number() over(order by inst_board_no desc) rnum, inst_board_no, title, id, 
			hit, to_char(time_posted, 'YYYY.MM.DD') as time_posted
			from inst_board
			) inst where rnum between 1 and 5
			

select ib.rnum, ib.inst_board_no, ib.title, ib.content, ib.id, ib.hit, ib.time_posted, m.mem_name from(
			select row_number() over(order by to_number(inst_board_no) desc) rnum, inst_board_no, title, content, id,
			hit, to_char(time_posted, 'YYYY.MM.DD') as time_posted 
			from inst_board where id like '%강정호%'
			) ib, member m where ib.id = m.id and rnum between 1 and 20
			
select ib.rnum, ib.inst_board_no, ib.title, ib.content, ib.id, ib.hit, ib.time_posted, m.mem_name from(
			select row_number() over(order by to_number(inst_board_no) desc) rnum, inst_board_no, title, content, id,
			hit, to_char(time_posted, 'YYYY.MM.DD') as time_posted 
			from inst_board where mem_name like '%강정호%'
			) ib, member m where ib.id = m.id and rnum between 1 and 20

select * from inst_board

select inst_board_seq.nextval from dual


SELECT count(*) FROM inst_board WHERE title LIKE '%연습%'
select * from inst_board

select * from qna_board

select count(*) from
( select row_number() over(order by to_number
(proposal_board_no) desc) rnum, proposal_board_no, 
title, id, hit, to_char(time_posted, 'YYYY.MM.DD') as time_posted
from proposal_board where title like '%1%') ib, member m where ib.id = m.id
select *from proposal_board




select ib.proposal_board_no, ib.title, ib.id, ib.hit, ib.time_posted, m.mem_name from(
select row_number() over(order by to_number(proposal_board_no) desc) rnum, proposal_board_no, title, id,
hit, to_char(time_posted, 'YYYY.MM.DD') as time_posted from proposal_board where title like ?) ib, member m where ib.id = m.id and rnum between ? and ?




	           select tb.rnum, tb.proposal_board_no, tb.title, tb.content, tb.id, tb.hit, tb.time_posted, tb.mem_name,tb.secret from(
	           select row_number() over(order by to_number(proposal_board_no) desc) rnum, ib.proposal_board_no, ib.title, ib.content, ib.id,ib.secret,
	           ib.hit, to_char(time_posted, 'YYYY.MM.DD') as time_posted, m.mem_name 
	           from proposal_board ib, member m where ib.id = m.id and m.mem_name like '%1%'
	            ) tb where rnum between 1 and 20
	            
	            
	            select ib.proposal_board_no, ib.title, ib.id, ib.hit, ib.time_posted, m.mem_name,ib.secret from(
	            select row_number() over(order by to_number(proposal_board_no) asc) rnum, proposal_board_no, title, id,secret,
	            hit, to_char(time_posted, 'YYYY.MM.DD') as time_posted
	            from proposal_board where title like '%임%' or content like '%1%'
	            ) ib, member m where ib.id = m.id and rnum between 1 and 20 


select ib.qna_board_no, ib.title, ib.id, ib.hit, ib.time_posted, m.mem_name,ib.secret from(
select row_number() over(order by to_number(qna_board_no) desc) rnum, qna_board_no, title, id,hit,secret, to_char(
				time_posted, 'YYYY.MM.DD') as time_posted from qna_board where title like '%연습%' or content like '%연습%') ib, member m
					where ib.id = m.id and rnum between 1 and 20


select ib.inst_board_no, ib.title, ib.id, ib.hit, ib.time_posted, m.mem_name from(
           select row_number() over(order by to_number(inst_board_no) desc) rnum, inst_board_no, title, id, 
           hit, to_char(time_posted, 'YYYY.MM.DD') as time_posted 
           from inst_board where title like '%연습%'
           ) ib, member m where ib.id = m.id and rnum between 1 and 20
           
           
           select ib.inst_board_no, ib.title, ib.id, ib.hit, ib.time_posted, m.mem_name from(
           select row_number() over(order by to_number(inst_board_no) desc) rnum, inst_board_no, title, id, 
           hit, to_char(time_posted, 'YYYY.MM.DD') as time_posted 
           from inst_board where title like '%연습%'
            ) ib, member m where ib.id = m.id and rnum between 1 and 20
            
select count(*) from inst_comment where inst_board_no=15


select ib.qna_board_no, ib.title, ib.id, ib.hit, ib.time_posted, m.mem_name,ib.secret from(
					select row_number() over(order by qna_board_no asc) rnum, qna_board_no, title, id,hit,secret, to_char(
					time_posted, 'YYYY.MM.DD') as time_posted from qna_board where title like '%연습%') ib, member m 
					where ib.id = m.id and rnum between 1 and 10;
					
select X.rnum, X.id, X.name, X.content, to_char(X.time_posted, 'yyyy-MM-dd') as createdate
from ( select rownum as rnum, A.id, A.name, A.content, A.time_posted  
from ( select id, name, content, time_posted from inst_board order by time_posted) A
where rownum <= 1) X where X.rnum >= 10

select ib.qna_board_no, ib.title, ib.id, ib.hit, ib.time_posted, m.mem_name,ib.secret from
(select row_number() over(order by qna_board_no desc) rnum,qna_board_no,title,id,hit,time_posted,secret from qna_board 
where title like '%연습%' order by time_posted desc) ib, member m where ib.id = m.id and rnum between 1 and 10;

select *from qna_board;

order by time_posted desc;
					
select * from SELECTING_PRESENTER;

select m.id, m.mem_name, s.cnt_presentation, m.mem_number from member m, selecting_presenter s 
			where m.id = s.id  and s.cnt_presentation=(select min(cnt_presentation) from selecting_presenter)
			
select ib.rnum,ib.inst_board_no, ib.title, ib.id, ib.hit, ib.time_posted, m.mem_name from(
select row_number() over(order by inst_board_no desc) rnum, inst_board_no, title, id, 
hit, to_char(time_posted, 'YYYY.MM.DD') as time_posted from inst_board where title like '%연%') 
ib, member m where ib.id = m.id and rnum between 1 and 11 order by rnum asc		
			
select * from inst_Board

select ib.inst_board_no, ib.title, ib.id, ib.hit, ib.time_posted, m.mem_name from(
select row_number() over(order by inst_board_no desc) rnum, inst_board_no, title, id,hit, to_char(time_posted, 'YYYY.MM.DD') as time_posted from inst_board) ib, 
member m where ib.id = m.id and rnum between 1 and 10 order by rnum asc

