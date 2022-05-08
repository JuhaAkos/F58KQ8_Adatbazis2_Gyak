create or replace trigger trig_etel_mod before update on etel for each row
begin
    if (:new.kisar < :old.kisar) then        
        :new.kisar := :old.kisar;
        raise_application_error (-20001,'A kisarat csak novelni szabad!');
    elsif (:new.nagyar < :old.nagyar) then        
        :new.nagyar := :old.nagyar;
        raise_application_error (-20002,'Az nagyarat csak novelni szabad!');
    end if;
end;
