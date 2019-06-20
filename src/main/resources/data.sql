INSERT INTO users(id,email,password,user_name,last_name,active) VALUES (1,'sencer@sencer.com','123','sencer','seven',1);
INSERT INTO users_detail(description,profile_img,user_id) VALUES ('Bu benim description yazımdır.','sencerseven.png','1');
INSERT INTO role(id,role) VALUES (1,'ADMIN');

INSERT INTO user_role(user_id,role_id) VALUES (1,1);

INSERT INTO category(category_name,category_description,menu_active,main_page_status,category_url) VALUES ('Öğlen Yemeği','Öğlen Yemeği',true,true,'oglen-yemegi');
INSERT INTO category(category_name,category_description,menu_active,main_page_status,category_url) VALUES ('Akşam Yemeği','Akşam Yemeği',true,true,'aksam-yemegi');
INSERT INTO category(category_name,category_description,menu_active,main_page_status,category_url) VALUES ('Kahvaltı','Kahvaltı',true,true,'kahvalti');
INSERT INTO category(category_name,category_description,menu_active,main_page_status,category_url,parent_category_id) VALUES ('atıştırmalık','atıştırmalık',true,true,'atistirmalik',2);
INSERT INTO category(category_name,category_description,menu_active,main_page_status,category_url,parent_category_id) VALUES ('omletler','omletler',true,true,'omletler',2);


INSERT INTO recipe(created_at,description,title,view_count,user_id) VALUES (DATE '2018-03-10','Sodalı Kırpık Börek ','Sodalı Kırpık Börek',5,1);
INSERT INTO recipe(created_at,description,title,view_count) VALUES (DATE '2018-06-10','Sodalı Börek Tarifi (Videolu)','Sodalı Börek Tarifi (Videolu)',6);


INSERT INTO cat_recipe(RECIPE_ID,CATEGORY_ID) VALUES (1,4);
INSERT INTO cat_recipe(RECIPE_ID,CATEGORY_ID) VALUES (2,5);

INSERT INTO recipe_steps(description,recipe_id,view_rows) VALUES ('oo mikemmel',1,3);
INSERT INTO recipe_steps(description,recipe_id,view_rows) VALUES ('oo mikemmel 2',1,1);


INSERT INTO recipe_tips(description,recipe_id,view_rows) VALUES ('oo mikemmel 2',1,1);

INSERT INTO comment(text,recipe_id,user_id) VALUES ('eline sağlık çok güzel olmuş',1,1);

INSERT INTO cuisine(cuisine) VALUES ('Türk Mutfağı');
INSERT INTO cuisine(cuisine) VALUES ('İtalyan Mutfağı');
INSERT INTO cuisine(cuisine) VALUES ('Fransız Mutfağı');



