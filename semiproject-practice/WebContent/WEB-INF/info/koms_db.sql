-- drop table
drop table free_comment;
drop table proposal_comment;
drop table qna_comment;
drop table inst_comment;
drop table free_board;
drop table proposal_board;
drop table qna_board;
drop table inst_board;
drop table selecting_presenter;
drop table selecting_group;
drop table attendance;
drop table member;



-- drop sequence
drop sequence id_seq;
drop sequence free_board_seq;
drop sequence proposal_board_seq;
drop sequence qna_board_seq;
drop sequence inst_board_seq;
drop sequence free_comment_seq;
drop sequence proposal_comment_seq;
drop sequence qna_comment_seq;
drop sequence inst_comment_seq;

-- create sequence
create sequence id_seq;
create sequence free_board_seq;
create sequence proposal_board_seq;
create sequence qna_board_seq;
create sequence inst_board_seq;
create sequence free_comment_seq;
create sequence proposal_comment_seq;
create sequence qna_comment_seq;
create sequence inst_comment_seq;

-- create table
-- member 테이블
create table member(
	id					varchar2(100) primary key,
	password		varchar2(100) not null,
	mem_name		varchar2(100) not null,
	birth_date	date,
	gender			varchar2(100) not null,
	mem_type		varchar2(100) not null
)
insert into member (id, password, mem_name, birth_date, gender, mem_type) 
values ('java', '1234', '강정호', '1970-01-01', '남', '일반학생');
insert into member (id, password, mem_name, birth_date, gender, mem_type) 
values ('spring', '1234', '김가린', '1970-01-01', '여', '일반학생');
insert into member (id, password, mem_name, birth_date, gender, mem_type) 
values ('tico', '1234', '서정우', '1970-01-01', '남', '강사');

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

-- 출결기록 테이블
create table attendance(
	attend_date			varchar2(100),
	id							varchar2(100),
	attendance_state	varchar2(100) not null,
	constraint pk_attend primary key(attend_date, id),	-- 복합키
	constraint fk_id_attend foreign key(id) references member(id)
)

-- 자유게시판 테이블
create table free_board(
   free_board_no      varchar2(100) primary key,
   id                     varchar2(100) not null,
   title                  varchar2(100) not null,
   content               clob,
   time_posted         date,
   file_name            varchar2(100) not null,
   hit                  number default(0),
   hidden               char(1),      -- 'Y' or 'N'으로 쓸 예정(?)
   constraint fk_id_free_board foreign key(id) references member(id)
)

-- 자유게시판 댓글 테이블
create table free_comment(
   free_comment_no      varchar2(100) primary key,
   free_board_no         varchar2(100) not null,
   id                  varchar2(100) not null,
   time_posted            date,
   content                  clob,
   constraint fk_id_free_comment foreign key(id) references member(id),
   constraint fk_free_board_no foreign key(free_board_no) references free_board(free_board_no)
)

-- 건의사항 게시판 테이블
create table proposal_board(
   proposal_board_no      varchar2(100) primary key,
   id                           varchar2(100) not null,
   title                        varchar2(100) not null,
   content                     clob,
   time_posted               date,
   file_name                  varchar2(100),
   hit                        number default(0),
   hidden                     char(1),      -- 'Y' or 'N'으로 쓸 예정(?)
   constraint fk_id_proposal_board foreign key(id) references member(id)
)

-- 건의사항 게시판 댓글 테이블
create table proposal_comment(
   proposal_comment_no      varchar2(100) primary key,
   proposal_board_no         varchar2(100) not null,
   id varchar2(100) not null,
   time_posted                  date,
   content                        clob,
   constraint fk_proposal_board_no foreign key(proposal_board_no) references proposal_board(proposal_board_no)
   ,constraint fk_id_proposal_comment foreign key(id) references member(id)
   )

-- QnA 게시판 테이블
create table qna_board(
   qna_board_no      varchar2(100) primary key,
   id                     varchar2(100) not null,
   title                  varchar2(100) not null,
   content               clob,
   time_posted         date,
   file_name            varchar2(100),
   hit                  number default(0),
   hidden               char(1),      -- 'Y' or 'N'으로 쓸 예정(?)
   constraint fk_id_qna_board foreign key(id) references member(id)
)

-- QnA 게시판 댓글 테이블
create table qna_comment(
   qna_comment_no      varchar2(100) primary key,
   qna_board_no         varchar2(100) not null,
   id                  varchar2(100) not null,
   time_posted            date,
   content                  clob,
   constraint fk_id_qna_comment foreign key(id) references member(id),
   constraint fk_qna_board_no foreign key(qna_board_no) references qna_board(qna_board_no)
)

