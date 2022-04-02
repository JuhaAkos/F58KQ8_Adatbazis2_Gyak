create or replace procedure proba is
    a auto%rowtype;

begin
    select * into a from auto where szin='feher';
    dbms_output.put_line(a.rsz || ' ' || a.szin);
    
    exception
        when TOO_MANY_ROWS then
            dbms_output.put_line('Túl sok sor!');
        when NO_DATA_FOUND then
            dbms_output.put_line('Túl kevés sor!');

end;

begin
    proba();

end;

--------------------------------------------------------------------------

create or replace procedure oregauto(hatar IN number, rek_szam OUT number) IS

zero_record exception;

begin
    update auto set ar=ar*0.8 where to_char(sysdate, 'yyyy')-evjarat>hatar;
    rek_szam:=sql%rowcount;
    if sql%notfound then
        raise zero_record;
    elsif sql%found then
        dbms_output.put_line('Siker!');
    end if;
    
    exception
        when zero_record then dbms_output.put_line('Hiba!');

end;

declare
    rszam number(2);
begin
    oregauto(100,rszam);
    dbms_output.put_line('Módosított: ' || rszam);
end;
    