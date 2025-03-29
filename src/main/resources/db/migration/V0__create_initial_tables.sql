/*
 For now, I'm not thinking about a system of friends or followers. That's why the users table is relatively simple.
*/
CREATE TABLE users
(
    id              SERIAL PRIMARY KEY,
    username        VARCHAR(50)  NOT NULL UNIQUE,                                    -- This username will be the name displayed in his profile
    email           VARCHAR(100) NOT NULL UNIQUE,                                    -- This email will be used to authenticate the user
    password_hash   VARCHAR(255) NOT NULL,                                           -- This will be the hash of the password
    profile_picture VARCHAR(255),                                                    -- This will be the URL of the user's profile picture. It will be saved in a s3 bucket later on
    created_at      TIMESTAMP DEFAULT CURRENT_TIMESTAMP,                             -- This will be the date when the user registered
    updated_at      TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP, -- This will be the date when the user updated his profile
    is_active       BOOLEAN   DEFAULT TRUE                                           -- This will be true if the user is active and false if the user is inactive, so that we can doesn't delete the user from the database
);

/*
 Users table indexes
 */
CREATE INDEX users_id_idx ON users(id);
CREATE INDEX users_username_idx ON users(username);
CREATE INDEX users_email_idx ON users(email);


/*
 This medias table should be enough to store the information of different types of medias from different APIs.
 The idea is to have a unique id for each media, so that we can store the information of the media in a single table.
*/

CREATE TABLE medias
(
    id                    SERIAL PRIMARY KEY,
    unique_api_id         VARCHAR(255) NOT NULL UNIQUE, -- This will be the unique id of the media returned by their API
    type_of_media         INTEGER NOT NULL,             -- This will be the type of media (0 = movie, 1 = series, 2 = song, 3 = book, 4 = game, 5 = anime)
    title                 VARCHAR(255) NOT NULL,        -- This will be the title of the media
    description           TEXT,                         -- This will be the description of the media
    main_image_url        VARCHAR(255),                 -- This will be the URL of the main image of the media
    alternative_image_url VARCHAR(255),                 -- This will be the URL of the alternative image of the media. Could be like a cover
    genres                VARCHAR(255),                 -- This will be the genres of the media. Could be like "Action, Adventure, Comedy"
    release_date          TIMESTAMP                     -- This will be the release date of the media
);

/*
 Medias table indexes
*/
CREATE INDEX medias_id_idx ON medias(id);
CREATE INDEX medias_type_of_media_idx ON medias(type_of_media);
CREATE INDEX medias_title_idx ON medias(title);
CREATE INDEX medias_description_idx ON medias(description);
CREATE INDEX medias_genres_idx ON medias(genres);
CREATE INDEX medias_release_date_idx ON medias(release_date);

/*
    The main idea of this table is to save what medias a user has recommended for another media.
    I'm thinking that, given a user profile, I can show the medias that he has recommended for a specific media.
    Also, this table is enough to count how many recommendations a media has received.
*/
CREATE TABLE users_recommendations
(
    id                SERIAL PRIMARY KEY,
    user_id           INTEGER REFERENCES users(id) ON DELETE SET NULL, -- This will be the id of the user who made the recommendation. On delete, set null because I don't want to lose how many recomendations were made
    origin_media_id   INTEGER NOT NULL REFERENCES medias(id) ON DELETE CASCADE, -- This will be the id of the media that had the recommendation
    target_media_id   INTEGER NOT NULL REFERENCES medias(id) ON DELETE CASCADE, -- This will be the id of the media that was recommended
    created_at        TIMESTAMP DEFAULT CURRENT_TIMESTAMP,                      -- This will be the date when the recommendation was made
    updated_at        TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP -- This will be the date when the recommendation was updated,
);

/*
 Users recommendations table indexes
*/
CREATE INDEX users_recommendations_id_idx ON users_recommendations(id);
CREATE INDEX users_recommendations_user_id_idx ON users_recommendations(user_id);
CREATE INDEX users_recommendations_origin_media_id_idx ON users_recommendations(origin_media_id);
CREATE INDEX users_recommendations_target_media_id_idx ON users_recommendations(target_media_id);

/*
    Whilst the users_recommendations table is enough to count how many recommendations a media has received,
    I think it is not enough because I can't count the number of upvotes a media has received.

    This table will be populated only once with a combination of two media ids when the user makes a recommendation.
*/
CREATE TABLE media_recommendations_stats
(
    id               SERIAL PRIMARY KEY,
    origin_media_id  INTEGER NOT NULL REFERENCES medias(id) ON DELETE CASCADE, -- This will be the id of the media that had the recommendation
    target_media_id  INTEGER NOT NULL REFERENCES medias(id) ON DELETE CASCADE, -- This will be the id of the media that was recommended
    upvotes          INTEGER DEFAULT 0                                         -- This will be the number of upvotes of the recommendation
);

/*
    Media recommendations stats table indexes
*/
CREATE INDEX media_recommendations_stats_id_idx ON media_recommendations_stats(id);
CREATE INDEX media_upvotes_idx ON media_recommendations_stats(upvotes);

CREATE TABLE media_comments
(
    id           SERIAL PRIMARY KEY,
    user_id      INTEGER NOT NULL REFERENCES users(id) ON DELETE SET NULL, -- This will be the id of the user who made the comment. On delete, set null because I don't want to lose how many comments were made
    media_id     INTEGER NOT NULL REFERENCES medias(id) ON DELETE CASCADE, -- This will be the id of the media that had the comment
    content      TEXT NOT NULL,                                            -- This will be the content of the comment. For now, it will be a text
    created_at   TIMESTAMP DEFAULT CURRENT_TIMESTAMP,                      -- This will be the date when the comment was made
    updated_at   TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP  -- This will be the date when the comment was updated
);

/*
    Media comments table indexes
*/
CREATE INDEX media_comments_id_idx ON media_comments(id);
CREATE INDEX media_comments_user_id_idx ON media_comments(user_id);
CREATE INDEX media_comments_media_id_idx ON media_comments(media_id);
CREATE INDEX media_comments_created_at_idx ON media_comments(created_at);