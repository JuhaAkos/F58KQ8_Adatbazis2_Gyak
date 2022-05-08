create or replace procedure EtelFel(EID number, kisar number, nagyar number, tipus varchar2, nev varchar2) is
begin
insert into etel values(EID, kisar, nagyar, tipus, nev);
dbms_output.put_line('Etel adatok felveve!');
end;

create or replace procedure AkciokFel(EID number, lear number, honap number) is
begin
insert into akciok values(EID, lear,honap);
dbms_output.put_line('Akcio adatok felveve!');
end;

create or replace procedure MegrendelesFel(MID number, datum date, rendelo varchar2, cim varchar2, telefon varchar2) is
begin
insert into megrendeles values(MID, datum, rendelo, cim, telefon);
dbms_output.put_line('Megrendelo adatok felveve!');
end;

create or replace procedure ERKapcsoloFel(EID number, MID number)is
begin
insert into erkapcsolo values(EID, MID);
dbms_output.put_line('ERKapcsolo adatok felveve!');
end;