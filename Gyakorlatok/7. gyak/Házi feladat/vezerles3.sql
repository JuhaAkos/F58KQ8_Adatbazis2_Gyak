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
        dbms_output.put_line(c||', '||mi||' és '||ma||' között van!');
    else
        dbms_output.put_line(c||' nincs '||mi||' és '||ma||' között!');
    end if;
     
end;