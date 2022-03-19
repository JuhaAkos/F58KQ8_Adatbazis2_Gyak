DECLARE
    db integer := 0;
    pi number(6.5):=1;
    denominator integer:=1;

BEGIN
    while db<=1000 Loop
        denominator:=denominator+2;
        if mod(db,2)=0 then
            pi:=pi-1/(denominator+2)
        else
            pi:=pi+1/denominator
        db:=db+1;
        end if;
        db:=db+1;
    end loop;
    
    dbms_output.put_line('Pi= '||pi*4);
END;