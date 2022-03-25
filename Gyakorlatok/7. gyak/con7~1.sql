set serveroutput on;
declare
    a NUMBER;
    b NUMBER;
    c NUMBER;
begin
    a:=10;
    b:=81;
    if a>b THEN
        dbms_output.put_line(a||' nagyobb, mint '||b);
    elsif b<a THEN
        dbms_output.put_line(b||' nagyobb, mint '||a);
    else
        dbms_output.put_line(a||' es '||b||' egyenloek!');
    end if;
    
    c:=:c;
    if c>b and c<a then
        dbms_output.put_line('kettõ között van');
    elsif c<b and c>a then
        dbms_output.put_line('kettõ között van');
    else
        dbms_output.put_line('nincs a kettõ között');
    end if;    
     
end;