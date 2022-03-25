select * from car;
alter table car add(kor number(2));
alter table car modify(year number(10));
select * from car;

declare
    new_year number(10);
    color varchar2(10);
begin
    new_year:=:new_year;
    color:=:color;
    update car set year=new_year where color=color;
end;