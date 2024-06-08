USE ecommerce;

CREATE TABLE TaxCategoriesSetting (
    tax_category_id    bigint primary key comment '課税ーカテゴリーID',
    tax_category_name  varchar(64)   not null comment '課税ーカテゴリー名',
    tax_rate           decimal(3, 2) not null comment '税率',
    round_rule         char(2)       not null default (1) comment '税率規則',
    status             boolean                default (1) comment '課税状態',
    responsible_object char(2) comment '課税対象',
    description        varchar(255) comment '説明',
    created_at         timestamp comment '作成日時',
    created_user_id    bigint        not null comment '作成ユーザーID',
    updated_at         timestamp comment '更新日時',
    updated_user_id    bigint        not null comment '更新ユーザーID'
);
-- TaxCategoriesSetting table foreignkey
ALTER TABLE TaxCategoriesSetting
    ADD CONSTRAINT fk_TaxCategoriesSetting_created_user_id
        FOREIGN KEY (created_user_id) REFERENCES users (user_id);
ALTER TABLE TaxCategoriesSetting
    ADD CONSTRAINT fk_TaxCategoriesSetting_updated_user_id
        FOREIGN KEY (created_user_id) REFERENCES users (user_id);

CREATE TABLE TaxSetting (
    tax_id          bigint primary key comment '税率ーID',
    tax_category_id bigint        not null comment '課税ーカテゴリーID',
    organization_id bigint        not null comment '組織ID',
    tax_name        varchar(255)  not null comment '課税名',
    tax_rate        decimal(3, 2) not null comment '税率',
    round_rule      char(2)       not null comment '税率規則',
    start_date_time timestamp     not null comment '開始日時',
    implement_type  char(2) default ('01') comment '使用種類',
    created_at      timestamp comment '作成日時',
    created_user_id bigint        not null comment '作成ユーザーID',
    updated_at      timestamp comment '更新日時',
    updated_user_id bigint        not null comment '更新ユーザーID'
);

-- taxSetting table foreignkey
ALTER TABLE TaxSetting
    ADD CONSTRAINT fk_TaxSetting_tax_category_id
        FOREIGN KEY (tax_category_id) REFERENCES TaxCategoriesSetting (tax_category_id);
ALTER TABLE TaxSetting
    ADD CONSTRAINT fk_TaxSetting_tax_organization_id
        FOREIGN KEY (organization_id) REFERENCES Organizations (organization_id);
ALTER TABLE TaxSetting
    ADD CONSTRAINT fk_TaxSetting_updated_user_id
        FOREIGN KEY (created_user_id) REFERENCES users (user_id);