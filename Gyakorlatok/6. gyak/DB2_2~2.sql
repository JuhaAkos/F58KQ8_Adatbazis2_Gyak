DECLARE
    loopmax number(2);
    summa number(4);
BEGIN
    summa:=0;
    loopmax := :loopmax;
    for i in 1..loopmax loop
        summa:=summa+i;
    end loop;
    
    dbms_output.put_line('1-t�l'||loopmax||'-ig a sz�mok �ssze: '|| summa);
END;