insert into Kind (id, kind) values (0, '支出');
insert into Kind (id, kind) values (1, '収入');
insert into Kind (id, kind) values (2, '転出');
insert into Kind (id, kind) values (3, '転入');

insert into Currency (id, currency) values (0, 'JPY');
insert into Currency (id, currency) values (1, 'USD');
insert into Currency (id, currency) values (2, 'CAD');
insert into Currency (id, currency) values (3, 'EUR');

insert into Category (id, category, kind_id) values (0, '食費', 0);
insert into Category (id, category, kind_id) values (1, '生活費', 0);
insert into Category (id, category, kind_id) values (2, '衣料費', 0);
insert into Category (id, category, kind_id) values (3, '医療費', 0);
insert into Category (id, category, kind_id) values (4, '教育費', 0);
insert into Category (id, category, kind_id) values (5, '交通費', 0);
insert into Category (id, category, kind_id) values (6, '通信費', 0);
insert into Category (id, category, kind_id) values (7, '趣味、レジャー', 0);
insert into Category (id, category, kind_id) values (8, '交際費', 0);
insert into Category (id, category, kind_id) values (9, '雑費', 0);
insert into Category (id, category, kind_id) values (10, '献金', 0);
insert into Category (id, category, kind_id) values (11, '給与', 1);
insert into Category (id, category, kind_id) values (12, '贈与', 1);
