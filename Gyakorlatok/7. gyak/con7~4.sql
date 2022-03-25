declare

begin
    update car set kor=year-to_char(sysdate.'yyyy')-year;
end;

select * from car;