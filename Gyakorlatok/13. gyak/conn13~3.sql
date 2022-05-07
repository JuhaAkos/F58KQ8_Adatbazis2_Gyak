--varray anyag?... extra
create or replace procedure test1 as
type varray_t is varray(4) of varchar(100);
tomb varray_t;
begin
    tomb(1):='alma';
    tomb(2):='balma';
    tomb(3):='calma';
end;

create or replace procedure test1 as
    type foursome is varray(4) of varchar(100);
    --team foursome;
    team foursome:=foursome('A','B','C','D');
begin
    team(1):='alma';
   
   for i in 1..4 loop
    dbms_output_line(i || 1.1 || team(i));
    end loop;
end;

create or replace procedure test1 as
    type foursome is varray(4) of varchar(100);
    --team foursome;
    team foursome:=foursome('A','B','C','D');
    
begin
team.extend;
    team(1):='alma';
    team.extend;
    team(2):='cucc';
    for i in 1..2 loop
        dbms_output_line(i || 1.1 || team(i));
    end loop;
end;

begin
    test1();
end;



create or replace package my_type is
    type alma is table of varchar(20) index by string;
    function init return alma;
end;

create or replace package body my_type is
    function init return alma is
        ret alma;
    begin
        ret('a'):='asd';
        ret('b'):='qwe';
        return ret;
    end;
end;

declare
    v my_type.alma:=my_type.init();
begin
    dbms_output.put_line(v('b'));
end;
