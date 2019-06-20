INSERT INTO USERS(id,email,password,user_name,last_name,active) VALUES (1,'sencer@sencer.com','123','sencer','seven',1);
INSERT INTO USERS_DETAIL(description,profile_img,users_id) VALUES ('description','sencerseven.png','1');
INSERT INTO ROLE(id,role) VALUES (1,'ADMIN');

INSERT INTO USERS_ROLE(users_id,role_id) VALUES (1,1);

INSERT INTO CATEGORY(category_name,category_description,menu_active,main_page_status,category_url) VALUES ('Oglen Yemegi','Oglen Yemegi',true,true,'oglen-yemegi');
INSERT INTO CATEGORY(category_name,category_description,menu_active,main_page_status,category_url) VALUES ('Akşam Yemegi','Akşam Yemegi',true,true,'aksam-yemegi');
INSERT INTO CATEGORY(category_name,category_description,menu_active,main_page_status,category_url) VALUES ('Kahvalti','Kahvalti',true,true,'kahvalti');
INSERT INTO CATEGORY(category_name,category_description,menu_active,main_page_status,category_url,parent_category_id) VALUES ('atiştirmalik','atiştirmalik',true,true,'atistirmalik',2);
INSERT INTO CATEGORY(category_name,category_description,menu_active,main_page_status,category_url,parent_category_id) VALUES ('omletler','omletler',true,true,'omletler',2);


INSERT INTO RECIPE(created_at,description,title,view_count,users_id) VALUES (DATE '2018-03-10','Sodali Kirpik BOrek ','Sodali Kirpik BOrek',5,1);
INSERT INTO RECIPE(created_at,description,title,view_count) VALUES (DATE '2018-06-10','Sodali BOrek Tarifi (Videolu)','Sodali BOrek Tarifi (Videolu)',6);


INSERT INTO CAT_RECIPE(recipe_id,category_id) VALUES (1,4);
INSERT INTO CAT_RECIPE(recipe_id,category_id) VALUES (2,5);

INSERT INTO RECIPE_STEPS(description,recipe_id,view_rows) VALUES ('oo mikemmel',1,3);
INSERT INTO RECIPE_STEPS(description,recipe_id,view_rows) VALUES ('oo mikemmel 2',1,1);


INSERT INTO RECIPE_TIPS(description,recipe_id,view_rows) VALUES ('oo mikemmel 2',1,1);

INSERT INTO COMMENT(text,recipe_id,users_id) VALUES ('eline saglik Cok gUzel olmuş',1,1);

INSERT INTO CUISINE(cuisine) VALUES ('TUrk Mutfagi');
INSERT INTO CUISINE(cuisine) VALUES ('Italyan Mutfagi');
INSERT INTO CUISINE(cuisine) VALUES ('Fransiz Mutfagi');



