set serveroutput on;
declare
    a NUMBER;
    b NUMBER;

begin
    a:=10;
    b:=81;
    if b > a THEN
        dbms_output.put_line(b||' nagyobb, mint '||a);
    elsif b < a THEN
        dbms_output.put_line(a||' nagyobb, mint '||b);
    else
        dbms_output.put_line(a||' es '||b||' egyenloek!');
    end if;
     
end;