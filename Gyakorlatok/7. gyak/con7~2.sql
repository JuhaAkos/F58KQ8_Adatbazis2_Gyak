set serveroutput on;
declare
    a NUMBER;
    b NUMBER;
    c NUMBER;
    t NUMBER;
    s NUMBER;
begin
    a:=3;
    b:=4;
    b:=5;
    if (a+b)>c and (a+c)>b and (b+c)>a THEN
        dbms_output.put_line('Lehet h�romsz�g!');
        s:=(a+b+c)/2;
        t:=(s*(s-a)*(s-b)*(s-c))**0.5;
        dbms_output.put_line('Ter�lete: '||t);
    else
        dbms_output.put_line('Nem lehet h�romsz�g! :c');
    end if;
    
    
    
    
    dbms_output.put_line('V�ge');    
end;