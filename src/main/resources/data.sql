INSERT INTO USERS(email,password,user_name,last_name,active) VALUES ('sencer@sencer.com','123','sencer','seven',1);
INSERT INTO USERS_DETAIL(description,profile_img,user_id) VALUES ('Bu benim description yazımdır.','sencerseven.png','1');
INSERT INTO ROLE(role) VALUES ('ADMIN');

INSERT INTO USER_ROLE(user_id,role_id) VALUES (1,1);

INSERT INTO CATEGORY(category_name,category_description,menu_active,category_url) VALUES ('Öğlen Yemeği','Öğlen Yemeği',true,'oglen-yemegi');
INSERT INTO CATEGORY(category_name,category_description,menu_active,category_url) VALUES ('Akşam Yemeği','Akşam Yemeği',true,'aksam-yemegi');
INSERT INTO CATEGORY(category_name,category_description,menu_active,category_url) VALUES ('Kahvaltı','Kahvaltı',true,'kahvalti');
INSERT INTO CATEGORY(category_name,category_description,menu_active,category_url,parent_category_id) VALUES ('atıştırmalık','atıştırmalık',true,'atistirmalik',2);
INSERT INTO CATEGORY(category_name,category_description,menu_active,category_url,parent_category_id) VALUES ('omletler','omletler',true,'omletler',2);


INSERT INTO CUISINE(cuisine) VALUES ('Türk Mutfağı');
INSERT INTO CUISINE(cuisine) VALUES ('İtalyan Mutfağı');
INSERT INTO CUISINE(cuisine) VALUES ('Fransız Mutfağı');



