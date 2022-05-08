create or replace procedure arValt(ujEID number, ujkisar number, ujnagyar number) is
begin
       update etel set etel.kisar=ujkisar, etel.nagyar=ujnagyar where etel.EID=ujEID;
       dbms_output.put_line('Etel tabla modositva!');
end;

create or replace procedure etelMod(ujEID number, ujkisar number, ujnagyar number, ujtipus varchar2, ujnev varchar2) is
begin
       update etel set etel.kisar=ujkisar, etel.nagyar=ujnagyar, etel.tipus=ujtipus, etel.nev=ujnev where etel.EID=ujEID;
       dbms_output.put_line('Etel tabla modositva!');
end;

create or replace procedure akciokMod(ujEID number, ujlear number, ujhonap number) is
begin
       update akciok set akciok.learazas=ujlear, akciok.honap=honap where akciok.EID=ujEID;
       dbms_output.put_line('Akciok tabla modositva!');
end;

create or replace procedure megrendelesMod(ujMID number, ujdatum date, ujrendelo varchar2, ujcim varchar2, ujtelefon varchar2) is
begin
       update megrendeles set megrendeles.datum=ujdatum, megrendeles.megrendelo=ujrendelo, megrendeles.cim=ujcim, megrendeles.telefon=ujtelefon where megrendeles.MID=ujMID;
       dbms_output.put_line('Megrendeles tabla modositva!');
end;