-- 강사 게시판 테이블
create table inst_board(
   inst_board_no      varchar2(100) primary key,
   id                     varchar2(100) not null,
   title                  varchar2(100) not null,
   content               clob,
   time_posted         date,
   file_name            varchar2(100),
   hit                  number default(0),
   avg_rating            binary_double default(5.0),
   constraint fk_id_inst_board foreign key(id) references member(id)
)
insert into inst_board(inst_board_no, id, title, content, time_posted)
values(inst_board_seq.nextval, 'java', '연습1', '연습이다1', sysdate);
insert into inst_board(inst_board_no, id, title, content, time_posted)
values(inst_board_seq.nextval, 'java', '연습2', '연습이다2', sysdate);
insert into inst_board(inst_board_no, id, title, content, time_posted)
values(inst_board_seq.nextval, 'java', '연습3', '연습이다3', sysdate);
insert into inst_board(inst_board_no, id, title, content, time_posted)
values(inst_board_seq.nextval, 'java', '연습4', '연습이다4', sysdate);
insert into inst_board(inst_board_no, id, title, content, time_posted)
values(inst_board_seq.nextval, 'java', '연습5', '연습이다5', sysdate);
insert into inst_board(inst_board_no, id, title, content, time_posted)
values(inst_board_seq.nextval, 'java', '연습6', '연습이다6', sysdate);
insert into inst_board(inst_board_no, id, title, content, time_posted)
values(inst_board_seq.nextval, 'java', '연습7', '연습이다7', sysdate);
insert into inst_board(inst_board_no, id, title, content, time_posted)
values(inst_board_seq.nextval, 'java', '연습8', '연습이다8', sysdate);
insert into inst_board(inst_board_no, id, title, content, time_posted)
values(inst_board_seq.nextval, 'java', '연습9', '연습이다9', sysdate);
insert into inst_board(inst_board_no, id, title, content, time_posted)
values(inst_board_seq.nextval, 'java', '연습10', '연습이다10', sysdate);
insert into inst_board(inst_board_no, id, title, content, time_posted)
values(inst_board_seq.nextval, 'java', '연습11', '연습이다11', sysdate);

insert into proposal_board(proposal_board_no, id, title, content, time_posted)
values(proposal_board_seq.nextval, 'java', '연습1', '연습이다1', sysdate);
insert into proposal_board(proposal_board_no, id, title, content, time_posted)
values(proposal_board_seq.nextval, 'java', '연습2', '연습이다2', sysdate);
insert into proposal_board(proposal_board_no, id, title, content, time_posted)
values(proposal_board_seq.nextval, 'java', '연습3', '연습이다3', sysdate);
insert into proposal_board(proposal_board_no, id, title, content, time_posted)
values(proposal_board_seq.nextval, 'java', '연습3', '연습이다3', sysdate);

insert into qna_board(qna_board_no, id, title, content, time_posted)
values(qna_board_seq.nextval, 'java', '연습1', '연습이다1', sysdate);
insert into qna_board(qna_board_no, id, title, content, time_posted)
values(qna_board_seq.nextval, 'java', '연습2', '연습이다2', sysdate);
insert into qna_board(qna_board_no, id, title, content, time_posted)
values(qna_board_seq.nextval, 'java', '연습3', '연습이다3', sysdate);
insert into qna_board(qna_board_no, id, title, content, time_posted)
values(qna_board_seq.nextval, 'java', '연습3', '연습이다3', sysdate);

-- 강사 게시판 댓글 테이블
create table inst_comment(
   inst_comment_no      varchar2(100) primary key,
   inst_board_no         varchar2(100) not null,
   id                  varchar2(100) not null,
   time_posted            date,
   content                  clob,
   rating                  number default(5),      -- number(?)
   constraint fk_id_inst_comment foreign key(id) references member(id),
   constraint fk_inst_board_no foreign key(inst_board_no) references inst_board(inst_board_no)
)

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
			
select count(*) from(
	select row_number() over(order by to_number(inst_board_no) desc) rnum, ib.inst_board_no, ib.title, ib.content, ib.id,
	ib.hit, to_char(time_posted, 'YYYY.MM.DD') as time_posted, m.mem_name
	from inst_board ib, member m where ib.id = m.id and m.mem_name like '%강정호%'
) tb 

select tb.rnum, tb.inst_board_no, tb.title, tb.content, tb.id, tb.hit, tb.time_posted, tb.mem_name from(
	select row_number() over(order by to_number(inst_board_no) desc) rnum, ib.inst_board_no, ib.title, ib.content, ib.id,
	ib.hit, to_char(time_posted, 'YYYY.MM.DD') as time_posted, m.mem_name
	from inst_board ib, member m where ib.id = m.id and m.mem_name like '%강정호%'
) tb where rnum between 1 and 20
			
select ib.rnum, ib.inst_board_no, ib.title, ib.content, ib.id, ib.hit, ib.time_posted, m.mem_name from(
			select row_number() over(order by to_number(inst_board_no) desc) rnum, ti.inst_board_no, ti.title, ti.content, ti.id,
			ti.hit, to_char(time_posted, 'YYYY.MM.DD') as time_posted 
			from inst_board ti, member m where ti.id = m.id and m.mem_name like '%강정호%'
			) ib where rnum between 0 and 10

select * from inst_board

select inst_board_seq.nextval from dual


SELECT count(*) FROM inst_board WHERE title LIKE '%연습%'
select * from inst_board

select * from qna_board

select count(*) from(
				select row_number() over(order by to_number(inst_board_no) desc) rnum, ib.inst_board_no, ib.title, ib.content, ib.id, 
				ib.hit, to_char(time_posted, 'YYYY.MM.DD') as time_posted, m.mem_name
				from inst_board ib, member m where ib.id = m.id and m.mem_name like '%강정호%'
			) tb
			
			select * from inst_board where id='tico'
			
