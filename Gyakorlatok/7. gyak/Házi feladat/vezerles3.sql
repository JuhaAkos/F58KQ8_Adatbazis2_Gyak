set serveroutput on;
declare
    mi NUMBER;
    ma NUMBER;
    c number;

begin
    mi:=10;
    ma:=100;
    c:=78;
    if c between mi and ma THEN
        dbms_output.put_line(c||', '||mi||' �s '||ma||' k�z�tt van!');
    else
        dbms_output.put_line(c||' nincs '||mi||' �s '||ma||' k�z�tt!');
    end if;
     
end;