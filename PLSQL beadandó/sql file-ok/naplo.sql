create table naplo (muvelet char(50),datum date, felhasznalo char(20));

create or replace trigger trig_etel_del after delete on etel for each row
begin
    insert into naplo values('Delete',sysdate,user);
end;

create or replace trigger trig_etel_fel after insert or update on etel for each row
begin
    if updating then
        insert into naplo values('Update',sysdate,user);
    elsif inserting then
        insert into naplo values('Insert',sysdate,user);
    end if;
    
end;


