db.createUser(
  {
    user: 'useradmin',
    pwd: 'secret',
    roles: [{ role: 'readWrite', db: 'cleandb' }],
  },
);
db.createCollection('roles');
db.roles.insertMany([
   { name: "ROLE_USER" },
   { name: "ROLE_MODERATOR" },
   { name: "ROLE_ADMIN" },
]);

