TRUNCATE TABLE users RESTART IDENTITY CASCADE;
TRUNCATE TABLE task RESTART IDENTITY CASCADE;
INSERT INTO users (full_name, department) VALUES
    ('Raman Shadryn', 1),
    ('Katsiaryna Shadryna' ,1),
    ('Aliaksandr Shadryn', 2),
    ('Ala Shadryna', 2);
INSERT INTO task (completed, description, name, task_status, user_user_id) VALUES
    (FALSE, 'Make a code review','Review', 0, 1),
    (FALSE, 'Find a problem in the code','Debug',  1, 3),
    (TRUE, 'Inform about current work status','Daily meeting', 2, 2);