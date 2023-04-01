`apt-get update` - atualiza pacotes

`apt-get install lamp-server^` - Instala pacote lamp

**Terminando a instalação já temos um ambiem `localhost`**

- Só colocar `localhost` no navegador

#
## **Dando permissão para pasta www**

Com essa permissão podemos criar, abrir e deletar arquivos

`chmod -R 777 /var/www/` - Da permissão total(Leitura-Escrita-Execução) para pasta www

`ls -la` - Verifica as permissões que a pasta tem
#
## **Teste**
- `mkdir teste` - Cria uma pasta

- `nano teste.php` - Cria um arquivo php

```php
<?php
echo "Olá, Mundo!";
?>
```
#
```php
<?php
phpinfo();
?>
```
Retorna a versão do php instalado e outras configs como o MySQL
#

- `CTRL + X` - Para sair
- `S` - para salva
- `Enter` - para confirmar o arquivo que deseja salvar
#
