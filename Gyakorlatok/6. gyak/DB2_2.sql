DECLARE
    l_seed char(100);
    r number(4);
BEGIN
    l_seed:=to_char(SYSTIMESTAMP,'YYYYDDMMHH24MISSFFFF');
    r := dbms_random.value(0,4);
    dbms_output.put_line(r||'/4=');
    case r
        when 1 then dbms_output.put_line(trunc(r/4.0)||' 1 maradék ');
        when 2 then dbms_output.put_line(trunc(r/4.0)||' 2 maradék ');
        when 3 then dbms_output.put_line(trunc(r/4.0)||' 3 maradék ');
        else
            dbms_output.put_line(' valami más ');
    end case;    
END;