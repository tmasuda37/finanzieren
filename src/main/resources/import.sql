insert into Kind (id, kind) values (1, '支出');
insert into Kind (id, kind) values (2, '収入');
insert into Kind (id, kind) values (3, '転出');
insert into Kind (id, kind) values (4, '転入');

insert into Currency (id, currency) values (1, 'JPY');
insert into Currency (id, currency) values (2, 'USD');
insert into Currency (id, currency) values (3, 'CAD');
insert into Currency (id, currency) values (4, 'EUR');

insert into Category (id, category, kind_id) values (1, '食費', 1);
insert into Category (id, category, kind_id) values (2, '生活費', 1);
insert into Category (id, category, kind_id) values (3, '衣料費', 1);
insert into Category (id, category, kind_id) values (4, '医療費', 1);
insert into Category (id, category, kind_id) values (5, '教育費', 1);
insert into Category (id, category, kind_id) values (6, '交通費', 1);
insert into Category (id, category, kind_id) values (7, '通信費', 1);
insert into Category (id, category, kind_id) values (8, '趣味、レジャー', 1);
insert into Category (id, category, kind_id) values (9, '交際費', 1);
insert into Category (id, category, kind_id) values (10, '雑費', 1);
insert into Category (id, category, kind_id) values (11, '献金', 1);
insert into Category (id, category, kind_id) values (12, '給与', 2);
insert into Category (id, category, kind_id) values (13, '贈与', 2);
insert into Category (id, category, kind_id) values (14, '銀行預金', 3);
insert into Category (id, category, kind_id) values (15, '銀行引出', 4);
