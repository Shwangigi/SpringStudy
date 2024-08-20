create sequence seq_board; -- 자동 번호 객체 생성

create table tbl_board(
   bno number(10,0),
   title varchar2(200) not null,
   content varchar2(2000) not null,
   writer varchar2(50) not null,
   regdate date default sysdate,
   updatedate date default sysdate
); -- tbl-board 테이블 생성 (번호, 제목, 내용, 작성자, 작성일, 수정일)

alter table tbl_board add constraint pk_board primary key(bno); -- pk 생성 및 제약조건 이름 생성

select * from tbl_board;

insert into TBL_BOARD(bno, title, content, writer)
values(seq_board.nextval, '테스트 제목', '테스트 내용', 'user00');


