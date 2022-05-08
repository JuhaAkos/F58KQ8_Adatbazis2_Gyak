CREATE SEQUENCE etel_seq;

CREATE OR REPLACE TRIGGER etel_insert
  BEFORE INSERT ON etel
  FOR EACH ROW
BEGIN
  SELECT etel_seq.nextval
  INTO :new.EID
  FROM dual;
END;

