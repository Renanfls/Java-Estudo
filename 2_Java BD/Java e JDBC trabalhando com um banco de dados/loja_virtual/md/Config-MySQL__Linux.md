**Instalar MySQL Server**

$ `sudo apt install mysql-server`

--------------------------------------------------------------------------------
**Ajuste Autenticação e Privilégios**

$ `sudo mysql`

mysql> `SELECT user,authentication_string,plugin,host FROM mysql.user;`

mysql> `ALTER USER 'root'@'localhost' IDENTIFIED WITH caching_sha2_password BY 'F7l9s0##';`

mysql> `exit`

$ `sudo mysql -u root -p`

mysql> `SELECT user,authentication_string,plugin,host FROM mysql.user;`

--------------------------------------------------------------------------------
**Instalar MySQL Workbench**

$ `sudo apt-get update`

$ `sudo snap install mysql-workbench-community`


