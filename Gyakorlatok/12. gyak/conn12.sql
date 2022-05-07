create or replace package autohandler is
    --type autorow is auto%rotype;
    type autorow is record (rsz varchar(6), szin varchar2(100));
    --function get_car_by_evjarat(evjarat number) return auto%rowtype;
    function get_car_by_evjarat(evjarat number) return autorow;
    function get_avg_car_evjarat return number;
    procedure add(rsz char,tipus  varchar2,evjarat number, ar number);
    procedure delete_car(srsz char);
end;

create or replace package body autohandler is
    
    function get_car_by_evjarat(evjarat number) return auto%rowtype;
           car auto%rowtype;
           carret autorow
    begin
        --select rsz,tipus,szin,evjarat,ar into car from auto where evjarat=evjarat;
        select rsz,tipus,szin,evjarat,ar into car from auto where evjarat=evjarat;
        select * into car from auto a where a.evjarat=sevjarat;
        --return car;
        --carret.rsz:=car.rsz;
        --carret.szin:=car.szin;
        return carret;
    end;
    
    function get_avg_car_evjarat return number is
        avg_evjarat number;
    begin
        select avg(evjarat) into avg_evjarat from auto;
        return avg_evjarat;
    end;
    
    procedure add_car(rsz char, tipus varchar2,szin varchar2, evjarat number, ar number) is
    begin
        insert into auto values(rsz, tipus, szin, evjarat, ar);
    end;
    
    procedure delete_car(srsz char) is
    begin
        delete from auto a where a.rsz = srsz;
    end; 
    
end;

select autohandler.get_car_by_evjarat(2000) from dual;

begin
    autohandler.add_car('11111',hupilila','skoda',1967,200);
end; 

declare
    ichar varchar(10);
begin
    for i IN 1..9 LOOP
        ichar:=to_char(i);
        autohandler.add_car(ichar+ichar+ichar+ichar+,'zöld','opel',to_char(sysdate,'YYYY'),11100);
    END LOOP;
end;    


