--triggerek:
--create or replace trigger nev before/after/instead of insert/update/delete (i/u/d) or can be added between them) ON auto
--for each row when
--declare
--begin
--end;

create or replace trigger delete_on_auto after delete on auto for each row
declare
    msg char(50):=concat(:old.rsz,'rendszamu auto torolve');
begin
    dbms_output.put_line(msg);
end;

delete from auto;

set serveroutput on;