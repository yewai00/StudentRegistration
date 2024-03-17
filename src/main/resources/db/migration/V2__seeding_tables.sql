INSERT INTO `users` (`name`, `password`, `role`)
VALUES ('admin', '$2a$10$.UOubX6ot6SN6A/P3gbCC.2hMkrDRec20kF5PC7tMicaMeGy/XpzS', 'ADMIN'),
('user1', '$2a$10$.UOubX6ot6SN6A/P3gbCC.2hMkrDRec20kF5PC7tMicaMeGy/XpzS', 'USER'),
('user2', '$2a$10$.UOubX6ot6SN6A/P3gbCC.2hMkrDRec20kF5PC7tMicaMeGy/XpzS', 'USER');

INSERT INTO `courses` (`name`, `year`) VALUES
('spring boot','2024'),
('react','2024'),
('angular','2023'),
('aws saa','2024'),
('docker','2024'),
('k8s','2024');