create table bulletin
(
    id            bigint auto_increment
        primary key,
    type_id       bigint              null,
    content       longtext            not null,
    title         varchar(100)        not null,
    top           bit    default b'0' not null,
    file_path     varchar(300)        null,
    file_name     varchar(50)         null,
    file_ext_name varchar(10)         null,
    file_size     bigint default 0    null,
    add_user      bigint              not null,
    add_time      datetime            not null,
    update_user   bigint              null,
    update_time   datetime            null,
    is_deleted    int(1) default 0    not null
);

create table cro_comment
(
    id              bigint auto_increment
        primary key,
    user_id         bigint            not null,
    auditor         bigint            null,
    auditor_opinion varchar(300)      null,
    add_user        bigint            null,
    add_time        datetime          null,
    update_user     bigint            null,
    update_time     datetime          null,
    is_deleted      tinyint default 0 not null,
    auditor_time    datetime          null,
    state_id        bigint  default 0 not null
);

create table cro_download
(
    cro_source_id bigint   not null,
    user_id       bigint   not null,
    add_time      datetime null,
    add_user      bigint   null,
    update_user   bigint   null,
    update_time   datetime null
);

create table cro_favorite
(
    cro_source_id bigint   not null,
    user_id       bigint   not null,
    add_user      bigint   null,
    add_time      datetime null,
    update_user   bigint   null,
    update_time   datetime null
)
    comment 'record view count of resource';

create table cro_resource
(
    id bigint auto_increment
        primary key
);

create table cro_star
(
    cro_source_id bigint        not null,
    user_id       bigint        not null,
    Score         int default 0 null,
    add_user      bigint        null,
    add_time      datetime      null,
    update_user   bigint        null,
    update_time   datetime      null
)
    comment 'record view count of resource';

create table cro_view
(
    cro_source_id bigint   not null,
    user_id       bigint   not null,
    add_user      bigint   null,
    add_time      datetime null,
    update_user   bigint   null,
    update_time   datetime null
)
    comment 'record view count of resource';

create table dictionary
(
    id          bigint auto_increment
        primary key,
    name        varchar(50)  not null,
    parentId    bigint       not null,
    description varchar(500) null,
    code        varchar(100) null,
    sort        bigint       null,
    relevant_id bigint       null,
    value       varchar(50)  null
);

create table menu
(
    id          bigint auto_increment
        primary key,
    parent_id   bigint default 0 not null,
    level       bigint           not null,
    path        varchar(100)     not null,
    title       varchar(50)      not null,
    state_id    bigint           null,
    add_user    bigint           not null,
    add_time    datetime         not null,
    update_user bigint           null,
    update_time datetime         null,
    is_deleted  int(1) default 0 not null
)
    comment 'menus';

create table menu_role
(
    menu_id     bigint           not null,
    role_id     bigint           not null,
    add_user    bigint           null,
    add_time    datetime         null,
    update_user bigint           null,
    update_time datetime         null,
    is_deleted  bit default b'0' not null
);

create table package
(
    id bigint auto_increment
        primary key
);

create table package_download
(
    package_id  bigint   not null,
    user_id     bigint   not null,
    add_time    datetime null,
    add_user    bigint   null,
    update_user bigint   null,
    update_time datetime null
);

create table package_favorite
(
    package_id  bigint   not null,
    user_id     bigint   not null,
    add_user    bigint   null,
    add_time    datetime null,
    update_user bigint   null,
    update_time datetime null
)
    comment 'record view count of resource';

create table package_star
(
    package_id  bigint        not null,
    user_id     bigint        not null,
    Score       int default 0 null,
    add_user    bigint        null,
    add_time    datetime      null,
    update_user bigint        null,
    update_time datetime      null
)
    comment 'record view count of resource';

create table package_view
(
    package_id  bigint   not null,
    user_id     bigint   not null,
    add_user    bigint   null,
    add_time    datetime null,
    update_user bigint   null,
    update_time datetime null
)
    comment 'record view count of resource';

create table res_comment
(
    id              bigint auto_increment
        primary key,
    user_id         bigint            not null,
    auditor         bigint            null,
    auditor_opinion varchar(300)      null,
    add_user        bigint            null,
    add_time        datetime          null,
    update_user     bigint            null,
    update_time     datetime          null,
    is_deleted      tinyint default 0 not null,
    auditor_time    datetime          null,
    state_id        bigint  default 0 not null
);

create table res_company
(
    id           bigint auto_increment
        primary key,
    company_name varchar(255)     null,
    parent_id    bigint           not null,
    add_user     bigint           not null,
    add_time     datetime         not null,
    update_user  bigint           null,
    update_time  datetime         null,
    is_deleted   int(1) default 0 not null
);

