INSERT INTO USERS(email,password,user_name,last_name,active) VALUES ('sencer@sencer.com','123','sencer','seven',1);
INSERT INTO USERS_DETAIL(description,profile_img,user_id) VALUES ('Bu benim description yazımdır.','sencerseven.png','1');
INSERT INTO ROLE(role) VALUES ('ADMIN');

INSERT INTO USER_ROLE(user_id,role_id) VALUES (1,1);

INSERT INTO CATEGORY(category_name,category_description) VALUES ('Sıcak Yemekler','Sıcak Yemekler');


INSERT INTO RECIPE(recipe_description,recipe_title,view_count,users_id) VALUES ('Sodalı Kırpık Börek ','Sodalı Kırpık Börek',5,1);
INSERT INTO RECIPE(recipe_description,recipe_title,view_count) VALUES ('Sodalı Börek Tarifi (Videolu)','Sodalı Börek Tarifi (Videolu)',6);

INSERT INTO RECIPE_STATS(recipe_id) VALUES (1);

INSERT INTO RECIPE_STEPS(description,recipe_id,view_rows) VALUES ('oo mikemmel',1,3);
INSERT INTO RECIPE_STEPS(description,recipe_id,view_rows) VALUES ('oo mikemmel 2',1,1);


INSERT INTO RECIPE_TIPS(description,recipe_id,view_rows) VALUES ('oo mikemmel 2',1,1);

INSERT INTO COMMENT(text,recipe_id,users_id) VALUES ('eline sağlık çok güzel olmuş',1,1);



