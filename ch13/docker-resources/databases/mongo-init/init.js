db = db.getSiblingDB('authentication_db');

db.createCollection('authentication');

db.getCollection('authentication').insertOne(
    {
        "accountNonExpired": true,
        "accountNonLocked": true,
        "credentialsNonExpired": true,
        "isEnabled": true,
        "password": "$2a$10$08PEO.k5bWz4GqKmz5yvweQo5MdE1fv.AQeki1MveWxfghbfm8MDS",
        "username": "user@wxauction.com"
    }
);

db.getCollection('authentication').insertOne(
    {
        "accountNonExpired": true,
        "accountNonLocked": true,
        "credentialsNonExpired": true,
        "isEnabled": true,
        "password": "$2a$10$08PEO.k5bWz4GqKmz5yvweQo5MdE1fv.AQeki1MveWxfghbfm8MDS",
        "username": "admin@wxauction.com"
    }
);