create or replace trigger auto_t before delete or insert or update of ar on auto for each row
begin
    if deleting then
        dbms_output.put_line('Delete');
    elsif updating then
        dbms_output.put_line('update');
    elsif inserting then
        dbms_output.put_line('insert');
    end if;
    
end;

update auto set tipus='opel' where szin='zöld'
update auto set ar=200 where szin='zöld'

delete auto where tipus='asd'

create or replace view autoview as select rsz. to_char(sysdate.'yyyy')-evjarat kor from auto;



--tabla helyet viewra kell?...
--create or replace trigger autotorles instead of delete on auto
create or replace trigger autotorles instead of delete on autoview
begin
    raise_application_error(-20001.'Nem szavad');
end;