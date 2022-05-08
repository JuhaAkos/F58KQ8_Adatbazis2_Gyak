create or replace package etelPack is
     procedure EtelFel(EID number, kisar number, nagyar number, tipus varchar2, nev varchar2);
     function lekerEtelNev(azon number) return varchar2;
     function lekerEtelek(azon number) return etel%rowtype;
     function lekerAtlagAr return number;
     procedure DelEtel(tEID number);
     procedure arValt(ujEID number, ujkisar number, ujnagyar number);
     procedure etelMod(ujEID number, ujkisar number, ujnagyar number, ujtipus varchar2, ujnev varchar2);
end etelPack;
