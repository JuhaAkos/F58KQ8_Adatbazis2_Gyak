declare
    a number;
    b number;
    n number;
    c number;
begin
    a:=0;
    b:=1;
    n:=10;
    dbms_output.put_line(a);
    dbms_output.put_line(b);
    for i in 1..n loop
        c:=a+b;
        a:=b;
        b:=c;        
        
        dbms_output.put_line(c);
    end loop;
end;