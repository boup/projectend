-----insert in city table
insert into city(city_name,latitude,longitude) values('Dakar','-17.467686°W','14.716677°N')

insert into city(city_name,latitude,longitude) values('Saint-Louis','16.4818°W','16.0326°N')
insert into city(city_name,latitude,longitude) values('Louga','16.2287° W','15.6142° N')

insert into city(city_name,latitude,longitude) values('Thies','16.9359° W','14.7910° N')

insert into city(city_name,latitude,longitude) values('Ziguinchor','16.2640° W','12.5641° N')

insert into city(city_name,latitude,longitude) values('Matam','13.2577° W','15.6600° N')

insert into city(city_name,latitude,longitude)values('Kaolack',' 16.0758° W','14.1652° N')

insert into city(city_name,latitude,longitude)values('Diourbel','16.2346° W','14.6561° N')

insert into city(city_name,latitude,longitude) values('Kedougou','12.1747° W','12.5605° N')

insert into city(city_name,latitude,longitude) values('Kolda','14.9506° W','12.9107° N')

insert into city(city_name,latitude,longitude)values('Tambacounda','13.6710° W','13.7726° N')

insert into city(city_name,latitude,longitude) values('Fatick','16.4111° W','14.3390° N')

insert into city(city_name,latitude,longitude) values('Kaffrine','15.5416° W','14.1052° N')

insert into city(city_name,latitude,longitude) values('Sédhiou','15.5562° W','12.7046° N')

------insert in Line table---
insert into line (number,link,city_id) values(1,
insert into line(link,number,city_id) values(1,4,6,7,8,9,10,13,18,20,23,121,2,5,11,12,15,16A,16B,217,218,219,227,228,232,'http://demdikk.com/ligne-13-liberte-5-palais-2/','iddakar')

insert into line values('1,4,6,7,8,9,10,13,18,20,23,121,2,5,11,12,15,16A,16B,217,218,219,227,228,232','http://demdikk.com/ligne-13-liberte-5-palais-2/','idthies')

insert into line values('1,4,6,7,8,9,10,13,18,20,23,121,2,5,11,12,15,16A,16B,217,218,219,227,228,232','http://demdikk.com/ligne-13-liberte-5-palais-2/','idsaintlouis')

insert into line values('1,4,6,7,8,9,10,13,18,20,23,121,2,5,11,12,15,16A,16B,217,218,219,227,228,232','http://demdikk.com/ligne-13-liberte-5-palais-2/','idkaolack')

insert into line values('1,4,6,7,8,9,10,13,18,20,23,121,2,5,11,12,15,16A,16B,217,218,219,227,228,232','http://demdikk.com/ligne-13-liberte-5-palais-2/','idsedhiou')

insert into line values('1,4,6,7,8,9,10,13,18,20,23,121,2,5,11,12,15,16A,16B,217,218,219,227,228,232','http://demdikk.com/ligne-13-liberte-5-palais-2/','iddiourbel')

------table operator-----

insert into operator(description,link,name_operator,city_id) values('Public Bus','http://demdikk.com/','Dakar Dem Dikk',1);

insert into operator(description,link,name_operator,city_id) values('Private Bus','http://aftu-senegal.org/','Aftu',1);

insert into operator(description,link,name_operator,city_id) values('Private Bus/Taxi','https://carrapideprestige.business.site/?utm_source=gmb&utm_medium=referral','Car Rapide transport',1);

insert into operator(description,link,name_operator,city_id) values('Private Bus/Taxi','https://courses.rapidosapp.com/','Rapidos Car',1);

insert into operator(description,link,name_operator,city_id) values('Dakar Dem Dkk','http://demdikk.com/',1);

-----table ticket----remark i will add price
insert into ticket(duration,price,typeof_ticket,city_id) values ('End of section 1',150,'section 1', 1);
insert into ticket(duration,price,typeof_ticket,city_id) values ('End of section 2',175,'section 2', 1);
insert into ticket(duration,price,typeof_ticket,city_id) values ('End of section 3',200,'section 3', 1);
insert into ticket(duration,price,typeof_ticket,city_id) values ('End of section 4',250,'section 4', 1);
insert into ticket(duration,price,typeof_ticket,city_id) values ('End of section 5',300,'section 5', 1);
insert into ticket(duration,price,typeof_ticket,city_id) values ('End of sous/under section ', 100, 'sous/under section ', 1);
--insert into ticekt values ('20min',175,'zone1','cityid')
----insert into travel bus--
insert into travelbus(price,link,city_id) values(3500,'http://demdikk.com/senegal_dem_dikk/',1);
insert into travelbus(price,link,city_id) values(6000,'http://rapido.com',1);
insert into travelbus(price,link,city_id) values(7000,'http://senegaltours.com',1);
---insert table travelfly
insert into travelfly (price,company, city_id) values (70000,'http://edreams.com/flights/dakar-ziguinchor/DKR/ZIG',1);
insert into travelfly (price,company,city_id) values (80000,'http://edreams.com/flights/dakar-ziguinchor/DKR/ZIG',1);
insert into travelfly (price,company,city_id) values (100000,'http://edreams.com/flights/dakar-ziguinchor/DKR/ZIG',1);

-----table  transcity---

insert into transcity values('http://demdikk.com/senegal_dem_dikk/','Bus Senegal Dem Dikk','city_id')
insert into transcity values('http://edreams.com/flights/dakar-ziguinchor/DKR/ZIG','Avion','city_id')
insert into transcity values('http://edreams.com/flights/dakar-ziguinchor/DKR/ZIG','Avion','city_id')
insert into transcity values('http://edreams.com/flights/dakar-ziguinchor/DKR/ZIG','Avion','city_id')


1,4,6,7,8,9,10,13,18,20,23,121,2,5,11,12,15,16A,16B,217,218,219,227,228,232
