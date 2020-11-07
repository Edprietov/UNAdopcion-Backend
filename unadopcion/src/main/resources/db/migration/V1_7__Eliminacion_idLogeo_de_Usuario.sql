alter table logeo drop constraint fk_logeado2;
alter table logeo drop column usuario_id;
alter table logeo add column token varchar(64);
alter table usuario drop column usuario_foto;