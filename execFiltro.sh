#!/bin/sh
jq -c {text} <DIRETÓRIO DA BASE JSON> >> <DIRETÓRIO PARA SAIDO DO JQ>/filtrado.txt
echo 'filtrado.txt gerado!'

javac Filtrador.java
java Filtrador filtrado.txt

rm <DIRETÓRIO DO ARQUIVO filtrado.txt> filtrado.txt

echo 'filtrado2.txt gerado!'
