DROP TABLE IF EXISTS "public"."messages";
CREATE TABLE "public"."messages" (
  "id" varchar(100) NOT NULL,
  "topic" varchar(50) NULL,
  "msg" varchar(500) NULL,
  "sendTime" timestamp(6)
);
