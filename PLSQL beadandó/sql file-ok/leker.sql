create or replace function lekerEtelNev(azon number) return varchar2 as 
    x varchar2(30);
begin
    select etel.nev into x from etel where etel.eid=azon;
    return x;
end;

declare
    c_eid etel.eid%type;
    c_kisar etel.kisar%type;
    c_nagyar etel.nagyar%type;
    c_tipus etel.tipus%type;
    c_nev etel.nev%type;
    cursor c_etel is select eid, kisar, nagyar, tipus, nev from etel;
begin
    open c_etel;
    loop
        fetch c_etel into c_eid, c_kisar, c_nagyar, c_tipus, c_nev;
        exit when c_etel%notfound;
        dbms_output.put_line(c_eid || ' - ' || c_kisar || ' - ' || c_nagyar || ' - ' || c_tipus || ' - ' || c_nev);
    end loop;
    close c_etel;
end;

create or replace function lekerEtelek(azon number) return etel%rowtype as 
    x etel%rowtype;
begin
    select * into x from etel where etel.eid=azon;
    return x;
    dbms_output.put_line(x.nev);
end;


create or replace function lekerAtlagAr return number as 
    x number;
begin
    select sum(etel.kisar)/count(*) into x from etel;
    return x;
end;

