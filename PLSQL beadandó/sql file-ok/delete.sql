create or replace procedure DelEtel(tEID number) is
begin
       delete from etel where etel.EID=tEID;
       dbms_output.put_line('Etel torolve!');
end;

create or replace procedure DelAkciok(tEID number) is
begin
       delete from akciok where akciok.EID=tEID;
       dbms_output.put_line('Akcio torolve!');
end;

create or replace procedure DelRendeles(tMID number) is
begin
       delete from megrendeles where megrendeles.MID=tMID;
       dbms_output.put_line('Rendeles torolve!');
end;

create or replace procedure DelErkapcsolo(tEID number) is
begin
       delete from erkapcsolo where erkapcsolo.EID=tEID;
       dbms_output.put_line('erkapcsolo torolve!');
end;