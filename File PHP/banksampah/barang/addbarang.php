<?php
$link = mysql_connect('localhost', 'root', '') or die('Cannot connect to the DB');
mysql_select_db('banksampah', $link) or die('Cannot select the DB');

$query = "INSERT INTO jenisbarang(kodebarang,jenisbarang,harga) VALUES('$_POST[kodebarang]','$_POST[jenisbarang]','$_POST[harga]')";
$result = mysql_query($query, $link) or die('Errorquery:  '.$query);
?>
