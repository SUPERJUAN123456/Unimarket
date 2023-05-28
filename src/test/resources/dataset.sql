insert into person values ("1005088944", "Barrio Manantiales 2 etapa ", "3226634625", "Armenia", "Colombia","juanesmosquera23@gmail.com","Juan","$2a$10$agn/vAblv8Cslt2townIkOot/8FwJTcxacKCVrkVLxF/Mr1spTpcS",0);
insert into person values ("1001010101", "La patria", "3233709142", "Armenia", "Colombia","sandra@gmail.com","Sandra Maria"," $2a$10$6Hm.s/5DnuB7476rmXS0ze79oLSnSpdpIvoQRaXyxzly47aCalxD2",1);
insert into person values ("41942243", "Bosque de pinares", "3045205818", "Armenia", "Colombia","cristyvlm@gmail.com","Cristy Vanessa","$2a$10$tId/XpX0HXc27IzRG3TjmuLgd8GPbEH.kLZ9z1giIleJ3NeFaQCIy",1);
insert into person values ("1106060606", "Villa Liliana", "3035789203", "Armenia", "Colombia","caloa567@gmail.com","Albeiro","$2a$10$hgr3ERjT8D9EwRcM9ix4f.kb9LxkTEWPROOTFBsKSSr9ZZpEuCgPe",1);
insert into person values ("1007070707", "Villa Andrea", "3117829030", "Armenia", "Colombia","pilicar69@gmail.com","Maria del Pilar","$2a$10$hgr3ERjT8D9EwRcM9ix4f.kb9LxkTEWPROOTFBsKSSr9ZZpEuCgPe",1);

insert into category values(1,"Tecnologia","desktop/category/img1");
insert into category values(2,"Ropa","desktop/category/img2");
insert into category values(3,"Deporte","desktop/category/img3");
insert into category values(4,"Inmueble","desktop/category/img4");
insert into category values(5,"Entretenimiento","desktop/category/img5");

insert into product values (1,"2023-04-24","2023-05-24","Computador HP negro i5",0,1500000.0,0,1500000,0,"Computador i5",2,1,"1005088944");
insert into product values (2,"2023-04-24","2023-05-24","Camiseta de algohodon negra talla S",0,90500.0,0,90500,0,"Camiseta de alhodon",100,2,"1001010101");
insert into product values (3,"2023-04-24","2023-05-24","Mancuerna de acero con un peso de 25Kg",0,250000.0,0,250000,0,"Mancuerna 25kg",25,3,"41942243");
insert into product values (4,"2023-04-24","2023-05-24","Sofa",0,820000.0,0,820000,0,"Sofa rojo",0,4,"1106060606");
insert into product values (5,"2023-04-24","2023-05-24","Computador Gamer Pro",10,2499990.0,0,2499990,0,"Computador Gamer intel i9",25,5,"1007070707");

insert into product_images values(1,"prueba1","desktop/product/img1");
insert into product_images values(2,"prueba2","desktop/product/img2");
insert into product_images values(3,"prueba3","desktop/product/img3");
insert into product_images values(4,"prueba4","desktop/product/img4");
insert into product_images values(5,"prueba5","desktop/product/img5");

insert into person_favorite_products values("1005088944",1);
insert into person_favorite_products values("1005088944",2);
insert into person_favorite_products values("41942243",3);
insert into person_favorite_products values("1007070707",4);
insert into person_favorite_products values("41942243",5);

insert into comment values(1,"Me llego en buen estado el computador, muy buena atención :)","2022-10-15",5,1,"1005088944");
insert into comment values(2,"Muy comoda la camisa","2022-02-17",5,2,"1106060606");
insert into comment values(3,"La mancuerna es de muy buena calidad, aunque la caja me llego un poco maltratada","2022-03-20",4,3,"1005088944");
insert into comment values(4,"Muy grande y bonito el sofá","2022-11-09",5,4,"1001010101");
insert into comment values(5,"El pc llego en perfecto estado y esta muy bueno para jugar","2022-12-22",5,5,"1007070707");

insert into payment_method values(1,"Bancolombia","500424520102",762,"2028-12-01",true,"Juan Esteban Mosquera Zapata","1005088944");
insert into payment_method values(2,"BBVA","500274531102",123,"2029-11-01",true,"Sandra María Cortés","1001010101");
insert into payment_method values(3,"Bancolombia","780424220102",533,"2025-09-01",true,"Cristy Vanessa Loaiza Marín","41942243");
insert into payment_method values(4,"BBVA","800424520102",523,"2024-02-01",true,"Albeiro Castro Lopez","1106060606");
insert into payment_method values(5,"Bancolombia","689424520102",831,"2028-04-01",true,"María del Pilar Cardona Villa","1007070707");

insert into transaction values (1,"2023-01-05",1500000.0,1,"1005088944");
insert into transaction values (2,"2023-01-02",90500.0,2,"1106060606");
insert into transaction values (3,"2023-04-03",250000.0,3,"1005088944");
insert into transaction values (4,"2023-02-07",820000.0,4,"1001010101");
insert into transaction values (5,"2023-01-22",2499990.0,5,"1007070707");

insert into transaction_detail values (1,1500000,1,1,1);
insert into transaction_detail values (2,90500,1,2,2);
insert into transaction_detail values (3,250000,1,3,3);
insert into transaction_detail values (4,820000,1,4,4);
insert into transaction_detail values (5,2499990,1,5,5);

