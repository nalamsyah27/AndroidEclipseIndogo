<?php
$link = mysql_connect('localhost', 'root', '') or die('Cannot connect to the DB');
mysql_select_db('banksampah', $link) or die('Cannot select the DB');

$query = "UPDATE jenisbarang SET kodebarang='$_POST[kodebarang]',jenisbarang='$_POST[jenisbarang]',harga='$_POST[harga]' WHERE id='$_POST[id]'";
$result = mysql_query($query, $link) or die('Errorquery:  '.$query);
?>
