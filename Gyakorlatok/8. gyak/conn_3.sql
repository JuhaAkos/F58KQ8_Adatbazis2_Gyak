declare
    a auto%rowtype;
    r auto.rsz%type:='AAA000';
    darab number(10);

begin
    select * into a from  auto where rsz=r;
    dbms_output.put_line(a.rsz || ' ' || a.tipus || ' ' || a.szin);
    select count(*) into darab from auto;
    dbms_output.put_line('Az autok száma: ' || darab);

end;

declare 
    cursor pauto is select * from auto for update of ar;
    a auto%rowtype;
begin
    dbms_output.put_line('Program kezdete');
    open pauto;
    loop
        fetch pauto into a;
        exit when pauto%notfound;
        if to_char(sysdate,'YYYY')-a.evjarat < 12 then
            update auto set ar=ar*1.1 where current of pauto;
        else
            dbms_output.put_line(a.rsz||' - '||(to_char(sysdate,'YYYY')-a.evjarat)||' - '||a.ar);
        end if;
    end loop;
end;

---IMPLICIT megoldás:
declare
    a auto%rowtype;

begin
    for a in (select * from auto) loop
        dbms_output.put_line(a.rsz || ' ' || a.tipus);
    end loop;

end;

set serveroutput on

---EXPLICIT megoldás
declare
    CURSOR sauto IS select * from auto;
    a auto%rowtype;
    
begin
    open sauto;
    loop
        fetch sauto into a;
        exit when sauto%notfound;
        dbms_output.put_line(a.rsz || ' ' || a.evjarat || ' ' || a.tipus);
    end loop;

end;

