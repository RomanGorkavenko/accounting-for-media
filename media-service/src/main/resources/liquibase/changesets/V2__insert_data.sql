INSERT INTO categories (title)
VALUES ('Баннер'), ('Указатель'), ('Экран'), ('реклама'), ('Навигационный щит');

INSERT INTO objects (title)
VALUES ('Вход Отель 1'), ('Вход Отель 2'), ('Ресепшен Отель 1');

INSERT INTO status (title, color)
VALUES ('Установлен', 'Green'), ('Не установлен', 'Red'), ('Неизвестен', 'Gray');

INSERT INTO media (title, number, object_id, user_id, start_date, service_life, end_date, category_id, status_id)
VALUES ('Навигация бассейн', 192130, 3, 1, '2024-01-01', 12, '2024-12-31', 2, 1),
       ('Навигация лифт', 222130, 2, 1, '2024-01-01', 24, '2025-12-31', 5, 2),
       ('Рекламный баннер', 342130, 1, 2, '2024-01-01', 36, '2026-12-31', 5, 3)