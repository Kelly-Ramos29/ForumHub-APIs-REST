create table if not exists users(
  id bigserial primary key,
  email varchar(255) not null unique,
  password varchar(255) not null,
  role varchar(50) not null
);

create table if not exists courses(
  id bigserial primary key,
  name varchar(255) not null unique,
  category varchar(255)
);

create table if not exists topics(
  id bigserial primary key,
  title varchar(255) not null,
  message text not null,
  author_id bigint not null references users(id),
  course_id bigint not null references courses(id),
  created_at timestamp not null,
  status varchar(50) not null
);

create table if not exists replies(
  id bigserial primary key,
  topic_id bigint not null references topics(id) on delete cascade,
  author_id bigint not null references users(id),
  message text not null,
  created_at timestamp not null
);
