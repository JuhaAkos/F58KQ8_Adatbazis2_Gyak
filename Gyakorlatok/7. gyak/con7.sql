set serveroutput on;
declare
    vezeteknev VARCHAR2(300);
    keresztnev VARCHAR2(300);
begin
    keresztnev:=:keresztnev;
    vezeteknev:=:vezeteknev;
    
    for i in 1..3 loop
        if keresztnev like 's�n' THEN
            dbms_output.put_line('Ez nem is n�v');
        end if;
        dbms_output.put_line(vezeteknev||' '||keresztnev);
    end loop;
end;  