insert into Kind (id, kind) values (1, '支出');
insert into Kind (id, kind) values (2, '収入');
insert into Kind (id, kind) values (3, '転出');
insert into Kind (id, kind) values (4, '転入');

insert into Currency (id, currency) values (1, 'JPY');
insert into Currency (id, currency) values (2, 'USD');
insert into Currency (id, currency) values (3, 'CAD');
insert into Currency (id, currency) values (4, 'EUR');

insert into Category (id, category, kind_id) values (101, '食費', 1);
insert into Category (id, category, kind_id) values (102, '生活費', 1);
insert into Category (id, category, kind_id) values (103, '衣料費', 1);
insert into Category (id, category, kind_id) values (104, '医療費', 1);
insert into Category (id, category, kind_id) values (105, '美容費', 1);
insert into Category (id, category, kind_id) values (106, '教育費', 1);
insert into Category (id, category, kind_id) values (107, '書籍費', 1);
insert into Category (id, category, kind_id) values (108, '交通費', 1);
insert into Category (id, category, kind_id) values (109, '通信費', 1);
insert into Category (id, category, kind_id) values (110, '娯楽費', 1);
insert into Category (id, category, kind_id) values (111, '献金', 1);
insert into Category (id, category, kind_id) values (112, '手数料', 1);

insert into Category (id, category, kind_id) values (201, '給与', 2);
insert into Category (id, category, kind_id) values (202, '贈与', 2);

insert into Category (id, category, kind_id) values (301, '預金', 3);
insert into Category (id, category, kind_id) values (401, '引出', 4);
