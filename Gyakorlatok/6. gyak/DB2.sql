DECLARE
    --r number(2):=12;
    --pi CONSTANT number(3,2):=3.14;
    v_name varchar2(100);
BEGIN
    --dbms_output.put_line(r || ' sugar� k�r ter�lete: ' || r*r*pi);
    dbms_output.put_line(Upper(:v_name));
END;

--SET SERVEROUTPUT ON