declare
    cursor pauto (sz char) is select * from auto where szin=sz;
    a auto%rowtype;

begin
    open pauto('piros');
    loop    
        fetch pauto into a;
        exit when pauto%notfound;
        dbms_output.put_line(a.rsz || ' ' || a.szin);
    end loop;

end;

declare
    a auto%rowtype;
    sz auto.szin%type;

begin
    sz:=:sz;
    for a in (select * from auto where szin=sz) loop
        dbms_output.put_line(a.rsz || ' ' || a.szin);
    end loop;

end;

--impilicit
declare
    a auto%rowtype;
    jelenEv number(4) := TO_CHAR(SYSDATE, 'YYYY');
    autoEv auto.evjarat%type;
    kor number(3);

begin
    for a in (select * from auto) loop
        autoEv := a.evjarat;
        kor := jelenev - autoEv;
        dbms_output.put_line(a.rsz || ' ' || kor);
    end loop;

end;

--explicit
declare
    CURSOR sauto IS select * from auto;
    a auto%rowtype;
    jelenEv number(4) := TO_CHAR(SYSDATE, 'YYYY');
    autoEv auto.evjarat%type;
    kor number(3);

begin
    open sauto;
    loop
        fetch sauto into a;
        exit when sauto%notfound;
        autoEv := a.evjarat;
        kor := jelenev - autoEv;
        dbms_output.put_line(a.rsz || ' ' || kor);
    end loop;

end;

--implicit
declare
    a auto%rowtype;
    jelenEv number(4) := TO_CHAR(SYSDATE, 'YYYY');
    autoEv auto.evjarat%type;
    kor number(3);
    tempRsz auto.rsz%type;
    tempUjAr auto.ar%type;

begin

    for a in (select * from auto) loop
        autoEv := a.evjarat;
        kor := jelenev - autoEv;
        dbms_output.put_line(a.rsz || ' ' || kor);
        if kor<=12 then
            tempRsz := a.rsz;
            tempUjAr := a.ar * 1.1;
            update auto set ar = tempUjAr WHERE rsz=tempRsz;
        end if;
    end loop;

end;

select * from auto;

--explicit

declare
    cursor pauto is select * from auto for update of ar;
    a auto%rowtype;

begin
    open pauto;
    loop
        fetch pauto into a;
        exit when pauto%notfound;
        if to_char(sysdate, 'yyyy')-a.evjarat < 16 then
            update auto set ar=ar*1.1 where current of pauto;
        else
            dbms_output.put_line('Idős: ' || a.rsz || ' ' || a.tipus);
        end if;
    end loop;

end;
