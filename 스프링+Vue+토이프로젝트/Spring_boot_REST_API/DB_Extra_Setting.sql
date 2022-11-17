-- EER다이어그램으로 테이블을 생성후 UTF-8설정을 위해서 아래 쿼리문을 추가로 실행해 주세요.

ALTER DATABASE mydb DEFAULT CHARACTER SET utf8mb4;

ALTER TABLE `video` CONVERT TO CHARACTER SET utf8mb4;
ALTER TABLE `user` CONVERT TO CHARACTER SET utf8mb4;
ALTER TABLE `review` CONVERT TO CHARACTER SET utf8mb4;
ALTER TABLE `like` CONVERT TO CHARACTER SET utf8mb4;
ALTER TABLE `follow` CONVERT TO CHARACTER SET utf8mb4;
ALTER TABLE `comment` CONVERT TO CHARACTER SET utf8mb4;