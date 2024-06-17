INSERT INTO account (id, login, hashed_password, status, online, role) VALUES
(RANDOM_UUID(), 'creator', 'random_password', 'Dont even think about staring at me.', true, 'ROLE_CREATOR');

INSERT INTO article (id, title, body, slug, created_by_id, created_at, updated_at)
SELECT
    RANDOM_UUID(),
    'Добро пожаловать на сервер FunnyCraft!',
    'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec euismod ullamcorper leo at hendrerit. Donec nec tortor gravida, aliquet nisl venenatis, sagittis magna. Donec sit amet efficitur libero. Sed faucibus ex faucibus quam vulputate, eget bibendum dolor molestie. Morbi luctus, eros in dapibus venenatis, lorem ipsum aliquet enim, a euismod ex arcu et ligula. Integer ut quam at velit accumsan faucibus a id lacus. In urna ex, facilisis finibus dictum id, fermentum eget dui. Pellentesque vulputate dapibus ullamcorper. Cras tristique dictum tincidunt. Nam nec tellus condimentum, sodales nisl ac, placerat dui. Morbi congue lectus posuere, luctus est id, vulputate ex. Duis vel sodales mi. Praesent bibendum sit amet enim id commodo. Sed libero lorem, rhoncus vel semper sed, egestas eu enim. Nulla euismod lacus eu lacus malesuada efficitur.',
    'welcome-to-the-funnycraft-server',
    id,
    '2022-12-05 10:37:22',
    '2022-12-05 10:37:22',
FROM account WHERE login='creator';

INSERT INTO article (id, title, body, slug, created_by_id, created_at, updated_at)
SELECT
    RANDOM_UUID(),
    'Внимание, происходят работы на сервере!',
    'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec euismod ullamcorper leo at hendrerit. Donec nec tortor gravida, aliquet nisl venenatis, sagittis magna. Donec sit amet efficitur libero. Sed faucibus ex faucibus quam vulputate, eget bibendum dolor molestie. Morbi luctus, eros in dapibus venenatis, lorem ipsum aliquet enim, a euismod ex arcu et ligula. Integer ut quam at velit accumsan faucibus a id lacus. In urna ex, facilisis finibus dictum id, fermentum eget dui. Pellentesque vulputate dapibus ullamcorper. Cras tristique dictum tincidunt. Nam nec tellus condimentum, sodales nisl ac, placerat dui. Morbi congue lectus posuere, luctus est id, vulputate ex. Duis vel sodales mi. Praesent bibendum sit amet enim id commodo. Sed libero lorem, rhoncus vel semper sed, egestas eu enim. Nulla euismod lacus eu lacus malesuada efficitur.',
    'maintenance-work-on-server',
    id,
    '2023-12-05 12:37:22',
    '2023-12-05 12:37:22',
FROM account WHERE login='creator';

INSERT INTO tag (id, name) VALUES
(RANDOM_UUID(), 'Maintenance'),
(RANDOM_UUID(), 'Update'),
(RANDOM_UUID(), 'Event'),
(RANDOM_UUID(), 'Meme');

INSERT INTO commentary (id, body, article_id, created_by_id, created_at, updated_at)
SELECT
    RANDOM_UUID(),
    'Ашалеть можно, узнали согласны?',
    article.id,
    account.id,
    NOW(),
    NOW(),
FROM account, article WHERE account.login='creator' AND article.slug='maintenance-work-on-server'
