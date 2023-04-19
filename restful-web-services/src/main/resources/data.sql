insert into user_details(id,birth_date,name)
values(1001,current_date(),'tony');

insert into user_details(id,birth_date,name)
values(1003,current_date(),'hari');

insert into post(id,description,user_id)
values(2001,'I want to learn aws',1001);

insert into post(id,description,user_id)
values(2002,'I want to learn react redux',1001);