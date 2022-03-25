declare
    n number;
begin
    n:=10;
    for i in 1..n loop
        dbms_output.put_line(i);
    end loop;
end;