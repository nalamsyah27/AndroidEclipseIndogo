<?php
$link = mysql_connect('localhost', 'root', '') or die('Cannot connect to the DB');
mysql_select_db('banksampah', $link) or die('Cannot select the DB');

$query = "INSERT INTO anggota(nik,nama,username,password,notlp,alamat) VALUES('$_POST[nik]','$_POST[nama]','$_POST[username]','$_POST[password]','$_POST[notlp]','$_POST[alamat]')";
$result = mysql_query($query, $link) or die('Errorquery:  '.$query);
?>
