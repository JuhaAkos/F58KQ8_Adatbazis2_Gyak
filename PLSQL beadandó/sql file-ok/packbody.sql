create or replace package body etelPack is
    procedure EtelFel(EID number, kisar number, nagyar number, tipus varchar2, nev varchar2) is
    begin
    insert into etel values(EID, kisar, nagyar, tipus, nev);
    dbms_output.put_line('Etel adatok felveve!');
    end;
    
    function lekerEtelNev(azon number) return varchar2 as 
    x varchar2(30);
    begin
    select etel.nev into x from etel where etel.eid=azon;
    return x;
    end;
    
    function lekerEtelek(azon number) return etel%rowtype as 
    x etel%rowtype;
    begin
    select * into x from etel where etel.eid=azon;
    return x;
    dbms_output.put_line(x.nev);
    end;
    
    function lekerAtlagAr return number as 
    x number;
    begin
    select sum(etel.kisar)/count(*) into x from etel;
    return x;
    end;
    
    procedure DelEtel(tEID number) is
    begin
    delete from etel where etel.EID=tEID;
    dbms_output.put_line('Etel torolve!');
    end;
    
    procedure arValt(ujEID number, ujkisar number, ujnagyar number) is
    begin
    update etel set etel.kisar=ujkisar, etel.nagyar=ujnagyar where etel.EID=ujEID;
    dbms_output.put_line('Etel tabla modositva!');
    end;
    
    procedure etelMod(ujEID number, ujkisar number, ujnagyar number, ujtipus varchar2, ujnev varchar2) is
    begin
    update etel set etel.kisar=ujkisar, etel.nagyar=ujnagyar, etel.tipus=ujtipus, etel.nev=ujnev where etel.EID=ujEID;
    dbms_output.put_line('Etel tabla modositva!');
    end;

end etelPack;