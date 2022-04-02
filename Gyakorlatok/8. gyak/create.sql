create table auto(
	rsz char(6) primary key,
	tipus char(10) not null,
	szin char(10) default 'fehér',
	evjarat number(4),
	ar number(8) check(ar>0)
);

insert into auto values('AAA000','skoda','zöld',2008,200000);
insert into auto(rzs,tipus,evjarat,ar) values('AAA001','skoda',2010,300000);
insert into auto values('AAA002','suzuki','lila',2001,150000);

commit;
select * from auto;

set serveroutput on