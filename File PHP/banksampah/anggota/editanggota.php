<?php
$link = mysql_connect('localhost', 'root', '') or die('Cannot connect to the DB');
mysql_select_db('banksampah', $link) or die('Cannot select the DB');

$query = "UPDATE anggota SET nik='$_POST[nik]',nama='$_POST[nama]',username='$_POST[username]',password='$_POST[password]',notlp='$_POST[notlp]',alamat='$_POST[alamat]' WHERE id='$_POST[id]'";
$result = mysql_query($query, $link) or die('Errorquery:  '.$query);
?>
