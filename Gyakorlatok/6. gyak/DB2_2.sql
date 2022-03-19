DECLARE
    l_seed char(100);
    r number(4);
BEGIN
    l_seed:=to_char(SYSTIMESTAMP,'YYYYDDMMHH24MISSFFFF');
    r := dbms_random.value(0,4);
    dbms_output.put_line(r||'/4=');
    case r
        when 1 then dbms_output.put_line(trunc(r/4.0)||' 1 marad�k ');
        when 2 then dbms_output.put_line(trunc(r/4.0)||' 2 marad�k ');
        when 3 then dbms_output.put_line(trunc(r/4.0)||' 3 marad�k ');
        else
            dbms_output.put_line(' valami m�s ');
    end case;    
END;