create sequence rsz_n start with 100;

begin
    dbms_output.put_line(rsz_n.nextval);
end;

create or replace trigger auto_key before insert or update of rsz on auto for each row
declare
type a_rszt is record (
    betuk char(3),
    szamok char(3)
);
rendszam a_rszt;
begin
    select SUBSTR(:nev.rsz,1,3) INTO rendszam.betuk from dual;
    select SUBSTR(:nev.rsz,4,3) INTO rendszam.szamok from dual;
    if rendszam.szamok = 100 then
        rendszam.szamok:=rsz_n.nextval;
    end if;
    :nev.rsz:=rendszam ||rendszam.szamok;
    dbms_output.put_line('felvitt rendszam: ' || new.rsz);
end;

insert into auto values('ABC100','suzuki','zold',2000,200);

desc auto

set serveroutput on