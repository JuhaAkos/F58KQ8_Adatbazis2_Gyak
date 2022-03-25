declare
    a NUMBER;
    b NUMBER;
    c NUMBER;
    t NUMBER;
    s NUMBER;
begin
    a:=3;
    b:=4;
    c:=5;
    if (a+b)>c and (a+c)>b and (b+c)>a THEN
        s:=(a+b+c)/2;
        t:=(s*(s-a)*(s-b)*(s-c))**0.5;
        dbms_output.put_line('Haromszog területe: '||t);
    else
        dbms_output.put_line('Ez nem egy igazi haromszog!');
    end if;  
   
end;