create table res_download_copy
(
    resource_id bigint   not null,
    user_id     bigint   not null,
    add_time    datetime null,
    add_user    bigint   null,
    update_user bigint   null,
    update_time datetime null
);

create table res_favorite
(
    resource_id bigint   not null,
    user_id     bigint   not null,
    add_user    bigint   null,
    add_time    datetime null,
    update_user bigint   null,
    update_time datetime null
)
    comment 'record view count of resource';

create table res_package
(
    packageId   bigint           not null,
    resourceId  bigint           not null,
    add_user    bigint           null,
    add_time    datetime         null,
    update_user bigint           null,
    update_time datetime         null,
    is_deleted  bit default b'0' not null
);

create table res_real
(
    id            bigint auto_increment comment '主键id'
        primary key,
    card_no       varchar(50)            not null comment '卡号',
    id_card       varchar(50) default '' not null comment '身份证件号',
    card_password varchar(50)            not null,
    real_name     varchar(50)            not null,
    company_id    bigint                 not null comment '单位id',
    state_id      bigint      default 1  not null comment '状态id',
    add_user      bigint                 null,
    add_time      datetime               null,
    update_user   bigint                 null,
    update_time   datetime               null,
    is_deleted    tinyint     default 0  not null
);

create table res_role
(
    id          int auto_increment
        primary key,
    role_name   varchar(50)      not null,
    description varchar(255)     null,
    add_user    bigint           not null,
    add_time    datetime         not null,
    update_user bigint           null,
    update_time datetime         null,
    is_deleted  bit default b'0' not null
);

create table res_star
(
    resource_id bigint        not null,
    user_id     bigint        not null,
    Score       int default 0 null,
    add_user    bigint        null,
    add_time    datetime      null,
    update_user bigint        null,
    update_time datetime      null
)
    comment 'record view count of resource';

create table res_user
(
    id              bigint(50) auto_increment
        primary key,
    user_name       varchar(255)          not null,
    password        varchar(255)          not null,
    question        varchar(255)          null,
    answer          varchar(255)          null,
    real_name       varchar(255)          not null,
    photo_path      varchar(500)          null,
    gender          smallint(1) default 1 not null,
    mobile          varchar(11)           not null,
    email           varchar(255)          not null,
    id_card         varchar(255)          not null,
    company_id      int                   null,
    register_time   datetime              null,
    last_login_time datetime              null,
    state_id        bigint      default 0 not null,
    login_count     int(20)     default 0 not null,
    add_user        bigint                not null,
    add_time        datetime              not null,
    update_user     bigint                null,
    update_time     datetime              null,
    is_deleted      int(1)      default 0 not null
);

create table res_user_role
(
    user_id  bigint   not null,
    role_id  bigint   not null,
    add_time datetime null
);

create table res_view
(
    resource_id bigint   not null,
    user_id     bigint   not null,
    add_user    bigint   null,
    add_time    datetime null,
    update_user bigint   null,
    update_time datetime null
)
    comment 'record view count of resource';

create table resource
(
    id               bigint auto_increment comment '主键自增id'
        primary key,
    title            varchar(50)       not null comment 'title for resource',
    author           varchar(50)       not null,
    keywords         varchar(50)       not null,
    description      varchar(3000)     null,
    cover_path       varchar(500)      null,
    file_name        varchar(50)       null,
    resource_path    varchar(500)      not null,
    file_ext_name    varchar(10)       null,
    file_size        bigint            null,
    is_link          tinyint default 0 not null,
    author_company   varchar(100)      null,
    author_address   varchar(2000)     null,
    author_email     varchar(50)       null,
    author_phone     varchar(50)       null,
    medium_type_id   bigint  default 0 null,
    resource_type_id bigint  default 0 null,
    deformity_id     bigint  default 0 not null,
    state_id         bigint  default 0 not null,
    domain_id        bigint  default 0 not null,
    school_type_id   bigint  default 0 not null,
    import_source_id bigint  default 0 not null,
    stage_id         bigint  default 0 not null,
    grade_id         bigint  default 0 not null,
    subject_id       bigint  default 0 not null,
    learn_from_id    bigint  default 0 not null,
    view_count       int     default 0 not null,
    download_count   int     default 0 not null,
    favorite_count   int     default 0 not null,
    comment_count    int     default 0 not null,
    star_count       int     default 0 not null,
    star_total       int(20) default 0 not null,
    auditor          varchar(50)       null,
    auditor_opinion  varchar(300)      null,
    auditor_time     datetime          null,
    add_user         bigint            null,
    add_time         datetime          null,
    update_user      bigint            null,
    update_time      datetime          null,
    is_deleted       tinyint default 0 not null
);

