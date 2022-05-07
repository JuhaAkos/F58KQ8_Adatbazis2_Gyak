create table naplo (muvelet char(50),datum date, felhasznalo char(20));

create or replace trigger auto_del after delete on auto for each row
begin
    insert into naplo values(concat('Delete',:old.rsz),sysdate,user);
    --insert into naplo values('Delete',sysdate,user);
end;

create or replace trigger auto_del after delete on auto for each row when (old.tipus='asd')
begin
    insert into naplo values(concat('Delete',:old.rsz),sysdate,user);
    --insert into naplo values('Delete',sysdate,user);
end;

select * from naplo
select * from auto

insert into auto values(22222,'asd','sárga',2013,3300);